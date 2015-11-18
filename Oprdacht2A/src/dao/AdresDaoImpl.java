package dao;

import java.sql.DriverManager;
import business.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	public void insert(Adres adres) {
		
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO klant WHERE klant_id=? (straatnaam, postcode , toevoeging , huisnummer , woonplaats)VALUES(?,?,?,?,?)");

					preparedStatement.setString(1, adres.getStraatnaam());
					preparedStatement.setString(2, adres.getPostcode());
					preparedStatement.setString(3, adres.getToevoeging());
					preparedStatement.setInt(4, adres.getHuisnummer());
					preparedStatement.setString(5, adres.getWoonplaats());
					preparedStatement.setInt(6,  adres.getKlant_id());

					int rowsUpdated = preparedStatement.executeUpdate();
					preparedStatement.close();
							
						if (rowsUpdated > 0){
								System.out.println("Het adres is succesvol toegevoegd ");
							}
			} catch (SQLException e) {
				e.printStackTrace();
			}

        System.out.println(adres.toString());
        System.out.println("Adres succesvol toegevoegd");
    }

	@Override
	public List<Adres> readAllAdresses() {
		List<Adres> adressen = new ArrayList<Adres>();

			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM klant"); 

					Adres adres;
					while(resultSet.next()){
						
						adres = new Adres();
						
						adres.setStraatnaam(resultSet.getString("straatnaam"));
						adres.setPostcode(resultSet.getString("postcode"));
						adres.setToevoeging(resultSet.getString("toevoeging"));
						adres.setHuisnummer(resultSet.getInt("huisnummer"));
						adres.setWoonplaats(resultSet.getString("woonplaats"));
						adres.setKlant_id(resultSet.getInt("klant_id"));

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
	public void updateAdres(Adres adres) {																/* nu nog in dezelfde tabel!!! */
		
		try {
			
               PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Klant SET straatnaam=?, postcode=?, toevoeging=?, huisnummer=?, woonplaats=? WHERE klant_id =? ");
              
               preparedStatement.setString(1, adres.getStraatnaam());
               preparedStatement.setString(2, adres.getPostcode());
               preparedStatement.setString(3, adres.getToevoeging());
               preparedStatement.setInt(4, adres.getHuisnummer());
               preparedStatement.setString(5, adres.getWoonplaats());
               preparedStatement.setInt(6, adres.getKlant_id());

               preparedStatement.executeUpdate();
               preparedStatement.close();
               
               

       } catch (SQLException e){
         e.printStackTrace();
       }
		System.out.println("Adres is succesvol veranderd");
	}

	@Override
	public void deleteAdres(Adres adres) {																/* nu nog in dezelfde tabel!!!*/
		
		try{ 
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE klant SET straatnaam='-', postcode='-', toevoeging='-', huisnummer='-', woonplaats='-' WHERE klant_id=?");
			preparedStatement.setInt(1, adres.getKlant_id());		
			
			int rowsUpdated = preparedStatement.executeUpdate();
			preparedStatement.close();	
				
				if (rowsUpdated > 0) {
					System.out.println("Het adres is succesvol verwijdert ");
				}
				
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Adres> searchAdres(String straatnaam) {
		List<Adres> adressenStraatnaam = new LinkedList<Adres>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		try {
			preparedStatement = connection.prepareStatement("SELCT * FROM klant WHERE straatnaam=?");
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
			preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE postcode=? AND huisnummer=? ");
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
}

