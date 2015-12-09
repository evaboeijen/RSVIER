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
import menu.*;
import menu.klasseselectie.BestellingMenu;
import business.*;
import service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeleteMenu  {
	
	private static final Logger logger =  LoggerFactory.getLogger(DeleteMenu.class);
	
	public Connection connection = null;
	
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
	int gewensteKlantID = 0;
	int gewensteArtikel_id = 0;
	String gewensteArtikelNaam = null;
	int gewensteArtikelPrijs = 0;
	int gewensteAantal = 0;
	Artikel artikel = new Artikel(); 
	List<Artikel> alleArtikelen = null;
	Check check = new Check();
	
	
	public void toonMenu() {
	    System.out.println("\t-------------");
	    System.out.println("\tDelete  Menu");
	    System.out.println("\t-------------");
	    System.out.println("1. Delete het adres van een bestaande klant");  
	    System.out.println("2. Delete een artikel van een bestaande bestelling");  
	    System.out.println("3. Delete een bestaande bestelling");
	    System.out.println("4. Delete een artikel uit het assortiment");
	    System.out.println("5. Delete een klant");  		 
	    System.out.println();	    
	    System.out.println("10. Terug naar het vorige menu");
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    System.out.println();
	    System.out.print("Voer optie in en druk op Enter:");
	       
            
		int keuze = input.nextInt();
		       
		logger.info("content of variable 'keuze' is : " + keuze);
		
		
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
            				
            	
            	while (check.checkKlant_id(gewensteKlant_id)!= true) { 
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
            		       			
            	System.out.println();           		
        		System.out.println("Je kan hier artikelen verwijderen van een bestaande bestelling.");
        		System.out.println("Hieronder een overzicht van alle bestellingen en hun artikelen: ");
        		               		       			
        		lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
        		
        		System.out.println();
        		System.out.println();	
        		
        		System.out.println("Overzicht van alle bestellingen: ");
        		System.out.println("=================================");
        		
        		for (Bestelling overzicht : lijst) {
        			System.out.println("Klantnummer : " + overzicht.getKlant_id() + ". Ordernummer : " + overzicht.getBestelling_id());
        			System.out.println("---------------------------------------------");
        			
        			//aanpassing tbv opdracht 5 || AU 26/11/15
        			System.out.println("Artikelnummer: " + overzicht.getArtikel_id() + ". Artikelnaam: " + overzicht.getArtikel_naam() + ". Aantal: "+ overzicht.getArtikel_aantal() + ". Prijs: " + overzicht.getArtikel_prijs());
        			System.out.println();
        			System.out.println();	
        		}
        		
        		System.out.println();          		
        		System.out.print("Voer het bestelling ID in van de bestelling waarvan je artikelen wil verwijderen: ");
        		System.out.println(); 
        		            		           		
    			gewensteBestelling_id = input.nextInt();
    				

        		while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
        			System.out.print("\nVoer een ander bestellingnummer in: ");
        			gewensteBestelling_id = input.nextInt();
        			System.out.println();
        		}            		        
        		
        		try {
        			Connection connection = DBConnectivityManagement.getConnectionStatus();
                	
        			PreparedStatement preparedStatement;
        			preparedStatement = connection.prepareStatement("SELECT Klant_id FROM Bestelling WHERE Bestelling_id = ?");
        			preparedStatement.setInt(1, gewensteBestelling_id);   
        			ResultSet resultSet = preparedStatement.executeQuery();
        			
        			while(resultSet.next()){                                
        				gewensteKlantID = resultSet.getInt("Klant_id");                                                                                                    
        			}
        			resultSet.close();
        			preparedStatement.close();
                         
        		} 
        		
        		catch (SQLException e) {
        			logger.warn("SQL exception voor DeleteMenu case 2");
        			e.printStackTrace();
        		}
        		

        		System.out.print("\nVoer het artikel ID in dat je wil verwijderen van de bestelling (0 is terug naar het vorige menu): ");
        		System.out.println(); 
        		gewensteArtikel_id = input.nextInt();
        		
        		while (bestellingDaoImpl.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) != true) {
        			System.out.print("\nArtikelnummer niet gevonden in desbetreffende bestelling. Voer een ander artikelnummer in: ");
                	gewensteArtikel_id = input.nextInt();
                	if (gewensteArtikel_id == 0) {
                		toonMenu();
                	}
                	System.out.println();
        		}
        		
        		/* while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
        			System.out.print("\nVoer een ander artikelnummer in: ");
                    gewensteArtikel_id = input.nextInt();
                    System.out.println();
                }    */
        		
	
        		try {

    	        	logger.info("Content of connection object is : " + connection);
    	        	Connection connection = DBConnectivityManagement.getConnectionStatus();
    	        	logger.info("Content of connection object is : " + connection);
        					
        			// uitgecomment tbv opdracht 5 | 27/11/15 |AU            		
        			// PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
        			// nieuw tbv opdracht 5 | 27/11/15 |AU     
        			PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL_NAAM, ARTIKEL_PRIJS FROM artikel JOIN bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
        			statement.setInt(1, gewensteArtikel_id);

        			ResultSet resultSet = statement.executeQuery();
        				     		
        			
					
					while(resultSet.next()){          
        				// aangepast tbv opdracht 5 | 27/11/15 |AU     
        				gewensteArtikelNaam = resultSet.getString("Artikel_naam");
        				gewensteArtikelPrijs = resultSet.getInt("Artikel_prijs");
        			}
        			      
        			//System.out.println("AANTEPASSENBESTELLING OBJECT");	debug statement
        			
        			
					Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal , gewensteArtikelPrijs);
        				
        			
        			//System.out.println(aantepassenBestelling.getBestelling_id());   debug statements
        			//System.out.println(aantepassenBestelling.getKlant_id());
        			//System.out.println(aantepassenBestelling.getArtikel_id());
        			//System.out.println(aantepassenBestelling.getArtikel_naam());
        			//System.out.println(aantepassenBestelling.getArtikel_aantal());
        			//System.out.println(aantepassenBestelling.getArtikel_prijs());
        			
        			// System.out.println("UPDATE METHODE WORDT AANGEROEPEN"); debug statements

        			          		
        			bestellingDaoImpl.deleteArtikelFromBestelling(aantepassenBestelling);  
        		}
        			
        		catch (SQLException e) {
        			logger.warn("SQL error");
        			e.printStackTrace();
        		}
        			
        		finally {
        			// zinnige code
        		}

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
        			
        			//aanpassing tbv opdracht 5 || AU 26/11/15
        			System.out.println("Artikelnummer: " + overzicht.getArtikel_id() + ". Artikelnaam: " + overzicht.getArtikel_naam() + ". Aantal: "+ overzicht.getArtikel_aantal() + ". Prijs: " + overzicht.getArtikel_prijs());
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
        		System.out.println("Hieronder een overzicht van alle artikelen uit het assortiment: ");
                
            	try {
    	        	logger.info("Content of connection object is : " + connection);
    	        	Connection connection = DBConnectivityManagement.getConnectionStatus();
    	        	logger.info("Content of connection object is : " + connection);
                    	
            		Statement statement = connection.createStatement();
            		 
            		//aanpassing tbv opdracht 5 || AU 26/11/15
            		ResultSet resultSet = statement.executeQuery("SELECT DISTINCT(ARTIKEL_ID), ARTIKEL_NAAM, ARTIKEL_PRIJS FROM Artikel"); 
                    
            		resultSet.beforeFirst();
            		
            		while(resultSet.next()){
            			System.out.println("artikel_id: " + resultSet.getInt("Artikel_id") + "\tartikelnaam: " + resultSet.getString("Artikel_naam") + "\tprijs: " + resultSet.getDouble("Artikel_prijs"));                                                                             			
            		}
            		
            		resultSet.close();
            		statement.close();                                
            	} 
            	
        		catch (SQLException e) {
        			logger.warn("SQL exception voor DeleteMenu case 4");
        			e.printStackTrace();
        		}
        			
        		finally {
        			// zinnige code
        		}
            	
            	System.out.print("\nVoer het artikel ID in van het artikel dat je wil verwijderen uit het assortiment: ");
            	System.out.println(); 
            	int gewensteArtikel_id = input.nextInt();
            	while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
            		System.out.print("\nVoer een ander artikelnummer in: ");
            		gewensteArtikel_id = input.nextInt();
            		System.out.println();	
            	}    
            	
            	artikel.setArtikel_id(gewensteArtikel_id);
            	artikelDaoImpl.delete(artikel);
            	
            	toonMenu();
            	break; 
            	
            case 5:           		
            	System.out.println();          
            	System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            	alleKlanten = klantDaoImpl.read();
               		            		
            	System.out.println();          		
            	System.out.print("Voer het klant ID van de klant die je wil wissen: ");
            	System.out.println(); 
            
            	int gewensteKlant_id = input.nextInt();
            				
            	while (check.checkKlant_id(gewensteKlant_id)!= true) { 
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

