package annotations;


import java.lang.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import business.*;

public class EntityParser {
	
	private static final Logger logger =  LoggerFactory.getLogger(EntityParser.class);
	
	public String parse(Class<?> clazz) {
		String entityNaam = null;
		
		try {   
			if(clazz.isAnnotationPresent(Entity.class)){
		         Entity entity = clazz.getAnnotation(Entity.class);
		         
		         if("".equals(entity.value())){
		        	entityNaam = clazz.getSimpleName(); 
		         } else {
		         entityNaam = entity.value();
		         }
		         //System.out.println(entityNaam);
			}else {
				 throw new Exception("AnnotationNotPresent exception for " + clazz);
			}
		            
		} catch(Exception e){
		            	
		 	    // System.out.println("AnnotationEntityNotFoundException for " + clazz.getSimpleName());
		 	    e.printStackTrace();
		      }	
		 	logger.info("String entityNaam is: ");
		 	return entityNaam;
		 }
}