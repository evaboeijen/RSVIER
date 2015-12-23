package annotations;


public class TableParser {

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
          	
		 	     System.out.println("AnnotationTableNotFoundException for " + clazz.getSimpleName());
		 	    e.printStackTrace();
		        }
		 			return tabelNaam;
	      }
}
