/**
 * 
 */

/**
 * @author Agung
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KlantDaoImpl test = new KlantDaoImpl();
		Klant klant = new Klant();
		
		klant.setKlant_id(klant.getKlant_id());
		klant.setVoornaam("Agung");
		klant.setTussenvoegsel("");
		klant.setAchternaam("Udijana");
		
		test.getConnection();
		test.insert(klant);
		test.closeConnection();
	}

}
