
	package menu.klasseselectie;

	
	import java.sql.*;
	import java.util.*;
	import dao.*;
	import menu.*;
	import service.*;
	import business.*;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	public class KlantMenu  {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		KlantDaoImpl klantDaoImpl = daoImplKeuze.KlantDaoImplKeuze();
		DTO dto = new DTO();
		Klant klant = new Klant();
		int huidige_klant_id = 0;
		Check check = new Check();
		
		private static final Logger logger =  LoggerFactory.getLogger(KlantMenu.class);
				
		
		public void toonMenu() {
			
			
			logger.info("applicatielogica van KlantMenu() methode wordt aangeroepen");
		    
			Scanner input = new Scanner(System.in);		     
			try {
		    			    	   
				int keuze = input.nextInt();
				logger.info("Gebruiker koos voor optie: " + keuze);
			       
				switch (keuze) {
	            	case 1:		
	 	            			klant = dto.createKlantObject();
	            				klantDaoImpl.create(klant);
	            				System.out.println();
	            				System.out.println("Een nieuwe klant is aangemaakt");
	            				klantDaoImpl.read();
	            				
	                            KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
	                            klasseselectiemenu.viewKlantMenu();                    		
	                    		toonMenu();
	                    		break;
	            			           		          		
	                
	            	case 2:          		
	            				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
	            				
	            				huidige_klant_id = input.nextInt();
	    	        				
	    	        				
	    	            			while (check.checkKlant_id(huidige_klant_id)!= true) { 
	    	            				System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	    	            				huidige_klant_id = input.nextInt();
	    	            				System.out.println();
	    	            			}            		
	            				
	            				System.out.println("Uw huidige gegevens zijn: ");
	            				System.out.println(klantDaoImpl.readKlant(huidige_klant_id));
	            				
	                            KlasseSelectieMenu klasseselectiemenu2 = new KlasseSelectieMenu();
	                            klasseselectiemenu2.viewKlantMenu();                    		
	                    		toonMenu();
	                    		break;
	            		
	                
	            	case 3:
        				
	            				System.out.println();
	            				System.out.println("Voer uw klant id in, en druk op enter: ");
	            				System.out.println();
        
	            				huidige_klant_id = input.nextInt();
	        				
	        				
	            				while (check.checkKlant_id(huidige_klant_id)!= true) { 
	            					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	            					huidige_klant_id = input.nextInt();
	            					System.out.println();
		            			}    
	            				
	            				klant = dto.createKlantObject();
	            			    klant.setKlant_id(huidige_klant_id);
	            				
	            				klantDaoImpl.update(klant);
	            				
	            				System.out.println("Uw gegevens zijn aanpast");
	            				
	                            KlasseSelectieMenu klasseselectiemenu3 = new KlasseSelectieMenu();
	                            klasseselectiemenu3.viewKlantMenu();                    		
	                    		toonMenu();
	                    		break;
	            				
	            		
	            	case 4:
	            		
	            			System.out.println();           		       		
	            			System.out.print("Voer het klant ID in die u wilt verwijderen: ");
	            			System.out.println(); 

	            		
	            			huidige_klant_id = input.nextInt();
    				
    				
	            			while (check.checkKlant_id(huidige_klant_id)!= true) { 
	        					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
	        					huidige_klant_id = input.nextInt();
	        					System.out.println();
	            			}            		
    				        			
	            			klant.setKlant_id(huidige_klant_id);
	            		
	            			klantDaoImpl.delete(klant);
	            			System.out.println("Uw gegevens zijn gewist");
	            			
                            KlasseSelectieMenu klasseselectiemenu4 = new KlasseSelectieMenu();
                            klasseselectiemenu4.viewKlantMenu();                    		
                    		toonMenu();
                    		break;
	            
	            		
	            	case 10:
	            		KlasseSelectieMenu klasseselectiemenu5 = new KlasseSelectieMenu();
	            		klasseselectiemenu5.toonMenu();
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
		   finally {
				System.out.println("---Uw keuze is uitgevoerd---");		
			}	
	}
	}
