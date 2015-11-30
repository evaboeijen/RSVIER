package menu.crud;

import java.sql.*;
import java.util.*;

import business.*;
import dao.*;
import menu.*;
import menu.klasseselectie.*;

public class CreateMenu {
	
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
	
	
	
	public void addKlantToDatabase(){
		KlantDaoImpl nieuweKlantDaoImpl = new KlantDaoImpl();
		KlantMenu nieuweKlantMenu = new KlantMenu();
		Klant nieuweKlant = nieuweKlantMenu.createKlantObject();
		
		nieuweKlantDaoImpl.create(nieuweKlant);
		System.out.println();
		System.out.println("Een nieuwe klant is aangemaakt ");
		nieuweKlantDaoImpl.read();
	}
	
	public void addAdresToDatabase(){
		AdresDaoImpl updateAdresDaoImpl = new AdresDaoImpl();
		AdresMenu nieuwAdresKlant = new AdresMenu();
		Adres nieuwAdres = nieuwAdresKlant.createAdresObject();
		updateAdresDaoImpl.insert(nieuwAdres);
		
		System.out.println("Adres is toegevoegd aan klantgegevens");
	}

	public void toonMenu() {
		System.out.println("\t----------");
	    System.out.println("\tCreate Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Create nieuwe klant");
	    System.out.println("2. Create nieuwe klant met adres");
	    System.out.println("3. Create adres voor bestaande klant");
	    System.out.println("4. Create artikel voor bestaande bestelling");
	    System.out.println("5. Create bestelling voor nieuwe klant");
	    
	 
	    	    
	    System.out.println("10. Terug naar het vorige menu"); 
	    System.out.println("11. Terug naar het hoofdmenu"); 
	    System.out.println("12. Stoppen"); 
	    System.out.print("Voer optie in en druk op Enter:");
	    
	    Scanner input = new Scanner(System.in);	     
        int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		            		
            		CreateMenu createMenu = new CreateMenu();
            		createMenu.addKlantToDatabase();
            		toonMenu();
            		
            		break;
                
            		
            	case 2:
            		CreateMenu createMenu2 = new CreateMenu();
            		createMenu2.addKlantToDatabase();
    				
    				System.out.println("Nu volgen de adres gegevens: ");
    				createMenu2.addAdresToDatabase();
    				toonMenu();
    				
            		break;
                
            		
            	case 3:
            		CreateMenu createMenu3 = new CreateMenu();
            		createMenu3.addAdresToDatabase();
            		toonMenu();
            		
            		break;
                	
            		
            	case 4:
            		System.out.println();           		
            		System.out.println("Je kan hier artikelen toevoegen aan een bestaande bestelling.");
            		System.out.println("Hieronder een overzicht van alle bestellingen: ");
            		               		       			
            		lijst = bestellingDaoImpl.read();	
            		
            		System.out.println();
            		System.out.println();	
            		
            		System.out.println("Overzicht van alle bestellingen: ");
            		System.out.println("=================================");
            		
            		for (Bestelling overzicht : lijst) {
            			System.out.println("Klantnummer : " + overzicht.getKlant_id() + ". Ordernummer : " + overzicht.getBestelling_id());
            			System.out.println("---------------------------------------------");
            			
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
            			e.printStackTrace();
            		}
            			
            		finally {
            			// zinnige code
            		}
  
                	toonMenu();          			   			  		          		
                	break; 
                	
            
            		
            	case 5:
            		CreateMenu createMenu5 = new CreateMenu();
            		createMenu5.addKlantToDatabase();
            		
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
                		e.printStackTrace();
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
            				
                	nieuweBestelling.setArtikel_id(gewensteArtikel_id);  
                	nieuweBestelling.setArtikel_aantal(gewensteAantal);
	
                			
                	try {

                		Connection connection = DBConnectivityManagement.getConnectionStatus();
                		 		
                		PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL_NAAM, ARTIKEl_PRIJS FROM Artikel WHERE Artikel_id=?");
                		statement.setInt(1, gewensteArtikel_id);

                		ResultSet resultSet = statement.executeQuery();
                				     		
                		while(resultSet.next()) {                                    
                			nieuweBestelling.setArtikel_naam(resultSet.getString("Artikel_naam"));  //aanpassing tbv opdracht 5 || AU 26/11/15
                			nieuweBestelling.setArtikel_prijs(resultSet.getInt("Artikel_prijs")); 	 //aanpassing tbv opdracht 5 || AU 26/11/15
                		}
                            	
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


