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
import menu.InEnUitLoggen;
import business.*;

public class BestellingMenu  {
	
	public void toonMenu() {
	    System.out.println("\t----------");
	    System.out.println("\tBestelling Menu");
	    System.out.println("\t----------");
	    System.out.println("1. Create nieuwe bestelling voor nieuwe klant");  
	    System.out.println("2. Read alle bestellingen");  
	    System.out.println("3. Update een bestaande bestelling door toevoegen van artikelen");  
	    System.out.println("4. Delete een bestelling voor een bepaalde klant");  
	    

	    // etcetera
	    	    
	    System.out.println("5. Terug naar het vorige menu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("6. Terug naar het hoofdmenu"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.println("7. Stoppen"); // waarbij nummer nog bepaald moet worden afhankelijk van de plek van deze optie in het menu
	    System.out.print("Voer optie in en druk op Enter:");
	    
	    try (Scanner input = new Scanner(System.in);) {		     
            
			int keuze = input.nextInt();
		       
			switch (keuze) {
            	case 1:
            		System.out.println();
            		KlantDaoImpl lijst = new KlantDaoImpl();
            		System.out.println("Je kan hier een bestelling invoeren voor een bestaande klant.");
            		System.out.println("Hieronder een overzicht van alle klanten: ");
            		
            		List <Klant> alleKlanten = lijst.read();
               		
            		
            		System.out.println("Voor welke klant wil je een bestelling invoeren?");
            		System.out.print("Voer het klant ID in: ");
            			                      		
            			try (Scanner input2 = new Scanner(System.in);) {
            				int gewensteKlant_id = input.nextInt();
            				
            			for(int i = 0; i < alleKlanten.size(); i++) {
          	            	if (gewensteKlant_id == alleKlanten.get(i).getKlant_id()) {
          	            		break;
          	            	}
          	            }	
            			
            	       
            	        System.out.println("Je hebt een niet bestaand klant_id ingevoerd");
            	            	
                    	            
            			
            			Bestelling nieuweBestelling = new Bestelling();
            			nieuweBestelling.setKlant_id(gewensteKlant_id);
            			
                		System.out.println("Welk artikel wil je in de bestelling plaatsen?");
                		System.out.println("Hieronder een overzicht van alle aanwezige artikelen: ");
                		
                		List<Artikel> alleArtikelen = new ArrayList<>();
                        
                        try {
                                Connection connection = InEnUitLoggen.getConnectionStatus();
                        	
                        		Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery("SELECT ARTIKEL1_ID, ARTIKEL1_NAAM, ARTIKEL2_ID, ARTIKEL2_NAAM, ARTIKEL3_ID, ARTIKEL3_NAAM FROM Bestelling");
                                 
                                Artikel artikel;
                                while(resultSet.next()){
                                    artikel = new Artikel();                                  
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
                                 
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        for(int i = 0; i < alleArtikelen.size(); i++) {
                            System.out.println("artikel_id: " + alleArtikelen.get(i).getArtikel1_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel1_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel2_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel2_naam() + "\nartikel_id: " + alleArtikelen.get(i).getArtikel3_id() + "\tartikel omschrijving: " + alleArtikelen.get(i).getArtikel3_naam());                          
                        }
                                                       			
            			try (Scanner input3 = new Scanner(System.in);) {
            				
                            System.out.print("\nVoer het artikel ID in dat je in de bestelling wil plaatsen: ");
            				int gewensteArtikel_id = input.nextInt();
            				System.out.print("\nVoer het aantal in dat je in de bestelling wil plaatsen: ");
            				int gewensteAantal = input.nextInt();      
            				
                			nieuweBestelling.setArtikel1_id(gewensteArtikel_id);
                			nieuweBestelling.setArtikel1_aantal(gewensteAantal);

                 
                			
                			
                			try {

                				Connection connection = InEnUitLoggen.getConnectionStatus();
                					
                				PreparedStatement statement = connection.prepareStatement("SELECT ARTIKEL1_NAAM, ARTIKEl1_PRIJS FROM Bestelling WHERE Artikel1_id=?");
                				statement.setInt(1, gewensteArtikel_id);

                				ResultSet resultSet = statement.executeQuery();
                				     		
                                while(resultSet.next()){
                                    
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
                			}
                			
                			}
            			}
            			         	

            		
            		//BestellingMenu bestellingmenu1 = new BestellingMenu();
            		//bestellingmenu1.toonMenu();
            		
            		break; 
            		
            			           		          		
                
            	case 2:          		
            		// System.out.println(InEnUitLoggen.getConnectionStatus());
            	          			
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
            			         		
            		BestellingMenu bestellingmenu2 = new BestellingMenu();
            		bestellingmenu2.toonMenu();
            		
            		break;
                
            	case 3:
            		// etcetera
            		break;
                	
            	case 4:
            		// etcetera
            		break;
            
            	case 5:
            		KlasseSelectieMenu klasseselectiemenu = new KlasseSelectieMenu();
            		klasseselectiemenu.toonMenu();
            		break;
            		
            	case 6:
            		HoofdMenu hoofdmenu = new HoofdMenu();					
            		hoofdmenu.toonMenu(); 
            		break;
            		
            	case 7:
            		System.out.println("\nTot de volgende keer...");
            		System.exit(1);
            		break;
            		
            		
            	default:
            		System.out.println("\n! Ongeldige optie, probeer het nogmaals !\n");
            		this.toonMenu();
			} 
        
		}
		
		finally {
			// zinnige code			
		}	

	}	
}

