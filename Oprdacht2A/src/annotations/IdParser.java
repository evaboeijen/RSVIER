package annotations;

import java.lang.reflect.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdParser{
	
	private static final Logger logger =  LoggerFactory.getLogger(IdParser.class);
	
	public String parse(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		String idNaam = null;
		
		boolean IdAnnotationIsPresent = false;
		for (Field field: fields) {
			if (!field.isAnnotationPresent(Id.class)){
				//doNothing
		}else {
			IdAnnotationIsPresent = true;
		}
		}
		logger.info("Id Annotation is aanwezig?: " + IdAnnotationIsPresent);
		
		try {   
			if (IdAnnotationIsPresent == true) {
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
			}else {
				throw new Exception("AnnotationNotPresent exception for " + clazz);
			}
			 
			 	
		} catch(Exception e){
		            	
		 	    // System.out.println("AnnotationIdNotFoundException for " + clazz.getSimpleName());
		 	     e.printStackTrace();
		      }	
		 
		logger.info("String idNaam is: ");
		 return idNaam;
		 }
	
}

