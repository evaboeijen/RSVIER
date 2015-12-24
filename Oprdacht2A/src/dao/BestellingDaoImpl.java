package dao;
import java.sql.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import business.Bestelling;
import menu.DBConnectivityManagement;

public abstract class BestellingDaoImpl implements BestellingDao {
	
	private static final Logger logger =  LoggerFactory.getLogger(BestellingDaoImpl.class);
	
	public Connection connection = null;
	
	String queryBestellingToevoegen1 = null;
	String queryBestellingToevoegen2 = null;
	String queryToonAlleBestellingen = null;
	String queryUpdateBestelling1 = null;
	String queryUpdateBestelling2 = null;
	String queryDeleteBestelling = null;
	String queryDeleteArtikelFromBestelling = null;
	String queryUpdateAantallen = null;
	
		 
	// oude methode die niet meer gebruikt wordt - AU 24/12/15
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
	        
		PreparedStatement preparedStatement1 ;	// aangepast tbv opdracht 5 || 26/11/15 AU
		PreparedStatement preparedStatement2 ;	// toegevoegd tbv opdracht 5 || 26/11/15 AU	
			logger.info("Content of connection object is : " + connection);
	        // int rowsCreated = 0; // uitgecomment tbv opdracht 5 || 26/11/15 AU
	        int rowsCreated = 0; // toegevoegd tbv opdracht 5 || 26/11/15 AU
	      	        
        	logger.info("Content of connection object is : " + connection);
        		        	       	        
	        /* uitgecomment tbv opdracht 5 || 26/11/15 AU
	        String sql = "insert into Bestelling (bestelling_id, klant_id, artikel1_id, artikel1_naam, artikel1_aantal, artikel1_prijs, artikel2_id, artikel2_naam, artikel2_aantal, artikel2_prijs, artikel3_id, artikel3_naam, artikel3_aantal, artikel3_prijs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			*/
	        // toegevoegd tbv opdracht 5 || 26/11/15 AU
	        	        
	        
	        try {
	                 
	        	Connection connection = DBConnectivityManagement.getConnectionStatus();
	        	
	        	logger.info("Content of connection object is : " + connection);
	        	
	        	//connection.setAutoCommit(false);           
	            
	        	preparedStatement1 = connection.prepareStatement(queryBestellingToevoegen1);
	            preparedStatement1.setInt(1, bestelling.getBestelling_id());
	            preparedStatement1.setInt(2, bestelling.getKlant_id());
	            
	            // toegevoegd tbv opdracht 5 || 26/11/15 AU
	            //preparedStatement2 = connection.prepareStatement(sql2);
	            //preparedStatement2.setInt(1, bestelling.getArtikel_id());
	            //preparedStatement2.setString(2, bestelling.getArtikel_naam());	           
	            //preparedStatement2.setDouble(3, bestelling.getArtikel_prijs());	 
	            
	            /* uitgecomment tbv opdracht 5 || 26/11/15 AU
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
	            */
	            
	            // toegevoegd tbv opdracht 5 || 26/11/15 AU
	            preparedStatement2 = connection.prepareStatement(queryBestellingToevoegen2);
	            preparedStatement2.setInt(1, bestelling.getBestelling_id());
	            preparedStatement2.setInt(2, bestelling.getArtikel_id());
	            preparedStatement2.setInt(3, bestelling.getArtikel_aantal());
	            

	            rowsCreated = preparedStatement1.executeUpdate();            	           
	            preparedStatement2.executeUpdate();	        

	            logger.info("rowsCreated is " + rowsCreated);
	          
	            logger.info("preparedStatement1 is " + preparedStatement2);
	            logger.info("preparedStatement2 is " + preparedStatement2);
	            
	            preparedStatement1.close();	 
	            logger.info("preparedStatement1 closed and is " + preparedStatement1);
	            preparedStatement2.close();
	            logger.info("preparedStatement2 closed and is " + preparedStatement2);
	            
	            //connection.setAutoCommit(true); 
	        
	            
	        } catch (SQLException e) {
	        	logger.warn("SQL exception voor BestellingDaoImpl.create() methode");
	            e.printStackTrace();
	        }
	        System.out.println();
	        System.out.println("Bestelling met ordernummer " + bestelling.getBestelling_id() + " voor klantnummer " + bestelling.getKlant_id() + " succesvol toegevoegd");
	     // aangepast tbv opdracht 5 || 26/11/15 AU
	        System.out.println("Inhoud van de bestelling: " + bestelling.getArtikel_aantal() + " stuks " + bestelling.getArtikel_naam() + " (" + (bestelling.getArtikel_prijs()*100)/100.00 + " euro per stuk) en met totaalprijs van " + ((bestelling.getArtikel_aantal() * bestelling.getArtikel_prijs())*100)/100.00 + " euro.");	         
	        System.out.println("Aantal rijen in tabel Bestelling toegevoegd : " + rowsCreated);
	        System.out.println();
	        
	        return rowsCreated;
	        
	        
	       
		}
	 
	@Override
	public List<Bestelling> read() {
	        List<Bestelling> bestellingen = new LinkedList<Bestelling>();
	         try {
	        	 	logger.debug("Content of connection object is : " + connection);
	        	 	logger.info("Execute DBConnectivityManagement.getConnectionStatus()");
	                Connection connection = DBConnectivityManagement.getConnectionStatus();	
	                logger.debug("Content of connection object is : " + connection);
	                
	        	 	Statement statement = connection.createStatement();	               
	                // oude query : ResultSet resultSet = statement.executeQuery("SELECT * FROM bestelling");
	                // nieuwe query tbv opdracht 5 :
	        	 	ResultSet resultSet = statement.executeQuery(queryToonAlleBestellingen);
	        	 	    	 	
	                Bestelling bestelling;
	                while(resultSet.next()){
	                    bestelling = new Bestelling();
	                    bestelling.setBestelling_id(resultSet.getInt("bestelling_id"));	                   	                   	                    
	                    bestelling.setKlant_id(resultSet.getInt("klant_id"));	
	                    // nieuwe statements tbv opdracht 5 - 27/11/15 - AU
	                    bestelling.setArtikel_id(resultSet.getInt("artikel_id"));
	                    bestelling.setArtikel_naam(resultSet.getString("artikel_naam"));
	                    bestelling.setArtikel_aantal(resultSet.getInt("artikel_aantal"));
	                    bestelling.setArtikel_prijs(resultSet.getDouble("artikel_prijs"));
	                    
	                    /* uitgecomment tbv opdracht 5 - 27/11/15 - AU
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
	                    */
	                  
	                    bestellingen.add(bestelling);
	                }
	                logger.info("resultSet is " + resultSet);
	                logger.trace("logtrace");
	                logger.info("resultSet.close is being called");
	                resultSet.close();
	                logger.info("resultSet is " + resultSet);
	                statement.close();
	                 
	            } catch (SQLException e) {
	            	logger.warn("SQL exception voor BestellingDaoImpl.read() methode");
	                e.printStackTrace();
	            }
	            return bestellingen;
	    }

	@Override
	public int update(Bestelling bestelling) {       	        
	
        int bestelling_id = bestelling.getBestelling_id();       
        int new_artikel_id = bestelling.getArtikel_id();
        String new_artikel_naam = bestelling.getArtikel_naam();
        int new_artikel_aantal = bestelling.getArtikel_aantal();
        double new_artikel_prijs = bestelling.getArtikel_prijs();	
        
        int rowsUpdated = 0;
  
        //int rowsUpdated2 = 0;
        //int rowsUpdated3 = 0;
					
		PreparedStatement preparedStatement ;
					
	    	        
	   	         try {	  
	   	        	 	logger.info("Content of connection object is : " + connection);
	   	        	 	Connection connection = DBConnectivityManagement.getConnectionStatus();
	   	        	 	logger.info("Content of connection object is : " + connection);
	   	        	 
	   		            preparedStatement = connection.prepareStatement(queryUpdateBestelling1);	   		            	   		            
	   		            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	   		            ResultSet rset = preparedStatement.executeQuery();
	   		           		            
	   		            
	   		            /* if (!rset.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestellings ID / Ordernummer " + bestelling_id + " bestaat niet. \nDerhalve kan er niks aan worden toegevoegd.");	
	   			   		  	System.out.println("Aantal rijen geupdate : 0");
	   			   		  	return 0;
	   		            } 
	   		            
	   		            rset.beforeFirst(); */
	   		            
	   		            System.out.println();
	   		            System.out.println("Het opgegeven bestellings ID / Ordernummer luidt: " + bestelling_id);
	   		            
	   		            //while(rset.next()){	   	
	   		            	// opdracht 5 - 27/11/15 - AU : 
	   		            	//int artikelnummer = rset.getInt("artikel_id");
	   		            	// opdracht 5 - 27/11/15 - AU : int artikelnummer1 = rset.getInt("artikel1_id");
		                    // opdracht 5 - 27/11/15 - AU : int artikelnummer2 = rset.getInt("artikel2_id");
		                    // opdracht 5 - 27/11/15 - AU : int artikelnummer3 = rset.getInt("artikel3_id");

			            	/* uitgecomment tbv opdracht 5 - 27/11/15 - AU
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
			            	 } */
		                    
	   		            	  		            	
	   		            // nieuw tbv opdracht 5 - 27/11/15 - AU	
	   		            	
			            				            	                               
			 	                 PreparedStatement statement1 = connection.prepareStatement(queryUpdateBestelling2);
			 	                 statement1.setInt(1, bestelling_id);
			 	                 statement1.setInt(2, new_artikel_id);
			            		 statement1.setInt(3, new_artikel_aantal);
		            		 
			            		 int rowsUpdated1 = 0;
			 	            	 rowsUpdated = statement1.executeUpdate();
				                 if (rowsUpdated1 > 0) {
				                	 System.out.println(new_artikel_naam + " (" + new_artikel_aantal + " stuks)" + " succesvol toegevoegd!");
				                 } 	
				                 return rowsUpdated1;
	   	         			}
				                 
				    catch (SQLException e) {
				    	logger.warn("SQL exception voor BestellingDaoImpl.update() methode");
		                e.printStackTrace();
		            }
	   		             
	   	         
	   	   // opdracht 5 - 27/11/15 - AU :        
	   	  /* if(rowsUpdated2 > 0) {	   	          	         
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
	   	  } */
	   	         
	   	   // opdracht 5 - 27/11/15 - AU : 
	   	      return rowsUpdated;
	   		  
	}
	
public int updateAantallen(Bestelling bestelling) {		// added 29-11-15 AU
		
		
		int bestelling_id = bestelling.getBestelling_id();       
        int new_artikel_id = bestelling.getArtikel_id();
        String new_artikel_naam = bestelling.getArtikel_naam();
        int new_artikel_aantal = bestelling.getArtikel_aantal();
        double new_artikel_prijs = bestelling.getArtikel_prijs();	
        int oud_artikel_aantal = 0;
        
        int rowsUpdated = 0;
  				
		PreparedStatement preparedStatement ;
					
	   	         try {	
	   	        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
	 	        	logger.info("Content of connection object is : " + connection);

	   	        		preparedStatement = connection.prepareStatement(queryUpdateAantallen);	   		            	   		            
	   		            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	   		            ResultSet rset = preparedStatement.executeQuery();
	   		           		            
	   		            logger.info("ResultSet reset is: " + rset);
	   		            
	   		            /* if (!rset.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestellings ID / Ordernummer " + bestelling_id + " bestaat niet. \nDerhalve kan er niks aan worden toegevoegd.");	
	   			   		  	System.out.println("Aantal rijen geupdate : 0");
	   			   		  	return 0;
	   		            }
	   		                       		            	   		            
	   		            rset.beforeFirst(); */
	   		           
	   		            
	   		            while (rset.next()) {	   		            	
	   		            	oud_artikel_aantal = rset.getInt("artikel_aantal");	 
	   		            	logger.info("oud_artikel_aantal " + oud_artikel_aantal);
	   		            }
	   		            
	   		            String sql2 = "update Bestelling_Artikel set artikel_aantal = ? WHERE bestelling_id = ? AND artikel_id = ?";
			            				            	                               
			 	                 PreparedStatement statement1 = connection.prepareStatement(sql2);
			 	                 statement1.setInt(1, new_artikel_aantal);
			 	                 statement1.setInt(2, bestelling_id);
			 	                 statement1.setInt(3, new_artikel_id);
			            		 
		            		 
			            		 int rowsUpdated1 = 0;
			 	            	 rowsUpdated1 = statement1.executeUpdate();
				                 if (rowsUpdated1 > 0) {
				                	 System.out.println("");
				                	 System.out.println("Aantal " +  new_artikel_naam + " in bestelling " + bestelling_id + " aangepast van "+ oud_artikel_aantal + " stuks naar " + new_artikel_aantal + " stuks.");
				                	 System.out.println("");
				                 } 	
				                 return rowsUpdated1;
	   	         			}
				                 
				    catch (SQLException e) {
				    	logger.warn("SQL exception voor BestellingDaoImpl.updateAantallen() methode");
		                e.printStackTrace();
		            }
	   		             
	   	         
	   	   // opdracht 5 - 27/11/15 - AU :        
	   	  /* if(rowsUpdated2 > 0) {	   	          	         
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
	   	  } */
	   	         
	   	   // opdracht 5 - 27/11/15 - AU : 
	   	      return rowsUpdated;
	   		  
	}		
	
	
	            	           		           	
		@Override
	public int delete(Bestelling bestelling) {
			
			int bestelling_id = bestelling.getBestelling_id(); 
			int rowsDeleted = 0;
			
			try {

	        	 logger.info("Content of connection object is : " + connection);
	        	 Connection connection = DBConnectivityManagement.getConnectionStatus();
	        	 logger.info("Content of connection object is : " + connection);
					
				PreparedStatement statement = connection.prepareStatement(queryDeleteBestelling);
				
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
				logger.warn("SQL exception voor BestellingDaoImpl.delete() methode");
				e.printStackTrace();	
			}
		
			System.out.println("Ordernummer / bestelling ID: " + bestelling_id + ". Aantal rijen verwijderd : " + rowsDeleted);			
			return rowsDeleted;
		
		}

	 
		@Override
	public int deleteArtikelFromBestelling(Bestelling bestelling) {
			
			int bestelling_id = bestelling.getBestelling_id(); 
			int artikel_id = bestelling.getArtikel_id();
			int rowsDeleted = 0;
			
			try {

	        	logger.info("Content of connection object is : " + connection);
	        	Connection connection = DBConnectivityManagement.getConnectionStatus();
	        	logger.info("Content of connection object is : " + connection);
				
	        	logger.info("calling queryDeleteArtikelFromBestelling" + queryDeleteArtikelFromBestelling);
				PreparedStatement statement = connection.prepareStatement(queryDeleteArtikelFromBestelling);
				logger.info("queryDeleteArtikelFromBestelling uitgevoerd" + queryDeleteArtikelFromBestelling);
				statement.setInt(1, artikel_id);
				statement.setInt(2, bestelling_id);

				rowsDeleted = statement.executeUpdate();
				
				logger.info("rowsDeleted: " + rowsDeleted);
				
				if (rowsDeleted > 0) {
					System.out.println();
					System.out.println("Artikel " + artikel_id + " is gewist van bestelling " + bestelling_id + ".");													
				}
				else {
					System.out.println();
					System.out.println("Artikel " + artikel_id + " bestaat niet in bestelling " + bestelling_id + ".");	
				}
									
			} 
			
			catch (SQLException e) {
				logger.warn("SQL exception voor BestellingDaoImpl.deleteArtikelFromBestelling() methode");
				e.printStackTrace();	
			}
		
			System.out.println("Aantal rijen verwijderd : " + rowsDeleted);			
			return rowsDeleted;
		
		}

		
		
	//oude methode die niet meer gebruikt wordt - 14/12/15 AU */   
	 public void closeDBConnection(){
	        try {
	        		Connection connection = DBConnectivityManagement.getConnectionStatus();
	        	
	              if (connection != null) {
	                  connection.close();
	              }
	        } 
	        catch (Exception e) { 
	        	logger.warn("SQL exception voor BestellingDaoImpl.closeDBConnection() methode");
	            	e.printStackTrace();
	        }
	        
	        
	 } 	
	 
	 
}
	
	
	
	
	


