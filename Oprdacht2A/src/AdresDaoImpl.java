import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AdresDaoImpl implements AdresDao{
	Connection connection = null;
	
	public AdresDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	public AdresDaoImpl() {
	}
	
	public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver is geladen ");
           
            if(connection == null){ 
                String dbURL = "jdbc:mysql://localhost:3306/opdracht1";
                String username = "root";
                String password = "";

                Connection connection = DriverManager.getConnection(dbURL, username, password);
                System.out.println("Verbinding is gemaakt");
            }
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
        return connection;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}

	@Override
	public void insert(Adres adres) {
		int klant_idAdres = adres.getKlant_id();
        	try {
        			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Klant" + 
        			"(straatnaam, postcode, toevoeging, huisnummer, woonplaats) values (?, ?, ?, ?, ?)" + 
        			"WHERE klant_id = klant_idAdres");

            		preparedStatement.setString(1, adres.getStraatnaam());
            		preparedStatement.setString(2, adres.getPostcode());
            		preparedStatement.setString(3, adres.getToevoeging());
            		preparedStatement.setInt(4, adres.getHuisnummer());
            		preparedStatement.setString(5, adres.getWoonplaats());

            		
            		preparedStatement.executeUpdate();
            		preparedStatement.close();
            		connection.close();

        	} catch (SQLException e) {
        		e.printStackTrace();
        }

        System.out.println(adres.toString());
        System.out.println("Adres succesvol toegevoegd");
    }

	@Override
	public List<Adres> readAllAdresses() {
		List<Adres> adressen = new LinkedList<Adres>();

			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM klant"); 	/* nu nog in dezelfde tabel!!! */

					Adres adres;
					while(resultSet.next()){
						adres = new Adres();
						adres.setStraatnaam(resultSet.getString("straatnaam"));
						adres.setPostcode(resultSet.getString("postcode"));
						adres.setToevoeging(resultSet.getString("toevoeging"));
						adres.setHuisnummer(Integer.parseInt(resultSet.getString("huisnummer")));
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
	public void updateAdres(Adres adres) {											/* nu nog in dezelfde tabel!!! */
		int klant_idAdres = adres.getKlant_id();
		try {
			getConnection();
		
		String sql = "UPDATE Klant SET straatnaam=?, postcode=?, toevoeging=?, huisnummer=?, woonplaats=? WHERE klant_id = klant_idAdres ";
		
               PreparedStatement preparedStatement = connection.prepareStatement(sql);
              
               
               preparedStatement.setString(1, adres.getStraatnaam());
               preparedStatement.setString(2, adres.getPostcode());
               preparedStatement.setString(3, adres.getToevoeging());
               preparedStatement.setInt(4, adres.getHuisnummer());
               preparedStatement.setString(5, adres.getWoonplaats());

               int rowsUpdated = preparedStatement.executeUpdate();
               if (rowsUpdated > 0) {
               System.out.println("Een bestaand adres is succesvol geupdate ");
               }
       } catch (SQLException e){
               e.printStackTrace();
       }
	}

	@Override
	public void deleteAdres(Adres adres) {											/* nu nog in dezelfde tabel!!!*/
		int klant_idAdres = adres.getKlant_id();
		
		try{ 
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE TABLE Klant straatnaam=null, postcode=null, toevoeging=null, huisnummer=null, woonplaats=null WHERE klant_id = klant_idAdres");
					
			int rowsUpdated = preparedStatement.executeUpdate();
				
				if (rowsUpdated > 0) {
					System.out.println("Het adres is succesvol verwijdert ");

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Adres> searchStraatnaam(String stringStraatnaam) {
		List<Adres> adressenStraatnaam = new LinkedList<Adres>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM klant WHERE straatnaam = stringStraatnaam "); 	/* nu nog in dezelfde tabel!!! */

				Adres adres;
				while(resultSet.next()){
					adres = new Adres();
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(Integer.parseInt(resultSet.getString("huisnummer")));
					adres.setWoonplaats(resultSet.getString("woonplaats"));

					adressenStraatnaam.add(adres);
				}
				resultSet.close();
				statement.close();
                
		} catch (SQLException e) {
                e.printStackTrace();
                }
   System.out.println(adressenStraatnaam);
   return adressenStraatnaam;
	}
	
	public List<Adres> searchPostcodeAndHuisnummer(String stringPostcode, int intHuisnummer) {
		List<Adres> adressenPostcodeAndHuisnummer = new LinkedList<Adres>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM klant Where postcode = stringPostcode AND huisnummer = intHuisnummer"); 	/* nu nog in dezelfde tabel!!! */

				Adres adres;
				while(resultSet.next()){
					adres = new Adres();
					adres.setStraatnaam(resultSet.getString("straatnaam"));
					adres.setPostcode(resultSet.getString("postcode"));
					adres.setToevoeging(resultSet.getString("toevoeging"));
					adres.setHuisnummer(Integer.parseInt(resultSet.getString("huisnummer")));
					adres.setWoonplaats(resultSet.getString("woonplaats"));

					adressenPostcodeAndHuisnummer.add(adres);
				}
				resultSet.close();
				statement.close();
		} catch (SQLException e) {
            e.printStackTrace();
            }
		System.out.println(adressenPostcodeAndHuisnummer);
		return adressenPostcodeAndHuisnummer;
	}
}

