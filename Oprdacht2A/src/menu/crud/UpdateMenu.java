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
import menu.klasseselectie.*;
import business.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateMenu.class);
	
	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tUpdate Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Update klant");
	    System.out.println("2. Update adres");
	    System.out.println("3. Update bestelling");
	    System.out.println("4, Update artikel");
   
	    	    
	    System.out.println("10. Terug naar het vorige menu"); 
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    System.out.print("Voer optie in en druk op Enter:");
	    
	   Scanner input = new Scanner(System.in);		     
            
			int keuze = input.nextInt();
			logger.info("Gebruiker koos voor optie: " + keuze);
		       
			switch (keuze) {
            	case 1:
            		KlantDaoImpl updateKlantDaoImpl = new KlantDaoImpl();
    				
    				System.out.println();
    				System.out.println("Voer uw klant id in, en druk op enter: ");
    				System.out.println();
			
    			
    				int huidige_klant_id = input.nextInt();
				
				
    				while (updateKlantDaoImpl.checkKlant_id(huidige_klant_id)!= true) { 
    					System.out.print("\n Incorrecte invoer. Voer uw klant id opnieuw in: ");
    					huidige_klant_id = input.nextInt();
    					System.out.println();
        			}            		
				
    				KlantMenu klantMenu = new KlantMenu();            				
    			    Klant updateKlant = klantMenu.createKlantObject();
    				updateKlant.setKlant_id(huidige_klant_id);
    				updateKlantDaoImpl.update(updateKlant);
    				
    				System.out.println("Uw gegevens zijn aanpast");
            		
            		toonMenu();
            		
            		break;
                
            	case 2:
            	
            		AdresDaoImpl updateAdresDaoImpl = new AdresDaoImpl();
            		AdresMenu adresMenu = new AdresMenu();
            		System.out.println("Voer de gegevens in van het bij te werken adres ");
        			Adres updateAdres = adresMenu.createAdresObject();
        			updateAdresDaoImpl.updateAdres(updateAdres);
        			toonMenu();
        		
            		break;
                
            	case 3:
            		
            		List<Artikel> alleArtikelen = new ArrayList<>();
            		int gewensteArtikel_id = 0;
            		int gewensteAantal = 0;
            		BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
            		int gewensteBestelling_id = 0;
            	    Artikel artikel = new Artikel(); 
               		String gewensteArtikelNaam = null;
            		int gewensteKlantID = 0;
            		double gewensteArtikelPrijs = 0;
            		
            		System.out.println();           		
            		System.out.println("Je kan hier artikelen toevoegen aan een bestaande bestelling.");
            		System.out.println("Hieronder een overzicht van alle bestellingen: ");
            		               		       			
            		List<Bestelling> lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
            		
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
            		System.out.print("Voer het bestelling ID in van de bestelling die je wil uitbreiden: ");
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
            			logger.warn("SQL error");
            			e.printStackTrace();
            		}
            		
            		System.out.println();
            		System.out.println("Welk artikel wil je toevoegen aan de bestelling?");
            		System.out.println();
            		System.out.println("Hieronder een overzicht van alle aanwezige artikelen: ");
                    
                	try {
                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                        	
                		Statement statement = connection.createStatement();
                		
                		ResultSet resultSet = statement.executeQuery("SELECT DISTINCT(ARTIKEL_ID), ARTIKEL_NAAM, ARTIKEL_PRIJS FROM Artikel"); 
                        
                		resultSet.beforeFirst();
                		
                		while(resultSet.next()){
                					
                			System.out.println("artikel_id: " + resultSet.getInt("Artikel_id") + "\tartikelnaam: " + resultSet.getString("Artikel_naam") + "\tprijs: " + resultSet.getDouble("Artikel_prijs"));  
                			               			                                                                                                        
                			alleArtikelen.add(artikel);
                		}
                		resultSet.close();
                		statement.close();                                
                	} 
                		
                	catch (SQLException e) {
                		logger.warn("SQL error");
                		e.printStackTrace();
                	}                                                  			

            		System.out.print("\nVoer het artikel ID in dat je wil toevoegen aan de bestelling (0 is terug naar het vorige menu): ");
            		System.out.println(); 
            		gewensteArtikel_id = input.nextInt();
            		
            		while (bestellingDaoImpl.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) == true || bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) {
            			if (bestellingDaoImpl.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) == true) {          	
            				System.out.print("\nHet opgegeven artikelnummer zit al in deze bestelling. ");
            			}     
            			System.out.println("Voer een ander artikelnummer in: ");
                    	gewensteArtikel_id = input.nextInt();
                    	if (gewensteArtikel_id == 0) {
                    		toonMenu();
                    	}
                    	System.out.println();
            		}
            		
            		           		
            		System.out.print("\nVoer het aantal in dat je aan de bestelling wil toevoegen: ");
            		gewensteAantal = input.nextInt();      

      			
            		try {

            			Connection connection = DBConnectivityManagement.getConnectionStatus();
            		  
            			PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(ARTIKEL_NAAM), ARTIKEL_PRIJS FROM artikel JOIN bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
            			statement.setInt(1, gewensteArtikel_id);

            			ResultSet resultSet = statement.executeQuery();
            				     		
            			while(resultSet.next()){          
            				  
            				gewensteArtikelNaam = resultSet.getString("Artikel_naam");
            				gewensteArtikelPrijs = resultSet.getInt("Artikel_prijs");
            			}
            			                			
            			Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal, gewensteArtikelPrijs);
            				          			          		
            			bestellingDaoImpl.update(aantepassenBestelling);  
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
              
            	case 4:
            		System.out.println("U kunt de artikelgegevens wijzigen. Voer het artikelnummer in, en druk op enter");
            		int updateArtikel_id = input.nextInt();
            		
            		ArtikelDaoImpl updateArtikelDaoImpl = new ArtikelDaoImpl();
            		ArtikelMenu artikelMenu = new ArtikelMenu();
            		Artikel updateArtikel = artikelMenu.createArtikelObject();
            		updateArtikel.setArtikel_id(updateArtikel_id);
            		
            		updateArtikelDaoImpl.update(updateArtikel);
            		
            		System.out.println("De artikel gegevens zijn aangepast");
            		
            		toonMenu();          			   			  		          		
                	break; 
            	
		
	case 10:
		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
		klasseselectiemenu.toonMenu();
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
		this.toonMenu();
}
}
}

