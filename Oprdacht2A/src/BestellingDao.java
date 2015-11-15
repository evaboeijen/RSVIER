import java.util.List;

public interface BestellingDao {

	public int create(Bestelling bestelling);
	public List<Bestelling> read();
	public int update(Bestelling bestelling);
	// oude versie : public void update(int bestelling_id, int new_artikel_id, String new_artikel_naam, int new_artikel_aantal, double new_artikel_prijs);
	public int delete (Bestelling bestelling);
	// oude versie : public void delete(int bestelling_id);
}
