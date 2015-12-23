package annotations;


import java.lang.annotation.*;

public class EntityParser {
	
	public String parse(Class<?> clazz) {
		String entityNaam = null;
		
		 try {   
		         Entity entity = clazz.getAnnotation(Entity.class);
		         
		         if("".equals(entity.value())){
		        	entityNaam = clazz.getSimpleName(); 
		         }else{
		         entityNaam = entity.value();
		         }
		         //System.out.println(entityNaam);
		            
		            
		      } catch(Exception e){
		            	
		 	     System.out.println("AnnotationEntityNotFoundException for " + clazz.getSimpleName());
		 	    e.printStackTrace();
		      }	
		 	return entityNaam;
		 }
}