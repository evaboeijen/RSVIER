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
	    System.out.println("\t-------------------");
	    System.out.println("\tDatasourceselectie");
	    System.out.println("\t-------------------");
	    System.out.println("MySQL");
	    System.out.println("Firebird");
	    System.out.println("JSON");
	    System.out.println("XML\n");
	    	   	    
	    System.out.println("Stoppen\n");
	    System.out.print("Typ de naam van de door u gewenste datasource in zonder hoofdletters en druk op Enter:\n");
            
	    
	    
			String keuze = input.next();
			 
			switch (keuze) {
            	case "mysql":
            		dbKeuze = 1;
            		inloggen.toonMenu();
            		break;
            	case "firebird":
            		dbKeuze = 2;
            		inloggen.toonMenu();
            		break;
            	case "json":
            		JsonMenu jsonMenu = new JsonMenu();
            		jsonMenu.toonMenu();
            		break;
            	case "xml":
            		XmlMenu xmlMenu = new XmlMenu();
            		xmlMenu.toonMenu();
            		break;
            		
            	case "stoppen":
            		System.exit(0);
            		break;
            		
            	default:
            		System.out.println("Uw keuze was ongeldig!\n er wordt verder gegaan met de default datasource: MySQL");
            		dbKeuze = 1;
            		inloggen.toonMenu();
           }
            		
}
}
