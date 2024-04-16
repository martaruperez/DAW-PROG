package com.gsd.daw.prog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Log {
	private String ip;
	private String timestamp;
	private String request;
	private int result;
	private String bytes;
	private String ua;
	
	public Log(String[] lineaDeLog) {
		this.ip = lineaDeLog[0];
		this.timestamp = lineaDeLog[1];
		this.request = lineaDeLog[2];
		this.result = Integer.parseInt(lineaDeLog[3]);
		this.bytes = lineaDeLog[4];
		this.ua = lineaDeLog[5];
	}

    public void save( Connection conn) throws SQLException {
    	
    	try{
    		PreparedStatement sentencia= conn.prepareStatement("INSERT INTO APACHE_LOG_TBL2 (ip, timestamp, request, result, bytes, ua) VALUES(?,?,?,?,?,?)");
	    	sentencia.setString(1, this.ip);
	    	sentencia.setString(2, this.timestamp);
	    	sentencia.setString(3, this.request);
	    	sentencia.setInt(4, this.result);
	    	sentencia.setString(5, this.bytes);
	    	sentencia.setString(6, this.ua);
	    	int row = sentencia.executeUpdate();
	    	sentencia.close();
    	}
    	catch(SQLException e) {
    		throw e;
    	}
    }
    
    public String getIp() {
    	return this.ip;
    }
     
    public int getResult() {
    	return this.result;
    }
}