package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {

	private static Connection connection;
	
	public static Connection getInstance() throws SQLException, ClassNotFoundException {
		
		if(connection==null) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		}
		
		return connection;
	}
	
}
