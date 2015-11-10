import java.util.List;

public interface BestellingDao {

	public void create(Bestelling bestelling_id);
	public void update(Bestelling bestelling);
	public List<Bestelling> read();
	public void delete(Bestelling bestelling);

}