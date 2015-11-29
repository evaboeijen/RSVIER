package dao;

import java.sql.DriverManager;
import business.*;
import menu.DBConnectivityManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdresDaoImpl implements AdresDao{
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
	public void insert(Adres adres) {					// Moet nog worden gecontroleerd.
		int klant_id = adres.getKlant_id(); 
		int adres_id = 0;
		
		try {
				Connection connection = DBConnectivityManagement.getConnectionStatus();
								
				if (checkKlant_id(klant_id)){ /*Adres kan alleen toegevoegd worden voor een bestaande klant --> Adres al in DB ?*/
					PreparedStatement searchAdresStatement = connection.prepareStatement("SELECT adres_id FROM adres WHERE straatnaam=?" + 
							", postcode=?, toevoeging=?, huisnummer=?, woonplaats=?");
					searchAdresStatement.setString(1, adres.getStraatnaam());
					searchAdresStatement.setString(2,adres.getPostcode());
					searchAdresStatement.setString(3, adres.getToevoeging());
					searchAdresStatement.setInt(4,  adres.getHuisnummer());
					searchAdresStatement.setString(5, adres.getWoonplaats());
					
					ResultSet resultSetAdresReedsInDB = searchAdresStatement.executeQuery();
					adres_id = resultSetAdresReedsInDB.getInt("adres_id");
					resultSetAdresReedsInDB.close();
					searchAdresStatement.close();
					
					if (!resultSetAdresReedsInDB.next() ){ /*Adres toevoegen als nog niet in DB*/
						PreparedStatement insertAdresStatement = connection.prepareStatement("INSERT INTO adres" +
								"(straatnaam, postcode , toevoeging , huisnummer , woonplaats) VALUES (?,?,?,?,?)");
						insertAdresStatement.setString(1, adres.getStraatnaam());
						insertAdresStatement.setString(2, adres.getPostcode());
						insertAdresStatement.setString(3, adres.getToevoeging());
						insertAdresStatement.setInt(4, adres.getHuisnummer());
						insertAdresStatement.setString(5, adres.getWoonplaats());

						ResultSet resultSetInsertAdres = insertAdresStatement.executeQuery();
						adres_id = resultSetInsertAdres.getInt("adres_id");
						resultSetInsertAdres.close();
						insertAdresStatement.close();
					}
					/*klant_-d & adres_id invoegen in klant_adres tabel*/
					PreparedStatement adresBijKlantStatement = connection.prepareStatement("INSERT into klant_adres klant_id=?, adres_id=? ");
					adresBijKlantStatement.setInt(1, adres.getKlant_id());
					adresBijKlantStatement.setInt(2, adres_id);
					adresBijKlantStatement.executeQuery();
					adresBijKlantStatement.close();
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		System.out.println(adres.toString());
        System.out.println("Adres succesvol toegevoegd");
    }

	@Override
	public void updateAdres(Adres adres) {				// Moet nog worden gecontroleerd. 
		int klant_id = adres.getKlant_id();
		int adres_id = bijpassendAdres_id(klant_id);
		
		if (checkKlant_id(klant_id)){ 
			try {
				Connection connection = DBConnectivityManagement.getConnectionStatus();

				if (checkMeerderePersAdres(klant_id)){ /* Als meerdere personen op adres --> enkel uit klant_adres tabel regel verwijderen*/
					PreparedStatement verwijderKlantBijAdresStatement = connection.prepareStatement("DELETE FROM klant_adres WHERE klant_id=?");
					verwijderKlantBijAdresStatement.setInt(1, klant_id);
					verwijderKlantBijAdresStatement.executeQuery();
					verwijderKlantBijAdresStatement.close();
					insert(adres);
				}
				/*Als maar een iemand op adres --> adres updaten*/
				PreparedStatement updateAdresStatement = connection.prepareStatement("UPDATE adres SET straatnaam=?, postcode=?, toevoeging=?, huisnummer=?, woonplaats=? WHERE adres_id=?");
				updateAdresStatement.setString(1, adres.getStraatnaam());
				updateAdresStatement.setString(2, adres.getPostcode());
				updateAdresStatement.setString(3, adres.getToevoeging());
				updateAdresStatement.setInt(4, adres.getHuisnummer());
				updateAdresStatement.setString(5, adres.getWoonplaats());
				updateAdresStatement.setInt(6,  adres_id);
				updateAdresStatement.executeQuery();
				updateAdresStatement.close();

			} catch (SQLException e){
				e.printStackTrace();
			}
			System.out.println("Adres is succesvol veranderd");
		}
	}	

	@Override
	public void deleteAdres(Adres adres) {				// Moet nog worden gecontroleerd.
		int klant_id = adres.getKlant_id(); 

		if (checkKlant_id(klant_id)){
			try{ 
				Connection connection = DBConnectivityManagement.getConnectionStatus();

				if (!checkMeerderePersAdres(klant_id)){/*Als enige persoon op adres:  adres verwijderen uit adres & klant_adres tabel*/
				PreparedStatement verwijderUitAdresStatement = connection.prepareStatement("DELETE FROM adres WHERE klant_id=?");
				verwijderUitAdresStatement.setInt(1, klant_id);		
				verwijderUitAdresStatement.executeUpdate();
				verwijderUitAdresStatement.close();	
				}
				/*Verwijderen uit klant_adres*/
				PreparedStatement verwijderUitKlant_AdersStatement = connection.prepareStatement("DELETE FROM klant_adres WHERE klant_id=?");
				verwijderUitKlant_AdersStatement.setInt(1, klant_id);
				verwijderUitKlant_AdersStatement.executeUpdate();
				verwijderUitKlant_AdersStatement.close();

				System.out.println("Het adres is succesvol verwijderd ");

			}catch (SQLException e){
			e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Adres> readAllAdresses() {
		List<Adres> adressen = new ArrayList<Adres>();

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
	                
			} catch (SQLException e) {
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
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM adres WHERE straatnaam=?");
			preparedStatement.setString(1, straatnaam);
			resultSet = preparedStatement.executeQuery(); 

				Adres adres;
				while(resultSet.next()){
					adres = new Adres();
					
					adres.setKlant_id(resultSet.getInt("klant_id"));
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(resultSet.getInt("huisnummer"));
					adres.setWoonplaats(resultSet.getString("woonplaats"));

					adressenStraatnaam.add(adres);
				}
				resultSet.close();
				preparedStatement.close();
                
		} catch (SQLException e) {
                e.printStackTrace();
                }
   
		System.out.println(adressenStraatnaam);
		return adressenStraatnaam;
	}

	public List<Adres> searchAdres(String postcode, int huisnummer) {
		List<Adres> adressenPostcodeAndHuisnummer = new LinkedList<Adres>();
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM adres WHERE postcode=? AND huisnummer=? ");
			preparedStatement.setString(1, postcode);
			preparedStatement.setInt(2,  huisnummer);
			resultSet = preparedStatement.executeQuery(); 	
				
				Adres adres;
				while(resultSet.next()){
					adres = new Adres();
					
					adres.setKlant_id(resultSet.getInt("klant_id"));
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(resultSet.getInt("huisnummer"));
					adres.setWoonplaats(resultSet.getString("woonplaats"));

					adressenPostcodeAndHuisnummer.add(adres);
				}
				resultSet.close();
				preparedStatement.close();
		
		} catch (SQLException e) {
            e.printStackTrace();
            }
		
		System.out.println(adressenPostcodeAndHuisnummer);
		return adressenPostcodeAndHuisnummer;
	}

	@Override
	public boolean checkKlant_id(int klant_id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE klant_id=?");
			preparedStatement.setInt(1, klant_id);
			resultSet = preparedStatement.executeQuery(); 	// preparedStatement.close(); uitgecomment op 21/11/15 AU 	-->		Staat nu zowel bij if als else 23-11-2015 EB

			if (resultSet.next()){
				result = true;
				preparedStatement.close();
			} else {
				System.out.println("Het opgegeven klant_id bevindt zich niet in de database...");
				preparedStatement.close();
			}
		} catch (SQLException e) {
                e.printStackTrace();	
		}
		return result;
	}
	
	public boolean checkAdres_id(int adres_id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM adres WHERE adres_id=?");
			preparedStatement.setInt(1, adres_id);
			resultSet = preparedStatement.executeQuery(); 
			
			// preparedStatement.close(); uitgecomment op 21/11/15 AU 	-->		Staat nu zowel bij if als else 23-11-2015 EB

			if (resultSet.next()){
				result = true;
				preparedStatement.close();
			} else {
				System.out.println("Het opgegeven adres_id bevindt zich niet in de database...");
				preparedStatement.close();
			}

		} catch (SQLException e) {
                e.printStackTrace();	
		}
		
		return result;
	}

	public int bijpassendAdres_id(int klant_id){
		ResultSet resultSet;
		int adres_id = 0;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT adres_id FROM klant_adres WHERE klant_id=?");
			preparedStatement.setInt(1,  klant_id);
			resultSet = preparedStatement.executeQuery();
			
			adres_id = resultSet.getInt(1);
			return adres_id;
			
		} catch (SQLException e) {
            e.printStackTrace();
		}
		return adres_id;
	}
	
	public int bijpassendKlant_id(int adres_id){
		ResultSet resultSet;
		int klant_id = 0;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT klant_id FROM klant_adres WHERE adres_id=?");
			preparedStatement.setInt(1,  adres_id);
			resultSet = preparedStatement.executeQuery();
			
			adres_id = resultSet.getInt(1);
			return klant_id;
			
		} catch (SQLException e) {
            e.printStackTrace();
		}
		return klant_id;
	}

	public boolean checkMeerderePersAdres(int klant_id) {
		boolean checkPersonen = false;
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			int adres_id = bijpassendAdres_id(klant_id);
			PreparedStatement checkMeerPersOpAdresStatement = connection.prepareStatement("SELECT COUNT(klant_id) as klantenOpAdres FROM klant_adres WHERE adres_id = ? ");
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
		}catch (SQLException e){
		e.printStackTrace();
		}
		return checkPersonen;
	}	
}
	

