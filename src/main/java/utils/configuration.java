package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configuration {
	
	private String username;
	private String password;
	
	configuration(){
		username = "";
		password = "";
	}
	
	configuration(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public Connection connectToDataBase() {
		
		//Not to sure about this URL.
		String URL = "jdbc:postgresql://team-2-enterprise.cvtq9j4axrge.us-east-1.rds.amazonaws.com?user=postgres&password=postgres";
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	

}
