package menu.crud;

import java.sql.*;
import java.util.*;

import business.*;
import dao.*;
import menu.*;
import menu.klasseselectie.*;
import service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreateMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateMenu.class);
	
	DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
	List<Artikel> alleArtikelen = new ArrayList<>();
	int gewensteArtikel_id = 0;
	int gewensteAantal = 0;
	BestellingDaoImpl bestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze();
	int gewensteBestelling_id = 0;
	List<Bestelling> lijst = null;
	KlantDaoImpl klantDaoImpl = daoImplKeuze.KlantDaoImplKeuze();
	List <Klant> alleKlanten = null;            	              			
	Bestelling nieuweBestelling = new Bestelling();
	AdresDaoImpl adresDaoImpl = daoImplKeuze.AdresDaoImplKeuze();
	Artikel artikel = new Artikel(); 
	Bestelling teverwijderenBestelling = new Bestelling();
	String gewensteArtikelNaam = null;
	int gewensteKlantID = 0;
	double gewensteArtikelPrijs = 0;
	Check check = new Check();
	ArtikelDaoImpl artikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze();
	
	
	public void addKlantToDatabase(){
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		KlantDaoImpl nieuweKlantDaoImpl = daoImplKeuze.KlantDaoImplKeuze();
		DTO dto = new DTO();
		Klant nieuweKlant = dto.createKlantObject();
		
		nieuweKlantDaoImpl.create(nieuweKlant);
		System.out.println();
		System.out.println("\nEen nieuwe klant is aangemaakt\n ");
		System.out.println(nieuweKlantDaoImpl.read());
	}
	
	public void addAdresToDatabase(){
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		AdresDaoImpl updateAdresDaoImpl = daoImplKeuze.AdresDaoImplKeuze();
		DTO dto = new DTO();
		Adres nieuwAdres = dto.createAdresObject();
		updateAdresDaoImpl.insert(nieuwAdres);
		
		System.out.println("\nAdres is toegevoegd aan klantgegevens\n");
	}

	public void toonMenu() {

		logger.info("applicatielogica van CreateMenu() methode wordt aangeroepen");
	    
	    Scanner input = new Scanner(System.in);	     
        int keuze = input.nextInt();
        logger.info("Gebruiker koos voor optie: " + keuze);
		       
			switch (keuze) {
            	case 1:
            		            		
            		CreateMenu createMenu = new CreateMenu();
            		createMenu.addKlantToDatabase();
            		CrudMenu crudmenu = new CrudMenu();
            		crudmenu.viewCreateMenu();
            		toonMenu();
            		
            		break;
                
            		
            	case 2:
            		CreateMenu createMenu2 = new CreateMenu();
            		createMenu2.addKlantToDatabase();
    				            		
    				System.out.println("\nNu volgen de adres gegevens: ");
    				createMenu2.addAdresToDatabase();
    				CrudMenu crudmenu2 = new CrudMenu();
            		crudmenu2.viewCreateMenu();
    				toonMenu();
    				
            		break;
                
            		
            	case 3:
            		CreateMenu createMenu3 = new CreateMenu();
            		createMenu3.addAdresToDatabase();
            		CrudMenu crudmenu3 = new CrudMenu();
            		crudmenu3.viewCreateMenu();
            		toonMenu();
            		
            		break;
                	
            		
            	case 4:
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
        				

            		while (check.checkBestelling_id(gewensteBestelling_id)!= true) { 
            			logger.info("check.checkBestelling_id(gewensteBestelling_id) is: " + check.checkBestelling_id(gewensteBestelling_id));
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
            		
            		System.out.println(artikelDaoImpl.read());

            		System.out.print("\nVoer het artikel ID in dat je wil toevoegen aan de bestelling (0 is terug naar het vorige menu): ");
            		System.out.println(); 
            		gewensteArtikel_id = input.nextInt();
            		
            		while (check.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) == true || check.checkArtikel_id(gewensteArtikel_id)!= true) {
            			if (check.checkArtikelAlAanwezigInBestelling(gewensteBestelling_id, gewensteArtikel_id) == true) {          	
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
            					 
            			PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(ARTIKEL_NAAM), ARTIKEL_PRIJS FROM artikel , bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
            			logger.info("Query succesvol uitgevoerd : SELECT DISTINCT(ARTIKEL_NAAM), ARTIKEL_PRIJS FROM artikel , bestelling_artikel WHERE (bestelling_artikel.artikel_id = artikel.artikel_id AND artikel.artikel_id=?)");
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
  
            		
            		CrudMenu crudmenu4 = new CrudMenu();
            		crudmenu4.viewCreateMenu();
                	toonMenu();          			   			  		          		
                	break; 
                	
            
            		
            	case 5:
            		CreateMenu createMenu5 = new CreateMenu();
            		createMenu5.addKlantToDatabase();
            		
            		System.out.println();           		
            		System.out.println("Je kan hier een bestelling invoeren voor een bestaande klant.");
            		System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		alleKlanten = klantDaoImpl.read();
               		           		
            		System.out.println();          		
            		System.out.println("Voor welke klant wil je een bestelling invoeren?");
            		System.out.print("Voer het klant ID in: ");
            		System.out.println(); 
            			                      	            
            		int gewensteKlant_id = input.nextInt();
            				          		
                	while (check.checkKlant_id(gewensteKlant_id)!= true) { 
                		System.out.print("\nVoer een ander klantnummer in: ");
                		gewensteKlant_id = input.nextInt();
                		System.out.println();
                	}    
            		    
                	nieuweBestelling.setKlant_id(gewensteKlant_id);
                	nieuweBestelling.setBestelling_id(check.checkHoogste_Bestelling_id() + 1);
            			
                	System.out.println("Welk artikel wil je in de bestelling plaatsen?");
                	System.out.println("Hieronder een overzicht van het hele assortiment: ");
                	
                	System.out.println(artikelDaoImpl.read());
                		                	                      
                	                               			
                	System.out.print("\nVoer het artikel ID in dat je in de bestelling wil plaatsen: ");
                	System.out.println(); 
                	int gewensteArtikel_id = input.nextInt();
                	while (check.checkArtikel_id(gewensteArtikel_id)!= true) { 
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
                		logger.warn("SQL error");
                		e.printStackTrace();
                	}
                			
                	finally {
                		// zinnige code
                	}                	
						      	
                	CrudMenu crudmenu5 = new CrudMenu();
            		crudmenu5.viewCreateMenu();
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


