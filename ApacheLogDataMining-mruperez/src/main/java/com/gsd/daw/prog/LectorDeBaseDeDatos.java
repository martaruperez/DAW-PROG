package com.gsd.daw.prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LectorDeBaseDeDatos {
	private static final int TAMANIO_MAXIMO=10000; 
	private static final int COLUMNAS=6; 
	private Connection conn;
	public LectorDeBaseDeDatos(Connection conexion) {
		this.conn = conexion;
		
	}
	
    public String[][] extraerTabla( ) throws Exception {
    	
        Statement stmt = this.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery( "SELECT * FROM apache_log_tbl" );
        String[][] resultadoConsulta = new String [TAMANIO_MAXIMO][6];
        
        int i = 0;
        while ( resultSet.next() ) {
            try {
            	resultadoConsulta[i][0] = resultSet.getString( "IP" ) ;
            	resultadoConsulta[i][1] = resultSet.getString( "timestamp" ) ;
            	resultadoConsulta[i][2] = resultSet.getString( "request" ) ;
            	resultadoConsulta[i][3] = resultSet.getString( "result" ) ;
            	resultadoConsulta[i][4] = resultSet.getString( "bytes" ) ;
            	resultadoConsulta[i][5] = resultSet.getString( "ua" ) ;
            } 
            catch(NumberFormatException e) {

            }
            i++;
        }
        resultSet.close();
        stmt.close();
        conn.close();
        
        if( resultadoConsulta.length == i ) {return resultadoConsulta;}
        
        String aux[][] = new String [i][6];
        
		for(int j = (resultadoConsulta.length-1); j >= 0; j--) {
			if(resultadoConsulta[j][0] != null) {
				aux[i-1]=resultadoConsulta[j];
				i--;
			}
		}
        return aux;
    }
}
