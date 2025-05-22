package com.gsd.daw.prog.lenguaje;

import com.gsd.daw.prog.figuras.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Traductor {

	private String rutaEntrada;
	private String rutaSalida;
	private Contenedor figuras;
	
	public Traductor(String rutaEntrada, String rutaSalida) {
		this.rutaEntrada = rutaEntrada;
		this.rutaSalida = rutaSalida;
	}
	
	private boolean validar() {
	    File f = new File(this.rutaEntrada);
	    if (!f.exists() || !f.isFile()) {
	        System.out.println("ERROR: No se puede leer el fichero de entrada [" + this.rutaEntrada + "]");
	        return false;
	    }

	    String contenidoFichero = leerEntrada(f).trim();
	    if (contenidoFichero.isEmpty()) {
	        System.out.println("ERROR: El fichero de entrada está vacío");
	        return false;
	    }

	    String[] lineas = contenidoFichero.split("---");
	    if (!validarContenedor(lineas[0])) return false;

	    Map<String, Stroke> strokes = new HashMap<>();
	    for (int i = 1; i < lineas.length; i++) {
	        if (lineas[i].trim().isEmpty()) {
	            System.out.println("ERROR: Línea [" + (i + 1) + "] vacía");
	            return false;
	        }
	        if (!procesarLineaFigura(lineas[i], i + 1, strokes)) return false;
	    }

	    return true;
	}

	
	private boolean procesarLineaFigura(String linea, int numeroLinea, Map<String, Stroke> strokes) {
	    String[] partes = linea.split(" ");
	    if (partes.length == 0) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] vacía");
	        return false;
	    }

	    String tipo = partes[0];

	    if (tipo.equals("ST")) return procesarStroke(partes, numeroLinea, strokes);

	    Stroke stroke = null;
	    if (!(tipo.equals("LP") || tipo.equals("PO")) && partes.length > 1) {
	        String posibleStroke = partes[partes.length - 1];
	        if (strokes.containsKey(posibleStroke)) {
	            stroke = strokes.get(posibleStroke);
	        } else if (!esNumero(posibleStroke) && !esCoordenadaValida(posibleStroke)) {
	            System.out.println("ERROR: Línea [" + numeroLinea + "] usa un Stroke no definido: [" + posibleStroke + "]");
	            return false;
	        }
	    }

	    switch (tipo) {
	        case "CI": return procesarCirculo(partes, numeroLinea, stroke);
	        case "RE": return procesarRectangulo(partes, numeroLinea, stroke);
	        case "LI": return procesarLinea(partes, numeroLinea, stroke);
	        case "EL": return procesarElipse(partes, numeroLinea, stroke);
	        case "LP":
	        case "PO": return procesarPoligono(partes, numeroLinea, tipo, strokes);
	        default:
	            System.out.println("ERROR Línea [" + numeroLinea + "] tipo de figura desconocido [" + tipo + "]");
	            return false;
	    }
	}

	
	private boolean procesarStroke(String[] partes, int numeroLinea, Map<String, Stroke> strokes) {
	    if (partes.length != 6) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] formato incorrecto para Stroke");
	        return false;
	    }

	    String nombre = partes[1];
	    for (int i = 2; i <= 5; i++) {
	        if (!esNumero(partes[i])) {
	            System.out.println("ERROR: Línea [" + numeroLinea + "] los valores deben ser números");
	            return false;
	        }
	    }

	    int r = Integer.parseInt(partes[2]);
	    int g = Integer.parseInt(partes[3]);
	    int b = Integer.parseInt(partes[4]);
	    int ancho = Integer.parseInt(partes[5]);
	    Stroke stroke = new Stroke(new Color((byte) r, (byte) g, (byte) b), ancho);
	    strokes.put(nombre, stroke);
	    return true;
	}

	

	private boolean procesarCirculo(String[] partes, int numeroLinea, Stroke stroke) {
	    if ((stroke == null && partes.length != 3) || (stroke != null && partes.length != 4)) {
	        System.out.println("ERROR Línea [" + numeroLinea + "] formato incorrecto para Circulo");
	        return false;
	    }

	    String[] coords = partes[1].split(",");
	    if (coords.length != 2 || !esNumero(coords[0]) || !esNumero(coords[1])) {
	        System.out.println("ERROR Línea [" + numeroLinea + "] coordenadas del punto central incorrectas");
	        return false;
	    }

	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int radio;
	    if (!esNumero(partes[2])) {
	        System.out.println("ERROR Línea [" + numeroLinea + "] radio incorrecto");
	        return false;
	    }
	    radio = Integer.parseInt(partes[2]);

	    Circulo c = new Circulo(new Punto(x, y), radio);
	    if(stroke!=null)c.setStroke(stroke);
	    figuras.addCirculo(c);
	    return true;
	}

	private boolean procesarRectangulo(String[] partes, int numeroLinea, Stroke stroke) {
		if ((stroke == null && partes.length != 4) || (stroke != null && partes.length != 5)) {
	        System.out.println("ERROR eLínea [" + numeroLinea + "] formato incorrecto para Rectángulo");
	        return false;
	    }

	    String[] coords = partes[1].split(",");
	    if (coords.length != 2 || !esNumero(coords[0]) || !esNumero(coords[1])) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] coordenadas del punto incorrectas");
	        return false;
	    }

	    int x = Integer.parseInt(coords[0]);
	    int y = Integer.parseInt(coords[1]);
	    int ancho, alto;

	    if (!esNumero(partes[2]) || !esNumero(partes[3])) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] ancho y alto incorrectos");
	        return false;
	    }

	    ancho = Integer.parseInt(partes[2]);
	    alto = Integer.parseInt(partes[3]);

	    Rectangulo r = new Rectangulo(new Punto(x, y), ancho, alto);
	    if(stroke!=null)r.setStroke(stroke);
	    figuras.addRectangulo(r);
	    return true;
	}

	private boolean procesarLinea(String[] partes, int numeroLinea, Stroke stroke) {
		if ((stroke == null && partes.length != 3) || (stroke != null && partes.length != 4)) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] formato incorrecto para Línea");
	        return false;
	    }

	    String[] p1 = partes[1].split(",");
	    String[] p2 = partes[2].split(",");
	    if (p1.length != 2 || p2.length != 2 || !esNumero(p1[0]) || !esNumero(p1[1]) || !esNumero(p2[0]) || !esNumero(p2[1])) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] coordenadas de los puntos incorrectas");
	        return false;
	    }

	    Punto a = new Punto(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]));
	    Punto b = new Punto(Integer.parseInt(p2[0]), Integer.parseInt(p2[1]));
	    Linea l = new Linea(a, b);
	    if(stroke!=null)l.setStroke(stroke);
	    figuras.addLinea(l);
	    return true;
	}

	private boolean procesarElipse(String[] partes, int numeroLinea, Stroke stroke) {
	    if ((stroke == null && partes.length != 4) || (stroke != null && partes.length != 5)) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] formato incorrecto para Elipse");
	        return false;
	    }

	    String[] centro = partes[1].split(",");
	    if (centro.length != 2 || !esNumero(centro[0]) || !esNumero(centro[1])) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] coordenadas del centro incorrectas");
	        return false;
	    }

	    int x = Integer.parseInt(centro[0]);
	    int y = Integer.parseInt(centro[1]);
	    int r1, r2;

	    if (!esNumero(partes[2]) || !esNumero(partes[3])) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] lo radios deben ser números");
	        return false;
	    }

	    r1 = Integer.parseInt(partes[2]);
	    r2 = Integer.parseInt(partes[3]);

	    Elipse e = new Elipse(new Punto(x, y), r1, r2);
	    if(stroke!=null)e.setStroke(stroke);
	    figuras.addElipse(e);
	    return true;
	}

	private boolean esCoordenadaValida(String s) {
	    String[] xy = s.split(",");
	    return xy.length == 2 && esNumero(xy[0]) && esNumero(xy[1]);
	}

	
	private boolean procesarPoligono(String[] partes, int numeroLinea, String tipo, Map<String, Stroke> strokes) {
	    if (partes.length < 3) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] formato incorrecto para Polígono");
	        return false;
	    }

	    String posibleUltimo = partes[partes.length - 1];
	    Stroke stroke = null;
	    int totalPuntos = partes.length - 1; // Por defecto, todos son puntos

	    if (strokes.containsKey(posibleUltimo)) {
	        stroke = strokes.get(posibleUltimo);
	        totalPuntos--; // El último no es punto, es un nombre de stroke
	    }

	    if (totalPuntos < 2) {
	        System.out.println("ERROR: Línea [" + numeroLinea + "] no hay suficientes puntos");
	        return false;
	    }

	    Punto[] puntos = new Punto[totalPuntos];
	    for (int i = 0; i < totalPuntos; i++) {
	        String[] xy = partes[i + 1].split(",");
	        if (xy.length != 2 || !esNumero(xy[0]) || !esNumero(xy[1])) {
	            System.out.println("ERROR: Línea [" + numeroLinea + "] coordenadas incorrectas en el punto [" + partes[i + 1] + "]");
	            return false;
	        }
	        puntos[i] = new Punto(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
	    }

	    if (tipo.equals("LP")) {
	        LineaPoligonal lp = new LineaPoligonal(puntos);
	        if (stroke != null) lp.setStroke(stroke);
	        figuras.addLineaPoligonal(lp);
	    } else {
	        Poligono po = new Poligono(puntos);
	        if (stroke != null) po.setStroke(stroke);
	        figuras.addPoligono(po);
	    }

	    return true;
	}


	
	private boolean validarContenedor(String linea) {
	    String[] partes = linea.split(" ");
	    
	    if (partes.length != 3) {
	        System.out.println("ERROR: El contenedor debe tener 3 elementos: CO <ancho> <alto>");
	        return false;
	    }

	    if (!partes[0].equals("CO")) {
	        System.out.println("ERROR: La primera línea debe declarar el contenedor con 'CO'");
	        return false;
	    }

	    if (!esNumero(partes[1]) || !esNumero(partes[2])) {
	        System.out.println("ERROR: El ancho y el alto del contenedor deben ser números");
	        return false;
	    }

	    int ancho = Integer.parseInt(partes[1]);
	    int alto = Integer.parseInt(partes[2]);
	    this.figuras = new Contenedor(ancho, alto);

	    return true;
	}

	
	private boolean esNumero(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	private String leerEntrada(File f) {
		String s = "";
		Scanner input = null;
		try {
			input = new Scanner(f);
		} catch (FileNotFoundException e) {

		}
		while (input.hasNext()) {
			String line = input.nextLine();
			s+=line+"---";
		}
		input.close();
		
		return s;
	}
	
	private void escribirSalida() {
		File f = new File(this.rutaSalida);
		
		if(!f.isFile()) {
			System.out.println("ERROR: La ruta del fichero de salida no corresponde a ningun fichero existente."); 
			return;
		}
		if(!f.canWrite()) {
			System.out.println("ERROR: No se dispone de privilegios para escribir en el fichero de salida");
			return;
		}
		
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.rutaSalida));
            writer.write(this.figuras.toSvg());
            writer.flush();
        } catch (Exception e) {
            System.err.println("ERROR: No se pudo escribir en el fichero de salida");
        }
	}
	
	public void traducir() {
		if(validar()) {
			escribirSalida();
		}
	}
}
