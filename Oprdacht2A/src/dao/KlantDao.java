package dao;
import java.util.*;

import business.Klant;


public interface KlantDao {
   
public List<Klant> read();   
public Klant readKlant(int klant_id);
public Klant readKlant(String voornaam);
public void create(Klant klant);   
public void update(Klant klant);   
public void delete(Klant klant);
}



