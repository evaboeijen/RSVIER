package menu.klasseselectie;

import java.util.Scanner;
import menu.*;

public class KlasseSelectieMenu {
		
		public void toonMenu() {
		    System.out.println("\t----------");
		    System.out.println("\tKlasseselectie Menu");
		    System.out.println("\t----------");
		    System.out.println("1. Klant");
		    System.out.println("2. Adres");
		    System.out.println("3. Bestelling");
		    System.out.println("4. Artikel");
		    System.out.println("5. Terug naar het hoofdmenu");
		    System.out.println("6. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		KlantMenu klantmenu = new KlantMenu();
	            		klantmenu.toonMenu();
	            		break;
	                
	            	case 2:
	            		AdresMenu adresmenu = new AdresMenu();
	            		adresmenu.toonMenu();
	            		break;
	                
	            	case 3:
	            		BestellingMenu bestellingmenu = new BestellingMenu();
	            		bestellingmenu.toonMenu();
	            		break;
	                	
	            	case 4:
	            		ArtikelMenu artikelmenu = new ArtikelMenu();
	            		artikelmenu.toonMenu();
	            		break;
	            		
	            	case 5:
	            		HoofdMenu hoofdmenu = new HoofdMenu();
	            		hoofdmenu.toonMenu();
	            		break;
	            		
	            	case 6:
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

		
