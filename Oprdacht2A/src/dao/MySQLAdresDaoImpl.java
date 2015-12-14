package dao;

import java.sql.Connection;
import java.util.List;

import business.Adres;

public class MySQLAdresDaoImpl extends AdresDaoImpl{

	@Override
	public void insert(Adres adres) {
		String queryBestaandeKlant = "SELECT adres_id FROM adres WHERE straatnaam=? AND postcode=? AND toevoeging=? AND huisnummer=? AND woonplaats=?";
		String queryAdresToevoegen = "INSERT INTO adres (straatnaam, postcode , toevoeging , huisnummer , woonplaats) VALUES (?,?,?,?,?)";
		String queryAdres_Id = "SELECT adres_id FROM adres WHERE straatnaam=? AND postcode=? AND toevoeging=? AND huisnummer=? AND woonplaats=?";
		String queryAdres_IdKoppeling = "INSERT INTO klant_adres (klant_id, adres_id) VALUES (?,?)";
		super.insert(adres);
	}

	@Override
	public void updateAdres(Adres adres) {
		String queryDeletefromKlant_Adres = "DELETE FROM klant_adres WHERE klant_id=? AND adres_id=?";	
		super.updateAdres(adres);
	}

	@Override
	public void deleteAdres(Adres adres) {
		String queryDeleteFromKlant_Adres = "DELETE FROM klant_adres WHERE klant_id=? AND adres_id=?";
		super.deleteAdres(adres);
	}

	@Override
	public List<Adres> readAllAdresses() {
		String queryAll = "SELECT * FROM adres";
		return super.readAllAdresses();
	}

	@Override
	public List<Adres> searchAdres(String straatnaam) {
		String queryStraatnaam = "SELECT * FROM adres WHERE straatnaam=?";
		return super.searchAdres(straatnaam);
	}

	@Override
	public List<Adres> searchAdres(String postcode, int huisnummer) {
		String queryPostcodeHuisnummer = "SELECT * FROM adres WHERE postcode=? AND huisnummer=?";
		return super.searchAdres(postcode, huisnummer);
	}

	@Override
	public List<Adres> readAdressesFromKlant(int klant_id) {
		String queryAdressesKlant = "SELECT klant_adres.adres_id, straatnaam, huisnummer, woonplaats FROM adres JOIN klant_adres WHERE klant_adres.klant_id = ? AND klant_adres.adres_id = adres.adres_id";
		return super.readAdressesFromKlant(klant_id);
	}
}