package com.gsd.daw.prog;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LectorDeBaseDeDatos {
	//private static final int TAMANIO_MAXIMO=10000; 
	private static final int COLUMNAS=6; 
	private Connection conn;
	public LectorDeBaseDeDatos(Connection conexion) {
		this.conn = conexion;
		
	}
	
    public List<Log> extraerTabla( ) throws Exception {
    	
        Statement stmt = this.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery( "SELECT * FROM apache_log_tbl2" );
        List <Log> resultadoConsulta=new ArrayList<>();
        
        String[] linea = new String[COLUMNAS];
        while ( resultSet.next() ) {
            try {
            	linea[0] = resultSet.getString( "IP" ) ;
            	linea[1] = resultSet.getString( "timestamp" ) ;
            	linea[2] = resultSet.getString( "request" ) ;
            	linea[3] = resultSet.getString( "result" ) ;
            	linea[4] = resultSet.getString( "bytes" ) ;
            	linea[5] = resultSet.getString( "ua" ) ;
            	Log l = new Log(linea);
            	resultadoConsulta.add(l);
            } 
            catch(NumberFormatException e) {

            }
        }
        resultSet.close();
        stmt.close();
        conn.close();
        return resultadoConsulta;
    }
}
