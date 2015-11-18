package junittests;


import dao.*;

import static org.junit.Assert.*;

import java.util.*;


import org.junit.Test;


public class KlantDaoImpl_jUnitTest {


	
	 
	@Test
	public void testIT(){
		
		KlantDaoImpl testKlantDaoImpl = new KlantDaoImpl(); 
		
		
		// testConnection method	
		testKlantDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testKlantDaoImpl.connection );
			
		
		// test create and readKlant(String) method
		Klant testKlant = new Klant();
		testKlant.setKlant_id(1);
		testKlant.setVoornaam("jUnit");
		testKlant.setAchternaam("Test");
		testKlant.setEmail("jUnitTest@email.com");
		testKlantDaoImpl.create(testKlant);
		Klant klant = testKlantDaoImpl.readKlant("jUnit");
		
		assertEquals("Object hashcode should be the same", testKlant.hashCode(), klant.hashCode());
		

		// test read method
		Klant testKlant2 = new Klant();
		testKlant2.setKlant_id(1869);
		testKlant2.setVoornaam("Klant");
		testKlant2.setTussenvoegsel("has been");
		testKlant2.setAchternaam("Made");
		testKlant2.setEmail("@email.com");
		testKlantDaoImpl.create(testKlant2);
		
		List<Klant> klantList = new ArrayList<Klant>();
		klantList.add(testKlant);
		klantList.add(testKlant2);
				
		assertEquals("Lists must be the same", klantList.hashCode(),testKlantDaoImpl.read().hashCode());
		
	
		// test delete and readKlant(int) method
			testKlantDaoImpl.delete(testKlant);
			Klant klant2 = testKlantDaoImpl.readKlant(1);
			assertEquals("Klant id should be 0", 0 , klant2.getKlant_id());
			assertEquals("Klant voornaam should be null", null, klant2.getVoornaam());
			assertEquals("Klant achternaam should be null", null, klant2.getAchternaam());
			assertEquals("Klant tussenvoegsel should be null", null, klant2.getTussenvoegsel());
			assertEquals("Klant email should be null", null, klant2.getEmail());
			testKlantDaoImpl.delete(testKlant2);
		
	
		 testKlantDaoImpl.closeDBConnection();		
		assertNull("Connection should be null again", testKlantDaoImpl.connection);
	}
	
		@Test
		public void testEquals() {	
			KlantDaoImpl testKlantDaoImpl1 = new KlantDaoImpl(); 
			KlantDaoImpl testKlantDaoImpl2 = testKlantDaoImpl1; 
			assertEquals("Objects should be equal", testKlantDaoImpl1, testKlantDaoImpl2);
		}

	
		

}
	
	
	
	

