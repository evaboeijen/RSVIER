package junittests;

import dao.*;
import business.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

public class ArtikelDaoImpl_jUnitTest{

	
	@BeforeClass
	public static void testInitializeDB() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		ArtikelDaoImpl testArtikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze(); 
		
		testArtikelDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testArtikelDaoImpl.connection );
		
	}

	@Test
	public void testIT() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		ArtikelDaoImpl testArtikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze(); 
		Artikel testArtikel = new Artikel();
		
		// test create method plus test readArtikel method
		//testArtikel.setBestelling_id(56757);
		//testArtikel.setKlant_id(4102);
		testArtikel.setArtikel_id(1000);
		testArtikel.setArtikel_prijs(40);
		testArtikel.setArtikel_naam("prullenbak");
		//testArtikel.setArtikel_aantal(1);
		
		testArtikelDaoImpl.create(testArtikel);
		//Artikel artikel = testArtikelDaoImpl.readArtikel(56757, 1000);
		
		//assertEquals("Object hashcode should be the same", testArtikel.hashCode(), artikel.hashCode());
		
		
		//test update and read method
		testArtikel.setArtikel_id(81756);
		//testArtikel.setArtikel_aantal(4);
		testArtikel.setArtikel_prijs(50);
		testArtikel.setArtikel_naam("stoel");
		
		testArtikelDaoImpl.update(testArtikel);
		
		assertEquals("Number of rows is 1", 1 , testArtikelDaoImpl.read().size());
		
				
				
				//test delete method
				testArtikelDaoImpl.delete(56757, 1000);
				Artikel artikel2 = testArtikelDaoImpl.read(testArtikel);
				assertEquals("Artikel id should be 0", 0 , artikel2.getArtikel_id());
				assertEquals("Artikel naam should be null", null, artikel2.getArtikel_naam());
				assertEquals("Artikel prijs should be 0", 0,0 , artikel2.getArtikel_prijs());
				//assertEquals("Artikel aantal should be 0", 0, artikel2.getArtikel_aantal());
				
	}
	
	@Test
	public void testEquals() {	
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		
		ArtikelDaoImpl testArtikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze(); 
		ArtikelDaoImpl testArtikelDaoImpl2  = testArtikelDaoImpl;
		assertEquals("These should be equal", testArtikelDaoImpl, testArtikelDaoImpl2);
}

	@AfterClass
	public static void testCloseDBConnection() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();
		ArtikelDaoImpl testArtikelDaoImpl = daoImplKeuze.ArtikelDaoImplKeuze(); 
		
		testArtikelDaoImpl.closeDBConnection();		
		assertNull("Connection should be null again", testArtikelDaoImpl.connection );
}
}



