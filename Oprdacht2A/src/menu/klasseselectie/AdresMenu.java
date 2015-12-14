package menu.klasseselectie;

import java.util.Scanner;
import business.Adres;
import dao.AdresDaoImpl;
import dao.FireBirdAdresDaoImpl;
import dao.MySQLAdresDaoImpl;
import menu.*;
import service.*;

public class AdresMenu {
	MySQLAdresDaoImpl mySQLAdresDaoImpl = new MySQLAdresDaoImpl();
	FireBirdAdresDaoImpl fireBirdAdresDaoImpl = new FireBirdAdresDaoImpl();
	Check check = new Check();
	DTO dto = new DTO();
	int klant_id;
	Scanner input = new Scanner(System.in);

	public void toonMenu() {
		Adres adres = null;

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

	    try {
	    	int keuze = input.nextInt();

			switch (keuze) {
            	case 1://Create --> adres
            		System.out.println("Voer het nieuw toetevoegen adres in: ");
            			adres = dto.createAdresObject();
            				if(DBKeuzeMenu.getDBKeuze()==1){
            					mySQLAdresDaoImpl.insert(adres);
            				}else if(DBKeuzeMenu.getDBKeuze()==2){
            					fireBirdAdresDaoImpl.insert(adres);
            				}
            		toonMenu();
            		break;
                
            	case 2://Read --> alle adresen
            		if(DBKeuzeMenu.getDBKeuze()==1){
    					mySQLAdresDaoImpl.readAllAdresses();
    				}else if(DBKeuzeMenu.getDBKeuze()==2){
    					fireBirdAdresDaoImpl.readAllAdresses();
    				}
            		toonMenu();
            		break;
                
            	case 3://Read --> straatnaam
            		System.out.println("Voer de straatnaam in waarop u de tabel wil doorzoeken: ");
            			String straatnaam = input.next();
            			while (straatnaam.length() > 26){
            				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
            					straatnaam = input.next();
            				}
            			if(DBKeuzeMenu.getDBKeuze()==1){
        					mySQLAdresDaoImpl.searchAdres(straatnaam);
        				}else if(DBKeuzeMenu.getDBKeuze()==2){
        					fireBirdAdresDaoImpl.searchAdres(straatnaam);
        				}
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
            			if(DBKeuzeMenu.getDBKeuze()==1){
        					mySQLAdresDaoImpl.searchAdres(postcode, huisnummer);
        				}else if(DBKeuzeMenu.getDBKeuze()==2){
        					fireBirdAdresDaoImpl.searchAdres(postcode, huisnummer);
        				}
            		toonMenu();
            		break;
            	
            	case 5: //Read --> alle adressen bij klantnummer
            		System.out.println("Voer klantnummer in waarvan u alle adressen wilt weergeven: ");
            				klant_id = input.nextInt();            		
            				if(DBKeuzeMenu.getDBKeuze()==1){
            					mySQLAdresDaoImpl.readAdressesFromKlant(klant_id);
            				}else if(DBKeuzeMenu.getDBKeuze()==2){
            					fireBirdAdresDaoImpl.readAdressesFromKlant(klant_id);
            				}
            		toonMenu();
            		break;
            		
            	case 6://Update --> adres
            		System.out.println("Voer de gegevens in van het bij te werken adres ");
            			adres = dto.createAdresObject();
            			if(DBKeuzeMenu.getDBKeuze()==1){
        					mySQLAdresDaoImpl.updateAdres(adres);
        				}else if(DBKeuzeMenu.getDBKeuze()==2){
        					fireBirdAdresDaoImpl.updateAdres(adres);
        				}
            		toonMenu();
            		break;
            	
            	case 7://Delete --> adres
            		System.out.println("Voer klantnummer van het te verwijderen adres in: ");
            			klant_id = input.nextInt();
            			adres = new Adres(klant_id, "-", "-", "-", 1, "-");
            			if(DBKeuzeMenu.getDBKeuze()==1){
        					mySQLAdresDaoImpl.deleteAdres(adres);
        				}else if(DBKeuzeMenu.getDBKeuze()==2){
        					fireBirdAdresDaoImpl.deleteAdres(adres);
        				}	
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