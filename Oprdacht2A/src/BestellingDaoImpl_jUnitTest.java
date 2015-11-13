import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BestellingDaoImplTest {

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
		fail("Not yet implemented");
	}

	@Test
	public void testRead() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
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
