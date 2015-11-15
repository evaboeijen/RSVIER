import java.sql.*;
import java.util.*;

public class BestellingDaoImpl implements BestellingDao {
	
	
	Connection connection = null;
		 
	public void initializeDB() {
			
		
	
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is geladen	");

			if (connection == null) {
				String dbURL = "jdbc:mysql://localhost:3306/test";
				String username = "root";
				String password = "mysql";


				connection = DriverManager.getConnection(dbURL, username, password);	
				System.out.println("Verbinding is gemaakt");
			}
	 	} 

		catch (ClassNotFoundException e) {
 
	            e.printStackTrace();
	             	        } 
		
		catch (SQLException e) {
	             
	            e.printStackTrace();
	             
	        }
	    }
	    
	@Override
	public int create(Bestelling bestelling){
	        
	        PreparedStatement preparedStatement ;
	        int rowsCreated = 0;
            String sql = "insert into Bestelling (bestelling_id, klant_id, artikel1_id, artikel1_naam, artikel1_aantal, artikel1_prijs, artikel2_id, artikel2_naam, artikel2_aantal, artikel2_prijs, artikel3_id, artikel3_naam, artikel3_aantal, artikel3_prijs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        
	        try {
	                        
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
	                   	            
	            rowsCreated = preparedStatement.executeUpdate();	            
	            preparedStatement.close();
	        
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println();
	        System.out.println("Bestelling met ordernummer " + bestelling.getBestelling_id() + " succesvol toegevoegd");
	        
	        System.out.println("Aantal rijen toegevoegd : " + rowsCreated);
	        
	        return rowsCreated;
	       
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
	            return bestellingen;
	    }

	@Override
	public int update(Bestelling bestelling) {       	        
	
        int bestelling_id = bestelling.getBestelling_id();       
        int new_artikel_id = bestelling.getArtikel1_id();
        String new_artikel_naam = bestelling.getArtikel1_naam();
        int new_artikel_aantal = bestelling.getArtikel1_aantal();
        double new_artikel_prijs = bestelling.getArtikel1_prijs();	
               
        int rowsUpdated2 = 0;
        int rowsUpdated3 = 0;
					
		PreparedStatement preparedStatement ;
					String sql = "select * from Bestelling where bestelling_id = ?";
	    	        
	   	         try {	   	                                
	   		            preparedStatement = connection.prepareStatement(sql);	   		            	   		            
	   		            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	   		            ResultSet rset = preparedStatement.executeQuery();
	   		           		            
	   		            
	   		            if (!rset.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestellings ID / Ordernummer " + bestelling_id + " bestaat niet. \nDerhalve kan er niks aan worden toegevoegd.");	
	   			   		  	System.out.println("Aantal rijen geupdate : 0");
	   			   		  	return 0;
	   		            }
	   		            
	   		            rset.beforeFirst();
	   		            
	   		            System.out.println();
	   		            System.out.println("Het opgegeven bestellings ID / Ordernummer luidt: " + bestelling_id);
	   		            
	   		            while(rset.next()){	   		        
		                    int artikelnummer1 = rset.getInt("artikel1_id");
		                    int artikelnummer2 = rset.getInt("artikel2_id");
		                    int artikelnummer3 = rset.getInt("artikel3_id");

			            	String sql2 = "UPDATE bestelling SET artikel2_id=?, artikel2_naam=?, artikel2_aantal=?, artikel2_prijs=? WHERE bestelling_id = ?";
			            	String sql3 = "UPDATE bestelling SET artikel3_id=?, artikel3_naam=?, artikel3_aantal=?, artikel3_prijs=? WHERE bestelling_id = ?";	
			            	                
			            	 if (artikelnummer3 != 0) {
			            		 System.out.println("Er kan niks aan worden toegevoegd, \nwant het maximum aantal artikelen voor deze bestelling is al bereikt.");		            		 
			            	 }
			                	                
			            	 else if (artikelnummer2 == 0) {	                
			 	                 PreparedStatement statement2 = connection.prepareStatement(sql2);
			            		 statement2.setInt(1, new_artikel_id);
			            		 statement2.setString(2, new_artikel_naam);
			            		 statement2.setInt(3, new_artikel_aantal);
			            		 statement2.setDouble(4, new_artikel_prijs);
			            		 statement2.setInt(5, bestelling_id);
			            		 
			 	            	 rowsUpdated2 = statement2.executeUpdate();
				                 if (rowsUpdated2 > 0) {
				                 System.out.println(new_artikel_naam + " (" + new_artikel_aantal + " stuks)" + " is succesvol toegevoegd!");
				                 } 
			            	 }
			            	 
			            	 else if (artikelnummer1 != 0 && artikelnummer2 !=0 && artikelnummer3 == 0) {
			            		 PreparedStatement statement3 = connection.prepareStatement(sql3);
			            		 statement3.setInt(1, new_artikel_id);
			            		 statement3.setString(2, new_artikel_naam);
			            		 statement3.setInt(3, new_artikel_aantal);
			            		 statement3.setDouble(4, new_artikel_prijs);
			            		 statement3.setInt(5, bestelling_id);
			            		 
			 	            	 rowsUpdated3 = statement3.executeUpdate();
				                 if (rowsUpdated3 > 0) {
					                 System.out.println(new_artikel_naam + " (" + new_artikel_aantal + " stuks)" + " is succesvol toegevoegd!");
					                 System.out.println("Deze bestelling bevat nu het maximale aantal van 3 verschillende artikelen.");
				                 }
			            	 }
		                    
		                    
		               
	   		            }
	   		            	   		           
		                rset.close();
		                preparedStatement.close();
		                 
		            } 
				                 
				    catch (SQLException e) {
		                e.printStackTrace();
		            }
	   		             
	   	  if(rowsUpdated2 > 0) {	   	          	         
	   		  System.out.println("Aantal rijen geupdate : " + rowsUpdated2);
	   		  return rowsUpdated2;
	   	  }
	   	  else if (rowsUpdated3 > 0) {
	   		  System.out.println("Aantal rijen geupdate : " + rowsUpdated3);
	   		  return rowsUpdated3;
	   	  }
	   	  else {
	   		  System.out.println("Aantal rijen geupdate : 0");
	   		  return 0;
	   	  }
	   	     	     	   	  
	}
		
	            	 
            	
	           	
		@Override
	public int delete(Bestelling bestelling) {
			
			int bestelling_id = bestelling.getBestelling_id(); 
			int rowsDeleted = 0;
			
			try {

				PreparedStatement statement = connection.prepareStatement("DELETE FROM Bestelling WHERE bestelling_id=?");
				statement.setInt(1, bestelling_id);

				rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println();
					System.out.println("De bestelling met ordernummer " + bestelling_id + " is gewist uit de database.");													
				}
				else {
					System.out.println();
					System.out.println("De bestelling met ordernummer " + bestelling_id + " bestaat niet.");	
				}
									
			} 
			
			catch (SQLException e) {
				e.printStackTrace();	
			}
		
			System.out.println("Ordernummer / bestelling ID: " + bestelling_id + ". Aantal rijen verwijderd : " + rowsDeleted);			
			return rowsDeleted;
		
		}

	 
	
	     
	 public void closeDBConnection(){
	        try {
	              if (connection != null) {
	                  connection.close();
	              }
	            } catch (Exception e) { 
	            	e.printStackTrace();
	            }
	    }
	}
	
	
	
	
	


