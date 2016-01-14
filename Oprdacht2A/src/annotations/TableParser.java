package annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableParser {

	private static final Logger logger =  LoggerFactory.getLogger(TableParser.class);
	
	public String parse(Class<?> clazz) {
	     // Method[] methods = clazz.getMethods();
		String tabelNaam = null;
		 try {
	     // for (Method method : methods) {
	        
			  if (clazz.isAnnotationPresent(Table.class)) {
	 
				  		Table table = clazz.getAnnotation(Table.class);         
				  		if("".equals(table.value())){
				        	tabelNaam = clazz.getSimpleName(); 
				     }else {	         
				    	 tabelNaam = table.value();
					 		}
				  		
	                	//System.out.println(tabelNaam);
	                	
				  
			  }else {
	            	//System.out.println(clazz.getSimpleName());
	            	tabelNaam = clazz.getSimpleName();
	             }
			
		 } catch(Exception e){
          	
		 	    // System.out.println("AnnotationTableNotFoundException for " + clazz.getSimpleName());
		 	    e.printStackTrace();
		        }
		 logger.info("String tabelNaam is: ");
		 			return tabelNaam;
	      }
}
