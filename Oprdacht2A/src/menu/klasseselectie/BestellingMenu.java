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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BestellingMenu  {
	
	private static final Logger logger =  LoggerFactory.getLogger(BestellingMenu.class);
	
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
	    System.out.println("4. Update het aantal van een bepaald artikel in een bepaalde bestelling");  
	    System.out.println("5. Delete een bepaalde bestelling");  
	    System.out.println("6. Delete een artikel uit een bepaalde bestelling");  
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
                	System.out.println("Hieronder een overzicht van het hele assortiment: ");
                		                	                      
                	try {
                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                        	
                		Statement statement = connection.createStatement();
                		 
                		//aanpassing tbv opdracht 5 || AU 26/11/15
                		ResultSet resultSet = statement.executeQuery("SELECT DISTINCT(ARTIKEL_ID), ARTIKEL_NAAM, ARTIKEL_PRIJS FROM Artikel"); 
                        
                		resultSet.beforeFirst();
                		
                		while(resultSet.next()){
                					
                			/* artikel.setArtikel1_id(resultSet.getInt("Artikel_id"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_naam(resultSet.getString("Artikel_naam"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_prijs(resultSet.getDouble("Artikel_prijs"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			*/
                			 
                			//  toegevoegd tbv opdracht 5 || AU 26/11/15
                			System.out.println("artikel_id: " + resultSet.getInt("Artikel_id") + "\tartikelnaam: " + resultSet.getString("Artikel_naam") + "\tprijs: " + resultSet.getDouble("Artikel_prijs"));  
                			               			
                			/* uitgecomment tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_id(resultSet.getInt("Artikel1_id"));  
                			artikel.setArtikel1_naam(resultSet.getString("Artikel1_naam")); 
                			artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                			artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                			artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                			artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam")); */ 
                                                                                                        
                			alleArtikelen.add(artikel);
                		}
                		resultSet.close();
                		statement.close();                                
                	} 
                		
                	catch (SQLException e) {
                		logger.warn("SQL error");
                		e.printStackTrace();
                	}
                         	
                	/* uitgecomment 26/11/15 - AU
                	 * for(int i = 0; i < alleArtikelen.size(); i++) {
                		// aangepast tbv opdracht 5 || 26/11/15 AU : System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel2_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel2_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel3_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel3_naam());                          
                		//System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + " " + alleArtikelen.get(i).getArtikel1_prijs());  
                		System.out.println("artikel_id: " + alleArtikelen.get(2).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(0).getArtikel1_naam() + " " + alleArtikelen.get(0).getArtikel1_prijs());  
                	*/ 
                                                       			
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
            				
                	nieuweBestelling.setArtikel_id(gewensteArtikel_id);  //aanpassing tbv opdracht 5 || AU 26/11/15
                	nieuweBestelling.setArtikel_aantal(gewensteAantal); //aanpassing tbv opdracht 5 || AU 26/11/15
	
                			
                	try {

                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                					
                		// uitgecomment tbv opdracht 5 || 26/11/15 AU : PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
                		
                		// nieuw toegevoegd tbv opdracht 5 || 26/11/15 AU
                		PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL_NAAM, ARTIKEl_PRIJS FROM Artikel WHERE Artikel_id=?");
                		statement.setInt(1, gewensteArtikel_id);

                		ResultSet resultSet = statement.executeQuery();
                				     		
                		while(resultSet.next()) {                                    
                			nieuweBestelling.setArtikel_naam(resultSet.getString("Artikel_naam"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			nieuweBestelling.setArtikel_prijs(resultSet.getInt("Artikel_prijs")); 	 //aanpassing tbv opdracht 5 || AU 26/11/15
                		}
                        
                		// System.out.println(nieuweBestelling.getArtikel1_id() + " " + nieuweBestelling.getArtikel1_naam() + " " + nieuweBestelling.getArtikel1_prijs());
               		                	
                		bestellingDaoImpl.create(nieuweBestelling);  
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
                	
            		          			           		          		            
            	case 2:         		        			
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
                		 
                		//aanpassing tbv opdracht 5 || AU 26/11/15
                		ResultSet resultSet = statement.executeQuery("SELECT DISTINCT(ARTIKEL_ID), ARTIKEL_NAAM, ARTIKEL_PRIJS FROM Artikel"); 
                        
                		resultSet.beforeFirst();
                		
                		while(resultSet.next()){
                					
                			/* artikel.setArtikel1_id(resultSet.getInt("Artikel_id"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_naam(resultSet.getString("Artikel_naam"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_prijs(resultSet.getDouble("Artikel_prijs"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			*/
                			 
                			//  toegevoegd tbv opdracht 5 || AU 26/11/15
                			System.out.println("artikel_id: " + resultSet.getInt("Artikel_id") + "\tartikelnaam: " + resultSet.getString("Artikel_naam") + "\tprijs: " + resultSet.getDouble("Artikel_prijs"));  
                			               			
                			/* uitgecomment tbv opdracht 5 || AU 26/11/15
                			artikel.setArtikel1_id(resultSet.getInt("Artikel1_id"));  
                			artikel.setArtikel1_naam(resultSet.getString("Artikel1_naam")); 
                			artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                			artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                			artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                			artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam")); */ 
                                                                                                        
                			alleArtikelen.add(artikel);
                		}
                		resultSet.close();
                		statement.close();                                
                	} 
                		
                	catch (SQLException e) {
                		logger.warn("SQL error");
                		e.printStackTrace();
                	}
                         	
                	/* uitgecomment 26/11/15 - AU
                	 * for(int i = 0; i < alleArtikelen.size(); i++) {
                		// aangepast tbv opdracht 5 || 26/11/15 AU : System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel2_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel2_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel3_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel3_naam());                          
                		//System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + " " + alleArtikelen.get(i).getArtikel1_prijs());  
                		System.out.println("artikel_id: " + alleArtikelen.get(2).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(0).getArtikel1_naam() + " " + alleArtikelen.get(0).getArtikel1_prijs());  
                	*/ 
                                                   			

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
            		
            		/* while (bestellingDaoImpl.checkArtikel_id(gewensteArtikel_id)!= true) { 
            			System.out.print("\nVoer een ander artikelnummer in: ");
                        gewensteArtikel_id = input.nextInt();
                        System.out.println();
                    }    */
            		

            		
            		
            		System.out.print("\nVoer het aantal in dat je aan de bestelling wil toevoegen: ");
            		gewensteAantal = input.nextInt();      

      			
            		try {

            			Connection connection = DBConnectivityManagement.getConnectionStatus();
            					
            			// uitgecomment tbv opdracht 5 | 27/11/15 |AU            		
            			// PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
            			// nieuw tbv opdracht 5 | 27/11/15 |AU     
            			PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(ARTIKEL_NAAM), ARTIKEL_PRIJS FROM artikel JOIN bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
            			statement.setInt(1, gewensteArtikel_id);

            			ResultSet resultSet = statement.executeQuery();
            				     		
            			while(resultSet.next()){          
            				// aangepast tbv opdracht 5 | 27/11/15 |AU     
            				gewensteArtikelNaam = resultSet.getString("Artikel_naam");
            				gewensteArtikelPrijs = resultSet.getInt("Artikel_prijs");
            			}
            			      
            			//System.out.println("AANTEPASSENBESTELLING OBJECT");	debug statement
            			
            			Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal, gewensteArtikelPrijs);
            				
            			
            			//System.out.println(aantepassenBestelling.getBestelling_id());   debug statements
            			//System.out.println(aantepassenBestelling.getKlant_id());
            			//System.out.println(aantepassenBestelling.getArtikel_id());
            			//System.out.println(aantepassenBestelling.getArtikel_naam());
            			//System.out.println(aantepassenBestelling.getArtikel_aantal());
            			//System.out.println(aantepassenBestelling.getArtikel_prijs());
            			
            			// System.out.println("UPDATE METHODE WORDT AANGEROEPEN"); debug statements

            			          		
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
                	
                	             	
            	case 4:	// added 29-11-15 AU
            		System.out.println();           		
            		System.out.println("Je kan hier het aantal van een bepaald artikel in een bestaande bestelling wijzigen.");
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
            		System.out.print("Voer het bestelling ID in van de bestelling waarvan je een artikel aantal wil wijzigen: ");
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
            		System.out.println("Van welk artikel in de desbetreffende bestelling wil je het aantal aanpassen?");           		
            		System.out.print("\nVoer het artikel ID in (0 is terug naar het vorige menu): ");
            		System.out.println(); 
            		gewensteArtikel_id = input.nextInt();
            		
            		while (bestellingDaoImpl.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) != true) {
            			System.out.print("\nHet artikel zit niet in de desbetreffende bestelling. \nVoer een ander artikelnummer in: ");
                    	gewensteArtikel_id = input.nextInt();
                    	if (gewensteArtikel_id == 0) {
                    		toonMenu();
                    	}
                    	System.out.println();
            		}
         		
            		System.out.print("\nVoer het nieuwe aantal in: ");
            		gewensteAantal = input.nextInt();      

      			
            		try {

            			Connection connection = DBConnectivityManagement.getConnectionStatus();
           					  
            			PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(ARTIKEL_NAAM), ARTIKEL_PRIJS FROM artikel JOIN bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
            			statement.setInt(1, gewensteArtikel_id);

            			ResultSet resultSet = statement.executeQuery();
            				     		
            			while(resultSet.next()){          
            				// aangepast tbv opdracht 5 | 27/11/15 |AU     
            				gewensteArtikelNaam = resultSet.getString("Artikel_naam");
            				gewensteArtikelPrijs = resultSet.getInt("Artikel_prijs");
            			}
            			      
            			//System.out.println("AANTEPASSENBESTELLING OBJECT");	debug statement
            			
            			Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal, gewensteArtikelPrijs);
            				
            			
            			//System.out.println(aantepassenBestelling.getBestelling_id());   debug statements
            			//System.out.println(aantepassenBestelling.getKlant_id());
            			//System.out.println(aantepassenBestelling.getArtikel_id());
            			//System.out.println(aantepassenBestelling.getArtikel_naam());
            			//System.out.println(aantepassenBestelling.getArtikel_aantal());
            			//System.out.println(aantepassenBestelling.getArtikel_prijs());
            			
            			// System.out.println("UPDATE METHODE WORDT AANGEROEPEN"); debug statements

            			          		
            			bestellingDaoImpl.updateAantallen(aantepassenBestelling);  
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
                	

                	
                	
            	case 5:
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
            
            		
            	case 6:
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

            			Connection connection = DBConnectivityManagement.getConnectionStatus();
            					
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
            			
            			Bestelling aantepassenBestelling = new Bestelling(gewensteBestelling_id, gewensteKlantID, gewensteArtikel_id, gewensteArtikelNaam, gewensteAantal, gewensteArtikelPrijs);
            				
            			
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
            			e.printStackTrace();
            		}
            			
            		finally {
            			// zinnige code
            		}
  
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

