package menu.klasseselectie;

import java.util.*;
import business.*;
import dao.*;
import menu.*;
import service.Check;
import service.DTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArtikelMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtikelMenu.class);
	
	DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
	ArtikelDaoImpl artikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze();
	Artikel artikel = new Artikel();
	Check check = new Check();
	DTO dto = new DTO();
	int artikel_id = 0;
	
	
public void toonMenu() {
		
		logger.info("applicatielogica van ArtikelMenu() methode wordt aangeroepen");
	    
	    Scanner input = new Scanner(System.in);    
		int keuze = input.nextInt();
		
		logger.info("Gebruiker koos voor optie:  " + keuze);
		       
			switch (keuze) {
            	case 1:
            		artikel = dto.createArtikelObject();
            		artikelDaoImpl.create(artikel);
            		          		
                    KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
                    klasseselectiemenu.viewArtikelMenu();                    		
            		toonMenu();
            		break;
            		
    
            	case 2:
            		            		
            		System.out.println("De volgende artikellen zitten in het assortiment: ");
            		artikelDaoImpl.read();
            		
                    KlasseSelectieMenu klasseselectiemenu2 = new KlasseSelectieMenu();
                    klasseselectiemenu2.viewArtikelMenu();                    		
            		toonMenu();
            		break;
                
            		
            	case 3:
            		   		
            		System.out.println("U kunt de artikelgegevens wijzigen. Voer het artikelnummer in, en druk op enter");
            		artikel_id = input.nextInt();
            		
            		while (check.checkArtikel_id(artikel_id)!= true){
	  					System.out.println("Voer het artikelnummer opnieuw in: ");
	  					
	  					artikel_id = input.nextInt();
	  				}
            	
            		artikel = dto.createArtikelObject();
            		artikel.setArtikel_id(artikel_id);
            		
            		artikelDaoImpl.update(artikel);
            		
            		System.out.println("De artikel gegevens zijn aangepast");
            		
                    KlasseSelectieMenu klasseselectiemenu3 = new KlasseSelectieMenu();
                    klasseselectiemenu3.viewArtikelMenu();                    		
            		toonMenu();
            		break;
                	
            		
            	case 4:
            		
            		System.out.println("Voer het artikelnummer in, en druk op enter");
            		artikel_id = input.nextInt();
            		
            		while (check.checkArtikel_id(artikel_id)!= true){
	  					System.out.println("Voer het artikelnummer opnieuw in: ");
	  					
	  					artikel_id = input.nextInt();
	  				}
            		
            		artikel.setArtikel_id(artikel_id);
            	           		
            		artikelDaoImpl.delete(artikel);

                    KlasseSelectieMenu klasseselectiemenu4 = new KlasseSelectieMenu();
                    klasseselectiemenu4.viewArtikelMenu();                    		
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

