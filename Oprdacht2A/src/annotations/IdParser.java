package annotations;

import business.*;
import java.lang.reflect.*;

public class IdParser {
	
	public String parse(Class<?> clazz) {
		
		Field[] fields = clazz.getDeclaredFields();
		
		String idNaam = null;
		
		
		 try {   
			 
			 for (Field field : fields) {
				 if (field.isAnnotationPresent(Id.class)) {
		         Id id = field.getAnnotation(Id.class);
		         
		         if("".equals(id.value())){
			        	idNaam = field.getName(); 
			     }else {	         
			    	 idNaam = id.value();
				 		}
		        
				 	}
			 	}
		     } catch(Exception e){
		            	
		 	     System.out.println("AnnotationIdNotFoundException for " + clazz.getSimpleName());
		 	     e.printStackTrace();
		      }	
		 
		 return idNaam;
		 }
	
}

