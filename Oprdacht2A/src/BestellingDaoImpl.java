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
	                    bestelling.setBestelling_id(resultSet.getInt("bestelling_id"));
	                    bestelling.setKlant_id(resultSet.getInt("klant_id"));
	                    bestelling.setArtikel1_id(resultSet.getInt("artikel1_id"));
	                    bestelling.setArtikel1_naam(resultSet.getString("artikel1_naam"));
	                    bestelling.setArtikel1_aantal(resultSet.getInt("artikel1_aantal"));
	                    bestelling.setArtikel1_prijs(resultSet.getDouble("artikel1_prijs"));
	                    bestelling.setArtikel2_id(resultSet.getInt("artikel2_id"));
	                    bestelling.setArtikel2_naam(resultSet.getString("artikel2_naam"));
	                    bestelling.setArtikel2_aantal(resultSet.getInt("artikel2_aantal"));
	                    bestelling.setArtikel2_prijs(resultSet.getDouble("artikel2_prijs"));
	                    bestelling.setArtikel3_id(resultSet.getInt("artikel3_id"));
	                    bestelling.setArtikel3_naam(resultSet.getString("artikel3_naam"));
	                    bestelling.setArtikel3_aantal(resultSet.getInt("artikel3_aantal"));
	                    bestelling.setArtikel3_prijs(resultSet.getDouble("artikel3_prijs"));
	                  
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
	            	         	
	            	String sql2 = "UPDATE bestelling SET artikel2_id=?, artikel2_naam=?, artikel2_aantal=?, artikel2_prijs=? WHERE bestelling_id = number";
	            	String sql3 = "UPDATE bestelling SET artikel3_id=?, artikel3_naam=?, artikel3_aantal=?, artikel3_prijs=? WHERE bestelling_id = number";	
	            	                
	            	 if (bestellingen.get(11) != null) {
	            		 System.out.println("Maximum aantal artikelen voor deze bestelling bereikt");
	            		 return;
	            	 }
	                	                
	            	 else if (bestellingen.get(7) == null) {	                
	 	                 PreparedStatement statement2 = connection.prepareStatement(sql2);
	            		 statement2.setInt(7, bestelling.getArtikel2_id());
	            		 statement2.setString(8, bestelling.getArtikel2_naam());
	            		 statement2.setInt(9, bestelling.getArtikel2_aantal());
	            		 statement2.setDouble(10, bestelling.getArtikel2_prijs()); 
	            		 
	 	            	 int rowsUpdated2 = statement2.executeUpdate();
		                 if (rowsUpdated2 > 0) {
		                 System.out.println("Artikel is succesvol toegevoegd!");
	            	 } 
	            	 
	            	 else if (bestellingen.get(7) != null && bestellingen.get(11) == null) {
	            		 PreparedStatement statement3 = connection.prepareStatement(sql3);
	            		 statement3.setInt(11, bestelling.getArtikel3_id());
	            		 statement3.setString(12, bestelling.getArtikel3_naam());
	            		 statement3.setInt(13, bestelling.getArtikel3_aantal());
	            		 statement3.setDouble(14, bestelling.getArtikel3_prijs());
	            		 
	 	            	 int rowsUpdated3 = statement3.executeUpdate();
		                 if (rowsUpdated3 > 0) {
		                 System.out.println("Artikel is succesvol toegevoegd!");
		                 }
	            	 }
	            	 }
	            }
	            	                	            		            			                
               	                       
	           catch (SQLException e) {
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
	
	
	
	
	


