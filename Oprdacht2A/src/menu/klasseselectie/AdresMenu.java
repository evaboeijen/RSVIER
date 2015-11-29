package menu.klasseselectie;

import java.util.Scanner;
import business.Adres;
import dao.AdresDaoImpl;
import menu.*;

public class AdresMenu {
	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();

	public Adres createAdresObject() {
	    
		Scanner input = new Scanner(System.in);					// NULPOINTER???
		
	    System.out.println("Klantnummer: ");
			int klant_id = input.nextInt();
				while (adresDaoImpl.checkKlant_id(klant_id)!= true){
					System.out.println("Het desbetreffende klant nummer bevind zich niet in de database! \nKlantnummer: ");
					klant_id = input.nextInt();
				}
		System.out.println("Straatnaam: ");
			String straatnaam = input.next();
				while (straatnaam.length() > 26){
				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
					straatnaam = input.next();
				}			
		System.out.println("Postcode: 			( zonder spatie ) ");
			String postcode = input.next();
				while (postcode.length() > 6){
				System.out.println("Een postcode mag niet meer dan 6 karakters bevatten! \nPostcode: ");
					straatnaam = input.next();
				}
		System.out.println("Toevoeging: 		( als geen toevoeging vul - in ) ");
			String toevoeging =  input.next();
				while (toevoeging.length() > 6){
				System.out.println("Een toevoeging mag niet meer dan 6 karakters bevatten! \nToevoeging: ");
					straatnaam = input.next();
				}
		System.out.println("Huisnummer: ");
			int huisnummer = input.nextInt();
				while (Integer.toString(huisnummer).length() > 6){
				System.out.println("Een huisnummer mag niet meer dan 6 karakters bevatten! \nHuisnummer: ");
					huisnummer = input.nextInt();				
				}
		System.out.println("Woonplaats: ");
			String woonplaats = input.next();
				while (woonplaats.length() > 26){
				System.out.println("Een woonplaats mag niet meer dan 26 karakters bevatten! \nWoonplaats: ");
					straatnaam = input.next();
				}		
		Adres adres = new Adres(klant_id, straatnaam, postcode, toevoeging, huisnummer, woonplaats);
		return adres;
	    }	

	public void toonMenu() {
		Adres adres = null;

		System.out.println("\n\t----------");
	    System.out.println("\tAdres Menu");
	    System.out.println("\t----------\n");

	    System.out.println("1. Create:      Creëer een nieuw adres voor bestaande klant");
	    System.out.println("2. Read:        Lees alle adressen uit de tabel");
	    System.out.println("3. Read:        Zoek op straatnaam");
	    System.out.println("4. Read:        Zoek op de combinatie van postcode & huisnummer");
	    System.out.println("5. Update:      Verander het adres van een bestaande klant");
	    System.out.println("6. Delete:      Verwijder het adres van een bestaande klant/n");    

	    System.out.println("10. Terug naar het vorige menu"); 
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen/n");

	    System.out.println("Voer optie in en druk op Enter:");

	    Scanner input = new Scanner(System.in);

	    try {		     

			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1://Create --> adres
            		System.out.println("Voer het nieuw toetevoegen adres in: ");
            			adres = createAdresObject();
            		adresDaoImpl.insert(adres);
            		toonMenu();
            		break;
                
            	case 2://Read --> alle adresen
            		adresDaoImpl.readAllAdresses();
            		toonMenu();
            		break;
                
            	case 3://Read --> straatnaam
            		System.out.println("Voer de straatnaam in waarop u de tabel wil doorzoeken: ");
            			String straatnaam = input.next();
            			while (straatnaam.length() > 26){
            				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
            					straatnaam = input.next();
            				}
            		adresDaoImpl.searchAdres(straatnaam);
            		toonMenu();
            		break;
                	
            	case 4://Read --> postcode & huisnummer
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
            	
            	case 5://Update --> adres
            		System.out.println("Voer de gegevens in van het bij te werken adres ");
            			adres = createAdresObject();
            		adresDaoImpl.updateAdres(adres);
            		toonMenu();
            		break;
            	
            	case 6://Delete --> adres
            		System.out.println("Voer klantnummer van het te verwijderen adres in: ");
            		int klant_id = input.nextInt();
            			adres = new Adres(klant_id, "-", "-", "-", 1, "-");
            		adresDaoImpl.deleteAdres(adres);	
            		toonMenu();
            		break;
            	
            	
            	
            	case 10://Terug naar het vorige menu
            		KlasseSelectieMenu klasseSelectieMenu = new KlasseSelectieMenu();
            		klasseSelectieMenu.toonMenu();
            		break;
            	
            	case 11://Hoofdmenu
            		HoofdMenu hoofdMenu =  new HoofdMenu();
            		hoofdMenu.toonMenu();
            		break;
            	
            	case 12://Stoppen
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