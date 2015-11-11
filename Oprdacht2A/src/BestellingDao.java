import java.util.List;

public interface BestellingDao {

	public void create(Bestelling bestelling);
	public List<Bestelling> read();
	public void update(Bestelling bestelling);
	public void delete(int bestelling_id);

}