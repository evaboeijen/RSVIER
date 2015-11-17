package junittests;


import dao.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;


public class KlantDaoImpl_jUnitTest {


	static KlantDaoImpl testKlantDaoImpl = new KlantDaoImpl(); 
	Klant testKlant = new Klant();
	
	@BeforeClass
	public static void testInitializeDB(){
		
		testKlantDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testKlantDaoImpl.connection );
		
	}
	 
	@Test
	public void testCreate(){
		
		testKlant.setKlant_id(4102);
		testKlant.setVoornaam("jUnit");
		testKlant.setAchternaam("Test");
		testKlant.setEmail("jUnitTest@email.com");
		testKlantDaoImpl.create(testKlant);
		Klant klant = testKlantDaoImpl.readKlant("jUnit");
		
		assertEquals("Object hashcode should be the same", testKlant.hashCode(), klant.hashCode());
		
	}
	
	@Test
	public void testRead(){
		
		
		assertEquals("Number of rows is 3", 3 , testKlantDaoImpl.read().size());
		
	}
		
	@Test
	public void testReadKlant(){
		Klant klant = testKlantDaoImpl.readKlant(4102);
		assertEquals("HashCode should be equal", testKlant, klant);
	}

		
	
		@Test
		public void testDelete(){
		
			testKlant.setKlant_id(4102);
			testKlantDaoImpl.delete(testKlant);
			Klant klant = testKlantDaoImpl.readKlant(4102);
			assertEquals("Klant id should be 0", 0 , klant.getKlant_id());
			assertEquals("Klant voornaam should be null", null, klant.getVoornaam());
			assertEquals("Klant achternaam should be null", null, klant.getAchternaam());
			assertEquals("Klant tussenvoegsel should be null", null, klant.getTussenvoegsel());
			assertEquals("Klant email should be null", null, klant.getEmail());
			
		}
		
		@Test
		public void testEquals() {	
			KlantDaoImpl testKlantDaoImpl1 = new KlantDaoImpl(); 
			KlantDaoImpl testKlantDaoImpl2 = testKlantDaoImpl1; 
			assertEquals("Objects should be equal", testKlantDaoImpl1, testKlantDaoImpl2);
		}

	
		@AfterClass
		public static void testCloseDBConnection() {
		
			 testKlantDaoImpl.closeDBConnection();		
			assertNull("Connection should be null again", testKlantDaoImpl.connection);
		}
		

}
	
	
	
	

