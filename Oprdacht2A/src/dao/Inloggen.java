package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Inloggen {
		
	public static Connection connectToDB() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Voer database hostname in: ");
		String dbHostName = input.nextLine();
		System.out.print("Database port: ");
		int dbPort = input.nextInt();
		input.nextLine();
		System.out.print("Database gebruikersnaam: ");
		String dbUsername = input.nextLine();
		System.out.print("Wachtwoord: ");
		String dbPassword = input.nextLine();
		
		
								
		Connection connection = null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Database driver is geladen	");

			if (connection == null) {
				String dbURL = ("jdbc:mysql://localhost:" + dbPort + "/" + dbHostName);
				String username = dbUsername;
				String password = dbPassword;
				connection = DriverManager.getConnection(dbURL, username, password);	
				System.out.println("Database verbinding is gemaakt");
			}
	 					
		} 
		
		
		
		
		catch (ClassNotFoundException e) {
			
            e.printStackTrace();
		} 
	
		catch (SQLException e) {
             
            e.printStackTrace();	             
		}
		
		/* TEST : 	System.out.println(connection);
					logOut(connection);
					System.out.println(connection); */
												
		finally {		
		   // zinnige code
		} 
		
		return connection;
		 																	
    } 
	
	
	public static Connection logOut(Connection connection) {
		 try {
              if (connection != null) {
                  System.out.println("Logging out...");
            	  connection.close();
            	  
              }
		 }     
              	              
         catch (Exception e) { 
            	e.printStackTrace();
         }
		 
		 return connection;
	
	}  
	

}
