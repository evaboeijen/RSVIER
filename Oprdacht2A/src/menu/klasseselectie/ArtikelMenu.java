package menu.klasseselectie;

import java.util.*;
import business.*;
import dao.*;
import menu.*;

public class ArtikelMenu {
	
	
public Artikel createArtikelObject(){
			Scanner input = new Scanner(System.in);	
		
			Artikel artikel = new Artikel();
			
			
			System.out.println("Vul de naam van het artikel in: ");
			String artikel_naam = input.nextLine(); 
			
			System.out.println("Wat is de prijs van het artikel: ");
			double artikel_prijs = input.nextDouble();

		
			artikel.setArtikel_naam(artikel_naam);
			artikel.setArtikel_prijs(artikel_prijs);
			
			return artikel;
		}
	
public void toonMenu() {
		
	    System.out.println("\t------------");
	    System.out.println("\tArtikel Menu");
	    System.out.println("\t------------");
	   
	    System.out.println("");
	    
	    System.out.println("1. Create:      Voeg een nieuw artikel toe aan het assortiment");
	    System.out.println("2. Read:        Bekijk alle artikelen uit het assortiment");
	    System.out.println("3. Update:      Verander de gegevens van een bestaand artikel");
	    System.out.println("4. Delete:      Verwijder een artikel uit het assortiment");    

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
            		createArtikelDaoImpl.create(createArtikel);
            		
            		
            		toonMenu(); 	   			  		          		
            		break; 
            		
    
            	case 2:
            		
            		          		
            		ArtikelDaoImpl leesArtikelDaoImpl = new ArtikelDaoImpl();
            		            		
            		System.out.println("De volgende artikellen zitten in het assortiment: ");
            		leesArtikelDaoImpl.read();
            		
            		toonMenu();
            		break;
                
            		
            	case 3:
            		   		
            		System.out.println("U kunt de artikelgegevens wijzigen. Voer het artikelnummer in, en druk op enter");
            		int updateArtikel_id = input.nextInt();
            		
            		ArtikelDaoImpl updateArtikelDaoImpl = new ArtikelDaoImpl();
            		Artikel updateArtikel = createArtikelObject();
            		updateArtikel.setArtikel_id(updateArtikel_id);
            		
            		updateArtikelDaoImpl.update(updateArtikel);
            		
            		System.out.println("De artikel gegevens zijn aangepast");
            		
            		toonMenu();
            		break;
                	
            		
            	case 4:
            		
            		System.out.println("Voer het artikelnummer in, en druk op enter");
            		int deleteArtikel_id = input.nextInt();
            		Artikel deleteArtikel = new Artikel();
            		deleteArtikel.setArtikel_id(deleteArtikel_id);
            	           		
            		ArtikelDaoImpl deleteArtikelDaoImpl = new ArtikelDaoImpl();
            		deleteArtikelDaoImpl.delete(deleteArtikel);

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

