package menu.crud;

import dao.*;
import dao.AdresDaoImpl;
import java.util.Scanner;
import menu.*;
import menu.klasseselectie.KlasseSelectieMenu;


public class ReadMenu {
	

	public void toonMenu() {
	    System.out.println("\t---------");
	    System.out.println("\tRead Menu");
	    System.out.println("\t---------");
	    
	    System.out.println("");
	    
	    System.out.println("1. Read:   Klant        Volledige tabel");
	    System.out.println("2. Read:   Klant            Zoeken op klant nummer");
	    System.out.println("3. Read:   Klant            Zoeken op voornaam");
	    System.out.println("4. Read:   Adres        Volledige tabel");
	    System.out.println("5. Read:   Adres            Zoeken op straatnaam");
	    System.out.println("6. Read:   Adres            Zoeken op de combinatie van postcode & huisnummer");
	    System.out.println("7. Read:   Artikel      Volledige tabel");
	    System.out.println("8. Read:   Artikel          Zoeken op artikel nummer");
	    System.out.println("9. Read:   Bestelling   Volledige tabel");
	    
	    System.out.println("");
	    
	    System.out.println("10. Terug naar het vorige menu"); 
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    
	    System.out.println("");
	    
	    System.out.print("Voer optie in en druk op Enter:");
	    
	    try (Scanner input = new Scanner(System.in);) {		     
            KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
	    	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
	    	ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
	    	BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
	    	
			int keuze = input.nextInt();
		    int klant_id;
			
			switch (keuze) {
            	case 1:
            		klantDaoImpl.read();
            		toonMenu();
            		break;
                
            	case 2:
            		System.out.println("Voer het klant nummer in waarop u de tabel klant wilt doorzoeken: ");
            		klant_id = input.nextInt();
            			while (adresDaoImpl.checkKlant_id(klant_id)!= true){ 
            				System.out.println("Het desbetreffende klant nummer bevind zich niet in de database! \nKlant nummer: ");
        					klant_id = input.nextInt();
            			}            				
            		klantDaoImpl.readKlant(klant_id);
            		toonMenu();            		
            		break;
                
            	case 3:
            		System.out.println("Voer de voornaam in waarop u de tabel klant wilt doorzoeken: ");
            		String voornaam = input.next();
            			while (voornaam.length() > 50){
            			System.out.println("Een voornaam mag niet meer dan 50 karakters bevatten! \nVoornaam: ");
            			voornaam = input.next();
            			}
            		klantDaoImpl.readKlant(voornaam);
            		toonMenu();
            		break;
                	
            	case 4:
            		adresDaoImpl.readAllAdresses();
            		toonMenu();
            		break;
            	
            	case 5:
            		System.out.println("Voer de straatnaam in waarop u de tabel wilt doorzoeken: ");
        			String straatnaam = input.next();
        			while (straatnaam.length() > 26){
        				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
        					straatnaam = input.next();
        				}
        		adresDaoImpl.searchAdres(straatnaam);
        		toonMenu();
        			break;
            	
            	case 6:
            		System.out.println("Voer de postcode in, zonder spatie: ");
        			String postcode = input.next();
        			while (postcode.length() > 6){
        				System.out.println("Een postcode mag niet meer dan 6 karakters bevatten! \nPostcode: ");
        					straatnaam = input.next();
        				}
        		System.out.println("Voer het huisnummer in: ");
        			int huisnummer = input.nextInt();
        			while (Integer.toString(huisnummer).length() > 6){
        				System.out.println("Een huisnummer mag niet meer dan 6 karakters bevatten! \nHuisnummer: ");
        					huisnummer = input.nextInt();				
        				}
        		adresDaoImpl.searchAdres(postcode, huisnummer);	
        		toonMenu();
        			break;
        			
            	case 7:
            		artikelDaoImpl.read();
            		toonMenu();
            		break;
            		
            	case 8:
            		System.out.println("Voer het artikel nummer in: ");
            		int artikel_id = input.nextInt();								// controle geldig artikel id dmv methode
            		System.out.println("Voer het klant nummer in: ");
            		klant_id = input.nextInt();
            		while (adresDaoImpl.checkKlant_id(klant_id)!=true){
            				System.out.println("Het desbetreffende klant nummer bevind zich niet in de database! \nKlant nummer: ");
        					klant_id = input.nextInt();
            		}
            		toonMenu();
            		break;
            		
            	case 9:
            		bestellingDaoImpl.read();
            		toonMenu();
            		break;
            	
            	case 10:
            		KlasseSelectieMenu klasseSelectieMenu = new KlasseSelectieMenu();
            		klasseSelectieMenu.toonMenu();
            		break;
            		
            	case 11:
            		HoofdMenu hoofdMenu =  new HoofdMenu();
            		hoofdMenu.toonMenu();
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
			// zinnige code			
		}	

	}	
}

