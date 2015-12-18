package service;

import business.Adres;

@EntityEva
public @interface TableEva {
	
	Object ding = adres.getAnnotation(adres.class);
	Adres adres = new Adres();
	String tableName() default adres.entityNaam;
}
