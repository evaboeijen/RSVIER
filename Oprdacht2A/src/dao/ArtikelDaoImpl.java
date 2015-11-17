package dao;

import java.sql.*;
import java.util.*;

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
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Bestelling");
                 
                Artikel artikel;
                while(resultSet.next()){
                    artikel = new Artikel();
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
            
        System.out.println(artikellen);
            return artikellen;
    }

@Override
public Artikel readArtikel(int artikel_id){

        Artikel artikel = new Artikel();
        ResultSet result = null;
        
        
        try {
            
           Statement statement = connection.createStatement();
           result = statement.executeQuery("SELECT * FROM Bestelling");
                
           while(result.next()){
               int artikel1 = result.getInt("Artikel1_id");
               int artikel2 = result.getInt("Artikel2_id");
               int artikel3 = result.getInt("Artikel3_id");
               
            if (artikel_id == artikel1){
            
               String sql = "SELECT * FROM Bestelling WHERE Artikel1_id=" + artikel_id;
               
           
            result = statement.executeQuery(sql);
            
            
             while (result.next()){
                artikel.setArtikel1_id(artikel_id);
                artikel.setArtikel1_naam(result.getString("Artikel1_naam"));
                artikel.setArtikel1_aantal(result.getInt("Artikel1_aantal"));
                artikel.setArtikel1_prijs(result.getDouble("Artikel1_prijs"));
                System.out.println("artikel id:" + artikel_id + " artikel naam " + artikel.getArtikel1_naam() + " artikel aantal " + artikel.getArtikel1_aantal() + " artikel prijs " + artikel.getArtikel1_prijs());
             } 
            } else if (artikel_id == artikel2){
                
                String sql = "SELECT * FROM Bestelling WHERE Artikel2_id=" + artikel_id;
               
            
            result = statement.executeQuery(sql);
            
             while (result.next()){
                artikel.setArtikel2_id(artikel_id);
                artikel.setArtikel2_naam(result.getString("Artikel2_naam"));
                artikel.setArtikel2_aantal(result.getInt("Artikel2_aantal"));
                artikel.setArtikel2_prijs(result.getDouble("Artikel2_prijs"));
                System.out.println("artikel id:" + artikel_id + " artikel naam " + artikel.getArtikel2_naam() + " artikel aantal " + artikel.getArtikel2_aantal() + " artikel prijs " + artikel.getArtikel2_prijs());
             }
            } else if (artikel_id == artikel3){
                 String sql = "SELECT * FROM Bestelling WHERE Artikel3_id=" + artikel_id;
                       
                 result = statement.executeQuery(sql);
            
             while (result.next()){
               artikel.setArtikel3_id(artikel_id);
                artikel.setArtikel3_naam(result.getString("Artikel3_naam"));
                artikel.setArtikel3_aantal(result.getInt("Artikel3_aantal"));
                artikel.setArtikel3_prijs(result.getDouble("Artikel3_prijs"));
                System.out.println("artikel id:" + artikel_id + " artikel naam " + artikel.getArtikel3_naam() + " artikel aantal " + artikel.getArtikel3_aantal() + " artikel prijs " + artikel.getArtikel3_prijs());
             }
                          
            } else {
                System.out.println("Artikel zit niet in de bestellinglijst");
            }
           }
            
        } catch (SQLException e){
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
  public void update(Artikel artikel) {        // zelfde methode als hiervoor, alleen nu met een object als parameter        	        
	
        int bestelling_id = artikel.getBestelling_id();       
        int new_artikel_id = artikel.getArtikel1_id();
        String new_artikel_naam = artikel.getArtikel1_naam();
        int new_artikel_aantal = artikel.getArtikel1_aantal();
        double new_artikel_prijs = artikel.getArtikel1_prijs();	
					
		PreparedStatement preparedStatement ;
					
	    	        
	   	         try {	   	                                
	   		            preparedStatement = connection.prepareStatement("select * from Bestelling where bestelling_id=?");	   		            	   		            
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
			            		 statement2.setInt(1, new_artikel_id);
			            		 statement2.setString(2, new_artikel_naam);
			            		 statement2.setInt(3, new_artikel_aantal);
			            		 statement2.setDouble(4, new_artikel_prijs);
			            		 statement2.setInt(5, bestelling_id);
			            		 
			 	            	 int rowsUpdated2 = statement2.executeUpdate();
				                 if (rowsUpdated2 > 0) {
				                 System.out.println(new_artikel_naam + " (" + new_artikel_aantal + " stuks)" + " is succesvol toegevoegd!");
				                 } 
			            	 }
			            	 
			            	 else if (artikelnummer1 != 0 && artikelnummer2 !=0 && artikelnummer3 == 0) {
			            		 PreparedStatement statement3 = connection.prepareStatement("UPDATE bestelling SET artikel3_id=?, artikel3_naam=?, artikel3_aantal=?, artikel3_prijs=? WHERE bestelling_id = ?");
			            		 statement3.setInt(1, new_artikel_id);
			            		 statement3.setString(2, new_artikel_naam);
			            		 statement3.setInt(3, new_artikel_aantal);
			            		 statement3.setDouble(4, new_artikel_prijs);
			            		 statement3.setInt(5, bestelling_id);
			            		 
			 	            	 int rowsUpdated3 = statement3.executeUpdate();
				                 if (rowsUpdated3 > 0) {
					                 System.out.println(new_artikel_naam + " (" + new_artikel_aantal + " stuks)" + " is succesvol toegevoegd!");
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
	public void delete(Artikel artikel) {
		
       	
			
			
			try {

				PreparedStatement statement = connection.prepareStatement("DELETE FROM Bestelling WHERE bestelling_id=?");
				statement.setInt(1, artikel.getBestelling_id());
                                ;

				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					
					System.out.println("Alle artikellen zijn verwijdert uit de bestelling");
				}
			} catch (SQLException e) {
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
