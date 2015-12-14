package dao;

import menu.DBKeuzeMenu;


public class DaoImplKeuze {

	public AdresDaoImpl AdresDaoImplKeuze() {
		AdresDaoImpl dbAdres = (AdresDaoImpl) new Object();
		if(DBKeuzeMenu.getDBKeuze()==1){
			dbAdres = new MySQLAdresDaoImpl();
		}else if(DBKeuzeMenu.getDBKeuze()==2){
			dbAdres = new FireBirdAdresDaoImpl();
		}
		return dbAdres;
	}
	public KlantDaoImpl KlantDaoImplKeuze() {
		KlantDaoImpl dbKlant = (KlantDaoImpl) new Object();
		if(DBKeuzeMenu.getDBKeuze()==1){
			dbKlant = new MySQLKlantDaoImpl();
		}else if(DBKeuzeMenu.getDBKeuze()==2){
			dbKlant = new FireBirdKlantDaoImpl();
		}
		return dbKlant;
	}
	
	public ArtikelDaoImpl ArtikelDaoImplKeuze() {
		ArtikelDaoImpl dbArtikel = (ArtikelDaoImpl) new Object();
		if(DBKeuzeMenu.getDBKeuze()==1){
			dbArtikel = new MySQLArtikelDaoImpl();
		}else if(DBKeuzeMenu.getDBKeuze()==2){
			dbArtikel = new FireBirdArtikelDaoImpl();
		}
		return dbArtikel;
	}
	
	public BestellingDaoImpl BestellingDaoImplKeuze() {
		//BestellingDaoImpl dbBestelling = (BestellingDaoImpl) new Object();
		BestellingDaoImpl dbBestelling = null;
		if(DBKeuzeMenu.getDBKeuze()==1){
			dbBestelling = new MySQLBestellingDaoImpl();
		}else if(DBKeuzeMenu.getDBKeuze()==2){
			dbBestelling= new FireBirdBestellingDaoImpl();
		}
		return dbBestelling;
	}

}
