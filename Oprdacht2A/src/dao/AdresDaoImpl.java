package dao;

import java.sql.DriverManager;
import business.Adres;
import menu.DBConnectivityManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdresDaoImpl implements AdresDao{
	private static final Logger logger =  LoggerFactory.getLogger(ArtikelDaoImpl.class);
	Connection connection = null;
	
	public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is geladen ");
           
            if(connection == null){ 
                String dbURL = "jdbc:mysql://localhost:3306/opdracht1";
                String username = "root";
                String password = "";

                connection = DriverManager.getConnection(dbURL, username, password);
                System.out.println("Verbinding is gemaakt");
            }
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
        return connection;
	}

	public void closeConnection() {
		try {
            if (connection != null) {
                connection.close();
                System.out.println("Verbinding is gesloten");
            }
          } catch (Exception e) { 
          }
	}

	@Override
	public void insert(Adres adres) {
		int klant_id = adres.getKlant_id(); 
		logger.info("Insert adres methode begint");
		logger.debug("klant_id is : " + klant_id);
		
		try {
				Connection connection = DBConnectivityManagement.getConnectionStatus();
				ResultSet resultSetAdresReedsInDB = null;
				ResultSet resultSetInsertAdres = null;
				
				if (checkKlant_id(klant_id)) { /*Adres kan alleen toegevoegd worden voor een bestaande klant --> Adres al in DB ?*/
					PreparedStatement searchAdresStatement = connection.prepareStatement("SELECT adres_id FROM adres WHERE straatnaam=?" + 
							"AND postcode=? AND toevoeging=? AND huisnummer=? AND woonplaats=?");
					searchAdresStatement.setString(1, adres.getStraatnaam());
					searchAdresStatement.setString(2, adres.getPostcode());
					searchAdresStatement.setString(3, adres.getToevoeging());
					searchAdresStatement.setInt(4, adres.getHuisnummer());
					searchAdresStatement.setString(5, adres.getWoonplaats());
					
					resultSetAdresReedsInDB = searchAdresStatement.executeQuery();					
						if (resultSetAdresReedsInDB.next()) {
							adres.setAdres_id(resultSetAdresReedsInDB.getInt("adres_id"));
						}else { /*Adres toevoegen als nog niet in DB*/
						PreparedStatement insertAdresStatement = connection.prepareStatement("INSERT INTO adres" + 
								"(straatnaam, postcode , toevoeging , huisnummer , woonplaats) VALUES (?,?,?,?,?)");
						insertAdresStatement.setString(1, adres.getStraatnaam());
						insertAdresStatement.setString(2, adres.getPostcode());
						insertAdresStatement.setString(3, adres.getToevoeging());
						insertAdresStatement.setInt(4, adres.getHuisnummer());					
						insertAdresStatement.setString(5, adres.getWoonplaats());

						insertAdresStatement.executeUpdate();
						
						PreparedStatement searchAdres_IdStatement = connection.prepareStatement("SELECT adres_id FROM adres WHERE straatnaam=?" + 
								"AND postcode=? AND toevoeging=? AND huisnummer=? AND woonplaats=?");
						searchAdres_IdStatement.setString(1, adres.getStraatnaam());
						searchAdres_IdStatement.setString(2, adres.getPostcode());
						searchAdres_IdStatement.setString(3, adres.getToevoeging());
						searchAdres_IdStatement.setInt(4, adres.getHuisnummer());
						searchAdres_IdStatement.setString(5, adres.getWoonplaats());
						resultSetInsertAdres = searchAdres_IdStatement.executeQuery();
						
							if (resultSetInsertAdres.next()) {
								adres.setAdres_id(resultSetInsertAdres.getInt("adres_id"));
							}
						resultSetInsertAdres.close();
						insertAdresStatement.close();
						}
					/*klant_-d & adres_id invoegen in klant_adres tabel*/
					PreparedStatement adresBijKlantStatement = connection.prepareStatement("INSERT INTO klant_adres (klant_id, adres_id) VALUES (?,?) ");
					adresBijKlantStatement.setInt(1, adres.getKlant_id());
					adresBijKlantStatement.setInt(2, adres.getAdres_id());
					adresBijKlantStatement.executeUpdate();
					adresBijKlantStatement.close();
					
					resultSetAdresReedsInDB.close();
					searchAdresStatement.close();
					logger.info("Insert adres methode eindigt");	
				}	
		} catch (SQLException e) {
				logger.warn("SQL exeption voor insert adres methode");
				e.printStackTrace();
		}
		System.out.println(adres.toString() + "\nHet nieuwe adres is succesvol gekoppeld aan uw klantnumer.");
    }

	@Override
	public void updateAdres(Adres adres) { 
		int klant_id = adres.getKlant_id();
		Scanner input =  new Scanner(System.in);
		logger.info("Update adres methode begint");
		
		if (checkKlant_id(klant_id)){ 
			try {
				Connection connection = DBConnectivityManagement.getConnectionStatus();
				readAdressesFromKlant(klant_id);
				
				System.out.println("Voer het adres_id in van het adres dat u wil updaten: ");
				int adres_id = input.nextInt();
				logger.info("adres_id = " + adres_id);
					PreparedStatement verwijderKlantBijAdresStatement = connection.prepareStatement("DELETE FROM klant_adres WHERE klant_id=? AND adres_id=?");
					System.out.println(klant_id);
					System.out.println(adres_id);
					verwijderKlantBijAdresStatement.setInt(1, klant_id);
					verwijderKlantBijAdresStatement.setInt(2, adres_id);
					verwijderKlantBijAdresStatement.executeUpdate();
					System.out.println("delete query goed uitgevoerd");
					verwijderKlantBijAdresStatement.close();
					insert(adres);
					logger.info("Insert adres methode eindigt");
			} catch (SQLException e){
				logger.warn("SQL exeption voor update adres methode");
				e.printStackTrace();
			}
			System.out.println("Adres is succesvol veranderd");
		}
	}	

	@Override
	public void deleteAdres(Adres adres) {
		int klant_id = adres.getKlant_id(); 
		Scanner input =  new Scanner(System.in);
		logger.info("Delete adres methode begint");
		
		if (checkKlant_id(klant_id)){
			try{ 
				Connection connection = DBConnectivityManagement.getConnectionStatus();
				
				readAdressesFromKlant(klant_id);				
				System.out.println("Voer het adres_id in van het adres dat u wil updaten: ");
				int adres_id = input.nextInt();
				
				PreparedStatement verwijderKlantBijAdresStatement = connection.prepareStatement("DELETE FROM klant_adres WHERE klant_id=? AND adres_id=?");
				System.out.println(klant_id);
				System.out.println(adres_id);
				verwijderKlantBijAdresStatement.setInt(1, klant_id);
				verwijderKlantBijAdresStatement.setInt(2, adres_id);
				verwijderKlantBijAdresStatement.executeUpdate();
				verwijderKlantBijAdresStatement.close();

				System.out.println("Het adres is succesvol verwijderd ");
				logger.info("Delete adres methode eindigt");
			}catch (SQLException e){
				logger.warn("SQL exeption voor delete adres methode");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Adres> readAllAdresses() {
		List<Adres> adressen = new ArrayList<Adres>();
		logger.info("Read all adresses methode begint");
			
			try {
				Connection connection = DBConnectivityManagement.getConnectionStatus();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM adres"); 

					Adres adres;
					while(resultSet.next()){
						
						adres = new Adres();
						
						adres.setAdres_id(resultSet.getInt("adres_id"));//
						adres.setStraatnaam(resultSet.getString("straatnaam"));
						adres.setPostcode(resultSet.getString("postcode"));
						adres.setToevoeging(resultSet.getString("toevoeging"));
						adres.setHuisnummer(resultSet.getInt("huisnummer"));
						adres.setWoonplaats(resultSet.getString("woonplaats"));
						
						adressen.add(adres);
					}
					resultSet.close();
					statement.close();
	                logger.info("Read all adresses methode eindigt");
			} catch (SQLException e) {
				logger.warn("SQL exeption voor read all adres methode");
	            e.printStackTrace();
	        }
	   System.out.println(adressen);
	   return adressen;
	}
	
	@Override
	public List<Adres> searchAdres(String straatnaam) {
		List<Adres> adressenStraatnaam = new LinkedList<Adres>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		logger.info("Read adres straatnaam methode begint");
		logger.debug("Straatnaam is: " + straatnaam);
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM adres WHERE straatnaam=?");
			preparedStatement.setString(1, straatnaam);
			resultSet = preparedStatement.executeQuery(); 
			Adres adres;
			
			if (!resultSet.next()){
				System.out.println("Er zijn geen adressen gevonden met de opgegeven straatnaam");
			}else{
				while(resultSet.next()){
					adres = new Adres();
					adres.setAdres_id(resultSet.getInt("adres_id"));
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(resultSet.getInt("huisnummer"));
					adres.setWoonplaats(resultSet.getString("woonplaats"));
					adressenStraatnaam.add(adres);
				}
			}
				resultSet.close();
				preparedStatement.close();
                logger.info("Read aders straatnaam methode eindigt");
		} catch (SQLException e) {
			logger.warn("SQL exeption voor read adres straatnaam methode");
			e.printStackTrace();
                }
   
		System.out.println(adressenStraatnaam);
		return adressenStraatnaam;
	}

	public List<Adres> searchAdres(String postcode, int huisnummer) {
		List<Adres> adressenPostcodeAndHuisnummer = new LinkedList<Adres>();
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		logger.info("Read adres postcode & huisnummer methode begint ");
		logger.debug("Postcode is: " + postcode + "\nHuisnummer is: " + huisnummer);
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM adres WHERE postcode=? AND huisnummer=? ");
			preparedStatement.setString(1, postcode);
			preparedStatement.setInt(2,  huisnummer);
			resultSet = preparedStatement.executeQuery(); 
			Adres adres;
			if (!resultSet.next()){
				System.out.println("Er zijn geen adressen gevonden met de opgegeven postcode & huisnummer");
			}else{
				while(resultSet.next()){
					adres = new Adres();
					adres.setAdres_id(resultSet.getInt("adres_id"));
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(resultSet.getInt("huisnummer"));
					adres.setWoonplaats(resultSet.getString("woonplaats"));
					adressenPostcodeAndHuisnummer.add(adres);
				}
			}	
				resultSet.close();
				preparedStatement.close();
				logger.info("Read adres postcode & huisnummer methode eindigt");
		} catch (SQLException e) {
			logger.warn("SQL exeption voor read adres postcode & huisnummer methode");
            e.printStackTrace();
            }
		
		System.out.println(adressenPostcodeAndHuisnummer);
		return adressenPostcodeAndHuisnummer;
	}
	
	public List<Adres> readAdressesFromKlant(int klant_id){
		List<Adres> adressenStraatnaam = new LinkedList<Adres>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		logger.info("Read adressen klantnummer methode begint");
		logger.debug("Klantnummer is: " + klant_id );
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT klant_adres.adres_id, straatnaam, huisnummer, woonplaats FROM adres JOIN klant_adres WHERE klant_adres.klant_id = ? AND klant_adres.adres_id = adres.adres_id");
			preparedStatement.setInt(1, klant_id);
			resultSet = preparedStatement.executeQuery(); 
			Adres adres;
			if (!resultSet.next()){
				System.out.println("Er zijn geen adressen gevonden voor het opgegeven klantnummer");
			}else{
				while(resultSet.next()){
					adres = new Adres();
					adres.setAdres_id(resultSet.getInt("adres_id"));
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(resultSet.getInt("huisnummer"));
					adres.setWoonplaats(resultSet.getString("woonplaats"));
					adressenStraatnaam.add(adres);
				}
			}	
				resultSet.close();
				preparedStatement.close();
                logger.info("Read adressen klantnummer methode eindigt" );
		} catch (SQLException e) {
			logger.warn("SQL exeption voor read adressen van klant methode");
			e.printStackTrace();
                }
   
		System.out.println(adressenStraatnaam);
		return adressenStraatnaam;
	}
	
	@Override
	public boolean checkKlant_id(int klant_id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		logger.info("Check klantnummer methode begint");
		logger.debug("Klantnummer is : "+ klant_id);
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE klant_id=?");
			preparedStatement.setInt(1, klant_id);
			resultSet = preparedStatement.executeQuery(); 	// preparedStatement.close(); uitgecomment op 21/11/15 AU 	-->		Staat nu zowel bij if als else 23-11-2015 EB
logger.debug("SQL query is: "+ preparedStatement);
			if (resultSet.next()){
				result = true;
				preparedStatement.close();
			} else {
				System.out.println("Het opgegeven klant_id bevindt zich niet in de database...");
				preparedStatement.close();
			}
			logger.info("Check klantnummer methode eindigt");
		} catch (SQLException e) {
			logger.warn("SQL exeption voor checkKlant_id methode");
			e.printStackTrace();	
		}
		return result;
	}

	public boolean checkMeerderePersAdres(int adres_id) {
		boolean checkPersonen = false;
		logger.info("Check meerdere personen op adres methode begint");
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			PreparedStatement checkMeerPersOpAdresStatement = connection.prepareStatement("SELECT COUNT(klant_id) as klantenOpAdres FROM klant_adres WHERE adres_id=? ");
			checkMeerPersOpAdresStatement.setInt(1, adres_id);
			ResultSet meerPersOpAdres = checkMeerPersOpAdresStatement.executeQuery(); 
			while (meerPersOpAdres.next()){
				int count = meerPersOpAdres.getInt("klantenOpAdres");
					if (count > 1) {
						checkPersonen = true;
					}
			}		
			meerPersOpAdres.close();
			checkMeerPersOpAdresStatement.close();	
			logger.info("Check meerdere personen op adres methode eindigt");
		}catch (SQLException e){
			logger.warn("SQL exeption voor check meerdere personen op adres methode");
			e.printStackTrace();
		}
		return checkPersonen;
	}	

}