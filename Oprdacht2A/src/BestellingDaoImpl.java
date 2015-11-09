import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class BestellingDaoImpl extends Bestelling implements BestellingDao {
	
	
	Connection connection = null;
		 
	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			if (connection == null) {
				String dbURL = "jdbc:mysql://localhost:3306/javabook";
				String username = "root";
				String password = "mysql";

				connection = DriverManager.getConnection(dbURL, username, password);
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
	    
	   @Override
	    public void insert(Bestelling bestelling){
	        
	        PreparedStatement preparedStatement ;
	        
	        try {
	                        
	            String sql = "insert into Bestelling (bestelling_id, klant_id, artikel1_id, artikel1_naam, artikel1_aantal, artikel1_prijs, artikel2_id, artikel2_naam, artikel2_aantal, artikel2_prijs, artikel3_id, artikel3_naam, artikel3_aantal, artikel3_prijs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setInt(1, bestelling.getBestelling_id());
	            preparedStatement.setInt(2, bestelling.getKlant_id());
	            preparedStatement.setInt(3, bestelling.getArtikel1_id());
	            preparedStatement.setString(4, bestelling.getArtikel1_naam());
	            preparedStatement.setInt(5, bestelling.getArtikel1_aantal());
	            preparedStatement.setDouble(6, bestelling.getArtikel1_prijs());	  
	            preparedStatement.setInt(7, bestelling.getArtikel2_id());
	            preparedStatement.setString(8, bestelling.getArtikel2_naam());
	            preparedStatement.setInt(9, bestelling.getArtikel2_aantal());
	            preparedStatement.setDouble(10, bestelling.getArtikel2_prijs());	
	            preparedStatement.setInt(11, bestelling.getArtikel3_id());
	            preparedStatement.setString(12, bestelling.getArtikel3_naam());
	            preparedStatement.setInt(13, bestelling.getArtikel3_aantal());
	            preparedStatement.setDouble(14, bestelling.getArtikel3_prijs());	
	                   	            
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println(bestelling);
	        System.out.println("Bestelling succesvol toegevoegd");
	    }
	 
	   @Override
	    public List<Klant> select() {
	        List<Klant> klanten = new LinkedList<Klant>();
	         try {
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM klant");
	                 
	                Klant klant;
	                while(resultSet.next()){
	                    klant = new Klant();
	                    klant.setKlant_id(Integer.parseInt(resultSet.getString("klant_id")));
	                    klant.setVoornaam(resultSet.getString("voornaam"));
	                    klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
	                    klant.setAchternaam(resultSet.getString("achternaam"));
	                    klant.setEmail(resultSet.getString("email"));
	                    
	                    klanten.add(klant);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(klanten);
	            return klanten;
	    }
	    
	    @Override
		public Klant getKlant(int klant_id) {
			// TODO Auto-generated method stub
			return null;
		}
	
		public void update(Bestelling bestelling) {
			// TODO Auto-generated method stub
	            try {
	             String sql = "UPDATE klant SET voornaam=?, tussenvoegsel=?, achternaam=?, email=? WHERE klant_id = number";
	
	                PreparedStatement statement = connection.prepareStatement(sql);
	                statement.setString(2, "Frank");
	                statement.setString(3, "de");
	                statement.setString(4, "Boer");
	                statement.setString(5, "frankdeboer@gmail.com");
	
	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                System.out.println("An existing user was updated successfully!");
	                }
	        } catch (SQLException e){
	                e.printStackTrace();
	        }
		}
	
		public void delete(Bestelling bestelling) {
			// TODO Auto-generated method stub
			
		}
	 
	
	     
	    public void closeConnection(){
	        try {
	              if (connection != null) {
	                  connection.close();
	              }
	            } catch (Exception e) { 
	                //do nothing
	            }
	    }




	}
	
	
	
	
	


