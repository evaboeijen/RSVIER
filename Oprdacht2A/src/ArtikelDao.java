import java.sql.*;
import java.util.*;

public interface ArtikelDao {
    public Connection getConnection();  
public List<Artikel> selectAll();   
public Artikel getArtikel(int artikel_id);

public void insert(Artikel artikel);   
public void update(Artikel artikel);   
public void delete(Artikel artikel);

}
