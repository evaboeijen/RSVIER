package service;

public class TableJesseAnnotationParser {

	public void parse(Class<?> clazz) throws Exception {
	     // Method[] methods = clazz.getMethods();
		  try {
	     // for (Method method : methods) {
	         //if (clazz.isAnnotationPresent(TableJesse.class)) {
	            // this is how you access to the attributes
	            TableJesse tableJesse = clazz.getAnnotation(TableJesse.class);
	            
	            String tabelNaam = tableJesse.tableName();
	         	         
	            if ("".equals(tabelNaam)) {
	                System.out.println(clazz.getSimpleName());
	                
	            }else {
	            	System.out.println(tabelNaam);
	             }
	            //)
	         } catch(Exception e){
	            	
	 	     System.out.println("AnnotationTableNotFoundException for " + clazz.getSimpleName());
	 	     e.printStackTrace();
	      }
	      }
}
