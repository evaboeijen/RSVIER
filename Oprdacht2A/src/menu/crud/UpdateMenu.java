package menu.crud;

import java.util.Scanner;
import dao.*;
import menu.*;
import menu.klasseselectie.*;
import business.*;

public class UpdateMenu {
	

	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tUpdate Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Update klant");
	    System.out.println("2. Update adres");
	    System.out.println("3. Update bestelling");
   
	    	    
	    System.out.println("10. Terug naar het vorige menu"); 
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    System.out.print("Voer optie in en druk op Enter:");
	    
	   Scanner input = new Scanner(System.in);		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		KlantDaoImpl updateKlantDaoImpl = new KlantDaoImpl();
    				
    				System.out.println();
    				System.out.println("Voer uw klant id in, en druk op enter: ");
    				System.out.println();
			
    			
    				int huidige_klant_id = input.nextInt();
				
				
    				while (updateKlantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
    					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
    					huidige_klant_id = input.nextInt();
    					System.out.println();
        			}            		
				
    				KlantMenu klantMenu = new KlantMenu();            				
    			    Klant updateKlant = klantMenu.createKlantObject();
    				updateKlant.setKlant_id(huidige_klant_id);
    				updateKlantDaoImpl.update(updateKlant);
    				
    				System.out.println("Uw gegevens zijn aanpast");
            		
            		toonMenu();
            		
            		break;
                
            	case 2:
            	
            		AdresDaoImpl updateAdresDaoImpl = new AdresDaoImpl();
            		AdresMenu adresMenu = new AdresMenu();
            		System.out.println("Voer de gegevens in van het bij te werken adres ");
        			Adres updateAdres = adresMenu.createAdresObject();
        			updateAdresDaoImpl.updateAdres(updateAdres);
        			toonMenu();
        		
            		break;
                
            	case 3:
            		ArtikelDaoImpl createArtikelDaoImpl = new ArtikelDaoImpl();
            		ArtikelMenu artikelMenu = new ArtikelMenu();
            		Artikel createArtikel = artikelMenu.createArtikelObject();
            		createArtikelDaoImpl.update(createArtikel); 
            		
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

