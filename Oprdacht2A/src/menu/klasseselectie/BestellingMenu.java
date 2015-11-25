package menu.klasseselectie;

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
import menu.DBConnectivityManagement;
import business.*;

public class BestellingMenu  {
	
	// declaratie en initialisering van gemeenschappelijk objecten en primitieve variabelen 
	// die zullen worden geraadpleegd door
	// de diverse case keuzes in het switch block in de methode toonMenu()	
	Scanner input = new Scanner(System.in);	
	List<Artikel> alleArtikelen = new ArrayList<>();
	int gewensteArtikel_id = 0;
	int gewensteAantal = 0;
	BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
	int gewensteBestelling_id = 0;
	List<Bestelling> lijst = null;
	KlantDaoImpl klantDaoImpl = new KlantDaoImpl();
	List <Klant> alleKlanten = null;            	              			
	Bestelling nieuweBestelling = new Bestelling();
	AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
	Artikel artikel = new Artikel(); 
	Bestelling teverwijderenBestelling = new Bestelling();
	String gewensteArtikelNaam = null;
	int gewensteKlantID = 0;
	double gewensteArtikelPrijs = 0;
	
	
	public void toonMenu() {
	    System.out.println("\t---------------");
	    System.out.println("\tBestelling Menu");
	    System.out.println("\t---------------");
	    System.out.println("1. Create nieuwe bestelling voor bestaande klant");  
	    System.out.println("2. Read alle bestellingen");  
	    System.out.println("3. Update een bestaande bestelling door toevoegen van artikelen");  
	    System.out.println("4. Delete een bestelling voor een bepaalde klant");  		 
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
            		System.out.println("Je kan hier een bestelling invoeren voor een bestaande klant.");
            		System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		alleKlanten = klantDaoImpl.read();
               		           		
            		System.out.println();          		
            		System.out.println("Voor welke klant wil je een bestelling invoeren?");
            		System.out.print("Voer het klant ID in: ");
            		System.out.println(); 
            			                      	            
            		int gewensteKlant_id = input.nextInt();
            				          		
                	while (klantDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
                		System.out.print("\nVoer een ander klantnummer in: ");
                		gewensteKlant_id = input.nextInt();
                		System.out.println();
                	}    
            		    
                	nieuweBestelling.setKlant_id(gewensteKlant_id);
                	nieuweBestelling.setBestelling_id(bestellingDaoImpl.checkHoogste_Bestelling_id() + 1);
            			
                	System.out.println("Welk artikel wil je in de bestelling plaatsen?");
                	System.out.println("Hieronder een overzicht van alle aanwezige artikelen: ");
                		                	                      
                	try {
                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                        	
                		Statement statement = connection.createStatement();
                		ResultSet resultSet = statement.executeQuery("SELECT ARTIKEL1_ID, ARTIKEL1_NAAM, ARTIKEL2_ID, ARTIKEL2_NAAM, ARTIKEL3_ID, ARTIKEL3_NAAM FROM Bestelling");
                                
                		while(resultSet.next()){		                                 
                			artikel.setArtikel1_id(resultSet.getInt("Artikel1_id"));
                			artikel.setArtikel1_naam(resultSet.getString("Artikel1_naam"));
                			artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                			artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                			artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                			artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam"));
                                                                                                        
                			alleArtikelen.add(artikel);
                		}
                		resultSet.close();
                		statement.close();                                
                	} 
                		
                	catch (SQLException e) {
                		e.printStackTrace();
                	}

                	for(int i = 0; i < alleArtikelen.size(); i++) {
                		System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel2_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel2_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel3_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel3_naam());                          
                	}
                                                       			
                	System.out.print("\nVoer het artikel ID in dat je in de bestelling wil plaatsen: ");
                	System.out.println(); 
                	int gewensteArtikel_id = input.nextInt();
                	while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
                		System.out.print("\nVoer een ander artikelnummer in: ");
                		gewensteArtikel_id = input.nextInt();
                		System.out.println();
                	}    
                						               						
                	System.out.print("\nVoer het aantal in dat je in de bestelling wil plaatsen: ");
                	gewensteAantal = input.nextInt();      
            				
                	nieuweBestelling.setArtikel1_id(gewensteArtikel_id);
                	nieuweBestelling.setArtikel1_aantal(gewensteAantal);
	
                			
                	try {

                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                					
                		PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
                		statement.setInt(1, gewensteArtikel_id);

                		ResultSet resultSet = statement.executeQuery();
                				     		
                		while(resultSet.next()) {                                    
                			nieuweBestelling.setArtikel1_naam(resultSet.getString("Artikel1_naam"));
                			nieuweBestelling.setArtikel1_prijs(resultSet.getInt("Artikel1_prijs"));
                		}
                        
                		// System.out.println(nieuweBestelling.getArtikel1_id() + " " + nieuweBestelling.getArtikel1_naam() + " " + nieuweBestelling.getArtikel1_prijs());
                		
                		bestellingDaoImpl.create(nieuweBestelling);  
                	}
                			
                	catch (SQLException e) {
                		e.printStackTrace();
                	}
                			
                	finally {
                		// zinnige code
                	}                	
						      			
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
            		
            		toonMenu(); 	   			  		          		
            		break; 
            		
                
            	case 3:
            		System.out.println();           		
            		System.out.println("Je kan hier artikelen toevoegen aan een bestaande bestelling.");
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
            			e.printStackTrace();
            		}
            		
            		
            		
            		System.out.println();
            		System.out.println("Welk artikel wil je toevoegen aan de bestelling?");
            		System.out.println();
            		System.out.println("Hieronder een overzicht van alle aanwezige artikelen: ");
            		                   
            		try {
            			Connection connection = DBConnectivityManagement.getConnectionStatus();
                    	
            			Statement statement = connection.createStatement();
            			ResultSet resultSet = statement.executeQuery("SELECT ARTIKEL1_ID, ARTIKEL1_NAAM, ARTIKEL2_ID, ARTIKEL2_NAAM, ARTIKEL3_ID, ARTIKEL3_NAAM FROM Bestelling");
                             
            			while(resultSet.next()){                                
            				artikel.setArtikel1_id(resultSet.getInt("Artikel1_id"));
            				artikel.setArtikel1_naam(resultSet.getString("Artikel1_naam"));
            				artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
            				artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
            				artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
            				artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam"));
                                                                                                    
            				alleArtikelen.add(artikel);
            			}
            			resultSet.close();
            			statement.close();
                             
            		} 
            		
            		catch (SQLException e) {
            			e.printStackTrace();
            		}

            		for(int i = 0; i < alleArtikelen.size(); i++) {
            			System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel2_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel2_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel3_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel3_naam());                          
            		}
                                                   			

            		System.out.print("\nVoer het artikel ID in dat je wil toevoegen aan de bestelling: ");
            		System.out.println(); 
            		gewensteArtikel_id = input.nextInt();
            		while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
            			System.out.print("\nVoer een ander artikelnummer in: ");
                        gewensteArtikel_id = input.nextInt();
                        System.out.println();
                    }    
            						            						
            		System.out.print("\nVoer het aantal in dat je aan de bestelling wil toevoegen: ");
            		gewensteAantal = input.nextInt();      

      			
            		try {

            			Connection connection = DBConnectivityManagement.getConnectionStatus();
            					
            			PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
            			statement.setInt(1, gewensteArtikel_id);

            			ResultSet resultSet = statement.executeQuery();
            				     		
            			while(resultSet.next()){                
            				gewensteArtikelNaam = resultSet.getString("Artikel1_naam");
            				gewensteArtikelPrijs = resultSet.getInt("Artikel1_prijs");
            			}
            			       
            			Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal, gewensteArtikelPrijs);
            				
            			bestellingDaoImpl.update(aantepassenBestelling);  
            		}
            			
            		catch (SQLException e) {
            			e.printStackTrace();
            		}
            			
            		finally {
            			// zinnige code
            		}
            			
            		toonMenu(); 	   			  		          		
            		break; 
            		
                	
            	case 4:
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
            		
        			gewensteBestelling_id = input.nextInt();
        				
            		while (bestellingDaoImpl.checkBestelling_id(gewensteBestelling_id)!= true) { 
            			System.out.print("\nVoer een ander bestellingnummer in: ");
            			gewensteBestelling_id = input.nextInt();
            			System.out.println();
            		}            		        
            			          			            			
            		teverwijderenBestelling.setBestelling_id(gewensteBestelling_id);            		
            		bestellingDaoImpl.delete(teverwijderenBestelling);
            		
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
            		
		} // dit sluit de switch
			      
	}	// dit sluit de toonMenu() methode
	
} // dit sluit de BestellingMenu klasse

