package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import menu.crud.CrudMenu;
import menu.klasseselectie.KlasseSelectieMenu;

public class InEnUitLoggen {
		
		public void toonMenu() {
		    System.out.println("\t---------");
		    System.out.println("\tInloggen");
		    System.out.println("\t---------");
		    System.out.println("1. Zelf databasegegevens invoeren");
		    System.out.println("2. Standaard database gegevens gebruiken");
		    System.out.println("3. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		InEnUitLoggen.connectToDBWithUserInput();	
	            		HoofdMenu hoofdmenu = new HoofdMenu();					
	            		hoofdmenu.toonMenu(); 	            						
	            		break;
	                
	            	case 2:
	            		InEnUitLoggen.connectToDBWithDefaultData();
	            		HoofdMenu hoofdmenu2 = new HoofdMenu();					
	            		hoofdmenu2.toonMenu(); 	            						
	            		break;
	                               	
	            	case 3:
	            		System.out.println("\nTot de volgende keer...");
	            		System.exit(1);
	            		break;
	            
	            	default:
	            		System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
	            		this.toonMenu();
				} 
	        
			}
			
			finally {
				// zinnige code			
			}
		}
		
				
public static Connection connectToDBWithUserInput() {	
		
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
		
		finally {		
		   // zinnige code
		} 
		
		return connection;
		 																	
    } 
	

public static Connection connectToDBWithDefaultData() {	
								
	Connection connection = null;
	
	try {
		 Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Database driver is geladen	");

		if (connection == null) {
			String dbURL = ("jdbc:mysql://localhost:3306/test");
			String username = "root";
			String password = "mysql";
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
