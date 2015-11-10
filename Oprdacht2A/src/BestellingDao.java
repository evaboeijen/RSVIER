import java.util.List;

public interface BestellingDao {

	public void create(Bestelling bestelling_id);
	public List<Bestelling> read();
	public void update(Bestelling bestelling);
	public void delete(Bestelling bestelling);

}