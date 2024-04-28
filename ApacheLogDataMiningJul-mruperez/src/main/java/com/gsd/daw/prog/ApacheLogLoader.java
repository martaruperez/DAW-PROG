package com.gsd.daw.prog;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.logging.Logger;

public class ApacheLogLoader {
	private final static Logger LOGGER = Logger.getLogger( "LOGGER TEST 1" );
	private String varEntorno = System.getenv().get("LOG_LEVEL");
//	if () {
//		
//	}
	public static void main( String[] args ) {
		
        InputStream ficheroDeConfiguracion = ApacheLogLoader.class.getClassLoader().getResourceAsStream( "config/logging-prod.properties" );
        try {
            LogManager.getLogManager().readConfiguration( ficheroDeConfiguracion );
        } catch ( IOException e ) {
            e.printStackTrace();
            return;
        }
		
	    BaseDeDatos bbdd = null;
    	
		// Comprobación de argumentos
	    if(args.length < 5 || args.length > 6 ) {
    		System.out.println("ERROR: Argumentos incorrectos."+
    						"\n"+"FORMATO: [ip] [nombre-BBDD] [username] [password] [fichero] [opcional: SGBD]");
    		return;
    	}
    	
	    // Creacion de la conexión
    	switch(args.length) {
    		case 5:
    			 bbdd = new BaseDeDatos(args[0], args[1]);
    		break;
    		case 6:
    			 bbdd = new BaseDeDatos(args[0], args[1], args[5]);
    		break;
    	}
    	
    	Connection conn=null;
	    try{
	    	conn = bbdd.getConexion(args[2], args[3]);
	    	System.out.println( "INFO: conectado a la BBDD." );
	    }
	    catch(Exception e) {
	    	//System.out.println(e);
	    	System.out.println("ERROR: Conexión a la BBDD fallida, revise sus parámetros.");
	    	return;
	    }
	    // Lectura de datos a estructuras planas
	    // Esto sin colecciones será un String[][] array de tamaño máximo 10000
	    // elementos
	    // Crea una clase aparte cuya responsabilidad sea recibir un nombre de fichero
	    // y devolver una estructura String[10000][6] con los datos en columnas
	    LectorDeLineasDeLog lector = null;
	    try {
    		lector = new LectorDeLineasDeLog(args[4]);
    	}
    	catch(Exception e ) {
    		System.out.println(e.getMessage());
    		return;
    	}
    	
	    System.out.println( "INFO: leidas ["+ lector.length() + "] lineas del fichero." );

	    // Conversion de estructuras planas a objetos del modelo
	    // Crea una clase que modele los datos que tiene una linea de log de Apache
	    // Convierte la estructura "anónima" en un array de objetos del modelo
	    
	    String[][] s = lector.obtenerColumnasDeLogs();
	    Log[] logs = new Log[s.length];
	    
	    int j = 0;
	    for (int i = 0; i < s.length; i++) {
			logs[j]= new Log(s[i]);
			j++;
		}
	    
	    System.out.println( "INFO: creados [" + logs.length + "] objetos del modelo." );

	    // Guardado de los objetos del modelo en BBDD
	    // La clase del modelo debe tener un método save( Connection ) que recibe una
	    // conexion JDBC y hace que los datos del objeto se guarden en BBDD
	    try {
	    	int i = 0;
	    	while (i < logs.length) {
				logs[i].save(conn);
				i++;
			}
	    	conn.close();
	    	System.out.println( "INFO: insertadas [" + i + "] filas en BBDD." );
	    }
	    catch (Exception e) {
			System.out.println("ERROR: Inserción fallida: "+e.getMessage());
			return;
		}
	    
	}
}
