package menu.reflection;

import java.util.Scanner;

import business.*;
import dao.KlantDaoImpl;
import menu.*;
import service.DTO;
import service.Reflection;

public class ReflectionMenu {
	
	DTO dto = new DTO();
	Reflection reflection = new Reflection();
	
		public void toonMenu() {
		    System.out.println("\t---------------");
		    System.out.println("\tReflection Menu");
		    System.out.println("\t---------------");
		    System.out.println();
		    System.out.println("1.buildInsertStatement met een klant object (opdracht 9)");
		    System.out.println();
		    System.out.println("buildInsertStatement met een object van klasse: ");
		    System.out.println("\t---------------------------------------------");
		    System.out.println();
		    System.out.println("2. Klant");
		    System.out.println("3. Adres");
		    System.out.println("4. Artikel");
		    System.out.println("5. Bestelling");
		    System.out.println("10. Terug naar het hoofdmenu");
		    System.out.println("11. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		
	            		Klant klant = dto.createKlantObject();
	            		
	            		String query = reflection.buildInsertStatement(klant);
	            		
	            		reflection.insert_buildInsertStatement(query);
	            		
	            		
	            		break;
	                
	            	case 2:
	            			            		
	            		Object klant2 = dto.createKlantObject();
	            		
	            		query = reflection.buildInsertStatement(klant2);
	            		
	            		reflection.insert_buildInsertStatement(query);
	            		
	            		break;
	                
	            	case 3:
	            		
	            		Object adres = dto.createAdresObject();
	            		
	            		query = reflection.buildInsertStatement(adres);
	            		
	            		reflection.insert_buildInsertStatement(query);
	            		
	            		break;
	                	
	            	case 4:
	            		
	            		break;
	            		
	            	case 10:
	            		HoofdMenu hoofdmenu = new HoofdMenu();
	            		hoofdmenu.toonMenu();
	            		break;
	            		
	            	case 11:
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
}
