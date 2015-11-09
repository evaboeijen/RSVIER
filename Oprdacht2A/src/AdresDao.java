import java.util.List;
	
public interface AdresDao {

		public void insert(Adres adres);

		public List<Adres> select();

		public void getAdres(int klant_id);

		public void updateAdres(int klant_id);

		public void deleteAdres(int klant_id);

	}


