package dao;

import java.sql.*;
import java.util.*;

import business.Klant;
import menu.DBConnectivityManagement;



public class KlantDaoImpl implements KlantDao {

	public Connection connection = null;


   public Connection initializeDB(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            if(connection == null){ 
                String dbURL = "jdbc:mysql://localhost:3308/opdracht1";
                String username = "root";
                String password = "JaRsvier15";

        
                connection = DriverManager.getConnection(dbURL, username, password);
                  System.out.println("Verbinding is gemaakt");
            }
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
      
        return connection;
        
    }
    
   @Override
    public void create(Klant klant){
        
	
                   
        try {
        	   Connection connection = DBConnectivityManagement.getConnectionStatus();
                        
            	PreparedStatement preparedStatement = connection.prepareStatement("insert into klant(klant_id, voornaam, achternaam, tussenvoegsel, email) values (?, ?, ?, ? , ?)");
			preparedStatement.setInt(1, klant.getKlant_id());
			preparedStatement.setString(2, klant.getVoornaam());
			preparedStatement.setString(3, klant.getAchternaam());
			preparedStatement.setString(4, klant.getTussenvoegsel());
			preparedStatement.setString(5, klant.getEmail());
            
                     
            
            int rowsInserted = preparedStatement.executeUpdate();
            
            if (rowsInserted > 0) {
            System.out.println("Een nieuwe klant is succesvol toegevoegd aan de database");
        }
            preparedStatement.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
   @Override
    public List<Klant> read() {
        List<Klant> klanten = new ArrayList<Klant>();
         
        try {
                Connection connection = DBConnectivityManagement.getConnectionStatus();
        		
        		Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM klant");
                 
                Klant klant;
                while(resultSet.next()){
                    klant = new Klant();
                    klant.setKlant_id(resultSet.getInt("Klant_id"));
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
    public Klant readKlant(int klant_id) {
	
        
        Klant klant = new Klant();
        ResultSet result;
        String sql = "SELECT * FROM klant WHERE Klant_id= " + klant_id;
        
        try {
        	Connection connection = DBConnectivityManagement.getConnectionStatus();
        	
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
            
            
             while (result.next()){
                klant.setKlant_id(klant_id);
                klant.setVoornaam(result.getString(2));
                klant.setTussenvoegsel(result.getString(4));
                klant.setAchternaam(result.getString(3));
                klant.setEmail(result.getString(5));
              
                
        }
             result.close();
             statement.close();
                     
        } catch (SQLException e){
            e.printStackTrace();
           
        }
       System.out.println(klant);
       return klant;
}
 
    @Override
 public Klant readKlant(String voornaam) {
	
        Klant klant = null;
        ResultSet result;
                
        try {
        	   Connection connection = DBConnectivityManagement.getConnectionStatus();
        	   
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM klant WHERE voornaam=?");
            statement.setString(1, voornaam);
            result = statement.executeQuery();
            
             while (result.next()){
                klant = new Klant();
                klant.setKlant_id(result.getInt(1));
                klant.setVoornaam(result.getString(2));
                klant.setTussenvoegsel(result.getString(4));
                klant.setAchternaam(result.getString(3));
                klant.setEmail(result.getString(5));
              
                
        }
             result.close();
             statement.close();
             
        } catch (SQLException e){
            e.printStackTrace();
           
        }
       System.out.println(klant);
       return klant;
}
        
	@Override
	public void update(Klant klant) {
		 
                                    
            try {
            	Connection connection = DBConnectivityManagement.getConnectionStatus();

                PreparedStatement statement = connection.prepareStatement("UPDATE Klant SET voornaam=?, tussenvoegsel=?, achternaam=?, email=? WHERE Klant_id=?");
                statement.setString(1, klant.getVoornaam());
                statement.setString(2, klant.getTussenvoegsel());
                statement.setString(3, klant.getAchternaam());
                statement.setString(4, klant.getEmail());
                statement.setInt(5, klant.getKlant_id());
               

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
	System.out.println("De gegevens van de bestaande klant zijn aangepast!");
}
             statement.close();
             
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
	}

        @Override
	public void delete(Klant klant) {
		// TODO Auto-generated method stub
            try{ 
            	   Connection connection = DBConnectivityManagement.getConnectionStatus();
                          
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Klant WHERE Klant_id=?");
                statement.setInt(1, klant.getKlant_id());

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
	System.out.println("De klant is gewist uit de database");
        }
                statement.close();
                
	} catch (SQLException e) {
            e.printStackTrace();
        }
        }

    
    	public boolean checkKlant_id(int klant_id) {	
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			boolean result = false;
			
			try {
				
				Connection connection = DBConnectivityManagement.getConnectionStatus(); 
				
				preparedStatement = connection.prepareStatement("SELECT * FROM Klant WHERE klant_id=?");
				preparedStatement.setInt(1, klant_id);
				resultSet = preparedStatement.executeQuery(); 
				
				if (resultSet.next()){
					result = true;
				} else {
					System.out.println("Het opgegeven klant_id bevindt zich niet in de database...");
				}

			} catch (SQLException e) {
	                e.printStackTrace();	
			}
			return result;
		}
    	
     
    public void closeDBConnection(){
        try {
              if (connection != null) {
                  connection.close();
                  System.out.println("Verbinding is gesloten");
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
}
