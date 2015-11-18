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
		
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		testBestellingDaoImpl.initializeDB();		
		assertNotNull("Connection should not be null", testBestellingDaoImpl.connection );
		
	}

	@Test
	public void testCreate() {
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		Bestelling testBestelling = new Bestelling(9000, 9000, 9000, "test jUnit artikel1", 9000, 9000.99, 10000, "test jUnit artikel2", 10000, 1999.99, 20000, "test jUnit artikel4", 20000, 22222.99);	
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows created is 1", 1, testBestellingDaoImpl.create(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}
	
		
		
	@Test
	public void testRead() {
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows is 3", 3 , testBestellingDaoImpl.read().size());
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testUpdate() {
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		Bestelling testBestelling = new Bestelling(1111, 6969, 6969, "jUnit test update method", 6969, 6969.99);	
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows created is 1", 1, testBestellingDaoImpl.update(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testDelete() {
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		Bestelling testBestelling = new Bestelling(9000);
		testBestellingDaoImpl.initializeDB();
		assertEquals("Number of rows deleted is 1", 1, testBestellingDaoImpl.delete(testBestelling));
		testBestellingDaoImpl.closeDBConnection();
	}

	@Test
	public void testCloseDBConnection() {
		BestellingDaoImpl testBestellingDaoImpl = new BestellingDaoImpl();
		testBestellingDaoImpl.closeDBConnection();		
		assertNull("Connection should be null again", testBestellingDaoImpl.connection );
	}

	@Test
	public void testHashCode() {
		BestellingDaoImpl testBestellingDaoImpl1 = new BestellingDaoImpl();
		BestellingDaoImpl testBestellingDaoImpl2 = testBestellingDaoImpl1;
		assertTrue( "HashCodes should be equal", testBestellingDaoImpl1.hashCode() == testBestellingDaoImpl2.hashCode() );
		
	}

	@Test
	public void testEquals() {	
		BestellingDaoImpl testBestellingDaoImpl1 = new BestellingDaoImpl();
		BestellingDaoImpl testBestellingDaoImpl2 = testBestellingDaoImpl1;
		assertEquals("These should be equal", testBestellingDaoImpl1, testBestellingDaoImpl2);
	}

}
