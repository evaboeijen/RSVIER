import java.util.List;
	
public interface AdresDao {

		public void insert(Adres adres);

		public List<Adres> readAllAdresses();

		public List<Adres> searchStraatnaam(String straatnaam);

		public List<Adres> searchPostcodeAndHuisnummer(String postcode, int huisnummer);

		public void updateAdres(Adres adres);

		public void deleteAdres(Adres adres);

	}


