package dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Bestelling;

public class FireBirdBestellingDaoImpl extends BestellingDaoImpl{
	
	private static final Logger logger =  LoggerFactory.getLogger(FireBirdBestellingDaoImpl.class);
	
		@Override
		public int create(Bestelling bestelling) {
			queryBestellingToevoegen1 = "insert into Bestelling (bestelling_id, klant_id) values (?, ?)";
	    	queryBestellingToevoegen2 = "insert into Bestelling_Artikel (bestelling_id, artikel_id, artikel_aantal) values (?, ?, ?)";
			logger.info("insert query for Firebird executed");
	    	return super.create(bestelling);
		}
		@Override
		public List<Bestelling> read() {						
			queryToonAlleBestellingen = "SELECT * FROM bestelling JOIN bestelling_artikel JOIN artikel WHERE (bestelling.bestelling_id = bestelling_artikel.bestelling_id AND bestelling_artikel.artikel_id = artikel.artikel_id)";
			logger.info("select query for Firebird executed");
			return super.read();
		}
		@Override
		public int update(Bestelling bestelling) {
			queryUpdateBestelling1 = "SELECT * FROM bestelling JOIN bestelling_artikel JOIN artikel WHERE (bestelling.bestelling_id = ? AND bestelling.bestelling_id = bestelling_artikel.bestelling_id AND bestelling_artikel.artikel_id = artikel.artikel_id)";
			queryUpdateBestelling2 = "insert into Bestelling_Artikel (bestelling_id, artikel_id, artikel_aantal) values (?, ?, ?)";
			logger.info("select and insert queries for Firebird executed");
			return super.update(bestelling);
		}
		@Override
		public int updateAantallen(Bestelling bestelling) {
			queryUpdateAantallen = "SELECT * FROM bestelling JOIN bestelling_artikel JOIN artikel WHERE (bestelling.bestelling_id = ? AND bestelling.bestelling_id = bestelling_artikel.bestelling_id AND bestelling_artikel.artikel_id = artikel.artikel_id)";
			logger.info("select query for Firebird executed");
			return super.updateAantallen(bestelling);
		}
		@Override
		public int delete(Bestelling bestelling) {
			queryDeleteBestelling = "DELETE FROM bestelling WHERE bestelling_id = ?";
			logger.info("delete query for Firebird executed");
			return super.delete(bestelling);
		}
		@Override
		public int deleteArtikelFromBestelling(Bestelling bestelling) {
			queryDeleteArtikelFromBestelling = "DELETE bestelling_artikel FROM bestelling_artikel WHERE bestelling_artikel.artikel_id = ? AND bestelling_artikel.bestelling_id = ?";
			logger.info("delete query for Firebird executed");
			return super.deleteArtikelFromBestelling(bestelling);
		}
	
}
