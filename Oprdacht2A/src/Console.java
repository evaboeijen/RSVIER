import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class Console {

	public static void main(String[] args) {
							
		Console.initializeDB();
		
		Scanner input = new Scanner(System.in);		     
        Menus.hoofdMenu(); 
        
        int keuze = input.nextInt();
                                         
        switch (keuze) {
            case 1:
                Menus.crudMenu();
                break;
                
            case 2:
                Menus.klasseSelectieMenu();
                break;
                
            case 3:
                Console.initializeDB();
                break;
                	
            case 4:
                System.out.println("Tot de volgende keer...");
                System.exit(1);
                break;
            
            default:
                System.out.println("Ongeldige optie");
        } 
        
	
	} 
	

		// inlogscherm wordt getoond met invoerveld voor databaseurl, user, password,
		// waarna de user op het hoofdmenu aankomt
				
		// hoofdmenu : 
		// 1 crud handelingen
		// 2 klasse selectie
		// 3 uitloggen
		// 4 stoppen
				
		// indien user optie 1 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "crud handeling" :
		// 1 create
		// 2 read
		// 3 update
		// 4 delete
		// 5 terug naar het hoofdmenu
		
		// indien user optie 2 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 3 heeft gekozen in hoofdmenu, volgt weer het inlogscherm :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 5 heeft gekozen in hoofdmenu, wordt het programmaatje beeindigd 
	
		
		public static void initializeDB() {
			
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
			
		}
		
/*			finally {
			    if (input != null) { 
			        System.out.println("Closing Scanner");
			        input.close();
			    } else { 
			        System.out.println("Scanner not open");
			    } 
			} 																	
	    } */
		
		
/*		public static void logOut() {
			 try {
	              if (connection != null) {
	                  Console.connection.close();
	              }
			 }     
	              	              
	         catch (Exception e) { 
	            	e.printStackTrace();
	         }
		
		}  */
		
		
}


