package service;

import java.lang.annotation.Annotation;

import business.*;

public class TestClassJesse {

	public static void main(String[] args) throws Exception{
	
		EntityJesseAnnotationParser parserJesseEntity = new EntityJesseAnnotationParser();
		parserJesseEntity.parse(Klant.class);
		

		TableJesseAnnotationParser parserJesseTable = new TableJesseAnnotationParser();
		parserJesseTable.parse(Klant.class);
	 /*for (Annotation annotation : Klant.class.getAnnotations()) {
         Class<? extends Annotation> type = annotation.annotationType();
         System.out.println(type.getSimpleName());
	
	}*/
}
}
	   
	


