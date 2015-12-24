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
		 					
		 					Column column = fields[i].getAnnotation(Column.class);
		 						if("".equals(column.value())){
					        	tempArray[i][j] = fields[i].getName(); 
		 						}else {	         
					    	     tempArray[i][j] = column.value();
						 		}
		 					
		 				}else if (!fields[i].isAnnotationPresent(Id.class)){
		 						tempArray[i][j] = fields[i].getName();
		 						
		 				}
			 			
			 		}else{	
			 			if(!fields[i].isAnnotationPresent(Id.class) && fields[i].getType().isPrimitive() || (fields[i].getType()+ "").contains("String")){
			 			tempArray[i][j] = ""+fields[i].getType();
		 				
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
	

}

