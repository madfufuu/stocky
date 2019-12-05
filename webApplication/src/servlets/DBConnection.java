package servlets;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 


public class DBConnection {
	
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException { 
		// Initialize all the information regarding 
		// Database Connection 
		String dbDriver = "com.mysql.jdbc.Driver"; 
		String dbURL = "jdbc:mysql://localhost:3306/"; 
		// Database name to access 
		String dbName = "stocky"; 
		String dbUsername = "root"; 
		String dbPassword = "SoS00zxc"; 

		Class.forName(dbDriver); 
		Connection con = DriverManager.getConnection(dbURL + dbName, 
				dbUsername,  
				dbPassword); 
		return con; 
	}
	
//	public static Object retrieveFromDB(String tableName, String attributeName, String condition) throws SQLException, ClassNotFoundException{
//		Connection con = DBConnection.initializeDatabase();
//		
//		Statement stmt = con.createStatement();
//		String sql = "SELECT " + attributeName + " FROM " + tableName + " WHERE Email_Address = \'" + userEmail + "\'";
//		ResultSet rs = stmt.executeQuery(sql);
//		
//		return null;
//	}

}
