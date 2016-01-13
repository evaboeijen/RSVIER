package menu;

import java.io.File;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Klant;
import dao.KlantDaoXml;
import service.Check;
import service.DTO;

public class XmlMenu {
	
	KlantDaoXml klantDaoXml = new KlantDaoXml();
	Klant klant = new Klant();
	DTO dto = new DTO();
	Check check = new Check();
	int klant_id_final = 0;
	File file = new File("");
		
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
            		System.out.println();
                	System.out.println();
                	System.out.println("----------------------------------------------------------");
                	System.out.println("Er zal een klant?.xml bestand aangemaakt worden, voor de nieuwe klant in uw project folder.");
                	System.out.println("? staat voor het klant_id van de nieuwe klant.");
                	System.out.println("----------------------------------------------------------");
                	System.out.println();
                	System.out.println();
                	
            		klant = dto.createKlantObject();
    				klantDaoXml.create(klant);   
    				toonMenu();
            		break;
                
            	case 2:
            		System.out.println(klantDaoXml.read());   
            		toonMenu();
            		break;
                
            	case 3:
            		System.out.println();
    				System.out.println("Voer het klant id in dat je wil updaten en druk op Enter: ");
    				klant_id_final = input.nextInt();
            		
            		file = new File("klant" + klant_id_final  + ".json");
            		
            		while(!file.exists()) {
            			System.out.println("Het door u opgegeven klant_id bestaat niet.");
            			System.out.println("Voer een ander klant_id in en druk op Enter.");
            			klant_id_final= input.nextInt(); 
            			file = new File("klant" + klant_id_final + ".json");
            		}
            		
    				klant = dto.createKlantObject();
    				klant.setKlant_id(klant_id_final);
    				
    				klantDaoXml.update(klant); 
    							
    				System.out.println();
    				System.out.println("De update is voltooid, de wijzigingen zijn opgeslagen.");
    				System.out.println();
    				
            		toonMenu();
            		break;
                	
            	case 4:
            		System.out.println();
    				System.out.println("Voer het klant id in van de klant die je wil deleten en druk op Enter: ");
    				System.out.println();
    				klant_id_final = input.nextInt();
            		
            		
            		file = new File("klant" + klant_id_final  + ".json");
            		
            		while(!file.exists()) {
            			System.out.println("Het door u opgegeven klant_id bestaat niet.");
            			System.out.println("Voer een ander klant_id in en druk op Enter.");
            			klant_id_final= input.nextInt(); 
            			file = new File("klant" + klant_id_final + ".json");
            		} 
            		
    				klantDaoXml.delete(klant_id_final);   
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
