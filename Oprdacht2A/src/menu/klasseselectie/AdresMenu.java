package menu.klasseselectie;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Adres;
import dao.AdresDaoImpl;
import dao.DaoImplKeuze;
import dao.FireBirdAdresDaoImpl;
import dao.MySQLAdresDaoImpl;
import menu.*;
import menu.crud.CrudMenu;
import menu.crud.ReadMenu;
import service.*;

public class AdresMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(AdresMenu.class);
	
	MySQLAdresDaoImpl mySQLAdresDaoImpl = new MySQLAdresDaoImpl();
	FireBirdAdresDaoImpl fireBirdAdresDaoImpl = new FireBirdAdresDaoImpl();
	Check check = new Check();
	DTO dto = new DTO();
	int klant_id;
	Scanner input = new Scanner(System.in);

	public void toonMenu() {
			
		logger.info("applicatielogica van AdresMenu() methode wordt aangeroepen");
		
		Adres adres = null;
		Adres returnMethode = null;

	    try {
	    	int keuze = input.nextInt();
	    	DaoImplKeuze daoKeuze = new DaoImplKeuze();
	    	AdresDaoImpl dbAdres = daoKeuze.AdresDaoImplKeuze();

	    	if(DBKeuzeMenu.getDBKeuze()==1){
				dbAdres = new MySQLAdresDaoImpl();
			}else if(DBKeuzeMenu.getDBKeuze()==2){
				dbAdres = new FireBirdAdresDaoImpl();
			}
	    	
			switch (keuze) {
            	case 1://Create --> adres
            		System.out.println("Voer het nieuw toetevoegen adres in: ");
            			adres = dto.createAdresObject();
            				 returnMethode = dbAdres.insert(adres);
            				 System.out.println("Adres: " + returnMethode + " is ingevoerd in de database");
                    KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
                    klasseselectiemenu.viewAdresMenu();                    		
            		toonMenu();
            		break;
                
            	case 2://Read --> alle adresen
            		dbAdres.readAllAdresses();
                    KlasseSelectieMenu klasseselectiemenu2 = new KlasseSelectieMenu();
                    klasseselectiemenu2.viewAdresMenu();                    		
            		toonMenu();
            		break;
                
            	case 3://Read --> straatnaam
            		System.out.println("Voer de straatnaam in waarop u de tabel wil doorzoeken: ");
            			String straatnaam = input.next();
            			while (straatnaam.length() > 26){
            				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
            					straatnaam = input.next();
            			}
            			System.out.println(dbAdres.searchAdres(straatnaam));
                        KlasseSelectieMenu klasseselectiemenu3 = new KlasseSelectieMenu();
                        klasseselectiemenu3.viewAdresMenu();                    		
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
            			System.out.println(dbAdres.searchAdres(postcode, huisnummer));
                        KlasseSelectieMenu klasseselectiemenu4 = new KlasseSelectieMenu();
                        klasseselectiemenu4.viewAdresMenu();                    		
                		toonMenu();
                		break;
            	
            	case 5: //Read --> alle adressen bij klantnummer
            		System.out.println("Voer klantnummer in waarvan u alle adressen wilt weergeven: ");
            				klant_id = input.nextInt();            		
            				System.out.println(dbAdres.readAdressesFromKlant(klant_id));
                            KlasseSelectieMenu klasseselectiemenu5 = new KlasseSelectieMenu();
                            klasseselectiemenu5.viewAdresMenu();                    		
                    		toonMenu();
                    		break;
            		
            	case 6://Update --> adres
            		System.out.println("Voer de gegevens in van het bij te werken adres ");
            			adres = dto.createAdresObject();
            			returnMethode = dbAdres.updateAdres(adres);
                        System.out.println("Adres: " + returnMethode + " is succesvol verandert in de database " );
            			KlasseSelectieMenu klasseselectiemenu6 = new KlasseSelectieMenu();
                        klasseselectiemenu6.viewAdresMenu();                    		
                		toonMenu();
                		break;
            	
            	case 7://Delete --> adres
            		System.out.println("Voer klantnummer van het te verwijderen adres in: ");
            			klant_id = input.nextInt();
            			adres = new Adres(klant_id, "-", "-", "-", 1, "-");
            			returnMethode = dbAdres.deleteAdres(adres);
            			System.out.println("Adres: "+ returnMethode + "is succesvol verwijdert uit de database ");
                        KlasseSelectieMenu klasseselectiemenu7 = new KlasseSelectieMenu();
                        klasseselectiemenu7.viewAdresMenu();                    		
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