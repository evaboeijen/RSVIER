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
            		ArtikelDaoImpl createArtikelDaoImpl = new ArtikelDaoImpl();
            		ArtikelMenu artikelMenu = new ArtikelMenu();
            		Artikel createArtikel = artikelMenu.createArtikelObject();
            		createArtikelDaoImpl.update(createArtikel); 
            		
            		toonMenu();
            		
            		break;
            
            		
            	case 5:
            		CreateMenu createMenu5 = new CreateMenu();
            		createMenu5.addKlantToDatabase();
            		
            		System.out.println();           		
            	    System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		alleKlanten = klantDaoImpl.read();
               		           		
            		System.out.println();          		
            		System.out.println("Voor welke klant wil je een bestelling invoeren?");
            		System.out.print("Voer het klant ID in: ");
            		System.out.println(); 
            			                      	            
            		int gewensteKlant_id = input.nextInt();
            				          		
                	while (adresDaoImpl.checkKlant_id(gewensteKlant_id)!= true) { 
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
                                
                		BestellingDaoImpl bestellingDaoImpl1 = new BestellingDaoImpl();
                		bestellingDaoImpl1.create(nieuweBestelling);  
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


