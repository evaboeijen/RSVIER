import java.util.*;

public interface ArtikelDao {
   
public List<Artikel> read();   
public Artikel getArtikel(int artikel_id);
public void create(Artikel artikel);   
public void update(Artikel artikel);   
public void delete(Artikel artikel);

}
