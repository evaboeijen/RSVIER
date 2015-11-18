package junittests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BestellingDaoImpl_jUnitTest.class, KlantDaoImpl_jUnitTest.class	 })  // later test unit voor Artikel en Adres toevoegen
public class AllJunitTests {

} 