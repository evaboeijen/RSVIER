package junittests;

import dao.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.Bestelling;



public class BestellingDaoImpl_jUnitTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// nog (niet) geimplementeerd
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// nog (niet) geimplementeerd
	}

	@Before
	public void setUp() throws Exception {
		// nog (niet) geimplementeerd
	}

	@After
	public void tearDown() throws Exception {
		// nog (niet) geimplementeerd
	}

	@Test
	public void testInitializeDB() {
		
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		testBestellingDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testBestellingDaoImpl.connection );
		
	}

	@Test
	public void testCreate() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		Bestelling testBestelling = new Bestelling(9000, 9000, 9000, "test jUnit artikel1", 9000, 9000.99);	
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows created is 1", 1, testBestellingDaoImpl.create(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}
	
		
		
	@Test
	public void testRead() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows is 3", 3 , testBestellingDaoImpl.read().size());
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testUpdate() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		Bestelling testBestelling = new Bestelling(1111, 6969, 6969, "jUnit test update method", 6969, 6969.99);	
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows created is 1", 1, testBestellingDaoImpl.update(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testDelete() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		Bestelling testBestelling = new Bestelling(9000);
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows deleted is 1", 1, testBestellingDaoImpl.delete(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testCloseDBConnection() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl = daoImplKeuze.BestellingDaoImplKeuze(); 
		testBestellingDaoImpl.closeDBConnection();		
		assertNull("Connection should be null again", testBestellingDaoImpl.connection );
	}

	@Test
	public void testHashCode() {
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl1 = daoImplKeuze.BestellingDaoImplKeuze(); 
		BestellingDaoImpl testBestellingDaoImpl2 = daoImplKeuze.BestellingDaoImplKeuze(); 
		assertTrue( "HashCodes should be equal", testBestellingDaoImpl1.hashCode() == testBestellingDaoImpl2.hashCode() );
		
	}

	@Test
	public void testEquals() {	
		DaoImplKeuze daoImplKeuze = new DaoImplKeuze();		
		BestellingDaoImpl testBestellingDaoImpl1 = daoImplKeuze.BestellingDaoImplKeuze(); 
		BestellingDaoImpl testBestellingDaoImpl2 = daoImplKeuze.BestellingDaoImplKeuze(); 
		assertEquals("These should be equal", testBestellingDaoImpl1, testBestellingDaoImpl2);
	}

}
