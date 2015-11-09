
public class Test {

	public static void main(String[] args) {
		BestellingDaoImpl test = new BestellingDaoImpl();
		Bestelling bestelling = new Bestelling();
		
		bestelling.setBestelling_id(bestelling.getBestelling_id());
		
		test.getConnection();
		test.insert(bestelling);
		test.closeConnection();
	}

}
