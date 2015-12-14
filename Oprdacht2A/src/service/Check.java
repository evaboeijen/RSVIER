package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ArtikelDaoImpl;
import menu.DBConnectivityManagement;

public class Check {
	private static final Logger logger =  LoggerFactory.getLogger(ArtikelDaoImpl.class);

	public boolean checkKlant_id(int klant_id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		logger.info("Check klantnummer methode begint");
		logger.debug("Klantnummer is : "+ klant_id);
		
		try {
			Connection connection = DBConnectivityManagement.getConnectionStatus();
			preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE klant_id=?");
			preparedStatement.setInt(1, klant_id);
			resultSet = preparedStatement.executeQuery(); 	// preparedStatement.close(); uitgecomment op 21/11/15 AU 	-->		Staat nu zowel bij if als else 23-11-2015 EB
			logger.debug("SQL query is: "+ preparedStatement);
			if (resultSet.next()){
				result = true;
				preparedStatement.close();
			} else {
				System.out.println("Het opgegeven klant_id bevindt zich niet in de database...");
				preparedStatement.close();
			}
			logger.info("Check klantnummer methode eindigt");
		} catch (SQLException e) {
			logger.warn("SQL exeption voor checkKlant_id methode");
			e.printStackTrace();	
		}
		return result;
	}

	
	public boolean checkArtikel_id(int artikel_id) {		// toegevoegd 21/11/15 AU
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		
		logger.info("Check Artikelnummer methode begint");
		
		try {
			
        	Connection connection = DBConnectivityManagement.getConnectionStatus();
        	logger.info("Content of connection object is : " + connection);
			
			// uitgecomment tbv opdracht 5 || AU 26/11/15 : preparedStatement = connection.prepareStatement("SELECT * FROM bestelling WHERE (artikel1_id=? OR artikel2_id=? OR artikel3_id=?)");
			
			// nieuw tbv opdracht 5 || AU 26/11/15
			preparedStatement = connection.prepareStatement("SELECT * FROM artikel WHERE artikel_id=?");
			preparedStatement.setInt(1, artikel_id);
			// preparedStatement.setInt(2, artikel_id);
			// preparedStatement.setInt(3, artikel_id);
			resultSet = preparedStatement.executeQuery(); 
			
			if (resultSet.next()){
				result = true;
			} else {
				System.out.println("Het opgegeven artikelnummer bevindt zich niet in de database...");
			}

		} catch (SQLException e) {
			logger.warn("SQL exception voor BestellingDaoImpl.checkArtikel_id() methode");
                e.printStackTrace();	
		}
		return result;
	}
	
	
	public boolean checkBestelling_id(int bestelling_id) {	// toegevoegd 21/11/15 AU
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean result = false;
		
		logger.info("Check Bestellingnummer methode begint");
		
		try {
			
        	Connection connection = DBConnectivityManagement.getConnectionStatus();
        	logger.info("Content of connection object is : " + connection); 
			
			preparedStatement = connection.prepareStatement("SELECT * FROM bestelling WHERE bestelling_id=?");
			preparedStatement.setInt(1, bestelling_id);
			resultSet = preparedStatement.executeQuery(); 
			
			if (resultSet.next()){
				result = true;
			} else {
				System.out.println("Het opgegeven bestelling_id bevindt zich niet in de database...");
			}

		} catch (SQLException e) {
			logger.warn("SQL exception voor BestellingDaoImpl.checkBestelling_id() methode");
                e.printStackTrace();	
		}
		return result;
	}
}
