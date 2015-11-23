package menu.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dao.*;
import menu.HoofdMenu;
import menu.InEnUitLoggen;
import business.*;

public class DeleteMenu  {
	
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
	    
	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		System.out.println();
            		KlantDaoImpl lijst = new KlantDaoImpl();
            		System.out.println("Je kan hier het adres van een bestaande klant wissen.");
            		System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		List <Klant> alleKlanten = lijst.read();
               		
            		
            		System.out.println();          		
            		System.out.println("Van welke klant wil je het adres wissen?");
            		System.out.print("Voer het klant ID in: ");
            		System.out.println(); 
            			                      		
            			try (Scanner input2 = new Scanner(System.in);) {
            				int gewensteKlant_id = input.nextInt();
            				
            				AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
                			while (adresDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
                				System.out.print("\nVoer een ander klantnummer in: ");
                				gewensteKlant_id = input.nextInt();
                				System.out.println();
                			}    
                			
                			Adres teWissenAdres = new Adres();
                			
                			teWissenAdres.setKlant_id(gewensteKlant_id);
                			teWissenAdres.setHuisnummer(0);
                			teWissenAdres.setPostcode("-");
                			teWissenAdres.setStraatnaam("-");
                			teWissenAdres.setToevoeging("-");
                			teWissenAdres.setWoonplaats("-");
                	               			
                			adresDaoImpl.deleteAdres(teWissenAdres);
         			}
            		    
            			
                		catch (Exception ex)	 {
                			ex.printStackTrace();                			
                		}
            		
            			           		          		
                
            	case 2:          		
            		BestellingDaoImpl bestellingDaoImpl2 = new BestellingDaoImpl();
        			
            		List<Bestelling> lijst2 = bestellingDaoImpl2.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
            		System.out.println();
            		System.out.println();	
            		
            		System.out.println("Overzicht van alle bestellingen: ");
            		System.out.println("=================================");
            		
            		for (Bestelling overzicht : lijst2) {
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

            		
            		try (Scanner input2 = new Scanner(System.in);) {
        				int gewensteBestelling_id = input.nextInt();
        				
        				BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
            			while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
            				System.out.print("\nVoer een ander bestellingnummer in: ");
            				gewensteBestelling_id = input.nextInt();
            				System.out.println();
            			}      
            		
            			          			               		
            			
            			System.out.println();   
                		System.out.print("Voer het artikel ID in: ");
                		System.out.println();  
                	
            			try (Scanner input3 = new Scanner(System.in);) {
        					int gewensteArtikel_id = input.nextInt();
        				
        					BestellingDaoImpl bestellingDaoImpl3 = new BestellingDaoImpl();
    						while (bestellingDaoImpl3.checkArtikel_id(gewensteArtikel_id)!= true) { 
    							System.out.print("\nVoer een ander artikelummer in: ");
    							gewensteArtikel_id = input.nextInt();
    							System.out.println();
    						}     
    					
    					
    						ArtikelDaoImpl artikelDaoImpl = new ArtikelDaoImpl();
                    	
            					artikelDaoImpl.delete(gewensteBestelling_id, gewensteArtikel_id);
            			
            			}
            		
            		}
        
            		break;
            		
         
            	case 3:
            		System.out.println();           		
            		System.out.println("Je kan hier complete bestellingen verwijderen");
            		System.out.println("Hieronder een overzicht van alle bestellingen: ");
            		
               		BestellingDaoImpl bestellingDaoImpl3 = new BestellingDaoImpl();
        			
            		List<Bestelling> lijst3 = bestellingDaoImpl3.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
            		System.out.println();
            		System.out.println();	
            		
            		System.out.println("Overzicht van alle bestellingen: ");
            		System.out.println("=================================");
            		
            		for (Bestelling overzicht : lijst3) {
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
            		
            		
            		
            		try (Scanner input2 = new Scanner(System.in);) {
        				int gewensteBestelling_id = input.nextInt();
        				
        				BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
            			while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
            				System.out.print("\nVoer een ander bestellingnummer in: ");
            				gewensteBestelling_id = input.nextInt();
            				System.out.println();
            			}            		        
            			          			
            			Bestelling teverwijderenBestelling = new Bestelling();
            			teverwijderenBestelling.setBestelling_id(gewensteBestelling_id);
            		
            			bestellingDaoImpl.delete(teverwijderenBestelling);
            		}
               		break; 
                	
            	case 4:           		
            	    System.out.println();
            		KlantDaoImpl lijst4 = new KlantDaoImpl();
            		System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		List <Klant> alleKlanten2 = lijst4.read();
               		
            		
            		System.out.println();          		
            		System.out.print("Voer het klant ID van de klant die je wil wissen: ");
            		System.out.println(); 
            			                      		
            			try (Scanner input2 = new Scanner(System.in);) {
            				int gewensteKlant_id = input.nextInt();
            				
            				AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
                			while (adresDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
                				System.out.print("\nVoer een ander klantnummer in: ");
                				gewensteKlant_id = input.nextInt();
                				System.out.println();
                			}    
                			
                			
                			KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
                			Klant teWissenKlant = new Klant();
                			
                			teWissenKlant.setKlant_id(gewensteKlant_id);
            
                			klantDaoImpl.delete(teWissenKlant);
         			}
            		    
            			
                		catch (Exception ex)	 {
                			ex.printStackTrace();                			
                		}
            		
            		
            		
            		
            		
            		
            		
            		
            		
            		
            		
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
			} 
			
    		input.close();
			DeleteMenu deletemenu = new DeleteMenu();
    		deletemenu.toonMenu();
        
		}
		
		finally {
			// zinnige code			
		}	

	}	
}

