package dao;

import java.util.*;

import business.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLKlantDaoImpl extends KlantDaoImpl {

	private static final Logger logger =  LoggerFactory.getLogger(KlantDaoImpl.class);
	
	@Override
    public String create(Klant klant){
		super.create(klant);
		return klant.toString();
	}
	
	 @Override
	    public List<Klant> read() {

		 List<Klant> klanten = super.read();
		 return klanten;
	 }
	
	 @Override
	 public Klant readKlant(int klant_id){
		 Klant klant = super.readKlant(klant_id);
		 return klant;
	 }
	 
	 @Override
	 public Klant readKlant(String voornaam){
		 Klant klant = super.readKlant(voornaam);
		 return klant;
	 }
	 
	 @Override   
	 public String update(Klant klant){
		 super.update(klant);
		 return "De gegevens van Klant_id: " + klant.getKlant_id() + "zijn aangepast";
	 }
	 
	 @Override
	 public String delete(Klant klant){
		 super.delete(klant);
		 return "De gegevens van Klant_id: " + klant.getKlant_id() + "zijn gewist";
	 }
}

