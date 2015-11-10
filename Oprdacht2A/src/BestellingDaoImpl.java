import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class BestellingDaoImpl implements BestellingDao {
	
	
	Connection connection = null;
		 
	public Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			if (connection == null) {
				String dbURL = "jdbc:mysql://localhost:3306/javabook";
				String username = "root";
				String password = "mysql";

				connection = DriverManager.getConnection(dbURL, username, password);		
			}
	 
	        } catch (ClassNotFoundException e) {
	 
	            e.printStackTrace();
	             
	        } catch (SQLException e) {
	             
	            e.printStackTrace();
	             
	        }
	        System.out.println("Verbinding is gemaakt");
	        return connection;
	        
	    }
	    
	   @Override
	    public void create(Bestelling bestelling){
	        
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
	    public List<Bestelling> read() {
	        List<Bestelling> bestellingen = new LinkedList<Bestelling>();
	         try {
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM bestelling");
	                 
	                Bestelling bestelling;
	                while(resultSet.next()){
	                    bestelling = new Bestelling();
	                    bestelling.setBestelling_id(Integer.parseInt(resultSet.getString("bestelling_id")));
	                    bestelling.setKlant_id(Integer.parseInt(resultSet.getString("klant_id")));
	                    bestelling.setArtikel1_id(Integer.parseInt(resultSet.getString("artikel1_id")));
	                    bestelling.setArtikel1_naam(resultSet.getString("artikel1_naam"));
	                    bestelling.setArtikel1_aantal(Integer.parseInt(resultSet.getString("artikel1_aantal")));
	                    bestelling.setArtikel1_prijs(Double.parseDouble(resultSet.getString("artikel1_prijs")));
	                    bestelling.setArtikel2_id(Integer.parseInt(resultSet.getString("artikel2_id")));
	                    bestelling.setArtikel2_naam(resultSet.getString("artikel2_naam"));
	                    bestelling.setArtikel2_aantal(Integer.parseInt(resultSet.getString("artikel2_aantal")));
	                    bestelling.setArtikel2_prijs(Double.parseDouble(resultSet.getString("artikel2_prijs")));
	                    bestelling.setArtikel3_id(Integer.parseInt(resultSet.getString("artikel3_id")));
	                    bestelling.setArtikel3_naam(resultSet.getString("artikel3_naam"));
	                    bestelling.setArtikel3_aantal(Integer.parseInt(resultSet.getString("artikel3_aantal")));
	                    bestelling.setArtikel3_prijs(Double.parseDouble(resultSet.getString("artikel3_prijs")));
	                  
	                    bestellingen.add(bestelling);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(bestellingen);
	            return bestellingen;
	    }

		public void update(Bestelling bestelling) {
			
	            try {
	             
	            	List<Bestelling> bestellingen = read();            	
	            	
	            	 if (bestellingen.get(11) != null) {
	            		 System.out.println("Maximum aantal artikelen voor deze bestelling bereikt");
	            		 return;
	            	 }
	            	
	            	String sql = "UPDATE bestelling SET artikel?_id=?, artikel?_naam=?, artikel?_aantal=?, artikel?_prijs=? WHERE bestelling_id = number";
	
	                PreparedStatement statement = connection.prepareStatement(sql);
	                statement.setInt(2, 12345);
	                statement.setString(3, "TestArtikel");
	                statement.setInt(4, 69);
	                statement.setDouble(5, 20.25);
	
	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                System.out.println("Artikel is succesvol toegevoegd!");
	                }
	        } catch (SQLException e){
	                e.printStackTrace();
	        }
		}
	
		@Override
		public void delete(Bestelling bestelling) {
			try {

				PreparedStatement statement = connection.prepareStatement("DELETE FROM Bestelling WHERE Bestelling_id=?");
				statement.setInt(1, bestelling.getBestelling_id());

				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("De bestelling is gewist uit de database");
				}
			} catch (SQLException e) {
				e.printStackTrace();	
			}
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
	
	
	
	
	


