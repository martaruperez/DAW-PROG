package com.gsd.daw.prog.lenguaje;

import com.gsd.daw.prog.figuras.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        try {
            Scanner s = new Scanner(f); 
            s.close();                  
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Ruta del fichero de Figuras no válida [" + this.rutaEntrada + "]\n" + e.getMessage());
            return false;
        }
		
        // El fichero debe tener un solo contenedor, el contenedor estará en la 1a línea, 
        // todas las figuras pertenecen al contenedor
        String[] contenido = leerEntrada(f).split("---");
        String[] primeraLinea = contenido[0].split(" ");
        if(primeraLinea.length!=3 || primeraLinea[0].charAt(0)!='C' || primeraLinea[0].charAt(1)!='O') {
        	System.out.println("ERROR: Formato del fichero Figuras incorrecto-->El contenedor debe estar en la primera línea");
        	return false;
        }
        if(!esNumero(primeraLinea[1]) || !esNumero(primeraLinea[2])) {
        	System.out.println("ERROR: Formato del fichero Figuras incorrecto-->No ha introducido una altura y un ancho correctos para el contenedor");
        	return false;
        }
        this.figuras = new Contenedor(Integer.parseInt(primeraLinea[1]), Integer.parseInt(primeraLinea[2]));
        
        // Una figura por línea
        
        
        // Si una figura usa un Stroke, este debe estar definido antes de la figura
        
        // Todos los números serán números enteros
        
        // Los Puntos se expresan como coordenadaX,coordenadaY
        
		return false;
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
		Scanner inputScannerFromFile = null;
		try {
			inputScannerFromFile = new Scanner(f);
		} catch (FileNotFoundException e) {

		}
		while (inputScannerFromFile.hasNext()) {
			String line = inputScannerFromFile.nextLine();
			s+=line+"---";
		}
		inputScannerFromFile.close();
		
		return s;
	}
	
	private void escribirSalida() {
		
	}
	
	public void traducir() {
		if(validar()) {
			escribirSalida();
		}
	}
}
