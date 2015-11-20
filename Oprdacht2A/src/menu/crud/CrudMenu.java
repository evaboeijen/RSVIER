package menu.crud;

import java.util.Scanner;
import menu.*;

public class CrudMenu {
		
		public void toonMenu() {
		    System.out.println("\t----------");
		    System.out.println("\tCrud Menu");
		    System.out.println("\t----------");
		    System.out.println("1. Create menu");
		    System.out.println("2. Read menu");
		    System.out.println("3. Update menu");
		    System.out.println("4. Delete menu");
		    System.out.println("5. Terug naar het hoofdmenu");
		    System.out.println("6. Stoppen");
		    System.out.print("Voer optie in en druk op Enter:");

		    try (Scanner input = new Scanner(System.in);) {		     
	            
				int keuze = input.nextInt();
			       
				switch (keuze) {
	            	case 1:
	            		CreateMenu createmenu = new CreateMenu();
	            		createmenu.toonMenu();
	            		break;
	                
	            	case 2:
	            		ReadMenu readmenu = new ReadMenu();
	            		readmenu.toonMenu();
	            		break;
	                
	            	case 3:
	            		UpdateMenu updatemenu = new UpdateMenu();
	            		updatemenu.toonMenu();
	            		break;
	                	
	            	case 4:
	            		DeleteMenu deletemenu = new DeleteMenu();
	            		deletemenu.toonMenu();
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

		
