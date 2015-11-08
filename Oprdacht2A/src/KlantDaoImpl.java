<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */
=======
>>>>>>> Workshop-deel1
import java.sql.*;
import java.util.*;

public class KlantDaoImpl implements KlantDao {

	Connection connection = null;
	 
   public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            if(connection == null){ 
<<<<<<< HEAD
                String dbURL = "jdbc:mysql://localhost:3308/javabook";
                String username = "root";
                String password = "JaRsvier15";

        
                Connection conn = DriverManager.getConnection(dbURL, username, password);
=======
                String dbURL = "jdbc:mysql://localhost:3306/opdracht1";
                String username = "root";
                String password = "";

        
                connection = DriverManager.getConnection(dbURL, username, password);
                  System.out.println("Verbinding is gemaakt");
>>>>>>> Workshop-deel1
            }
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
<<<<<<< HEAD
        System.out.println("Connection succesful");
=======
      
>>>>>>> Workshop-deel1
        return connection;
        
    }
    
   @Override
    public void insert(Klant klant){
        
<<<<<<< HEAD
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
=======
        
        List<Klant> klanten = new ArrayList<>();
        klanten.add(klant);

                
        try {
                        
            PreparedStatement preparedStatement = connection.prepareStatement("insert into klant(klant_id, voornaam, achternaam) values (?, ?, ?)");
            preparedStatement.setInt(1, klant.getKlant_id());
            preparedStatement.setString(2, klant.getVoornaam());
            preparedStatement.setString(3, klant.getAchternaam());
                     
            
            int rowsInserted = preparedStatement.executeUpdate();
            
            if (rowsInserted > 0) {
            System.out.println("Een nieuwe klant is succesvol toegevoegd aan de database");
        }
>>>>>>> Workshop-deel1
            preparedStatement.close();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
<<<<<<< HEAD
        System.out.println(klant);
        System.out.println("Klant succesvol toegevoegd");
    }
 
   @Override
    public List<Klant> select() {
        List<Klant> klanten = new LinkedList<Klant>();
         try {
=======
    }
 
   @Override
    public List<Klant> selectAll() {
        List<Klant> klanten = new ArrayList<Klant>();
         
        try {
>>>>>>> Workshop-deel1
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM klant");
                 
                Klant klant;
                while(resultSet.next()){
                    klant = new Klant();
<<<<<<< HEAD
                    klant.setKlant_id(Integer.parseInt(resultSet.getString("klant_id")));
=======
                    klant.setKlant_id(resultSet.getInt("Klant_id"));
>>>>>>> Workshop-deel1
                    klant.setVoornaam(resultSet.getString("voornaam"));
                    klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
                    klant.setAchternaam(resultSet.getString("achternaam"));
                    klant.setEmail(resultSet.getString("email"));
<<<<<<< HEAD
=======
                                        
>>>>>>> Workshop-deel1
                    
                    klanten.add(klant);
                }
                resultSet.close();
                statement.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
<<<<<<< HEAD
=======
        
>>>>>>> Workshop-deel1
            System.out.println(klanten);
            return klanten;
    }
    
    @Override
<<<<<<< HEAD
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
 
=======
    public Klant getKlant(int klant_id) {
	
        
        Klant klant = new Klant();
        ResultSet result;
        String sql = "SELECT * FROM klant WHERE Klant_id= " + klant_id;
        
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
            
            
             while (result.next()){
                klant.setKlant_id(klant_id);
                klant.setVoornaam(result.getString(2));
                klant.setTussenvoegsel(result.getString(4));
                klant.setAchternaam(result.getString(3));
                klant.setEmail(result.getString(5));
              
                
        }
        } catch (SQLException e){
            e.printStackTrace();
           
        }
       System.out.println(klant);
       return klant;
}
 
    @Override
 public Klant getKlant(String voornaam) {
	
        Klant klant = null;
        ResultSet result;
                
        try {
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
        } catch (SQLException e){
            e.printStackTrace();
           
        }
       System.out.println(klant);
       return klant;
}
        
	@Override
	public void update(Klant klant) {
		
                  List<Klant> klanten = new ArrayList<>();     
                  klanten.add(klant); 
                                    
            try {

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
                
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
	}

        @Override
	public void delete(Klant klant) {
		// TODO Auto-generated method stub
            try{ 
                              
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Klant WHERE Klant_id=?");
                statement.setInt(1, klant.getKlant_id());

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
	System.out.println("De klant is gewist uit de database");
        }
	} catch (SQLException e) {
            e.printStackTrace();
        }
        }

>>>>>>> Workshop-deel1

     
    public void closeConnection(){
        try {
              if (connection != null) {
                  connection.close();
<<<<<<< HEAD
=======
                  System.out.println("Verbinding is gesloten");
>>>>>>> Workshop-deel1
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
}
<<<<<<< HEAD

    
=======
>>>>>>> Workshop-deel1
