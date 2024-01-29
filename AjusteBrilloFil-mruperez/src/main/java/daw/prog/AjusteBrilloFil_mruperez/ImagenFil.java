package daw.prog.AjusteBrilloFil_mruperez;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class ImagenFil {
	
	private File ficheroImagen;
	private String ancho;
	private String altura;
	private String[] infoFichero = null;
	private int cantidadDePixeles;
	private int[][] pixeles;
	
	public ImagenFil(String rutaFichero) throws FileNotFoundException, Exception {
		this.ficheroImagen = new File(rutaFichero);
		
		if(!ficheroImagen.isFile()) {
			throw new FileNotFoundException("La ruta del fichero no corresponde "
					+ "a ningun fichero existente.");
		}
		if(!ficheroImagen.canRead()) {
			throw new FileNotFoundException("No se dispone de privilegios para"
					+ " leer en el fichero");
		}
		if(!ficheroImagen.canWrite()) {
			throw new FileNotFoundException("No se dispone de privilegios para"
					+ " escribir en el fichero");
		}
		
		try {
			this.infoFichero = guardarInfoDelFichero();
		}
		catch(Exception e) {
			throw e;
		}
		
		this.ancho = this.infoFichero[1];
		this.altura = this.infoFichero[2];
		if(!Utilidades.esNumero(this.altura) || !Utilidades.esNumero(this.ancho)) {
			throw new Exception("ERROR: La altura y el ancho deben ser numeros.");
		}
		
		this.cantidadDePixeles = (Integer.parseInt(altura)*Integer.parseInt(ancho));
		if(!formatoCorrecto()) {
			throw new Exception("ERROR: Ha introuducido un fichero con formato incorrecto");
		}
		this.pixeles = guardarPixeles();
	}
	
	private String[] guardarInfoDelFichero() throws FileNotFoundException, Exception{ 
		int cuentaLineas = 0;
		Scanner reader = null;
		
		try {
			reader = new Scanner(this.ficheroImagen);
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("ERROR: Fichero no encontrado.");
		}
		
		String s = "";
		while(reader.hasNext()) {
			s += reader.nextLine();
			s += "--partir-por-aqui--";
			cuentaLineas++;
		}
		
		String[] infoFichero = new String[cuentaLineas];
		infoFichero = s.split("--partir-por-aqui--");
		
		reader.close();
		return infoFichero;
	}
	
	private boolean formatoCorrecto() {
		if(!Utilidades.esNumero(this.altura)) {
			return false;
		}
		if(!Utilidades.esNumero(this.ancho)) {
			return false;
		}
		if(this.cantidadDePixeles != this.infoFichero.length - 3) {
			return false;
		}
		
		return true;
	}
	
	private int[][] guardarPixeles() throws IllegalArgumentException{
		int[][] pixeles = new int [this.cantidadDePixeles][3];
		String [] cadenaPixeles;
		
		for(int i = 0; i < this.cantidadDePixeles; i++) {
			cadenaPixeles = this.infoFichero[i+3].split(",");
			if(cadenaPixeles.length != 3) {
				throw new IllegalArgumentException("ERROR: '"+Arrays.toString(cadenaPixeles)+"'"
				+ " no sigue con el formato que debe tener el fichero.");
			}
			
			for (int j = 0; j < 3;j++) {
				
				if(cadenaPixeles[j].length() == 0 || !Utilidades.esNumero(cadenaPixeles[j])) {
					throw new IllegalArgumentException("ERROR: ["+cadenaPixeles[j]+"]"
					+ " no sigue con el formato que debe tener el fichero.");
				}
				
				if(Integer.parseInt(cadenaPixeles[j]) > 255 || Integer.parseInt(cadenaPixeles[j]) < 0) {
					throw new IllegalArgumentException("ERROR: ["+cadenaPixeles[j]+"]"
					+ " Los pixeles del fichero deben estar comprendidos entre 0 y 255.");
				}
				pixeles[i][j]=Integer.parseInt(cadenaPixeles[j]);
			}
			
		}
		
		return pixeles;
	}
	
	public void ajustarBrillo(String entrada) {
		int ajusteBrillo = Integer.parseInt(entrada);

		for(int i = 0; i < this.pixeles.length; i ++) {
			for(int j = 0; j < 3; j ++) {
				if((this.pixeles[i][j]+ajusteBrillo) >= 0 && (this.pixeles[i][j]+ajusteBrillo) <= 255) {
					this.pixeles[i][j]+=ajusteBrillo;
				}
			}
		}
		imprimirResultado();
	}
	
	private void imprimirResultado() {
		
		System.out.println(this.infoFichero[0]);
		System.out.println(this.infoFichero[1]);
		System.out.println(this.infoFichero[2]);
		
		for(int i = 0; i < this.pixeles.length; i ++) {
			for(int j = 0; j < 3; j ++) {
				System.out.print(this.pixeles[i][j]);
				if(j<2) {System.out.print(",");}
			}
			System.out.println();
		}
		
	}
	
}
