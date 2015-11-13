import java.util.*;


public interface KlantDao {

public void create(Klant klant);    
public List<Klant> read();   
public Klant getKlant(int klant_id);
public Klant getKlant(String voornaam);
public void update(Klant klant);   
public void delete(Klant klant);
}
