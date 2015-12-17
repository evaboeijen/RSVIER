package menu.reflection;

import java.util.Scanner;

import business.*;
import connectivity.FireBirdConnectionSetup;
import connectivity.MySQLConnectionSetup;
import dao.KlantDaoImpl;
import menu.*;
import service.DTO;
import service.Reflection;

public class ReflectionMenu {
	
	DTO dto = new DTO();
	Reflection reflection = new Reflection();
	
		public void toonMenu() {
		    System.out.println("\t------------------");
		    System.out.println("\tJava Reflection Menu");
		    System.out.println("\t------------------");
		    if(DBKeuzeMenu.getDBKeuze() == 1) {
				System.out.println("\t(Database selected: MySQL)");
			}
		    else if(DBKeuzeMenu.getDBKeuze()==2) {
				System.out.println("\t(Database selected: Firebird)");
			}
		    System.out.println();
		    System.out.println("Opdracht 9 :");
		    System.out.println();
		    System.out.println("1. buildInsertStatement met een klant object als parameter");
		    System.out.println();
		    System.out.println("-------------------------------");
		    System.out.println();
		    System.out.println("Opdracht 10 - algemene buildInsertStatement methode \nmet als parameter een object van klasse : ");
		    System.out.println();
		    System.out.println("2. Klant");
		    System.out.println("3. Adres");
		    System.out.println("4. Artikel");
		    //System.out.println("5. Bestelling");
		    System.out.println();
		    System.out.println("-------------------------------");
		    System.out.println();
		    System.out.println("10. Terug naar het hoofdmenu");
		    System.out.println("11. Stoppen");
		    System.out.println();
		    System.out.println("-------------------------------");
		    System.out.println();
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:	            	
	            		Klant klant = dto.createKlantObject();	            		
	            		String query = reflection.buildInsertStatement(klant);	            		
	            		reflection.insert_buildInsertStatement(query);	            		
	            		toonMenu();
	            		break;
	                
	            	case 2:	            			            		
	            		Object klant2 = dto.createKlantObject();
	           	        query = reflection.buildInsertStatement(klant2);            		
	            		reflection.insert_buildInsertStatement(query);	           
	            		toonMenu();
	            		break;
	                
	            	case 3:	            		
	            		Object adres = dto.createAdresObject();	            		
	            		query = reflection.buildInsertStatement(adres);	            		
	            		reflection.insert_buildInsertStatement(query);
	            		toonMenu();
	            		break;
	                	
	            	case 4:
	            		Object artikel = dto.createArtikelObject();	            		
	            		query = reflection.buildInsertStatement(artikel);	            		
	            		reflection.insert_buildInsertStatement(query);
	            		toonMenu();
	            		break;
	            		
	            	/* case 5:
	            		Object bestelling = dto.createBestellingObject();
	            		
	            		query = reflection.buildInsertStatement(bestelling);
	            		
	            		reflection.insert_buildInsertStatement(query);
	            		
	            		break; */
	            		
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
