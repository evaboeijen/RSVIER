
	package menu.klasseselectie;

	
	import java.sql.*;
	import java.util.*;
	import dao.*;
	import menu.*;
	import business.*;

	public class KlantMenu  {
		
		
		
		public Klant createKlantObject() {
			
			Scanner input = new Scanner(System.in);					
			
		    System.out.println("Vul uw gegevens in. Voer in uw voornaam, en druk op enter: ");
			String voornaam = input.nextLine();
			
					while (voornaam.length() > 50){
						System.out.println("Een voornaam mag niet meer dan 50 karakters bevatten! \n Voornaam: ");
						voornaam = input.nextLine();
					}
					
			System.out.println("Tussenvoegsel: ");
				String tussenvoegsel = input.nextLine();
					while (tussenvoegsel.length() > 12){
					System.out.println("Een tussenvoegsel niet meer dan 12 karakters bevatten! \n Tussenvoegsel: ");
						tussenvoegsel = input.nextLine();
					}			
			System.out.println("Achternaam: ");
				String achternaam = input.nextLine();
					while (achternaam.length() > 51){
					System.out.println("Een achternaam mag niet meer dan 51 karakters bevatten! \n Achternaam: ");
						achternaam = input.nextLine();
					}
			System.out.println("Emailadres: ");
				String email =  input.nextLine();
					while (email.length() > 80){
					System.out.println("Een email mag niet meer dan 80 karakters bevatten! \n Emailadres: ");
						email = input.nextLine();
					}
			
					Klant klant = new Klant();
					klant.setKlant_id(klant.getKlant_id());
					klant.setVoornaam(voornaam);
					klant.setTussenvoegsel(tussenvoegsel);
					klant.setAchternaam(achternaam);
					klant.setEmail(email);
					
					return klant;
					}		
		
				
		
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
			       
				switch (keuze) {
	            	case 1:	
	            				KlantDaoImpl nieuweKlantDaoImpl = new KlantDaoImpl();
	            				Klant nieuweKlant = createKlantObject();
	            				nieuweKlantDaoImpl.create(nieuweKlant);
	            				System.out.println();
	            				System.out.println("Een nieuwe klant is aangemaakt");
	            				nieuweKlantDaoImpl.read();
	            				
	            				toonMenu();
	            		break; 
	            			           		          		
	                
	            	case 2:          		
	            			            	          			
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
	            				toonMenu();
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
	        				
	            				            				
	            			    Klant updateKlant = createKlantObject();
	            			    updateKlant.setKlant_id(huidige_klant_id);
	            				
	            				updateKlantDaoImpl.update(updateKlant);
	            				
	            				System.out.println("Uw gegevens zijn aanpast");
	            				
	            				toonMenu();
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
	
