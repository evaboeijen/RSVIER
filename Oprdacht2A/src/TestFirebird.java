import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestFirebird {

	public static void main(String[] args) {
		
		final Logger logger =  LoggerFactory.getLogger(TestFirebird.class);
		
		Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
 
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
	            connection = DriverManager
	                    .getConnection(
	                            //"jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/RSVIER.FDB",
	                    		"jdbc:firebirdsql://localhost:3050/C:/Users/kuija/Dropbox/Java/Praktijk gedeelte/Firebird/eva/RSVIER",
	                    		"SYSDBA", "masterkey");
            
            logger.info("connection: " + connection);
            
            statement = connection.createStatement();
            
            logger.info("statement: " + statement);
            
          
            resultSet = statement.executeQuery("SELECT KLANT_ID FROM KLANT");
            
            logger.info("resultSet: " + resultSet);
            
            while (resultSet.next()) {
                System.out.println("KLANT:"
                        + resultSet.getString("Klant_ID")); 
            } 
        } catch (Exception e) {
        	
        	logger.warn("Exception called");
        	
            e.printStackTrace();
            logger.warn("stacktrace printed, called from first exception block");
        } finally {
            try {           	
            	logger.warn("Finally block executed");
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
            	logger.warn("Catch block in Finally block executed");
                e.printStackTrace();
                logger.warn("stacktrace printed, called from exception block withiny finally clause");
            }
        }

	}

}
