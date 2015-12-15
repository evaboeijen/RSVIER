package dao;

import java.util.*;

import business.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLArtikelDaoImpl extends ArtikelDaoImpl{
	
	private static final Logger logger =  LoggerFactory.getLogger(KlantDaoImpl.class);
	
	@Override
	public List<Artikel> read(){
	
		List<Artikel> artikellen = super.read();
		return artikellen;
	
	}
	
	@Override
	public Artikel read(Artikel artikel){
		return super.read(artikel);
	}
	
	@Override
	public Artikel readArtikel(int bestelling_id, int artikel_id){
		return super.readArtikel(bestelling_id, artikel_id);
	}
	
	@Override
	public void create(Artikel artikel){
		super.create(artikel);
	}
	
	@Override
	public void update(Artikel artikel){
		super.update(artikel);
	}
	
	@Override
	public void delete(int bestelling_id, int artikel_id){
		super.delete(bestelling_id, artikel_id);
	}
	
	@Override
	public void delete(Artikel artikel){
		super.delete(artikel);
	}
}
