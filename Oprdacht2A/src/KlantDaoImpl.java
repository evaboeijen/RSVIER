/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */
import java.sql.*;
import java.util.*;

public class KlantDaoImpl implements KlantDao {

	Connection connection = null;
	 
   public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            if(connection == null){ 
                String dbURL = "jdbc:mysql://localhost:3308/javabook";
                String username = "root";
                String password = "JaRsvier15";

        
                Connection conn = DriverManager.getConnection(dbURL, username, password);
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
    public void insert(Klant klant){
        
        PreparedStatement preparedStatement ;
        
        try {
                        
            String sql = "insert into Klant (klant_id, voornaam, tussenvoegsel, achternaam, email) values (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, klant.getKlant_id());
            preparedStatement.setString(2, klant.getVoornaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            preparedStatement.setString(4, klant.getAchternaam());
            preparedStatement.setString(5, klant.getEmail());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(klant);
        System.out.println("Klant succesvol toegevoegd");
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

	public void update(Klant klant) {
		// TODO Auto-generated method stub
            try {
             Integer number = klant.getKlant_id();
                
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

	public void delete(Klant klant) {
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

    