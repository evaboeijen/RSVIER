package menu.klasseselectie;

import java.util.List;
import java.util.Scanner;
import dao.*;
import menu.HoofdMenu;
import menu.InEnUitLoggen;
import business.*;

public class BestellingMenu extends InEnUitLoggen {
	
	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tBestelling Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Create nieuwe bestelling voor nieuwe klant");  
	    System.out.println("2. Read alle bestellingen");  
	    System.out.println("3. Update een bestaande bestelling door toevoegen van artikelen");  
	    System.out.println("4. Delete een bestelling voor een bepaalde klant");  
	    

	    // etcetera
	    	    
	    System.out.println("5. Terug naar het vorige menu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("6. Terug naar het hoofdmenu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("7. Stoppen"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.print("Voer optie in en druk op Enter:");
	    
	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		/* Bestelling bestelling = new Bestelling(1000, 1000, 1000, "test artikel", 1000, 1000.50, 2000, "test artikel2", 2000, 2000.50, 3000, "test artikel3", 3000, 3000.50);	
            		BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
            		bestellingDaoImpl.create(bestelling);         	*/ 	
            		System.out.println(this.getConnectionStatus());
            		break;
                
            	case 2:
            		InEnUitLoggen inloggen = new InEnUitLoggen();
            		inloggen.connectToDBWithDefaultData();            		
            		System.out.println(inloggen.getConnectionStatus());
            		if (inloggen.getConnectionStatus() != null) {           			
            			BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
            			System.out.println("nieuwe BestellingDaoImpl object geinstantieerd");
            			List<Bestelling> lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
            			System.out.println();
            			System.out.println();	
            		
            			System.out.println("Overzicht van alle bestellingen: ");
            			System.out.println("=================================");
            		
            			for (Bestelling overzicht : lijst) {
            				System.out.println("Klantnummer : " + overzicht.getKlant_id() + ". Ordernummer : " + overzicht.getBestelling_id());
            				System.out.println("---------------------------------------------");
            				System.out.println("Artikelnummer: " + overzicht.getArtikel1_id() + ". Artikelnaam: " + overzicht.getArtikel1_naam() + ". Aantal: "+ overzicht.getArtikel1_aantal() + ". Prijs: " + overzicht.getArtikel1_prijs());
            				System.out.println("Artikelnummer: " + overzicht.getArtikel2_id() + ". Artikelnaam: " + overzicht.getArtikel2_naam() + ". Aantal: "+ overzicht.getArtikel2_aantal() + ". Prijs: " + overzicht.getArtikel2_prijs());
            				System.out.println("Artikelnummer: " + overzicht.getArtikel3_id() + ". Artikelnaam: " + overzicht.getArtikel3_naam() + ". Aantal: "+ overzicht.getArtikel3_aantal() + ". Prijs: " + overzicht.getArtikel3_prijs());
            				System.out.println();
            				System.out.println();	
            			}
            			
            		}
            		break;
                
            	case 3:
            		// etcetera
            		break;
                	
            	case 4:
            		// etcetera
            		break;
            
            	case 5:
            		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
            		klasseselectiemenu.toonMenu();
            		break;
            		
            	case 6:
            		HoofdMenu hoofdmenu = new HoofdMenu();					
            		hoofdmenu.toonMenu(); 
            		break;
            		
            	case 7:
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

