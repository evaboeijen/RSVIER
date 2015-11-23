package menu.klasseselectie;

import java.util.List;
import java.util.Scanner;
import business.Artikel;
import business.Bestelling;
import dao.*;
import menu.*;

public class ArtikelMenu {
	ArtikelDao artikelDaoImpl = new ArtikelDaoImpl();
	
public void toonMenu() {
		
	    System.out.println("\t------------");
	    System.out.println("\tArtikel Menu");
	    System.out.println("\t------------");
	   
	    System.out.println("");
	    
	    System.out.println("1. Create:      Creëer een nieuw artikel voor een bestaande bestelling");
	    System.out.println("2. Read:        Lees alle artikelen voor een bestaande bestelling");
	    System.out.println("3. Read:        Zoek artikel in bestaande bestelling");
	    System.out.println("4. Update:      Voeg artikel toe aan bestaande bestelling");
	    System.out.println("5. Delete:      Verwijder een artikel van een bestaande bestaande");    

	    System.out.println("");
	    
	    System.out.println("10. Terug naar het vorige menu"); 	// waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("11. Terug naar het hoofdmenu"); 	// waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("12. Stoppen"); 						// waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    
	    System.out.println("");
	    
	    System.out.println("Voer optie in en druk op Enter:");
	    
	    try (Scanner input = new Scanner(System.in);) {		     
        
	    
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		
               		BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
        			
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
            		
            		
            		System.out.println("Voer bestellingnummer van bestelling waaraan je een nieuw artikel wil toevoegen: ");
            		Scanner input2 = new Scanner(System.in);
            		int gewensteBestellingnummer = input2.nextInt();           		
        			while (bestellingDaoImpl.checkBestelling_id(gewensteBestellingnummer)!= true) { 
        				System.out.print("\nVoer een ander bestellingnummer in: ");
        				gewensteBestellingnummer = input2.nextInt();
        				
        				System.out.println();
        			}  
        			
        			Artikel artikel = new Artikel();
        			artikel.setBestelling_id(gewensteBestellingnummer);          		            		            		
            		System.out.print("Klant ID (bedenk er een random): ");
            		int gewensteKlantnummer = input2.nextInt(); 
            		artikel.setKlant_id(gewensteKlantnummer);
            		System.out.print("Wat voor artikelnummer wil je je nieuwe artikel geven?");
            		int gewensteArtikelnummer = input2.nextInt(); 
            		artikel.setArtikel1_id(gewensteArtikelnummer);
            		System.out.print("Wat voor beschrijving wil je je nieuwe artikel geven?");
            		String gewensteArtikelnaam = input2.nextLine(); 
            		artikel.setArtikel1_naam(gewensteArtikelnaam);
            		artikel.setArtikel1_aantal(0);
    	   
            		
            		ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
            		artikelDaoImpl.create(artikel);
            		
            		
            		toonMenu(); 	   			  		          		
            		break; 
            		
            		
        
                
            	case 2:
            		
                
            	case 3:
            	
                	
            	case 4:
    
            	           		
            	case 5:

            		
            	
            	
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

