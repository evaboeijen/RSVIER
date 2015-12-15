package dao;

import java.util.*;

import business.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLKlantDaoImpl extends KlantDaoImpl {

	private static final Logger logger =  LoggerFactory.getLogger(KlantDaoImpl.class);
	
	@Override
    public void create(Klant klant){
		super.create(klant);
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
	 public void update(Klant klant){
		 super.update(klant);
	 }
	 
	 @Override
	 public void delete(Klant klant){
		 super.delete(klant);
	 }
}

