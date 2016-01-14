

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotations.ColumnParser;
import annotations.IdParser;
import annotations.TableParser;
import business.*;



public class AnnotationOpdracht {
	
	private static final Logger logger =  LoggerFactory.getLogger(AnnotationOpdracht.class);
	
	public static void main(String[] args){
		Klant klant = new Klant();
		AnnotationOpdracht annotationOpdracht = new AnnotationOpdracht();
		System.out.println("Create Table query voor klasse Klant: ");
		System.out.println(annotationOpdracht.createTable(klant));
		System.out.println();
		
		Artikel artikel = new Artikel();
		System.out.println("Create Table query voor klasse Artikel: ");
		System.out.println(annotationOpdracht.createTable(artikel));
		System.out.println();
		
		klant.setVoornaam("Test");
		klant.setTussenvoegsel("de");
		klant.setAchternaam("AnnotationQuery");
		klant.setEmail("Annotation@email.com");
		System.out.println("buildInsertStatement query voor een nieuwe Klant: ");
		System.out.println(annotationOpdracht.buildInsertStatement(klant));
		System.out.println();
		
		artikel.setArtikel_naam("Java boek");
		artikel.setArtikel_prijs(49.95);
		System.out.println("buildInsertStatement query voor een nieuw Artikel: ");
		System.out.println(annotationOpdracht.buildInsertStatement(artikel));	 
	}
	
	
public String createTable(Object object){
	Class<?> clazz = object.getClass();
	
	TableParser tableParser = new TableParser();
	String sqlTableName = tableParser.parse(clazz);
	logger.info("sqlTableName is: " + sqlTableName);
	
	IdParser idParser = new IdParser();
	String primaryKey = idParser.parse(clazz);
	logger.info("primaryKey is: " + primaryKey);
	
	
	String createTable = "CREATE TABLE " + sqlTableName + "(" + primaryKey + 
			" INT(11) NOT NULL AUTO_INCREMENT, ";
	
	ColumnParser columnParser = new ColumnParser();
	String[][] columnNamesAndTypes = columnParser.parse(clazz);
	
	String tussenString = "";
	for(int i = 0; i < columnNamesAndTypes.length; i++){
		for(int j = 0; j < columnNamesAndTypes[i].length; j++){
			
			if (j==0){
				if(columnNamesAndTypes[i][j] == null){
					//doNothing
				}else{
				tussenString+= columnNamesAndTypes[i][j] + " ";
				}
			}else {
					if(columnNamesAndTypes[i][j] == null){
						//doNothing
					}
					else if(columnNamesAndTypes[i][j].contains("String")){
						tussenString += "VARCHAR(45), ";
					}else if (columnNamesAndTypes[i][j].contains("long")|| (columnNamesAndTypes[i][j].contains("int"))){
						tussenString += "INT(11), ";
					
					}
					else {	
					 tussenString+= columnNamesAndTypes[i][j] + ", ";
					}
			}
		}
	
	}
	logger.info("tussenString = " + tussenString);
	createTable += tussenString;
	
	String createPrimaryKey = "PRIMARY KEY(" + primaryKey + "))";
	createTable += createPrimaryKey;
	logger.info("createTable final: " + createTable);
	return createTable;
}
	
public String buildInsertStatement(Object object) {
		

		int variableToInsert = 0;
		//String sqlTableName = Object.class.getSimpleName().toUpperCase();	
		//String sqlTableName = this.getKlasseNaam(object).toUpperCase();		
		Class<?> clazz = object.getClass();
		TableParser tableParser = new TableParser();
		String sqlTableName = tableParser.parse(clazz);
		
		logger.info("sqlTableName is: " + sqlTableName);
		String buildSqlStatement = "INSERT INTO " + sqlTableName + "(";
		String valueFieldEnd = "values (";
		//Field[] declaredFields = Object.class.getDeclaredFields();
	
		
		Field[] declaredFields = clazz.getDeclaredFields();
		
		logger.info("array declaredFields VOOR for loop" + declaredFields);
		
		logger.info("lengte van declaredFields is: " + declaredFields.length);
				
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

	private String getClassName (Object object) {	
	String className = object.getClass().getSimpleName();		
	logger.info("className: " + className);
	return className;
}

}
