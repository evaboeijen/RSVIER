package dao;
import java.util.*;

import business.Klant;


public interface KlantDao {
   
public List<Klant> read();   
public Klant readKlant(int klant_id);
public Klant readKlant(String voornaam);
public String create(Klant klant);   
public String update(Klant klant);   
public String delete(Klant klant);
}




