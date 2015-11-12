import java.util.List;
	
public interface AdresDao {

		public void insert(Adres adres);

		public List<Adres> select();

		public void getAdres(Adres adres);

		public void updateAdres(Adres adres);

		public void deleteAdres(Adres adres);

	}
