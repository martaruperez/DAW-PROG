package com.gsd.daw.prog;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ApacheLogLoader {
    private static final Logger LOGGER = Logger.getLogger(ApacheLogLoader.class.getName());
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // Cargar la configuración de logging desde un archivo de propiedades
        InputStream configFile = ApacheLogLoader.class.getClassLoader().getResourceAsStream("config/logging-prod.properties");
        try {
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar la configuración de logging: "+e);
            return;
        }

        // Obtener el nivel de log desde la variable de entorno
        String logLevelEnvVar = System.getenv("LOG_LEVEL");
        if (logLevelEnvVar != null) {
            try {
                Level logLevel = Level.parse(logLevelEnvVar);
                LOGGER.setLevel(logLevel);
                LOGGER.log(Level.INFO, "LogLevel forzado a ["+logLevel+"]");
            } catch (IllegalArgumentException e) {
            	LOGGER.log(Level.SEVERE,"Nivel de log inválido: "+logLevelEnvVar);
            }
        }

        // Validación de argumentos
        if (args.length < 5 || args.length > 6) {
        	LOGGER.log(Level.SEVERE,"ERROR: Argumentos incorrectos.\nFORMATO: [ip] [nombre-BBDD] [username] [password] [fichero] [opcional: SGBD]");
            return;
        }

        // Crear la conexión a la base de datos
        BaseDeDatos bbdd;
        if (args.length == 5) {
            bbdd = new BaseDeDatos(args[0], args[1]);
        } else {
            bbdd = new BaseDeDatos(args[0], args[1], args[5]);
        }

        Connection conn;
        try {
            conn = bbdd.getConexion(args[2], args[3]);
            LOGGER.log(Level.INFO, "Conectado a la BBDD.");
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE, "ERROR: Conexión a la BBDD fallida, revise sus parámetros: " + e);
            return;
        }

        // Leer los datos del archivo de log
        LectorDeLineasDeLog lector;
        try {
            lector = new LectorDeLineasDeLog(args[4]);
            LOGGER.log(Level.INFO,"Leidas ["+lector.length()+"] lineas del fichero.");
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE, "ERROR: No se pudo leer el archivo de log.", e);
            return;
        }

        // Convertir las líneas de log a objetos del modelo
        String[][] logData = lector.obtenerColumnasDeLogs();
        Log[] logs = new Log[logData.length];
        for (int i = 0; i < logData.length; i++) {
            logs[i] = new Log(logData[i]);
            LOGGER.log(Level.FINE, "Parseada linea ["+i+"] con timestamp: " +TIMESTAMP_FORMATTER.format(LocalDateTime.now())); 
        }
        LOGGER.log(Level.INFO, "Creados ["+logs.length+"] objetos del modelo.");

        // Guardar los objetos del modelo en la base de datos
        try {
            int filas = 0;
	    	while (filas < logs.length) {
				logs[filas].save(conn);
				LOGGER.log(Level.FINE, "Insertando linea ["+filas+"] con timestamp: " +TIMESTAMP_FORMATTER.format(LocalDateTime.now()));
				filas++;
			}
            conn.close();
            LOGGER.log(Level.INFO, "Insertadas ["+filas+"] filas en BBDD.");
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE,"ERROR: Inserción fallida: "+e);
            return;
        }
    }

}
