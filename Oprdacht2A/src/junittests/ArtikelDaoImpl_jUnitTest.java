package junittests;

import dao.*;
import business.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

public class ArtikelDaoImpl_jUnitTest{

	static ArtikelDaoImpl testArtikelDaoImpl = new ArtikelDaoImpl(); 
	Artikel testArtikel = new Artikel();
		
	
	@BeforeClass
	public static void testInitializeDB() {
		
		testArtikelDaoImpl = new ArtikelDaoImpl();
		testArtikelDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testArtikelDaoImpl.connection );
		
	}

	@Test
	public void testIT() {
		
		// test create method plus test readArtikel method
		testArtikel.setBestelling_id(56757);
		testArtikel.setKlant_id(4102);
		testArtikel.setArtikel1_id(1000);
		testArtikel.setArtikel1_prijs(40);
		testArtikel.setArtikel1_naam("prullenbak");
		testArtikel.setArtikel1_aantal(1);
		
		testArtikelDaoImpl.create(testArtikel);
		Artikel artikel = testArtikelDaoImpl.readArtikel(56757, 1000);
		
		assertEquals("Object hashcode should be the same", testArtikel.hashCode(), artikel.hashCode());
		
		
		//test update and read method
		testArtikel.setArtikel1_id(81756);
		testArtikel.setArtikel1_aantal(4);
		testArtikel.setArtikel1_prijs(50);
		testArtikel.setArtikel1_naam("stoel");
		
		testArtikelDaoImpl.update(testArtikel);
		
		assertEquals("Number of rows is 1", 1 , testArtikelDaoImpl.read().size());
		
				
				
				//test delete method
				testArtikelDaoImpl.delete(56757, 1000);
				Artikel artikel2 = testArtikelDaoImpl.readArtikel(56757, 1000);
				assertEquals("Artikel id should be 0", 0 , artikel2.getArtikel1_id());
				assertEquals("Artikel naam should be null", null, artikel2.getArtikel1_naam());
				assertEquals("Artikel prijs should be 0", 0,0 , artikel2.getArtikel1_prijs());
				assertEquals("Artikel aantal should be 0", 0, artikel2.getArtikel1_aantal());
				
	}
	
	@Test
	public void testEquals() {	
		ArtikelDaoImpl testArtikelDaoImpl1 = new ArtikelDaoImpl();
		ArtikelDaoImpl testArtikelDaoImpl2  = testArtikelDaoImpl1;
		assertEquals("These should be equal", testArtikelDaoImpl1, testArtikelDaoImpl2);
}

	@AfterClass
	public static void testCloseDBConnection() {
		testArtikelDaoImpl = new ArtikelDaoImpl();
		testArtikelDaoImpl.closeDBConnection();		
		assertNull("Connection should be null again", testArtikelDaoImpl.connection );
}
}



