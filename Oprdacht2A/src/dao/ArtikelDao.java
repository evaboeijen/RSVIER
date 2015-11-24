package dao;

import business.Artikel;

public interface ArtikelDao {
   
public Artikel read(Artikel artikel);   
public Artikel readArtikel(int bestelling_id, int artikel_id);
public void create(Artikel artikel);   
public void update(Artikel artikel);   
public void delete(int bestelling_id, int artikel_id);

}

