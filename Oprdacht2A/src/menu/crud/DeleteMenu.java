package menu.crud;

import java.util.Scanner;

public class DeleteMenu {
	

	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tDelete Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Delete klant");
	    System.out.println("2. Delete adres");
    
	    // etcetera
	    	    
	    System.out.println("<nummer>. Terug naar het vorige menu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("<nummer>. Terug naar het hoofdmenu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("<nummer>. Stoppen"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.print("Voer optie in en druk op Enter:");
	    
	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		/* hier zou dan de aanroep naar de delete methode voor Klant moeten komen.
            		hier kan ook gelijk de code komen voor input van de user 
            		(voor klant_id,  etcetera)
            		of wellicht kan er voor gekozen worden om hier nog een andere
            		subklasse aan te roepen waarin de delete methode voor Klant 
            		wordt aangeroepen met daarin ook de code voor user input? */
            		break;
                
            	case 2:
            		/* hier zou dan de aanroep naar de delete methode voor Adres moeten komen.
            		hier kan ook gelijk de code komen voor input van de user 
            		(voor klant_id etcetera)
            		of wellicht kan er voor gekozen worden om hier nog een andere
            		subklasse aan te roepen waarin de delete methode voor Adres 
            		wordt aangeroepen met daarin ook de code voor user input? */
            		break;
                
            	case 3:
            		// etcetera
            		break;
                	
            	case 4:
            		// etcetera
            		break;
            
            	// voeg cases toe evenredig aan het aantal menu opties
            		
            		
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

