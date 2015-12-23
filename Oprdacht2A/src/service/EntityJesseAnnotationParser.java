package service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import business.*;

public class EntityJesseAnnotationParser {
	
	public String parse(Class<?> clazz) {
		String entityNaam = null;
		
		 try {   
		         EntityJesse entityJesse = clazz.getAnnotation(EntityJesse.class);
		            
		         entityNaam = entityJesse.value();
		         
		         //System.out.println(entityNaam);
		            
		            
		      } catch(Exception e){
		            	
		 	     System.out.println("AnnotationEntityNotFoundException for " + clazz.getSimpleName());
		 	     e.printStackTrace();
		      }	
		 	return entityNaam;
		 }
}
		
		/*try {
		      
			Klant klant = new Klant();
		      Class<?> m = clazz.getClass();
		      Annotation[] annos = m.getAnnotations();

		      System.out.println("All annotations for Klant:");
		      for(Annotation a : annos)
		      System.out.println(a);

		    } catch (Exception exc) {
		    	exc.printStackTrace();
		    }
		  }
		}
	*/