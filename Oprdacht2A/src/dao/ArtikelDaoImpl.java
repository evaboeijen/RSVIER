package dao;

import java.sql.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.*;
import menu.*;

public class ArtikelDaoImpl implements ArtikelDao {
	
private static final Logger logger =  LoggerFactory.getLogger(ArtikelDaoImpl.class);
    
    public Connection connection = null;


   public Connection initializeDB(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            if(connection == null){ 
                String dbURL = "jdbc:mysql://localhost:3308/opdracht1";
                String username = "root";
                String password = "JaRsvier15";

        
                connection = DriverManager.getConnection(dbURL, username, password);
                System.out.println("Connectie is gemaakt");
            }
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
        
        return connection;
        
    }
   @Override
   public List<Artikel> read() {
   logger.info("read all artikelen methode start");
  
	   List<Artikel> artikelen = new ArrayList<>();
                
       try {
       	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
       	
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM Artikel");
                
               Artikel artikel;
               while(resultSet.next()){
                   artikel = new Artikel();
                   artikel.setArtikel_id(resultSet.getInt("Artikel_id"));
                   artikel.setArtikel_naam(resultSet.getString("Artikel_naam"));
                   artikel.setArtikel_prijs(resultSet.getDouble("Artikel_prijs"));
                   //artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                   //artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                   //artikel.setArtikel2_aantal(resultSet.getInt("Artikel2_aantal"));
                   //artikel.setArtikel2_prijs(resultSet.getDouble("Artikel2_prijs"));
                   //artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                   //artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam"));
                   //artikel.setArtikel3_aantal(resultSet.getInt("Artikel3_aantal"));
                   //artikel.setArtikel3_prijs(resultSet.getDouble("Artikel3_prijs"));
                   artikelen.add(artikel);
                   logger.info("");
               }
               resultSet.close();
               statement.close();
                
           } catch (SQLException e) {
               e.printStackTrace();
           }
           System.out.println(artikelen);
           logger.info("read all artikelen methode eindigt");
           return artikelen;
   }
   
   @Override
    public Artikel read(Artikel artikel) {
    
                 
        try {
        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
        	
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Artikel WHERE artikel_id =" + artikel.getArtikel_id());
                 
            
                while(resultSet.next()){
                    artikel = new Artikel();
                   
                    artikel.setArtikel_id(resultSet.getInt("Artikel_id"));
                    artikel.setArtikel_naam(resultSet.getString("Artikel_naam"));
                    artikel.setArtikel_prijs(resultSet.getDouble("Artikel_prijs"));
                    //artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                    //artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                    //artikel.setArtikel2_aantal(resultSet.getInt("Artikel2_aantal"));
                    //artikel.setArtikel2_prijs(resultSet.getDouble("Artikel2_prijs"));
                    //artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                    //artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam"));
                    //artikel.setArtikel3_aantal(resultSet.getInt("Artikel3_aantal"));
                    //artikel.setArtikel3_prijs(resultSet.getDouble("Artikel3_prijs"));
                    
                    
                    
                }
                resultSet.close();
                statement.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(artikel);
            return artikel;
    }


@Override
public Artikel readArtikel(int bestelling_id, int artikel_id){

        Artikel artikel = new Artikel();
             
        
        try {	  
        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
        	
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from bestelling_artikel where bestelling_id=? AND artikel_id=?");	   		            	   		            
	            preparedStatement.setInt(1, bestelling_id);
	            preparedStatement.setInt(2, artikel_id);
	            ResultSet result = preparedStatement.executeQuery();
	           	
	            
	                while(result.next()){
	               	artikel.setBestelling_id(result.getInt("Bestelling_id"));
	                artikel.setArtikel_id(result.getInt("Artikel_id"));
	                artikel.setArtikel_aantal(result.getInt("Artikel_aantal"));
	                }

	            preparedStatement = connection.prepareStatement("select * from artikel where artikel_id=?");
	            
	            while(result.next()){
                    artikel.setArtikel_naam(result.getString("Artikel_naam"));
                    artikel.setArtikel_prijs(result.getDouble("Artikel_prijs"));
	            }
	            
	            result.close();
	            preparedStatement.close();
	            
	            
	            //while(result.next()){
	            //    int artikel1 = result.getInt("artikel1_id");
                //    int artikel2 = result.getInt("artikel2_id");
                //    int artikel3 = result.getInt("artikel3_id");
	            
	            
	            //if (artikel1 == artikel_id){
	            //	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	            	
	              
	            //    while(result.next()){
	            //    	artikel.setBestelling_id(bestelling_id);
	            //        artikel.setKlant_id(artikel_id);
	            //        artikel.setArtikel1_id(result.getInt("Artikel1_id"));
	            //        artikel.setArtikel1_naam(result.getString("Artikel1_naam"));
	            //        artikel.setArtikel1_aantal(result.getInt("Artikel1_aantal"));
	            //        artikel.setArtikel1_prijs(result.getDouble("Artikel1_prijs"));
	            //}
	            //}
	            //else if (artikel2 == artikel_id){
	            //	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	                 
		              
	            //    while(result.next()){
	            //    	artikel.setBestelling_id(bestelling_id);
	            //       artikel.setKlant_id(artikel_id);
	            //        artikel.setArtikel1_id(result.getInt("Artikel2_id"));
	            //        artikel.setArtikel1_naam(result.getString("Artikel2_naam"));
	            //        artikel.setArtikel1_aantal(result.getInt("Artikel2_aantal"));
	            //        artikel.setArtikel1_prijs(result.getDouble("Artikel2_prijs"));
	            //}
	            //}
	            
	            //else if (artikel3 == artikel_id){
	            //	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	                 
		              
	            //    while(result.next()){
	            //    	artikel.setBestelling_id(bestelling_id);
	            //        artikel.setKlant_id(artikel_id);
	            //        artikel.setArtikel1_id(result.getInt("Artikel3_id"));
	            //        artikel.setArtikel1_naam(result.getString("Artikel3_naam"));
	            //        artikel.setArtikel1_aantal(result.getInt("Artikel3_aantal"));
	            //        artikel.setArtikel1_prijs(result.getDouble("Artikel3_prijs"));
	            //}
	            //}
	            
	            //else{
	            //	System.out.println("Artikel zit niet in bestelling");
	            //}
	            //}
	            
	            
        }catch (SQLException e){
        	e.printStackTrace();
        }
        
        return artikel;
}

  
   @Override
    public void create(Artikel artikel){
        
        
        List<Artikel> artikellen = new ArrayList<>();
        artikellen.add(artikel);
        
                
        try {
        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
        		
        	
        	PreparedStatement preparedStatement = connection.prepareStatement("insert into artikel (artikel_id, artikel_naam, artikel_prijs) values (?, ?, ?)");
	            preparedStatement.setInt(1, artikel.getArtikel_id());
	            preparedStatement.setString(2, artikel.getArtikel_naam());
	            preparedStatement.setDouble(3, artikel.getArtikel_prijs());
	            
	            
	        
	          //preparedStatement.setInt(7, artikel.getArtikel2_id());
	          //  preparedStatement.setString(8, artikel.getArtikel2_naam());
	          //  preparedStatement.setInt(9, artikel.getArtikel2_aantal());
	          //  preparedStatement.setDouble(10, artikel.getArtikel2_prijs());	
	          //  preparedStatement.setInt(11, artikel.getArtikel3_id());
	          //  preparedStatement.setString(12, artikel.getArtikel3_naam());
	          //  preparedStatement.setInt(13, artikel.getArtikel3_aantal());
	          //  preparedStatement.setDouble(14, artikel.getArtikel3_prijs());
	            
	      
            
        int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
            System.out.println("Artikel(len) toegevoegd aan het assortiment!");
        }
        
            preparedStatement.close();
                
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
   
    
   @Override
  public void update(Artikel artikel) {         
					
	    	        
	   	         try {	
	   	      	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
   	        	
	   	      		PreparedStatement preparedStatement = connection.prepareStatement("select * from Artikel where artikel_id=?");	   		            	   		            
		            preparedStatement.setInt(1, artikel.getArtikel_id());	   		                   	            
		            ResultSet result = preparedStatement.executeQuery();
		           		            
		            
		            if (!result.next()) {
		            	System.out.println();
		            	System.out.println("Het opgegeven artikel ID " + artikel.getArtikel_id() + " bestaat niet");	   		      
		            	return;
		            }
		            
		            result.beforeFirst();
		            
		            System.out.println();
		            System.out.println("Het opgegeven artikel ID luidt: " + artikel.getArtikel_id());
		            
		            preparedStatement = connection.prepareStatement("update artikel set artikel_naam=?, artikel_prijs=? where artikel_id=?");
		            preparedStatement.setString(1, artikel.getArtikel_naam());
		            preparedStatement.setDouble(2, artikel.getArtikel_prijs());
		            preparedStatement.setInt(3, artikel.getArtikel_id());	
		            
		            int rowsUpdated = preparedStatement.executeUpdate();
			                 if (rowsUpdated > 0) {
			                 System.out.println("Bij Artikel_id: " + artikel.getArtikel_id() + " hoort nu de volgende naam: " + artikel.getArtikel_naam() + " en prijs: " + artikel.getArtikel_prijs());
			    
			                 }
			                 
	   		            //while(result.next()){	   		        
		                //    int artikelnummer1 = result.getInt("artikel1_id");
		                //    int artikelnummer2 = result.getInt("artikel2_id");
		                //   int artikelnummer3 = result.getInt("artikel3_id");

			            			            	                
			            //	if (artikelnummer1 == 0){
			            //		 PreparedStatement statement1 = connection.prepareStatement("UPDATE bestelling SET artikel1_id=?, artikel1_naam=?, artikel1_aantal=?, artikel1_prijs=? WHERE bestelling_id=?");
			            //		 statement1.setInt(1, artikel.getArtikel1_id());
			            //		 statement1.setString(2, artikel.getArtikel1_naam());
			            //		 statement1.setInt(3, artikel.getArtikel1_aantal());
			            //		 statement1.setDouble(4, artikel.getArtikel1_prijs());
			            //		 statement1.setInt(5, artikel.getBestelling_id());
			            //		 
			            //		 int rowsUpdated1 = statement1.executeUpdate();
				        //         if (rowsUpdated1 > 0) {
				        //         System.out.println(artikel.getArtikel1_naam() + " (" + artikel.getArtikel1_aantal() + " stuks)" + " is succesvol toegevoegd!");
				        //         } 
			            //	 }
			            //	 else if (artikelnummer1 !=0 || artikelnummer2 == 0) {	                
			 	        //         PreparedStatement statement2 = connection.prepareStatement("UPDATE bestelling SET artikel2_id=?, artikel2_naam=?, artikel2_aantal=?, artikel2_prijs=? WHERE bestelling_id=?");
			            //		 statement2.setInt(1, artikel.getArtikel1_id());
			            //		 statement2.setString(2, artikel.getArtikel1_naam());
			            //		 statement2.setInt(3, artikel.getArtikel1_aantal());
			            //		 statement2.setDouble(4, artikel.getArtikel1_prijs());
			            //		 statement2.setInt(5, artikel.getBestelling_id());
			            		 
			 	        //    	 int rowsUpdated2 = statement2.executeUpdate();
				        //        if (rowsUpdated2 > 0) {
				        //         System.out.println(artikel.getArtikel1_naam() + " (" + artikel.getArtikel1_aantal() + " stuks)" + " is succesvol toegevoegd!");
				        //         } 
			            //	 }
			            	 
			            //	 else if (artikelnummer1 != 0 && artikelnummer2 !=0 && artikelnummer3 == 0) {
			            //		 PreparedStatement statement3 = connection.prepareStatement("UPDATE bestelling SET artikel3_id=?, artikel3_naam=?, artikel3_aantal=?, artikel3_prijs=? WHERE bestelling_id = ?");
			            //		 statement3.setInt(1, artikel.getArtikel1_id());
			            //		 statement3.setString(2, artikel.getArtikel1_naam());
			            //		 statement3.setInt(3, artikel.getArtikel1_aantal());
			            //		 statement3.setDouble(4, artikel.getArtikel1_prijs());
			            //		 statement3.setInt(5, artikel.getBestelling_id());
			            		 
			 	        //    	 int rowsUpdated3 = statement3.executeUpdate();
				        //         if (rowsUpdated3 > 0) {
					    //             System.out.println(artikel.getArtikel1_id() + " (" + artikel.getArtikel1_aantal() + " stuks)" + " is succesvol toegevoegd!");
					    //                          }
			            //	 }
		                    
			            //	 else {
			            //		 System.out.println("Maximum aantal artikellen bereikt!");
			            //	 }
		               
	   		            //}
	   		            
		                result.close();
		                preparedStatement.close();
		                 
		            } 
				                 
				    catch (SQLException e) {
		                e.printStackTrace();
		            }
	   		               		            
		  }
  

        @Override
	public void delete(int bestelling_id, int artikel_id) {
			
        	
        	
    	   	         try {	 
    	   	        	
    	   	        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
    	   	        	
    	   	        	PreparedStatement preparedStatement = connection.prepareStatement("select * from bestelling_artikel where bestelling_id=?");	   		            	   		            
	   		            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	   		            ResultSet result = preparedStatement.executeQuery();
	   		           		            
	   		            
	   		            if (!result.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestelling ID " + bestelling_id + " bestaat niet");	   		      
	   		            	return;
	   		            }
	   		            
	   		            result.beforeFirst();
	   		            
	   		            System.out.println();
	   		            System.out.println("Het opgegeven bestelling ID luidt: " + artikel_id);
    	   	        	 
    	   	        	              preparedStatement = connection.prepareStatement("DELETE bestelling_artikel FROM bestelling_artikel WHERE bestelling_artikel.artikel_id = ? AND bestelling_artikel.bestelling_id = ?");
    			            		  preparedStatement.setInt(1, artikel_id);
    	   	        	              preparedStatement.setInt(2, bestelling_id);
    			            		 
    			            		 int rowsDeleted = preparedStatement.executeUpdate();
    			                     if (rowsDeleted > 0) {
    			     	System.out.println("Het artikel is gewist uit de bestelling!");
    			                     } 
    			          
    			                  
    			                   
    			                     preparedStatement.close();
    			                     result.close();
    			                     
        			}catch (SQLException e){
        				e.printStackTrace();
        			}
    	   	         
    	   	         finally {
    					
    				}
    	   		               		            
    		  }
      
    @Override
    public void delete(Artikel artikel) {
			
        	
        	
  	         try {	 
  	        	
  	        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
  	        	
  	        	PreparedStatement preparedStatement = connection.prepareStatement("select * from Artikel where artikel_id=?");	   		            	   		            
		            preparedStatement.setInt(1, artikel.getArtikel_id());	   		                   	            
		            ResultSet result = preparedStatement.executeQuery();
		           		            
		            
		            if (!result.next()) {
		            	System.out.println();
		            	System.out.println("Het opgegeven artikel ID " + artikel.getArtikel_id() + " bestaat niet");	   		      
		            	return;
		            }
		            
		            result.beforeFirst();
		            
		            System.out.println();
		            System.out.println("Het opgegeven artikel ID luidt: " + artikel.getArtikel_id());
  	        	 
  	        	              preparedStatement = connection.prepareStatement("delete from Artikel where artikel_id=?");
		            		 preparedStatement.setInt(1, artikel.getArtikel_id());
		            		 
		            		 int rowsDeleted = preparedStatement.executeUpdate();
		                     if (rowsDeleted > 0) {
		     	System.out.println("Het artikel is gewist uit het assortiment!");
		                     } 
		          
		                  
		                   
		                     preparedStatement.close();
		                     result.close();
		                     
			}catch (SQLException e){
				e.printStackTrace();
			}
  	         
  	         finally {
				
			}
  		               		            
	  }


     
    public void closeDBConnection(){
        try {
              if (connection != null) {
                  connection.close();
                  System.out.println("Connectie is gesloten");
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
}
