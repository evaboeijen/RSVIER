package menu;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import menu.crud.*;
import menu.klasseselectie.*;
import menu.reflection.ReflectionMenu;


public class HoofdMenu {
		
	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tHoofd Menu");
	    System.out.println("\t----------");
	    System.out.println("1. C.r.u.d. handelingen");
	    System.out.println("2. Klasseselectie");
	    System.out.println("3. Java Reflection");
	    System.out.println("4. Uitloggen");
	    System.out.println("5. Stoppen");
	    System.out.print("Voer optie in en druk op Enter:");

	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		CrudMenu crudmenu = new CrudMenu();
            		crudmenu.toonMenu();
            		break;
                
            	case 2:
            		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
            		klasseselectiemenu.toonMenu();
            		break;
            		
            	case 3:
            		ReflectionMenu reflectionmenu = new ReflectionMenu();
            		reflectionmenu.toonMenu();
            		break;
                
            	case 4:
            		DBConnectivityManagement.logOut(DBConnectivityManagement.connection);
            		break;
                	
            	case 5:
            		System.out.println("\nTot de volgende keer...");
            		System.exit(1);
            		break;
            
            	default:
            		System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
            		this.toonMenu();
			} 
        
		}
	    
	    
		catch (NoSuchElementException e) {
			System.out.println("\n! Ongeldige optie, het programma zal stoppen !\n");
    		System.exit(0);
            e.printStackTrace();
		} 
	
	
	}
		
}




