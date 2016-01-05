package menu.crud;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import menu.*;

public class CrudMenu {
	
		private static final Logger logger = LoggerFactory.getLogger(CrudMenu.class);
		
		public void toonMenu() {
		    System.out.println("\t----------");
		    System.out.println("\tCrud Menu");
		    System.out.println("\t----------");
		    System.out.println("1. Create menu");
		    System.out.println("2. Read menu");
		    System.out.println("3. Update menu");
		    System.out.println("4. Delete menu");
		    System.out.println("5. Terug naar het hoofdmenu");
		    System.out.println("6. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:	            		
	            		viewCreateMenu();	            		
	            		CreateMenu createmenu = new CreateMenu();	            
	            		createmenu.toonMenu();
	            		break;
	                
	            	case 2:
	            		viewReadMenu();	    
	            		ReadMenu readmenu = new ReadMenu();
	            		readmenu.toonMenu();
	            		break;
	                
	            	case 3:
	            		viewUpdateMenu();
	            		UpdateMenu updatemenu = new UpdateMenu();
	            		updatemenu.toonMenu();
	            		break;
	                	
	            	case 4:
	            		viewDeleteMenu();
	            		DeleteMenu deletemenu = new DeleteMenu();
	            		deletemenu.toonMenu();
	            		break;
	            		
	            	case 5:
	            		HoofdMenu hoofdmenu = new HoofdMenu();
	            		hoofdmenu.toonMenu();
	            		break;
	            		
	            	case 6:
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
		
		public void viewCreateMenu() { 
			
			logger.info("viewCreateMenu() methode wordt aangeroepen");
			
			System.out.println("\t----------");
		    System.out.println("\tCreate Menu");
		    System.out.println("\t----------");
		    System.out.println("1. Create nieuwe klant");
		    System.out.println("2. Create nieuwe klant met adres");
		    System.out.println("3. Create adres voor bestaande klant");
		    System.out.println("4. Create artikel voor bestaande bestelling");
		    System.out.println("5. Create bestelling voor nieuwe klant");
		    
		 
		    	    
		    System.out.println("10. Terug naar het vorige menu"); 
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen"); 
		    System.out.print("Voer optie in en druk op Enter:");
		}
		
		
		public void viewDeleteMenu() { 
			
			logger.info("viewDeleteMenu() methode wordt aangeroepen");
			
			System.out.println("\t-------------");
		    System.out.println("\tDelete  Menu");
		    System.out.println("\t-------------");
		    System.out.println("1. Delete het adres van een bestaande klant");  
		    System.out.println("2. Delete een artikel van een bestaande bestelling");  
		    System.out.println("3. Delete een bestaande bestelling");
		    System.out.println("4. Delete een artikel uit het assortiment");
		    System.out.println("5. Delete een klant");  		 
		    System.out.println();	    
		    System.out.println("10. Terug naar het vorige menu");
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen"); 
		    System.out.println();
		    System.out.print("Voer optie in en druk op Enter:");
		}
		
		
		public void viewReadMenu() { 
			
			logger.info("viewReadMenu() methode wordt aangeroepen");
						
			System.out.println("\n\t---------");
			System.out.println("\tRead Menu");
			System.out.println("\t---------\n");

			System.out.println("1. Read:   Klant        Volledige tabel");
			System.out.println("2. Read:   Klant            Zoeken op klantnummer");
			System.out.println("3. Read:   Klant            Zoeken op voornaam");
			System.out.println("4. Read:   Adres        Volledige tabel");
			System.out.println("5. Read:   Adres            Zoeken op straatnaam");
			System.out.println("6. Read:   Adres            Zoeken op de combinatie van postcode & huisnummer");
			System.out.println("7. Read:   Artikel      Volledige tabel");
			//System.out.println("8. Read:   Artikel          Zoeken op de combinatie van bestellingnummer & artikelnummer");
			System.out.println("8. Read:   Bestelling   Volledige tabel\n");

			System.out.println("10. Terug naar het vorige menu"); 
			System.out.println("11. Terug naar het hoofdmenu"); 
			System.out.println("12. Stoppen\n"); 

			System.out.print("Voer optie in en druk op Enter:");
									
		}
		
		
		public void viewUpdateMenu() { 
		
			logger.info("viewUpdateMenu() methode wordt aangeroepen");
			
		    System.out.println("\t----------");
		    System.out.println("\tUpdate Menu");
		    System.out.println("\t----------");
		    System.out.println("1. Update klant");
		    System.out.println("2. Update adres");
		    System.out.println("3. Update bestelling");
		    System.out.println("4, Update artikel");
	   
		    	    
		    System.out.println("10. Terug naar het vorige menu"); 
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen"); 
		    System.out.print("Voer optie in en druk op Enter:");
		    
		}
									
}

		
