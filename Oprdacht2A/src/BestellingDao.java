import java.util.List;

/**
 * 
 */

/**
 * @author Agung
 *
 */
public interface BestellingDao {
	
	public void insert(Bestelling bestelling_id);
	public List<Klant> select();
	public Klant getKlant(int klant_id);
	public void update(Bestelling bestelling);
	public void delete(Bestelling bestelling);
	
}