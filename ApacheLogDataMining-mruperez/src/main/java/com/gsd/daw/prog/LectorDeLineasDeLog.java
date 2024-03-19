
package com.gsd.daw.prog;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class LectorDeLineasDeLog {
	private String rutaFichero;
	private String[] lineas;
	private final static int TAMANIO_MAXIMO=10000;
	
	public LectorDeLineasDeLog(String rutaFichero) throws Exception {
		this.rutaFichero = rutaFichero;
		try {
			this.lineas = extraerLineasDeLogs();
		}
		catch(Exception e) {
			throw(e);
		}
		
	}
	
	public String[][] obtenerColumnasDeLogs(){
		String aux[][] = new String[lineas.length][6];
		int count = 0; 
		
		for(int i = 0; i < lineas.length; i++) {
			if(lineas[i] != null) {
				aux[count] = splitearLineaLog(lineas[i]);
				count++;
			}
		}		
		if(lineas.length == count) {return aux;}
		
		String logs[][] = new String[count + 1][6];
		for (int i = (aux.length-1); i >= 0; i--) {
			if( (!(aux[i] == null) || (aux[i][0] == null)) ) {
				for(int j = 0; j < aux[i].length; j++) {
					logs[count][j]=aux[i][j];
					count--;
				}
			}
		}
		return logs;
	}
	
	private String[] extraerLineasDeLogs() throws FileNotFoundException, Exception { 
			File fichero = new File(this.rutaFichero);
			
			if(!fichero.isFile()) {
				throw new FileNotFoundException("ERROR: La ruta del fichero no corresponde "
						+ "a ningun fichero existente.");
			}
			if(!fichero.canRead()) {
				throw new FileNotFoundException("ERROR: No se dispone de privilegios para"
						+ " leer en el fichero");
			}
			if(!fichero.canWrite()) {
				throw new FileNotFoundException("ERROR: No se dispone de privilegios para"
						+ " escribir en el fichero");
			}
			
			int cuentaLineas = 0;
			Scanner reader = null;
			try {
				reader = new Scanner(fichero);
			}
			catch(FileNotFoundException e) {
				throw new FileNotFoundException("ERROR: Fichero no encontrado.");
			}
			
			String s = "";
			while(reader.hasNext()) {
				s += reader.nextLine();
				s += "--partir-por-aqui--";
				cuentaLineas++;
				if(!(cuentaLineas < TAMANIO_MAXIMO)) {
					throw new Exception("ERROR: El fichero supera las 10000 lineas.");
				}
			}
			
			String[] infoFichero = new String[cuentaLineas];
			infoFichero = s.split("--partir-por-aqui--");
			
			reader.close();
			return infoFichero;
	}
	
    private static String[] splitearLineaLog( String line ) {
        String  LOG_ENTRY_PATTERN = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"$";
        Pattern pattern = Pattern.compile( LOG_ENTRY_PATTERN );     
        
        String[] res = new String[6];
        Matcher matcher = pattern.matcher( line );
        if ( matcher.matches() ) {
            res[0] = matcher.group( 1 );
            res[1] = matcher.group( 4 );
            res[2] = matcher.group( 5 );
            res[3] = matcher.group( 6 );
            res[4] = matcher.group( 7 );
            res[5] = matcher.group( 9 );
        }
        return res;
    }
	
    public int length() {
    	if(this.lineas==null) {
    		return 0;
    	}
    	return lineas.length;
    }
}
