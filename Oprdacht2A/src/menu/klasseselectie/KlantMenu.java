
	package menu.klasseselectie;

	
	import java.sql.*;
	import java.util.*;
	import dao.*;
	import menu.*;
import service.DTO;
import business.*;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	public class KlantMenu  {
		
		KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
		DTO dto = new DTO();
		Klant klant = new Klant();
		int huidige_klant_id = 0;
		
		private static final Logger logger =  LoggerFactory.getLogger(KlantMenu.class);
				
		
		public void toonMenu() {
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
		    
		   Scanner input = new Scanner(System.in);		     
	            
				int keuze = input.nextInt();
				logger.info("Gebruiker koos voor optie: " + keuze);
			       
				switch (keuze) {
	            	case 1:		
	 	            			klant = dto.createKlantObject();
	            				klantDaoImpl.create(klant);
	            				System.out.println();
	            				System.out.println("Een nieuwe klant is aangemaakt");
	            				klantDaoImpl.read();
	            				
	            				toonMenu();
	            		break; 
	            			           		          		
	                
	            	case 2:          		
	            				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
	            				
	            				huidige_klant_id = input.nextInt();
	    	        				
	    	        				
	    	            			while (klantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	    	            				System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	    	            				huidige_klant_id = input.nextInt();
	    	            				System.out.println();
	    	            			}            		
	            				
	            				System.out.println("Uw huidige gegevens zijn: ");
	            				klantDaoImpl.readKlant(huidige_klant_id);
	            				toonMenu();
	            		break;
	            		
	                
	            	case 3:
        				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
        
	            				huidige_klant_id = input.nextInt();
	        				
	        				
	            				while (klantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	            					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	            					huidige_klant_id = input.nextInt();
	            					System.out.println();
		            			}    
	            				
	            				klant = dto.createKlantObject();
	            			    klant.setKlant_id(huidige_klant_id);
	            				
	            				klantDaoImpl.update(klant);
	            				
	            				System.out.println("Uw gegevens zijn aanpast");
	            				
	            				toonMenu();
	            		break;
	            				
	            		
	            	case 4:
	            		
	            			System.out.println();           		       		
	            			System.out.print("Voer het klant ID in die u wilt verwijderen: ");
	            			System.out.println(); 

	            		
	            			huidige_klant_id = input.nextInt();
    				
    				
	            			while (klantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	        					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	        					huidige_klant_id = input.nextInt();
	        					System.out.println();
	            			}            		
    				        			
	            			klant.setKlant_id(huidige_klant_id);
	            		
	            			klantDaoImpl.delete(klant);
	            			
	            			toonMenu();
	            			break;
	            
	            		
	            	case 10:
	            		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
	            		klasseselectiemenu.toonMenu();
	            		break;
	            		
	            	case 11:
	            		HoofdMenu hoofdmenu = new HoofdMenu();					
	            		hoofdmenu.toonMenu(); 
	            		break;
	            		
	            	case 12:
	            		System.out.println("\nTot de volgende keer...");
	            		System.exit(1);
	            		break;
	            		
	            		
	            	default:
	            		System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
	            		this.toonMenu();
				} 
				
		}
	}
	
