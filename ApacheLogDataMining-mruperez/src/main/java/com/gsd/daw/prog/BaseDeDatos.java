package com.gsd.daw.prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {
	public static final String SGBD_MYSQL  = "MYSQL";
    public static final String SGBD_ORACLE = "ORACLE";
    public static final String SGBD_POSTGRESQL = "POSTGRESQL";
    private String connectionString;
    private Connection conn=null;

    public BaseDeDatos(String ip, String databaseName, String mySgbd) {
        switch ( mySgbd ) {
        case SGBD_POSTGRESQL:
           this.connectionString = "jdbc:postgresql://";
            break;
        case SGBD_ORACLE:
        	this.connectionString = "jdbc:oracle:thin:@//";
            break;
        case SGBD_MYSQL:
        	this.connectionString = "jdbc:mysql://";
            break;
        default:
        	this.connectionString = "jdbc:mysql://";
            break;
        }
        this.connectionString+=ip; 
        if("jdbc:mysql://".equals(this.connectionString)) {
        	this.connectionString+=":3306/";
        	this.connectionString+=databaseName;
        	this.connectionString+="?serverTimezone=UTC";
        }
        else {
        	this.connectionString+="/";
        	this.connectionString+=databaseName;
        }
    }
    
    public BaseDeDatos(String ip, String databaseName) {
    	this.connectionString="jdbc:oracle:thin:@//";
    	this.connectionString+=ip; 
    	this.connectionString+="/";
    	this.connectionString+=databaseName;
    }
    
    public Connection getConexion(String usuario, String password) throws SQLException{
    	
        try {
            return DriverManager.getConnection( connectionString, usuario, password );
        } catch ( SQLException e ) {
        	throw e;
        }

    }
    
}
