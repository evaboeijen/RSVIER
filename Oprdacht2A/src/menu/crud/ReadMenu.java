package menu.crud;

import dao.*;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import menu.*;
import menu.klasseselectie.KlasseSelectieMenu;
import service.Check;

public class ReadMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(ReadMenu.class);
	
	Scanner input = new Scanner(System.in);
	int klant_id;

	public void toonMenu() {
		
		logger.info("applicatielogica van ReadMenu() methode wordt aangeroepen");


		try {
			DaoImplKeuze daoKeuze = new DaoImplKeuze();
			AdresDaoImpl dbAdres = daoKeuze.AdresDaoImplKeuze();
			KlantDaoImpl dbKlant = daoKeuze.KlantDaoImplKeuze();
			ArtikelDaoImpl dbArtikel = daoKeuze.ArtikelDaoImplKeuze();
			BestellingDaoImpl dbBestelling = daoKeuze.BestellingDaoImplKeuze();
			
			Check check = new Check();

	    	int keuze = input.nextInt();

			switch (keuze) {
            	case 1:/*klant --> voledige tabel*/
            		dbKlant.read();
            		CrudMenu crudmenu = new CrudMenu();
            		crudmenu.viewReadMenu();
            		toonMenu();
            		break;

            	case 2:/*Klant --> klantnummer*/
            		System.out.println("Voer het klantnummer in waarop u de tabel klant wilt doorzoeken: ");
            		klant_id = input.nextInt();
            		while (!check.checkKlant_id(klant_id)){
            			System.out.println("Het desbetreffende klantnummer bevind zich niet in de database! \nKlantnummer: ");
        				klant_id = input.nextInt();
            		}
            		dbKlant.readKlant(klant_id);
            		CrudMenu crudmenu2 = new CrudMenu();
            		crudmenu2.viewReadMenu();
            		toonMenu();
            		break;

            	case 3:/*Klant --> voornaam*/
            		System.out.println("Voer de voornaam in waarop u de tabel klant wilt doorzoeken: ");
            		String voornaam = input.next();
            			while (voornaam.length() > 50){
            			System.out.println("Een voornaam mag niet meer dan 50 karakters bevatten! \nVoornaam: ");
            			voornaam = input.next();
            			}
            		dbKlant.readKlant(voornaam);
            		CrudMenu crudmenu3 = new CrudMenu();
            		crudmenu3.viewReadMenu();
            		toonMenu();
            		break;

            	case 4:/*Adres --> voledige tabel*/
        			dbAdres.readAllAdresses();
            		CrudMenu crudmenu4 = new CrudMenu();
            		crudmenu4.viewReadMenu();
            		toonMenu();
            		break;
            		
            	case 5:/*Adres --> straatnaam*/
            		System.out.println("Voer de straatnaam in waarop u de tabel wilt doorzoeken: ");
        			String straatnaam = input.next();
        			while (straatnaam.length() > 26){
        				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
        					straatnaam = input.next();
        			}
        			dbAdres.searchAdres(straatnaam);
            		CrudMenu crudmenu5 = new CrudMenu();
            		crudmenu5.viewReadMenu();
        			toonMenu();
        			break;

            	case 6:/*Adres --> postcode & huisnummer*/
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
        			dbAdres.searchAdres(postcode, huisnummer);
            		CrudMenu crudmenu6 = new CrudMenu();
            		crudmenu6.viewReadMenu();
        			toonMenu();
        			break;

            	case 7:/*Artikel --> voledige tabel*/
        			System.out.println(dbArtikel.read());
            		CrudMenu crudmenu7 = new CrudMenu();
            		crudmenu7.viewReadMenu();
        			toonMenu();
        			break;

            	/*case 8://Artikel --> artikelnummer & bestellingnummer
            		System.out.println("Voer het artikelnummer in: ");
            		int artikel_id = input.nextInt();										// controle geldig artikel id dmv methode
            		System.out.println("Voer het bestellingnummer in ");
            		int bestelling_id = input.nextInt();
            			System.out.println(dbArtikel.readArtikel(bestelling_id, artikel_id));
            		toonMenu();
            		break;*/
            		
            	case 8:/*Bestelling --> voledige tabel*/
            		System.out.println(dbBestelling.read());
            		CrudMenu crudmenu8 = new CrudMenu();
            		crudmenu8.viewReadMenu();
            		toonMenu();
            		break;



            	case 10:/*Terug naar het vorige menu*/
            		KlasseSelectieMenu klasseSelectieMenu = new KlasseSelectieMenu();
            		klasseSelectieMenu.toonMenu();
            		break;

            	case 11:/*Hoofdmenu*/
            		HoofdMenu hoofdMenu = new HoofdMenu();
            		hoofdMenu.toonMenu();
            		break;

            	case 12:/*Stoppen*/
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