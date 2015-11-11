
public class Test {

	public static void main(String[] args) {
		BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
		Bestelling bestelling = new Bestelling(1000, 1000, 1000, "test artikel", 1000, 1000.50, 2000, "test artikel2", 2000, 2000.50, 3000, "test artikel3", 3000, 3000.50);
		bestellingDaoImpl.initializeDB();
							
		bestellingDaoImpl.create(bestelling);
		
		bestellingDaoImpl.delete(1000); // test - delete bestelling met bestelling_id = 1000
		
		bestellingDaoImpl.closeDBConnection(); 
	}

}
