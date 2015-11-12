import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public abstract class AdresDaoImpl extends Adres implements AdresDao{
	Connection connection = getConnection();
	
	public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

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
        System.out.println("Connection succesful");
        return connection;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}

	@Override
	public void insert(Adres adres) {
		PreparedStatement preparedStatement = null;

        		String sql = "INSERT INTO Klant" +  "(straatnaam, postcode, toevoeging, huisnummer, woonplaats) values" + "(?, ?, ?, ?, ?)";
        			try {
        			preparedStatement = connection.prepareStatement(sql);
        			
            		preparedStatement.setString (1, adres.getStraatnaam());
            		preparedStatement.setString (2, adres.getPostcode());
            		preparedStatement.setString (3, adres.getToevoeging());
            		preparedStatement.setInt    (4, adres.getHuisnummer());
            		preparedStatement.setString (5, adres.getWoonplaats());

            		preparedStatement.executeUpdate();
            		preparedStatement.close();
            		connection.close();

        	} catch (SQLException e) {
        		e.printStackTrace();
        }

        System.out.println(adres);
        System.out.println("Adres succesvol toegevoegd");
    }

	@Override
	public List<Adres> select() {
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
	public void updateAdres(Adres adres) {												/* nu nog in dezelfde tabel!!! */
		try {
			getConnection();
		
		String sql = "UPDATE Klant SET straatnaam=?, postcode=?, toevoeging=?, huisnummer=?, woonplaats=? WHERE klant_id = ? ";
		
               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(2, adres.getStraatnaam());
               statement.setString(3, adres.getPostcode());
               statement.setString(4, adres.getToevoeging());
               statement.setInt(5, adres.getHuisnummer());
               statement.setString(6, adres.getWoonplaats());

               int rowsUpdated = statement.executeUpdate();
               if (rowsUpdated > 0) {
               System.out.println("Een bestaand adres is succesvol geupdate ");
               }
       } catch (SQLException e){
               e.printStackTrace();
       }
	}
		

	@Override
	public void deleteAdres(Adres adres) {											/* nu nog in dezelfde tabel!!!*/
		try{ 
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Klant straatnaam=?, postcode=?, toevoeging=?, huisnummer=?, woonplaats=? WHERE klant_id = ?");
		
		int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Het adres is succesvol verwijdert ");

			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getAdres(Adres adres) {
		try{
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM klant WHERE klant_id = ?");
				ResultSetMetaData rSMetaData = resultSet.getMetaData();
				int kolommen = rSMetaData.getColumnCount();

					while (resultSet.next()){
						for (int i = 1; i <= kolommen; i++){
							if (i > 1) System.out.print(",  ");
						String kolomWaarde = resultSet.getString(i);
						System.out.print(kolomWaarde + " " + rSMetaData.getColumnName(i));
						}
					System.out.println("");
					}
				}catch (SQLException e){
				e.printStackTrace();
				}
		}
}
