/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */
import java.util.*;

public interface KlantDao {
  	
	public void insert(Klant klant);
	public List<Klant> select();
	public Klant getKlant(int klant_id);
	public void update(Klant klant);
	public void delete(Klant klant);

}
