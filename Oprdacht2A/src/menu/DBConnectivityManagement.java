package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import javax.security.auth.login.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.cfg.C3P0Config;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool;
import com.mchange.v2.c3p0.impl.C3P0PooledConnectionPoolManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import connectivity.DBConnectionMethods;
import connectivity.FireBirdConnectionSetup;
import connectivity.MySQLConnectionSetup;
import dao.AdresDaoImpl;
import dao.ArtikelDaoImpl;
import menu.crud.CrudMenu;
import menu.klasseselectie.KlasseSelectieMenu;

public class DBConnectivityManagement {
		int keuzeCP = 0;  
		HoofdMenu hoofdMenu = new HoofdMenu();
		Scanner input = new Scanner(System.in);
		private static final Logger logger =  LoggerFactory.getLogger(DBConnectivityManagement.class);
		static Connection connection = null;
		static HikariDataSource ds = null;
		static C3P0PooledConnectionPool C3POds = null;
		DBConnectionMethods connectionSetup = null;

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
	            
		    if(DBKeuzeMenu.getDBKeuze()==1){
				connectionSetup = new MySQLConnectionSetup();
			}else if(DBKeuzeMenu.getDBKeuze()==2){
				connectionSetup = new FireBirdConnectionSetup();
			}
		    
				int keuze = input.nextInt();
				 
				switch (keuze) {
	            	case 1:	            		
	            		System.out.println("1. Log in op de Hikari Connection Pool\n2. Log in op de C3PO Connection Pool");
	            		keuzeCP = input.nextInt();
	            		switch (keuzeCP) {
	            		case 1: 
	            			connection = connectionSetup.HikariCPInput();
	            			break;
	            		case 2:
	            			connection = connectionSetup.C3POCPInput();
	            			break;
	            		default:
	            			System.out.println("MySQL met Hikari wordt default gebruikt");
	            			connection = connectionSetup.HikariCPInput();
	            		}
	            		hoofdMenu.toonMenu(); 	            						
	            		break;
	                
	            	case 2:
	            		System.out.println("1. Log in op de Hikari Connection Pool\n2. Log in op de C3PO Connection Pool");
	            		keuzeCP = input.nextInt();
	            		switch (keuzeCP) {
	            		case 1: 
	            			connection = connectionSetup.HikariCPEva();
	            			break;
	            		case 2:
	            			connection = connectionSetup.C3POCPEva();
	            			break;
	            		default:
	            			System.out.println("MySQL met Hikari wordt default gebruikt");
	            			connection = connectionSetup.HikariCPEva();
	            		}
	            		hoofdMenu.toonMenu(); 	            						
	            		break;
	            		
	            	case 3:
	            		System.out.println("1. Log in op de Hikari Connection Pool\n2. Log in op de C3PO Connection Pool");
	            		int keuzeCP = input.nextInt();
	            		switch (keuzeCP) {
	            		case 1:
	            			connection = connectionSetup.HikariCPJesse();
	            			break;
	            		case 2:
	            			connection = connectionSetup.C3POCPJesse();
	            			break;
	            		default:
	            			System.out.println("MySQL met Hikari wordt default gebruikt");
	            			connection = connectionSetup.HikariCPJesse();
	            		}
	            		hoofdMenu.toonMenu(); 	            						
	            		break;
	            		
	            	case 4:
	            		System.out.println("1. Log in op de Hikari Connection Pool\n2. Log in op de C3PO Connection Pool");
	            		keuzeCP = input.nextInt();
	            		switch (keuzeCP) {
	            		case 1: 
	            			connection = connectionSetup.HikariCPAgung();
	            			break;
	            		case 2:
	            			connection = connectionSetup.C3POCPAgung();
	            			break;
	            		default:
	            			System.out.println("MySQL met Hikari wordt default gebruikt");
	            			connection = connectionSetup.HikariCPAgung();
	            		}
	            		hoofdMenu.toonMenu(); 	            						
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
		
				
				public static Connection logOut(Connection connection) {
					try {
						if (connection != null) {
							System.out.println("\nLogging out...\n");
							connection.close();
							connection = null;
							DBKeuzeMenu dBKeuzeMenu = new DBKeuzeMenu();
							dBKeuzeMenu.toonMenu();
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
		
							
		

			public static Connection getConnectionStatus() {
			return connection;
		}
			
			
			
			/*
		public ComboPooledDataSource setupC3POCP() {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			return cpds;
		}

			public Connection C3POCPInput() {
				String dbHostname = null;
				int dbPort = 0;
				
				
				System.out.print("Voer database hostname in: ");
				String dbHostName = input.next();
				System.out.print("Database port: ");
				dbPort = input.nextInt();
				input.nextLine();
				System.out.print("Database gebruikersnaam: ");
				String dbUsername = input.nextLine();
				System.out.print("Wachtwoord: ");
				String dbPassword = input.nextLine();
				String dbURL = ("jdbc:mysql://localhost:" + dbPort + "/" + dbHostName);
				ComboPooledDataSource cpds = setupC3POCP();
				//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
				cpds.setJdbcUrl(dbURL);
				cpds.setUser(dbUsername);                                  
				cpds.setPassword(dbPassword);
				
				try{
					connection = cpds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor C3POCPInput methode");
			            e.printStackTrace();
				}
				return connection;
			}

			public Connection C3POCPEva() {
				//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
				ComboPooledDataSource cpds = setupC3POCP();
				cpds.setJdbcUrl("jdbc:mysql://localhost:3306/opdracht1");
				cpds.setUser("root");                                  
				cpds.setPassword("");
				
				try{
					connection = cpds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor C3POCPEva methode");
			            e.printStackTrace();
				}
				return connection;
			}

			public Connection C3POCPAgung() {
				//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
				ComboPooledDataSource cpds = setupC3POCP();
				cpds.setJdbcUrl("jdbc:mysql://localhost:3306/opdracht1");
				cpds.setUser("root");                                  
				cpds.setPassword("mysql");
				
				try{
					connection = cpds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor C3POCAgung methode");
			            e.printStackTrace();
				}
				return connection;
			}

			public Connection C3POCPJesse() {
				//cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver            
				ComboPooledDataSource cpds = setupC3POCP();
				cpds.setJdbcUrl("jdbc:mysql://localhost:3308/opdracht1");
				cpds.setUser("root");                                  
				cpds.setPassword("JaRsvier15");
				
				try{
					connection = cpds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor C3POCJesse methode");
			            e.printStackTrace();
				}
				return connection;
			}

		public HikariConfig setupHikariCP() {
			HikariConfig config = new HikariConfig();
				config.addDataSourceProperty("cachePrepStmts", "true");
				config.addDataSourceProperty("prepStmtCacheSize", "250");
				config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			return config;
}

			public Connection HikariCPInput(){
				System.out.print("Voer database hostname in: ");
				String dbHostName = input.next();
				System.out.print("Database port: ");
				int dbPort = input.nextInt();
				input.nextLine();
				System.out.print("Database gebruikersnaam: ");
				String dbUsername = input.nextLine();
				System.out.print("Wachtwoord: ");
				String dbPassword = input.nextLine();
				String dbURL = ("jdbc:mysql://localhost:" + dbPort + "/" + dbHostName);
				
				HikariConfig config = setupHikariCP();
				config.setJdbcUrl(dbURL);
				config.setUsername(dbUsername);
				config.setPassword(dbPassword);
				HikariDataSource ds = new HikariDataSource(config);
				try{
					connection = ds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor HikariCPEva methode");
			            e.printStackTrace();
				}
				return connection;
			}

			public Connection HikariCPEva(){
				HikariConfig config = setupHikariCP();
				config.setJdbcUrl("jdbc:mysql://localhost:3306/opdracht1");
				config.setUsername("root");
				config.setPassword("");
				HikariDataSource ds = new HikariDataSource(config);
				try{
					connection = ds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor HikariCPEva methode");
			            e.printStackTrace();
				}
				return connection;
			}
	
			public Connection HikariCPAgung(){
				HikariConfig config = setupHikariCP();
				config.setJdbcUrl("jdbc:mysql://localhost:3306/opdracht1");
				config.setUsername("root");
				config.setPassword("mysql");
				HikariDataSource ds = new HikariDataSource(config);
				try{
					connection = ds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor HikariCPAgung methode");
			            e.printStackTrace();
				}
				return connection;
			}

			public Connection HikariCPJesse(){
				HikariConfig config = setupHikariCP();
				config.setJdbcUrl("jdbc:mysql://localhost:3308/opdracht1");
				config.setUsername("root");
				config.setPassword("JaRsvier15");
				HikariDataSource ds = new HikariDataSource(config);
				try{
					connection = ds.getConnection();
				}catch (SQLException e) {
						logger.warn("SQL exeption voor HikariCPJesse methode");
			            e.printStackTrace();
				}
				return connection;
			}


		
	 Oude inlog methoden
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
		} */
	}
