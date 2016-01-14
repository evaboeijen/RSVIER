package menu.klasseselectie;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import menu.*;
import menu.crud.CrudMenu;

public class KlasseSelectieMenu {
	
		private static final Logger logger = LoggerFactory.getLogger(KlasseSelectieMenu.class);
		
		public void toonMenu() {
		    System.out.println("\t-------------------");
		    System.out.println("\tKlasseselectie Menu");
		    System.out.println("\t-------------------");
		    System.out.println("1. Klant");
		    System.out.println("2. Adres");
		    System.out.println("3. Bestelling");
		    System.out.println("4. Artikel");
		    System.out.println("5. Terug naar het hoofdmenu");
		    System.out.println("6. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		viewKlantMenu();
	            		KlantMenu klantmenu = new KlantMenu();
	            		klantmenu.toonMenu();
	            		break;
	                
	            	case 2:
	            		viewAdresMenu();
	            		AdresMenu adresmenu = new AdresMenu();
	            		adresmenu.toonMenu();
	            		break;
	                
	            	case 3:
	            		viewBestellingMenu();
	            		BestellingMenu bestellingmenu = new BestellingMenu();
	            		bestellingmenu.toonMenu();
	            		break;
	                	
	            	case 4:
	            		viewArtikelMenu();
	            		ArtikelMenu artikelmenu = new ArtikelMenu();
	            		artikelmenu.toonMenu();
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
		
		
		public void viewAdresMenu() {
			
			logger.info("viewAdresMenu() methode wordt aangeroepen");
			
			System.out.println("\n\t----------");
		    System.out.println("\tAdres Menu");
		    System.out.println("\t----------\n");

		    System.out.println("1. Create:      Creëer een nieuw adres voor bestaande klant");
		    System.out.println("2. Read:        Lees alle adressen uit de tabel");
		    System.out.println("3. Read:        Zoek op straatnaam");
		    System.out.println("4. Read:        Zoek op de combinatie van postcode & huisnummer");
		    System.out.println("5. Read:        Zoek op klantnummer");
		    System.out.println("6. Update:      Verander het adres van een bestaande klant");
		    System.out.println("7. Delete:      Verwijder het adres van een bestaande klant\n");    

		    System.out.println("10. Terug naar het vorige menu"); 
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen\n");

		    System.out.println("Voer optie in en druk op Enter:");
			
		}
		
		public void viewArtikelMenu() {
			
			logger.info("viewArtikelMenu() methode wordt aangeroepen");
			
		    System.out.println("\t------------");
		    System.out.println("\tArtikel Menu");
		    System.out.println("\t------------");
		   
		    System.out.println("");
		    
		    System.out.println("1. Create:      Voeg een nieuw artikel toe aan het assortiment");
		    System.out.println("2. Read:        Bekijk alle artikelen uit het assortiment");
		    System.out.println("3. Update:      Verander de gegevens van een bestaand artikel");
		    System.out.println("4. Delete:      Verwijder een artikel uit het assortiment");    

		    System.out.println("");
		    
		    System.out.println("10. Terug naar het vorige menu"); 	
		    System.out.println("11. Terug naar het hoofdmenu"); 	
		    System.out.println("12. Stoppen"); 						
		    
		    System.out.println("");
		    
		    System.out.println("Voer optie in en druk op Enter:");
			
		}	
		
		
		public void viewBestellingMenu() {
			
			logger.info("viewBestellingMenu() methode wordt aangeroepen");
			
		    System.out.println("\t---------------");
		    System.out.println("\tBestelling Menu");
		    System.out.println("\t---------------");
		    System.out.println("1. Create nieuwe bestelling voor bestaande klant");  
		    System.out.println("2. Read alle bestellingen");  
		    System.out.println("3. Update een bestaande bestelling door toevoegen van artikelen");  
		    System.out.println("4. Update het aantal van een bepaald artikel in een bepaalde bestelling");  
		    System.out.println("5. Delete een bepaalde bestelling");  
		    System.out.println("6. Delete een artikel uit een bepaalde bestelling");  
		    System.out.println();	    
		    System.out.println("10. Terug naar het vorige menu");
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen"); 
		    System.out.println();
		    System.out.print("Voer optie in en druk op Enter:");
								
		}
		
		
		public void viewKlantMenu() {
			
			logger.info("viewKlantMenu() methode wordt aangeroepen");
			
		    System.out.println("\t-----------");
		    System.out.println("\tKlant Menu");
		    System.out.println("\t-----------");
		    System.out.println("1. Maak een nieuwe Klant id aan");  
		    System.out.println("2. Lees uw klantgegevens");  
		    System.out.println("3. Wijzig uw bestaande klantgegevens");  
		    System.out.println("4. Wis uw klant id en uw gegevens");  		 
		    System.out.println();	    
		    System.out.println("10. Terug naar het vorige menu");
		    System.out.println("11. Terug naar het hoofdmenu"); 
		    System.out.println("12. Stoppen"); 
		    System.out.println();
		    System.out.print("Voer optie in en druk op Enter:");
					
		}
		
		
}

		
