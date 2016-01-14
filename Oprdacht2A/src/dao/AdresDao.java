package dao;

import java.util.List;
import business.*;

public interface AdresDao {

		public Adres insert(Adres adres);

		public List<Adres> readAllAdresses();

		public List<Adres> searchAdres(String straatnaam);

		public List<Adres> searchAdres(String postcode, int huisnummer);

		public Adres updateAdres(Adres adres);

		public Adres deleteAdres(Adres adres);
}

