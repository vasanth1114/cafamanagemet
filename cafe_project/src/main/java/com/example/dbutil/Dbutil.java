package com.example.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Dbutil {
	private static Connection connection = null;
	public static Connection getConnection() throws SQLException {
		if(connection != null) {
			return connection;
		}
		else {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url ="jdbc:mysql://localhost:3306/cafe_project";
			String user="root";
			String password = "Mlv2102001!";
			
			try {
				Class.forName(driver);
				connection=DriverManager.getConnection(url,user,password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
}
