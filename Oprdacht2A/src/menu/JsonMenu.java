package menu;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonMenu {
			
	private static final Logger logger = LoggerFactory.getLogger(JsonMenu.class);
	
	public void toonMenu() {
	    System.out.println("\t----------------------");
	    System.out.println("\tJson Menu for 'Klant'");
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
    				System.out.println("To be implemented by Jesse");
    				System.out.println();
            		toonMenu();
            		break;
                
            	case 2:
            		System.out.println();
    				System.out.println("To be implemented by Jesse");
    				System.out.println();
            		toonMenu();
            		break;
                
            	case 3:
            		System.out.println();
    				System.out.println("To be implemented by Jesse");
    				System.out.println();
            		toonMenu();
            		break;
                	
            	case 4:
            		System.out.println();
    				System.out.println("To be implemented by Jesse");
    				System.out.println();
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
