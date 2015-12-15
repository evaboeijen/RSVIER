package menu;

import java.util.Scanner;

public class DBKeuzeMenu {
	DBConnectivityManagement inloggen = new DBConnectivityManagement();
	int keuze = 0;
	static int dbKeuze = 0;
	Scanner input = new Scanner(System.in);
	
	
	public static int getDBKeuze() {
		return dbKeuze;
	}
	
	public void toonMenu() {
	    System.out.println("\t-------------");
	    System.out.println("\tDatabasekeuze");
	    System.out.println("\t-------------");
	    System.out.println("1. MySQL");
	    System.out.println("2. Firebird");
	    
	    System.out.println("10. Stoppen");
	    System.out.print("Voer optie in en druk op Enter:");
            
	    
	    
			int keuze = input.nextInt();
			 
			switch (keuze) {
            	case 1:
            		dbKeuze = 1;
            		inloggen.toonMenu();
            		break;
            	case 2:
            		dbKeuze = 2;
            		inloggen.toonMenu();
            		break;
            		
            	case 10:
            		System.exit(0);
            		break;
            		
            	default:
            		dbKeuze = 1;
            		inloggen.toonMenu();
            		
            
            	
           }
            		
}
}
