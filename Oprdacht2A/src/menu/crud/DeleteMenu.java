package menu.crud;

import java.util.List;
import java.util.Scanner;
import dao.*;
import menu.HoofdMenu;
import business.*;

public class DeleteMenu  {
	
	// declaratie en initialisering van gemeenschappelijk objecten en primitieve variabelen 
	// die zullen worden geraadpleegd door
	// de diverse case keuzes in het switch block in de methode toonMenu()	
	Scanner input = new Scanner(System.in);
	List <Klant> alleKlanten = null;
	int gewensteKlant_id = 0;
	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
	Adres teWissenAdres = new Adres();
	BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
	List<Bestelling> lijst = null;
	KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
	int gewensteBestelling_id = 0;
	ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
	Klant teWissenKlant = new Klant();
	
	
	public void toonMenu() {
	    System.out.println("\t-------------");
	    System.out.println("\tDelete  Menu");
	    System.out.println("\t-------------");
	    System.out.println("1. Delete het adres van een bestaande klant");  
	    System.out.println("2. Delete een artikel van een bestaande bestelling");  
	    System.out.println("3. Delete een bestaande bestelling");  
	    System.out.println("4. Delete een klant");  		 
	    System.out.println();	    
	    System.out.println("10. Terug naar het vorige menu");
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    System.out.println();
	    System.out.print("Voer optie in en druk op Enter:");
	       
            
		int keuze = input.nextInt();
		       
		switch (keuze) {
		
        	case 1:

        		System.out.println();
            	
            	System.out.println("Je kan hier het adres van een bestaande klant wissen.");
            	System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            	alleKlanten = klantDaoImpl.read();
               		          		
            	System.out.println();          		
            	System.out.println("Van welke klant wil je het adres wissen?");
            	System.out.print("Voer het klant ID in: ");
            	System.out.println(); 
            			                      		
            
            	gewensteKlant_id = input.nextInt();
            				
            	
            	while (adresDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
            		System.out.print("\nVoer een ander klantnummer in: ");
                	gewensteKlant_id = input.nextInt();
                	System.out.println();
            	}    
                			 			
            	teWissenAdres.setKlant_id(gewensteKlant_id);
            	teWissenAdres.setHuisnummer(0);
            	teWissenAdres.setPostcode("-");
            	teWissenAdres.setStraatnaam("-");
            	teWissenAdres.setToevoeging("-");
            	teWissenAdres.setWoonplaats("-");
                	               			
                adresDaoImpl.deleteAdres(teWissenAdres);
            
            	toonMenu();
            	break;
            			           		          		
                
            case 2:          		
            		       			
            	lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
            	System.out.println();
            	System.out.println();	
            		
            	System.out.println("Overzicht van alle bestellingen: ");
            	System.out.println("=================================");
            		
            	for (Bestelling overzicht : lijst) {
            		System.out.println("Klantnummer : " + overzicht.getKlant_id() + ". Ordernummer : " + overzicht.getBestelling_id());
            		System.out.println("---------------------------------------------");
            		System.out.println("Artikelnummer: " + overzicht.getArtikel1_id() + ". Artikelnaam: " + overzicht.getArtikel1_naam() + ". Aantal: "+ overzicht.getArtikel1_aantal() + ". Prijs: " + overzicht.getArtikel1_prijs());
            		System.out.println("Artikelnummer: " + overzicht.getArtikel2_id() + ". Artikelnaam: " + overzicht.getArtikel2_naam() + ". Aantal: "+ overzicht.getArtikel2_aantal() + ". Prijs: " + overzicht.getArtikel2_prijs());
            		System.out.println("Artikelnummer: " + overzicht.getArtikel3_id() + ". Artikelnaam: " + overzicht.getArtikel3_naam() + ". Aantal: "+ overzicht.getArtikel3_aantal() + ". Prijs: " + overzicht.getArtikel3_prijs());
            		System.out.println();
            		System.out.println();	
            	}
            			   
            	System.out.println();          		
            	System.out.print("Voer het bestelling ID in van de bestelling waaruit je het artikel wil verwijderen: ");
            	System.out.println();          		

        		gewensteBestelling_id = input.nextInt();
        				
        		
            	while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
            		System.out.print("\nVoer een ander bestellingnummer in: ");
            		gewensteBestelling_id = input.nextInt();
            		System.out.println();
            	}      
            		
            	System.out.println();   
                System.out.print("Voer het artikel ID in: ");
                System.out.println();  
                	
        		int gewensteArtikel_id = input.nextInt();
        							
    			while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
    				System.out.print("\nVoer een ander artikelummer in: ");
    				gewensteArtikel_id = input.nextInt();
    				System.out.println();
    			}     
    					
    			artikelDaoImpl.delete(gewensteBestelling_id, gewensteArtikel_id);
            			
            	toonMenu();
            	break;
            		
            	
         
            case 3:
            	
            	System.out.println();           		
            	System.out.println("Je kan hier complete bestellingen verwijderen");
            	System.out.println("Hieronder een overzicht van alle bestellingen: ");
            		
            	lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
            	System.out.println();
            	System.out.println();	
            		
            	System.out.println("Overzicht van alle bestellingen: ");
            	System.out.println("=================================");
            		
            	for (Bestelling overzicht : lijst) {
            		System.out.println("Klantnummer : " + overzicht.getKlant_id() + ". Ordernummer : " + overzicht.getBestelling_id());
            		System.out.println("---------------------------------------------");
            		System.out.println("Artikelnummer: " + overzicht.getArtikel1_id() + ". Artikelnaam: " + overzicht.getArtikel1_naam() + ". Aantal: "+ overzicht.getArtikel1_aantal() + ". Prijs: " + overzicht.getArtikel1_prijs());
            		System.out.println("Artikelnummer: " + overzicht.getArtikel2_id() + ". Artikelnaam: " + overzicht.getArtikel2_naam() + ". Aantal: "+ overzicht.getArtikel2_aantal() + ". Prijs: " + overzicht.getArtikel2_prijs());
            		System.out.println("Artikelnummer: " + overzicht.getArtikel3_id() + ". Artikelnaam: " + overzicht.getArtikel3_naam() + ". Aantal: "+ overzicht.getArtikel3_aantal() + ". Prijs: " + overzicht.getArtikel3_prijs());
            		System.out.println();
            		System.out.println();	
            	}
            	
            	System.out.println();          		
            	System.out.print("Voer het bestelling ID in van de bestelling die je wil verwijderen: ");
            	System.out.println(); 
    
            	int gewensteBestelling_id = input.nextInt();
        				
        	
            	while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
            		System.out.print("\nVoer een ander bestellingnummer in: ");
            		gewensteBestelling_id = input.nextInt();
            		System.out.println();
            	}            		        
            			          			
            	Bestelling teverwijderenBestelling = new Bestelling();
            	teverwijderenBestelling.setBestelling_id(gewensteBestelling_id);         		
            	bestellingDaoImpl.delete(teverwijderenBestelling);
            
            	toonMenu();
            	break; 
                	
            	
            case 4:           		
            	System.out.println();          
            	System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            	alleKlanten = klantDaoImpl.read();
               		            		
            	System.out.println();          		
            	System.out.print("Voer het klant ID van de klant die je wil wissen: ");
            	System.out.println(); 
            
            	int gewensteKlant_id = input.nextInt();
            				
            	while (adresDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
                	System.out.print("\nVoer een ander klantnummer in: ");
                	gewensteKlant_id = input.nextInt();
                	System.out.println();
                }    
                			
                KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
                
                teWissenKlant.setKlant_id(gewensteKlant_id);
                klantDaoImpl.delete(teWissenKlant);
       
                toonMenu();
                break;
            		            
            		
            case 10:
            	CrudMenu crudmenu = new CrudMenu();
            	crudmenu.toonMenu();
            	break;
            		
            case 11:
            	HoofdMenu hoofdmenu = new HoofdMenu();					
            	hoofdmenu.toonMenu(); 
            	break;
            		
            case 12:
            	System.out.println("\nTot de volgende keer...");
            	System.exit(1);
            	break;
            		
            		
            default:
            	System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
        		DeleteMenu deletemenu = new DeleteMenu();
            	deletemenu.toonMenu();
	
		} // end of switch
		
 
	} // end of toonMenu() method
		
} // end of DeleteMenu class

