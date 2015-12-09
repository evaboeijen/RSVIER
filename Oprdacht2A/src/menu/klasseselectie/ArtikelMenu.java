package menu.klasseselectie;

import java.util.*;
import business.*;
import dao.*;
import menu.*;
import service.DTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArtikelMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtikelMenu.class);
	
	ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
	Artikel artikel = new Artikel();
	DTO dto = new DTO();
	int artikel_id = 0;
	
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
		
		logger.info("Gebruiker koos voor optie:  " + keuze);
		       
			switch (keuze) {
            	case 1:
            		artikel = dto.createArtikelObject();
            		artikelDaoImpl.create(artikel);
            		
            		
            		toonMenu(); 	   			  		          		
            		break; 
            		
    
            	case 2:
            		            		
            		System.out.println("De volgende artikellen zitten in het assortiment: ");
            		artikelDaoImpl.read();
            		
            		toonMenu();
            		break;
                
            		
            	case 3:
            		   		
            		System.out.println("U kunt de artikelgegevens wijzigen. Voer het artikelnummer in, en druk op enter");
            		artikel_id = input.nextInt();
            		
            	
            		artikel = dto.createArtikelObject();
            		artikel.setArtikel_id(artikel_id);
            		
            		artikelDaoImpl.update(artikel);
            		
            		System.out.println("De artikel gegevens zijn aangepast");
            		
            		toonMenu();
            		break;
                	
            		
            	case 4:
            		
            		System.out.println("Voer het artikelnummer in, en druk op enter");
            		artikel_id = input.nextInt();
            		artikel.setArtikel_id(artikel_id);
            	           		
            		artikelDaoImpl.delete(artikel);

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

