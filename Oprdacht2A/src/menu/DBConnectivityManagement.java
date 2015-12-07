package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ArtikelDaoImpl;
import menu.crud.CrudMenu;
import menu.klasseselectie.KlasseSelectieMenu;

import com.mchange.v2.c3p0.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectivityManagement {
		private static final Logger logger =  LoggerFactory.getLogger(ArtikelDaoImpl.class);
		static Connection connection = null;
		Scanner input = new Scanner(System.in);

		public void toonMenu() {
		    System.out.println("\t---------");
		    System.out.println("\tInloggen");
		    System.out.println("\t---------");
		    System.out.println("1. Zelf databasegegevens invoeren");
		    System.out.println("2. Log in met Eva's databasegegevens");
		    System.out.println("3. Log in met Jesse's databasegegevens");
		    System.out.println("4. Log in met Agung's databasegegevens");
		    
		    System.out.println("10. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		connectToDBWithUserInput();	
	            		//System.out.println("Connection status: " + this.getConnectionStatus());
	            		HoofdMenu hoofdmenu = new HoofdMenu();					
	            		hoofdmenu.toonMenu(); 
	            		break;
	                
	            	case 2:
	            		connectToDBWithDefaultDataEva();
	            		//System.out.println("Connection status: " + this.getConnectionStatus());
	            		HoofdMenu hoofdmenu2 = new HoofdMenu();					
	            		hoofdmenu2.toonMenu(); 	            						
	            		break;
	            		
	            	case 3:
	            		connectToDBWithDefaultDataJesse();
	            		//System.out.println("Connection status: " + this.getConnectionStatus());
	            		HoofdMenu hoofdmenu3 = new HoofdMenu();					
	            		hoofdmenu3.toonMenu(); 	            						
	            		break;
	            		
	            	case 4:
	            		connectToDBWithDefaultDataAgung();
	            		//System.out.println("Connection status: " + this.getConnectionStatus());
	            		HoofdMenu hoofdmenu4 = new HoofdMenu();					
	            		hoofdmenu4.toonMenu(); 	            						
	            		break;
	                               	
	            	case 10:
	            		System.out.println("\nTot de volgende keer...");
	            		System.exit(1);
	            		break;
	            
	            	default:
	            		System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
	            		this.toonMenu();  			            		
				}         
							
		}

		public static Connection getConnectionStatus() {
			return connection;
		}

		public Connection connectToDBWithUserInput() {
				
			try {
				System.out.print("Voer database hostname in: ");
				String dbHostName = input.next();
				System.out.print("Database port: ");
				int dbPort = input.nextInt();
				input.nextLine();
				System.out.print("Database gebruikersnaam: ");
				String dbUsername = input.nextLine();
				System.out.print("Wachtwoord: ");
				String dbPassword = input.nextLine();
				
		
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Database driver is geladen	");

				if (connection == null) {
					String dbURL = ("jdbc:mysql://localhost:" + dbPort + "/" + dbHostName);
					String username = dbUsername;
					String password = dbPassword;				
					connection = DriverManager.getConnection(dbURL, username, password);				
					System.out.println("Database verbinding is gemaakt\n");
				}
				logger.info("Connectie status is: " + getConnectionStatus());
			}	 	
			
			catch (ClassNotFoundException e) {		
				e.printStackTrace();
			} 

			catch (SQLException e) {        
				e.printStackTrace();	             
			}
	
			finally {		
				// zinnige code
			} 
	
				return connection;		 																	
		} 

		public Connection connectToDBWithDefaultDataEva() {
								
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Database driver is geladen	");

				if (connection == null) {
					String dbURL = ("jdbc:mysql://localhost:3306/opdracht1");
					String username = "root";
					String password = "";
					connection = DriverManager.getConnection(dbURL, username, password);	
					System.out.println("Database verbinding is gemaakt\n");
				}
				logger.info("Connectie is: " + connection);
			}	 	
						
			catch (ClassNotFoundException e) {		
				e.printStackTrace();
			} 

			catch (SQLException e) {        
				e.printStackTrace();	             
			}
	
			finally {		
				// zinnige code
			} 
	
			return connection;	 																	
		} 

		public Connection connectToDBWithDefaultDataJesse() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Database driver is geladen	");

				if (connection == null) {
					String dbURL = ("jdbc:mysql://localhost:3308/opdracht1");
					String username = "root";
					String password = "JaRsvier15";
					connection = DriverManager.getConnection(dbURL, username, password);	
					System.out.println("Database verbinding is gemaakt\n");
				}
				logger.info("Connectie is: " + connection);
			}	 	
						
			catch (ClassNotFoundException e) {		
				e.printStackTrace();
			} 

			catch (SQLException e) {        
				e.printStackTrace();	             
			}
	
			finally {		
				// zinnige code
			} 
	
			return connection;	 																	
		} 

		public Connection connectToDBWithDefaultDataAgung() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Database driver is geladen	");

				if (connection == null) {
					String dbURL = ("jdbc:mysql://localhost:3306/opdracht1");
					String username = "root";
					String password = "mysql";
					connection = DriverManager.getConnection(dbURL, username, password);	
					System.out.println("Database verbinding is gemaakt\n");
				}
				logger.info("Connectie is: " + connection);
			}	 	
						
			catch (ClassNotFoundException e) {		
				e.printStackTrace();
			} 

			catch (SQLException e) {        
				e.printStackTrace();	             
			}
	
			finally {		
				// zinnige code
			} 
	
			return connection;	 																	
		} 

		public static Connection logOut(Connection connection) {
			try {
				if (connection != null) {
					System.out.println("\nLogging out...\n");
					connection.close();
					connection = null;
          			DBConnectivityManagement inloggen = new DBConnectivityManagement();
          			inloggen.toonMenu();
				}
				else {
					System.out.println("\nYou are already logged out.\n");         	  
				}
				logger.info("Connectie is: " + connection);
			}     
              	              
			catch (Exception e) { 
            	e.printStackTrace();
			}
		 
			return connection;
		}  

}