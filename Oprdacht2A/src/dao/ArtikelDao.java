package dao;

import java.util.*;
import business.Artikel;

public interface ArtikelDao {

public List<Artikel> read();	
public Artikel read(Artikel artikel);   
//public Artikel readArtikel(int bestelling_id, int rtikel_id);
public String create(Artikel artikel);   
public String update(Artikel artikel);   
//public String delete(int bestelling_id, int artikel_id);
public String delete(Artikel Artikel);

}


