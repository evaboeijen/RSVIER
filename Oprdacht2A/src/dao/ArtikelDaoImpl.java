package dao;

import java.sql.*;
import java.util.*;

import business.Artikel;
import menu.InEnUitLoggen;

public class ArtikelDaoImpl implements ArtikelDao {
    
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
    
        List<Artikel> artikellen = new ArrayList<>();
         
        try {
        	Connection connection = InEnUitLoggen.getConnectionStatus(); 
        	
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Bestelling WHERE bestelling_id");
                 
                Artikel artikel;
                while(resultSet.next()){
                    artikel = new Artikel();
                    artikel.setKlant_id(resultSet.getInt("Klant_id"));
                    artikel.setArtikel1_id(resultSet.getInt("Artikel1_id"));
                    artikel.setArtikel1_naam(resultSet.getString("Artikel1_naam"));
                    artikel.setArtikel1_aantal(resultSet.getInt("Artikel1_aantal"));
                    artikel.setArtikel1_prijs(resultSet.getDouble("Artikel1_prijs"));
                    artikel.setArtikel2_id(resultSet.getInt("Artikel2_id"));
                    artikel.setArtikel2_naam(resultSet.getString("Artikel2_naam"));
                    artikel.setArtikel2_aantal(resultSet.getInt("Artikel2_aantal"));
                    artikel.setArtikel2_prijs(resultSet.getDouble("Artikel2_prijs"));
                    artikel.setArtikel3_id(resultSet.getInt("Artikel3_id"));
                    artikel.setArtikel3_naam(resultSet.getString("Artikel3_naam"));
                    artikel.setArtikel3_aantal(resultSet.getInt("Artikel3_aantal"));
                    artikel.setArtikel3_prijs(resultSet.getDouble("Artikel3_prijs"));
                    
                    
                    
                    artikellen.add(artikel);
                }
                resultSet.close();
                statement.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        
            return artikellen;
    }


@Override
public Artikel readArtikel(int bestelling_id, int artikel_id){

        Artikel artikel = new Artikel();
             
        
        try {	  
        	Connection connection = InEnUitLoggen.getConnectionStatus(); 
        	
	            PreparedStatement preparedStatement = connection.prepareStatement("select * from Bestelling where bestelling_id=?");	   		            	   		            
	            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	            ResultSet result = preparedStatement.executeQuery();
	           		            
	            while(result.next()){
	                int artikel1 = result.getInt("artikel1_id");
                    int artikel2 = result.getInt("artikel2_id");
                    int artikel3 = result.getInt("artikel3_id");
	            
	            
	            if (artikel1 == artikel_id){
	            	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	            	
	              
	                while(result.next()){
	                	artikel.setBestelling_id(bestelling_id);
	                    artikel.setKlant_id(artikel_id);
	                    artikel.setArtikel1_id(result.getInt("Artikel1_id"));
	                    artikel.setArtikel1_naam(result.getString("Artikel1_naam"));
	                    artikel.setArtikel1_aantal(result.getInt("Artikel1_aantal"));
	                    artikel.setArtikel1_prijs(result.getDouble("Artikel1_prijs"));
	            }
	            }
	            else if (artikel2 == artikel_id){
	            	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	                 
		              
	                while(result.next()){
	                	artikel.setBestelling_id(bestelling_id);
	                    artikel.setKlant_id(artikel_id);
	                    artikel.setArtikel1_id(result.getInt("Artikel2_id"));
	                    artikel.setArtikel1_naam(result.getString("Artikel2_naam"));
	                    artikel.setArtikel1_aantal(result.getInt("Artikel2_aantal"));
	                    artikel.setArtikel1_prijs(result.getDouble("Artikel2_prijs"));
	            }
	            }
	            
	            else if (artikel3 == artikel_id){
	            	result= preparedStatement.executeQuery("SELECT * FROM Bestelling where bestelling_id=" + bestelling_id);
	                 
		              
	                while(result.next()){
	                	artikel.setBestelling_id(bestelling_id);
	                    artikel.setKlant_id(artikel_id);
	                    artikel.setArtikel1_id(result.getInt("Artikel3_id"));
	                    artikel.setArtikel1_naam(result.getString("Artikel3_naam"));
	                    artikel.setArtikel1_aantal(result.getInt("Artikel3_aantal"));
	                    artikel.setArtikel1_prijs(result.getDouble("Artikel3_prijs"));
	            }
	            }
	            
	            else{
	            	System.out.println("Artikel zit niet in bestelling");
	            }
	            }
	            result.close();
	            preparedStatement.close();
        }catch (SQLException e){
        	e.printStackTrace();
        }
        return artikel;
}

  
   @Override
    public void create(Artikel artikel){
        
        
        List<Artikel> artikellen = new ArrayList<>();
        artikellen.add(artikel);

        PreparedStatement preparedStatement ;
        
        try {
        	Connection connection = InEnUitLoggen.getConnectionStatus(); 
        	
	            preparedStatement = connection.prepareStatement("insert into Bestelling (bestelling_id, klant_id, artikel1_id, artikel1_naam, artikel1_aantal, artikel1_prijs, artikel2_id, artikel2_naam, artikel2_aantal, artikel2_prijs, artikel3_id, artikel3_naam, artikel3_aantal, artikel3_prijs) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	            preparedStatement.setInt(1, artikel.getBestelling_id());
	            preparedStatement.setInt(2, artikel.getKlant_id());
	            preparedStatement.setInt(3, artikel.getArtikel1_id());
	            preparedStatement.setString(4, artikel.getArtikel1_naam());
	            preparedStatement.setInt(5, artikel.getArtikel1_aantal());
	            preparedStatement.setDouble(6, artikel.getArtikel1_prijs());	  
	            preparedStatement.setInt(7, artikel.getArtikel2_id());
	            preparedStatement.setString(8, artikel.getArtikel2_naam());
	            preparedStatement.setInt(9, artikel.getArtikel2_aantal());
	            preparedStatement.setDouble(10, artikel.getArtikel2_prijs());	
	            preparedStatement.setInt(11, artikel.getArtikel3_id());
	            preparedStatement.setString(12, artikel.getArtikel3_naam());
	            preparedStatement.setInt(13, artikel.getArtikel3_aantal());
	            preparedStatement.setDouble(14, artikel.getArtikel3_prijs());
	            
	      
            
        int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
            System.out.println("Artikellen toegevoegd aan de bestelling!");
        }
             
                preparedStatement.close();
                
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
   @Override
  public void update(Artikel artikel) {              
	
       			
		PreparedStatement preparedStatement ;
					
	    	        
	   	         try {	
	   	        	Connection connection = InEnUitLoggen.getConnectionStatus(); 
	   	        	
	   		            preparedStatement = connection.prepareStatement("select * from Bestelling where bestelling_id=?");	   		            	   		            
	   		            preparedStatement.setInt(1, artikel.getBestelling_id());	   		                   	            
	   		            ResultSet result = preparedStatement.executeQuery();
	   		           		            
	   		            
	   		            if (!result.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestellings ID / Ordernummer " + artikel.getBestelling_id() + " bestaat niet");	   		      
	   		            	return;
	   		            }
	   		            
	   		            result.beforeFirst();
	   		            
	   		            System.out.println();
	   		            System.out.println("Het opgegeven bestellings ID / Ordernummer luidt: " + artikel.getBestelling_id());
	   		            
	   		            while(result.next()){	   		        
		                    int artikelnummer1 = result.getInt("artikel1_id");
		                    int artikelnummer2 = result.getInt("artikel2_id");
		                    int artikelnummer3 = result.getInt("artikel3_id");

			            			            	                
			            	 if (artikelnummer3 != 0) {
			            		 System.out.println("Maximum aantal artikelen voor deze bestelling bereikt");
			            		 return;
			            	 }
			                	                
			            	 else if (artikelnummer2 == 0) {	                
			 	                 PreparedStatement statement2 = connection.prepareStatement("UPDATE bestelling SET artikel2_id=?, artikel2_naam=?, artikel2_aantal=?, artikel2_prijs=? WHERE bestelling_id=?");
			            		 statement2.setInt(1, artikel.getArtikel1_id());
			            		 statement2.setString(2, artikel.getArtikel1_naam());
			            		 statement2.setInt(3, artikel.getArtikel1_aantal());
			            		 statement2.setDouble(4, artikel.getArtikel1_prijs());
			            		 statement2.setInt(5, artikel.getBestelling_id());
			            		 
			 	            	 int rowsUpdated2 = statement2.executeUpdate();
				                 if (rowsUpdated2 > 0) {
				                 System.out.println(artikel.getArtikel1_naam() + " (" + artikel.getArtikel1_aantal() + " stuks)" + " is succesvol toegevoegd!");
				                 } 
			            	 }
			            	 
			            	 else if (artikelnummer1 != 0 && artikelnummer2 !=0 && artikelnummer3 == 0) {
			            		 PreparedStatement statement3 = connection.prepareStatement("UPDATE bestelling SET artikel3_id=?, artikel3_naam=?, artikel3_aantal=?, artikel3_prijs=? WHERE bestelling_id = ?");
			            		 statement3.setInt(1, artikel.getArtikel1_id());
			            		 statement3.setString(2, artikel.getArtikel1_naam());
			            		 statement3.setInt(3, artikel.getArtikel1_aantal());
			            		 statement3.setDouble(4, artikel.getArtikel1_prijs());
			            		 statement3.setInt(5, artikel.getBestelling_id());
			            		 
			 	            	 int rowsUpdated3 = statement3.executeUpdate();
				                 if (rowsUpdated3 > 0) {
					                 System.out.println(artikel.getArtikel1_id() + " (" + artikel.getArtikel1_aantal() + " stuks)" + " is succesvol toegevoegd!");
					                 System.out.println("Maximum van 3 verschillende artikelen is bereikt.");
				                 }
			            	 }
		                    
		                    
		               
	   		            }
	   		            
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
    	   	        	
    	   	        	Connection connection = InEnUitLoggen.getConnectionStatus(); 
    	   	        	
    	   	        	PreparedStatement preparedStatement = connection.prepareStatement("select * from Bestelling where bestelling_id=?");	   		            	   		            
	   		            preparedStatement.setInt(1, bestelling_id);	   		                   	            
	   		            ResultSet result = preparedStatement.executeQuery();
	   		           		            
	   		            
	   		            if (!result.next()) {
	   		            	System.out.println();
	   		            	System.out.println("Het opgegeven bestellings ID / Ordernummer " + bestelling_id + " bestaat niet");	   		      
	   		            	return;
	   		            }
	   		            
	   		            result.beforeFirst();
	   		            
	   		            System.out.println();
	   		            System.out.println("Het opgegeven bestellings ID / Ordernummer luidt: " + bestelling_id);
    	   	        	 
    	   	        	 Statement statement = connection.createStatement();
    	   	         
    	   	        	result = statement.executeQuery("SELECT * FROM Bestelling WHERE bestelling_id=" + bestelling_id);
    	                
    	               while(result.next()){
    	                 int artikel1 = result.getInt("Artikel1_id");
    	                 int artikel2 = result.getInt("Artikel2_id");
    	                 int artikel3 = result.getInt("Artikel3_id");
    	                 
    	   		             			            			            	                
    			            	 if (artikel1 == artikel_id) {
    			                     PreparedStatement statement1 = connection.prepareStatement("UPDATE bestelling SET artikel1_id=0, artikel1_naam=null, artikel1_aantal=0, artikel1_prijs=0 WHERE bestelling_id=?");
    			            		 statement1.setInt(1, bestelling_id);
    			            		 
    			            		 int rowsUpdated = statement1.executeUpdate();
    			                     if (rowsUpdated > 0) {
    			     	System.out.println("Het artikel is gewist!");
    			     }
    			                     statement1.close();
    			            	 }
    			            		            
    			            	 else if (artikel2 == artikel_id) {	                
    			 	                 PreparedStatement statement2 = connection.prepareStatement("UPDATE bestelling SET artikel2_id=0, artikel2_naam=null, artikel2_aantal=0, artikel2_prijs=0 WHERE bestelling_id=?");
    			            		 statement2.setInt(1, bestelling_id);
    			            		 
    			            		 int rowsUpdated = statement2.executeUpdate();
    			                     if (rowsUpdated > 0) {
    			     	System.out.println("Het artikel is gewist!");
    			     }
    			                     statement2.close();
    			            	 }
    			            	 
    			            	 else if (artikel3 == artikel_id) {	                
    			 	                 PreparedStatement statement3 = connection.prepareStatement("UPDATE bestelling SET artikel3_id=0 artikel3_naam=null, artikel3_aantal=0, artikel3_prijs=0 WHERE bestelling_id=?");
    			            		 statement3.setInt(1, bestelling_id);
    			            		 
    			            		 int rowsUpdated = statement3.executeUpdate();
    			                     if (rowsUpdated > 0) {
    			     	System.out.println("Het artikel is gewist!");
    			     }
    			                     statement3.close();
    			            	 }
    		                   
    			            	 else {
    			            		 System.out.println("Artikel zit niet in de bestelling");
    			            	 }
    		               
    	   		            }
    	   		            
    		                result.close();
    		                statement.close();
    		                 
    		            } 
    				                 
    				    catch (SQLException e) {
    		                e.printStackTrace();
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
