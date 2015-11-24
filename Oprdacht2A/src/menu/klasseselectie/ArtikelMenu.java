package menu.klasseselectie;

import java.util.*;
import business.*;
import dao.*;
import menu.*;

public class ArtikelMenu {
	
	
public Artikel createArtikelObject(){
			Scanner input = new Scanner(System.in);	
	
			BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
			
			System.out.println("Voer uw bestellingnummer in, en druk op enter: ");
			int bestelling_id = input.nextInt();
			       		
			while (bestellingDaoImpl.checkBestelling_id(bestelling_id)!= true) { 
				System.out.println("\nVoer een ander bestellingnummer in: ");
				bestelling_id = input.nextInt();
				
				System.out.println();
			}  
			
				
			Artikel artikel = new Artikel();
			          		            		            		
			System.out.println("Vul uw Klant ID in: ");
			int klant_id = input.nextInt(); 
			
			System.out.println("Vul het artikelnummer in: ");
			int artikel_id = input.nextInt(); 
			
			System.out.println("Vul de naam van het artikel in: ");
			String artikel_naam = input.nextLine(); 
			
			System.out.println("Vul het aantal in, wat u van dit artikel wilt: ");
			int artikel_aantal = input.nextInt();
			
			System.out.println("Wat is de prijs van het artikel: ");
			double artikel_prijs = input.nextDouble();

			artikel.setBestelling_id(bestelling_id);
			artikel.setKlant_id(klant_id);
			artikel.setArtikel1_id(artikel_id);
			artikel.setArtikel1_naam(artikel_naam);
			artikel.setArtikel1_aantal(artikel_aantal);
			artikel.setArtikel1_prijs(artikel_prijs);
			
			return artikel;
		}
	
public void toonMenu() {
		
	    System.out.println("\t------------");
	    System.out.println("\tArtikel Menu");
	    System.out.println("\t------------");
	   
	    System.out.println("");
	    
	    System.out.println("1. Create:      Voeg een nieuw artikel toe aan een bestaande bestelling");
	    System.out.println("2. Read:        Bekijk alle artikelen van uw bestaande bestelling");
	    System.out.println("3. Read:        Zoek een artikel in uw bestaande bestelling");
	    System.out.println("4. Delete:      Verwijder een artikel van een bestaande bestaande");    

	    System.out.println("");
	    
	    System.out.println("10. Terug naar het vorige menu"); 	
	    System.out.println("11. Terug naar het hoofdmenu"); 	
	    System.out.println("12. Stoppen"); 						
	    
	    System.out.println("");
	    
	    System.out.println("Voer optie in en druk op Enter:");
	    
	    
	    Scanner input = new Scanner(System.in);    
		int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		
            		
            		ArtikelDaoImpl createArtikelDaoImpl = new ArtikelDaoImpl();
            		Artikel createArtikel = createArtikelObject();
            		createArtikelDaoImpl.update(createArtikel);
            		
            		
            		toonMenu(); 	   			  		          		
            		break; 
            		
    
            	case 2:
            		
            		System.out.println("Voer uw bestellingnummer in, en druk op enter: ");
            		int leesBestelling_id = input.nextInt();
            		
            		ArtikelDaoImpl leesArtikelDaoImpl = new ArtikelDaoImpl();
            		Artikel leesAlleArtikellen = new Artikel();
            		leesAlleArtikellen.setBestelling_id(leesBestelling_id);
            		
            		System.out.println("De volgende artikellen zitten in de ingevoerde bestelling: " + leesBestelling_id);
            		leesArtikelDaoImpl.read(leesAlleArtikellen).toString();
            		
            		toonMenu();
            		break;
                
            		
            	case 3:
            		
            		System.out.println("Voer uw bestellingnummer in, en druk op enter: ");
            		int zoekBestelling_id = input.nextInt();
            		
            		System.out.println("Voer het artikelnummer in, en druk op enter");
            		int zoekArtikel_id = input.nextInt();
            		
            		ArtikelDaoImpl zoekArtikelDaoImpl = new ArtikelDaoImpl();
            		
            		System.out.println("De volgende artikellen zitten in de ingevoerde bestelling: " + zoekBestelling_id);
            		Artikel zoekArtikel = zoekArtikelDaoImpl.readArtikel(zoekBestelling_id, zoekArtikel_id);
            		
            		System.out.println("Artikel ID: " + zoekArtikel.getArtikel1_id() + ". Artikel naam: " + zoekArtikel.getArtikel1_naam() + ". Artikel prijs: " + zoekArtikel.getArtikel1_prijs() + ". Artikel aantal: " + zoekArtikel.getArtikel1_aantal());
            		
            		toonMenu();
            		break;
                	
            		
            	case 4:
            		System.out.println("Voer uw bestellingnummer in, en druk op enter: ");
            		int deleteBestelling_id = input.nextInt();
            		
            		System.out.println("Voer het artikelnummer in, en druk op enter");
            		int deleteArtikel_id = input.nextInt();
            	           		
            		ArtikelDaoImpl deleteArtikelDaoImpl = new ArtikelDaoImpl();
            		deleteArtikelDaoImpl.delete(deleteBestelling_id, deleteArtikel_id);

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
	
}

