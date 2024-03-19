package com.gsd.daw.prog;

import java.sql.Connection;

public class ApacheAnalizer {
	public static void main( String[] args ) {
	    // Comprobación de argumentos
    	if(args.length != 4) {
    		System.out.println("ERROR: La entrada no contiene el formato deseado.");
    		return;
    	}
		BaseDeDatos bbdd = new BaseDeDatos(args[0], args[1]);

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

	    // Carga de objetos del modelo de BBDD a estructura plana
	    // Esto sin colecciones será un String[][] array de tamaño máximo 10000
	    // elementos
	    // Crea una clase aparte cuya responsabilidad sea recibir una conexion de BBDD 
	    // y devolver una estructura String[10000][6] con los datos en columnas
		LectorDeBaseDeDatos lector = new LectorDeBaseDeDatos(conn);
		String[][] datosTabla = null;
		try {
			 datosTabla = lector.extraerTabla();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	    System.out.println( "INFO: leidas [" + datosTabla.length + "] lineas de BBDD." );

	    // Conversion de estructuras planas a objetos del modelo
	    // Reusa la clase que ya creaste para convertir la estructura "anónima"
	    // en un array de objetos del modelo
	    Log[] logs = new Log[datosTabla.length];
	    
	    int j = 0;
	    for (int i = 0; i < datosTabla.length; i++) {
			if(datosTabla[i][0] == null) {
				break;
			}
			
			logs[j]= new Log(datosTabla[i]);
			j++;
		}
	    System.out.println( "INFO: creados [" + logs.length + "] objetos del modelo." );

	    // Crea una clase separada para realizar cálculos y analisis sobre
	    // el array de objetos del modelo
	    // Puede ser una librería de funciones static (sin datos propios)
	    // que reciban un array de objetos del modelo y realicen cálculos sobre ellos
	    // Recuerda dividir responsabilidades entre calcular e imprimir.
	    // Los cálculos que se piden están especificados en el enunciado
	    
	    System.out.println(Calculos.contarStatusCode(logs));
	    System.out.println(Calculos.calcularIpsRepetidasMasDeDiezVeces(logs));
	}
}
