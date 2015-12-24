package annotations;

import java.lang.reflect.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Klant;
import java.lang.Exception;

public class ColumnParser {
	
	private static final Logger logger =  LoggerFactory.getLogger(ColumnParser.class);
	
	public String[][] parse(Class<?> clazz) {
	Field[] fields = clazz.getDeclaredFields();
		
		
		int numberOfFields = fields.length ;		
		//String[][] tempArray= null;
		String[][] tempArray = new String[numberOfFields] [2];
		int i = 0;
		int j = 0;
			
		 try {   
		 
			 	for(i=0; i < numberOfFields; i++ ) {			 				 		
			 		for(j=0; j < 2; j++ ){
		 			
			 		if (j==0){	
			 			
			 			if (!fields[i].isAnnotationPresent(Id.class) && fields[i].isAnnotationPresent(Column.class)) {
		 					logger.info("!fields[i].isAnnotationPresent(Id.class): "+ !fields[i].isAnnotationPresent(Id.class));
		 					 logger.info("waarde van i is" + i);
		 					 logger.info("waarde van j is" + j);
		 					Column column = fields[i].getAnnotation(Column.class);
		 						if("".equals(column.value())){
					        	tempArray[i][j] = fields[i].getName(); 
		 						}else {	         
					    	     tempArray[i][j] = column.value();
						 		}
		 					
		 					logger.info("tempArray[i][j] :" + tempArray[i][j]);	
		 					 logger.info("waarde van i is" + i);
		 					 logger.info("waarde van j is" + j);
		 				}else if (!fields[i].isAnnotationPresent(Id.class)){
		 						tempArray[i][j] = fields[i].getName();
		 						logger.info("tempArray[i][j] :" + tempArray[i][j]);
		 						 logger.info("waarde van i is" + i);
		 						 logger.info("waarde van j is" + j);
		 				}
			 			
			 		}else{	
			 			if(!fields[i].isAnnotationPresent(Id.class) && fields[i].getType().isPrimitive() || (fields[i].getType()+ "").contains("String")){
			 			tempArray[i][j] = ""+fields[i].getType();
		 				logger.info("tempArray[i][j] :" + tempArray[i][j]);
		 				 logger.info("waarde van i is" + i);
		 				 logger.info("waarde van j is" + j);
		 				
			 			}else if (!fields[i].getType().isPrimitive()){
			 				throw new Exception("DataTypeNotSupportedException"); 
			 			} 
			 		
			 		}
			 		
			 		}
			 	}
			 }catch(Exception e){
		            	
		 	     //System.out.println("DataTypeNotSupportedException");
		 	     e.printStackTrace();
		      }	
		 
		 logger.info("tempArray is: " + tempArray);
		 logger.info("waarde van i is" + i);
		 logger.info("waarde van j is" + j);
		 return tempArray;
		 }
	
	
	public static void main(String[] args){
		Klant klant = new Klant();
		AnnotationReflectionTest testCreateTable = new AnnotationReflectionTest();
		testCreateTable.createTable(klant);
		
		//ColumnParser columnParser = new ColumnParser();
		//String[][] test = columnParser.parse(Klant.class);
		//System.out.println(test.toString());
		
		/*EntityParser entityParser = new EntityParser();
		String test2 = entityParser.parse(Klant.class);
		System.out.println(test2);
		
		IdParser idParser = new IdParser();
		String test3 = idParser.parse(Klant.class);
		System.out.println(test3);
		
		TableParser tableParser = new TableParser();
		String test4 = tableParser.parse(Klant.class);
		System.out.println(test4);*/				
		
	}
}

