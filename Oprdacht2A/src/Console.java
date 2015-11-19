import java.util.*;
import java.io.*;
import menu.*;
import menu.crud.CrudMenu;
import menu.klasseselectie.KlasseSelectieMenu;
import dao.*;


public class Console {

	public static void main(String[] args) throws IOException {
							
		DatabaseConnection.initializeDB();
		
		HoofdMenu hoofdmenu = new HoofdMenu();
		CrudMenu crudmenu = new CrudMenu();
		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
		
		hoofdmenu.printMenu(); 
		try (Scanner input = new Scanner(System.in);) {		     
               
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		crudmenu.printMenu();
            		break;
                
            	case 2:
            		klasseselectiemenu.printMenu();
            		break;
                
            	case 3:
            		//Connection.logOut(connection);
            		break;
                	
            	case 4:
            		System.out.println("Tot de volgende keer...");
            		System.exit(1);
            		break;
            
            	default:
            		System.out.println("Ongeldige optie");
			} 
        
		}
		
		finally {
			// zinnige code			
		}
		
		
	} 
	

		// inlogscherm wordt getoond met invoerveld voor databaseurl, user, password,
		// waarna de user op het hoofdmenu aankomt
				
		// hoofdmenu : 
		// 1 crud handelingen
		// 2 klasse selectie
		// 3 uitloggen
		// 4 stoppen
				
		// indien user optie 1 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "crud handeling" :
		// 1 create
		// 2 read
		// 3 update
		// 4 delete
		// 5 terug naar het hoofdmenu
		
		// indien user optie 2 heeft gekozen in hoofdmenu, volgt het :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 3 heeft gekozen in hoofdmenu, volgt weer het inlogscherm :
		//
		// vervolgmenu "klasse selectie" :
		// 1 klant
		// 2 adres
		// 3 bestelling
		// 4 artikel
		// 5 terug naar het hoofdmenu
		
		// indien user optie 5 heeft gekozen in hoofdmenu, wordt het programmaatje beeindigd 
	
		

		
}


