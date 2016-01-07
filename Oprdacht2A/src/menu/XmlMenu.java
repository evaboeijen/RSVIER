package menu;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Klant;
import dao.KlantDaoXml;
import service.DTO;

public class XmlMenu {
	
	KlantDaoXml klantDaoXml = new KlantDaoXml();
	Klant klant = new Klant();
	DTO dto = new DTO();
		
	private static final Logger logger = LoggerFactory.getLogger(XmlMenu.class);
	
	public void toonMenu() {
	    System.out.println("\t----------------------");
	    System.out.println("\tXML Menu for 'Klant'");
	    System.out.println("\t----------------------");
	    System.out.println("1. Create nieuwe klant");
	    System.out.println("2. Read alle klanten");
	    System.out.println("3. Update een bestaande klant");
	    System.out.println("4. Delete een klant");
	    System.out.println("5. Terug naar het hoofdmenu");
	    System.out.println("6. Terug naar datasourceselectie");
	    System.out.println("7. Stoppen");
	    System.out.print("Voer optie in en druk op Enter:");

	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:	            		
            		klant = dto.createKlantObject();
    				klantDaoXml.create(klant);   
    				toonMenu();
            		break;
                
            	case 2:
            		klantDaoXml.read();   
            		toonMenu();
            		break;
                
            	case 3:
            		System.out.println();
    				System.out.println("Voer het klant id in dat je wil updaten en druk op Enter: ");
    				System.out.println();
    				int klant_id = input.nextInt();            		            		
    				klantDaoXml.update(klant_id);   
            		toonMenu();
            		break;
                	
            	case 4:
            		System.out.println();
    				System.out.println("Voer het klant id in van de klant die je wil deleten en druk op Enter: ");
    				System.out.println();
    				int klant_id_to_delete = input.nextInt();            		            		
    				klantDaoXml.delete(klant_id_to_delete);   
            		toonMenu();
            		break;
            		
            	case 5:
            		HoofdMenu hoofdmenu = new HoofdMenu();
            		hoofdmenu.toonMenu();
            		break;
            		
            	case 6:
            		DBKeuzeMenu dbKeuzeMenu = new DBKeuzeMenu();
            		dbKeuzeMenu.toonMenu();
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
