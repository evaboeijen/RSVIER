package dao;

import java.util.*;

import business.Artikel;

public interface ArtikelDao {
   
public List<Artikel> read();   
public Artikel readArtikel(int artikel_id);
public void create(Artikel artikel);   
public void update(Artikel artikel);   
public void delete(Artikel artikel);

}
