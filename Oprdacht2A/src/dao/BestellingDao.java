package dao;
import java.util.List;

import business.Bestelling;

// comment field added for eGit test

public interface BestellingDao {

	public int create(Bestelling bestelling);
	public List<Bestelling> read();
	public int update(Bestelling bestelling);
	// oude versie : public void update(int bestelling_id, int new_artikel_id, String new_artikel_naam, int new_artikel_aantal, double new_artikel_prijs);
	public int delete (Bestelling bestelling);
	// oude versie : public void delete(int bestelling_id);
	public boolean checkBestelling_id(int bestelling_id);	// toegevoegd 21/11/15 AU
	public boolean checkArtikel_id(int artikel_id);			// toegevoegd 21/11/15 AU
	public int checkHoogste_Bestelling_id();				// toegevoegd 21/11/15 AU
	public boolean checkArtikelAlAanwezigInBestelling(int bestelling_id, int artikel_id); //toegevoegd 28/11/15 AU
	public int deleteArtikelFromBestelling(Bestelling bestelling); //toegevoegd 28/11/15 AU
}	
