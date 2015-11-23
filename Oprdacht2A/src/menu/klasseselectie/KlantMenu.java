
	package menu.klasseselectie;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	import dao.*;
	import menu.HoofdMenu;
	import menu.InEnUitLoggen;
	import business.*;

	public class KlantMenu  {
		
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
		    
		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:	
	            		
	            				System.out.println();
	            				System.out.println("Voer uw gegevens in, en druk op enter." + '\n' + "Voornaam: ");
	            				String voornaam = input.nextLine();
	            				
	            				System.out.println("Tussenvoegsel (druk enter bij geen tussenvoegsel): ");
	            				String tussenvoegsel = input.nextLine();
	            				
	            				System.out.println("Achternaam: ");
	            				String achternaam = input.nextLine();
	            				
	            				System.out.println("emailadres: ");
	            				String email = input.nextLine();
	            				
	            				KlantDaoImpl nieuweKlantDaoImpl = new KlantDaoImpl();
	            				Klant nieuweKlant = new Klant();
	            				nieuweKlant.setKlant_id(nieuweKlant.getKlant_id());
	            				nieuweKlant.setVoornaam(voornaam);
	            				nieuweKlant.setTussenvoegsel(tussenvoegsel);
	            				nieuweKlant.setAchternaam(achternaam);
	            				nieuweKlant.setEmail(email);
	            				
	            				nieuweKlantDaoImpl.create(nieuweKlant);
	            				System.out.println();
	            				System.out.println("Een nieuwe klant is aangemaakt met de volgende gegevens: ");
	            				System.out.println("Klant id: " + nieuweKlant.getKlant_id() + ". Naam " + voornaam + " " + tussenvoegsel + " " + achternaam + ". Email: " + email);
	            			 
	            			 
	            		}
	            		break; 
	            			           		          		
	                
	            	case 2:          		
	            		// System.out.println(InEnUitLoggen.getConnectionStatus());
	            	          			
	            				KlantDaoImpl leesKlantDaoImpl = new KlantDaoImpl();
	            				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
	            				
	            			    int huidige_klant_id = input.nextInt();
	    	        				
	    	        				
	    	            			while (leesKlantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	    	            				System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	    	            				huidige_klant_id = input.nextInt();
	    	            				System.out.println();
	    	            			}            		
	            				
	            				System.out.println("Uw huidige gegevens zijn: ");
	            				leesKlantDaoImpl.readKlant(huidige_klant_id);
	            				
	            		break;
	            		
	                
	            	case 3:
	            				KlantDaoImpl updateKlantDaoImpl = new KlantDaoImpl();
        				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
        				
	            			
	            				huidige_klant_id = input.nextInt();
	        				
	        				
	            				while (updateKlantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	            					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	            					huidige_klant_id = input.nextInt();
	            					System.out.println();
		            			}            		
	        				
	            				System.out.println("Uw gegevens zijn bekend, voer uw nieuwe gegevens in: ");
	            				            				
	            				System.out.println("Voornaam: ");               		
	            				voornaam = input.nextLine();
	            		
	            				System.out.println("Tussenvoegsel (druk enter bij geen tussenvoegsel): ");
	            				tussenvoegsel = input.nextLine();
	            				
	            				System.out.println("Achternaam: ");
	            				achternaam = input.nextLine();
	            				
	            				System.out.println("emailadres: ");
	            				email = input.nextLine();
	            				
	            			    Klant updateKlant = new Klant();
	            				updateKlant.setKlant_id(huidige_klant_id);
	            				updateKlant.setVoornaam(voornaam);
	            				updateKlant.setTussenvoegsel(tussenvoegsel);
	            				updateKlant.setAchternaam(achternaam);
	            				updateKlant.setEmail(email);
	            				
	            				updateKlantDaoImpl.update(updateKlant);
	            				
	            				System.out.println();
	            				
	            				
	            		break;
	            				
	            		
	            	case 4:
	            		
	            			KlantDaoImpl verwijderKlantDaoImpl = new KlantDaoImpl();
	            		
	            			System.out.println();           		       		
	            			System.out.print("Voer het klant ID in die u wilt verwijderen: ");
	            			System.out.println(); 

	            		
	            			huidige_klant_id = input.nextInt();
    				
    				
	            			while (verwijderKlantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
	        					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	        					huidige_klant_id = input.nextInt();
	        					System.out.println();
	            			}            		
    				        			
	            			Klant verwijderKlant = new Klant();
	            			verwijderKlant.setKlant_id(huidige_klant_id);
	            		
	            			verwijderKlantDaoImpl.delete(verwijderKlant);
	            			
				
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
				
	    		KlantMenu klantmenu = new KlantMenu();
	    		klantmenu.toonMenu();    
	        
			}
			
			finally {
				// zinnige code			
			}	

		}	
	}

