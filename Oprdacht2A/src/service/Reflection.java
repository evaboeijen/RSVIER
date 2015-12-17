package service;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import menu.DBConnectivityManagement;
import business.*;

public class Reflection {
	
	Connection connection = null;
	
	private static final Logger logger =  LoggerFactory.getLogger(Reflection.class);
	
	public String buildInsertStatement(Klant klant) {
		
		int variableToInsert = 0;
		String sqlTableName = Klant.class.getSimpleName().toUpperCase();
		//String sqlTableName = this.getKlasseNaam(klant).toUpperCase();
		logger.info("sqlTableName is: " + sqlTableName);
		String buildSqlStatement = "INSERT INTO " + sqlTableName + "(";
		String valueFieldEnd = "values (";
		Field[] declaredFields = Klant.class.getDeclaredFields();
		
		for (int i = 0; i < declaredFields.length; i++) {			
			try {				
				declaredFields[i].setAccessible(true);
				
				if (declaredFields[i].get(klant) != null) {
					if (!isPrimitiveZero(declaredFields[i].get(klant))) {
						variableToInsert++;
						if (variableToInsert > 1) {
							buildSqlStatement += ", ";
							valueFieldEnd += ", ";
						}
					
						buildSqlStatement += declaredFields[i].getName();						
						if (declaredFields[i].get(klant) instanceof String) {
							valueFieldEnd += "\'";
						}
						
						valueFieldEnd += declaredFields[i].get(klant);
						
						if (declaredFields[i].get(klant) instanceof String) {
							valueFieldEnd += "\'";
						}
					}
				}
			}			
			catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
		}
	return buildSqlStatement + ") " + valueFieldEnd + ")";
	}
	
	
public String buildInsertStatement(Object object) {
		
		int variableToInsert = 0;
		//String sqlTableName = Object.class.getSimpleName().toUpperCase();	
		//String sqlTableName = this.getKlasseNaam(object).toUpperCase();		
		String sqlTableName = getClassName(object).toUpperCase();       
		logger.info("sqlTableName is: " + sqlTableName);
		String buildSqlStatement = "INSERT INTO " + sqlTableName + "(";
		String valueFieldEnd = "values (";
		Field[] declaredFields = Object.class.getDeclaredFields();
		
		logger.info("array declaredFields VOOR for loop" + declaredFields);
		
		
		for (int i = 0; i < declaredFields.length; i++) {			
			try {				
				declaredFields[i].setAccessible(true);
				
				if (declaredFields[i].get(object) != null) {
					if (!isPrimitiveZero(declaredFields[i].get(object))) {
						variableToInsert++;
						if (variableToInsert > 1) {
							buildSqlStatement += ", ";
							valueFieldEnd += ", ";
						}
					
						buildSqlStatement += declaredFields[i].getName();						
						if (declaredFields[i].get(object) instanceof String) {
							valueFieldEnd += "\'";
						}
						
						valueFieldEnd += declaredFields[i].get(object);
						
						if (declaredFields[i].get(object) instanceof String) {
							valueFieldEnd += "\'";
						}
					}
				}
			}
			
			catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("array declaredFields NA for loop" + declaredFields);
		
	return buildSqlStatement + ") " + valueFieldEnd + ")";
	}
	
	
	
	private boolean isPrimitiveZero(Object object) {

		boolean isPrimitiveZero = false;
	
		if (object instanceof Long) {
			if ((Long) object == 0) {
				isPrimitiveZero = true;
			}
		}
		else if (object instanceof Integer) {
			if ((Integer) object == 0) {
				isPrimitiveZero = true;
			}
		}
		else if (object instanceof Float) {
			if ((Float) object == 0.0) {
				isPrimitiveZero = true;
			}
		}
		else if (object instanceof Double) {
			if ((Double) object == 0.0) {
				isPrimitiveZero = true;
			}
		}
		return isPrimitiveZero;
	}


	
	
	
	/* private String getKlasseNaam(Object object) {
		String className = object.getClass().toString();
		String className2 = null;
		// klassenaam uit class XXXX string halen, class eraf
		className = className.substring("class ".length());
		className2 = className.substring("business.".length());
		Class<?> klass = null;
		
		
		try {
		klass = Class.forName(className2);
		}
		catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		
		logger.info("className2: " + className2);
		return className2;
	} */

	
	private String getClassName (Object object) {	
		String className = object.getClass().getSimpleName();		
		logger.info("className: " + className);
		return className;
	}
	
	
	
	public int insert_buildInsertStatement(String insertStatement)  {		// added 29-11-15 AU
	
	PreparedStatement preparedStatement ;
	int rowsUpdated = 0;
				
   	         try {	
   	        	Connection connection = DBConnectivityManagement.getConnectionStatus(); 
 	        	logger.info("Content of connection object is : " + connection);
 	        	
 	        	
 	        	logger.info("insertStatement: " + insertStatement);
 	        	
 	        	preparedStatement = connection.prepareStatement(insertStatement);
 	        	logger.info("preparedStatement uitgevoerd, inhoud: " + preparedStatement);
 	        	
 	        	rowsUpdated = preparedStatement.executeUpdate();
 	        	
 	        	logger.info("preparedStatement.executeUpdate() uitgevoerd, rowsUpdated: " + rowsUpdated);
   	         }
   	         
   	      
   	         catch (Exception ex) {
   	        	 
   	        	 //zinnige code
   	         
   	         }
   	         
   	         
   	         finally {
   	         
   	         }
   	         
   	      return rowsUpdated;
	}
}





