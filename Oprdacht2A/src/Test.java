import java.util.List;

public class Test {

	public static void main(String[] args) {
		BestellingDaoImpl bestellingDaoImpl = new BestellingDaoImpl();
		Bestelling bestelling = new Bestelling(1000, 1000, 1000, "test artikel", 1000, 1000.50, 2000, "test artikel2", 2000, 2000.50, 3000, "test artikel3", 3000, 3000.50);
		Bestelling bestelling2 = new Bestelling(2000, 2000, 1000, "test artikel", 1000, 1000.50, 2000, "test artikel2", 2000, 2000.50);
		Bestelling bestelling3 = new Bestelling(50, 30, 200, "iphone 6s plus", 10, 1200.99);
		
		
		bestellingDaoImpl.initializeDB();
							
		bestellingDaoImpl.create(bestelling);	// testen van create methode - invoeren van nieuwe bestelling met 3 artikelen
		bestellingDaoImpl.create(bestelling2); // testen van create methode - invoeren van nieuwe bestelling met 2 artikelen
		bestellingDaoImpl.create(bestelling3); // testen van create methode - invoeren van nieuwe bestelling met 2 artikelen
		
		
		List<Bestelling> lijst = bestellingDaoImpl.read();	// lees en toon alle bestellingen uit de Bestelling tabel
		
		System.out.println();
		System.out.println();	
		
		for (Bestelling overzicht : lijst) {
			System.out.println("Klantnummer : " + overzicht.klant_id + ". Ordernummer : " + overzicht.bestelling_id);
			System.out.println("---------------------------------------------");
			System.out.println("Artikelnummer: " + overzicht.artikel1_id + ". Artikelnaam: " + overzicht.artikel1_naam + ". Aantal: "+ overzicht.artikel1_aantal + ". Prijs: " + overzicht.artikel1_prijs);
			System.out.println("Artikelnummer: " + overzicht.artikel2_id + ". Artikelnaam: " + overzicht.artikel2_naam + ". Aantal: "+ overzicht.artikel2_aantal + ". Prijs: " + overzicht.artikel2_prijs);
			System.out.println("Artikelnummer: " + overzicht.artikel3_id + ". Artikelnaam: " + overzicht.artikel3_naam + ". Aantal: "+ overzicht.artikel3_aantal + ". Prijs: " + overzicht.artikel3_prijs);
			System.out.println();
			System.out.println();			
		}
		
		Bestelling updateBestelling5000 = new Bestelling(5000, 1234, 9999, "ipad", 2000, 499.99);		
		bestellingDaoImpl.update(updateBestelling5000);	// voeg een ipad artikel toe aan ordernummer 5000 die al aan zijn maximaal aantal artikelen van 3 zit

		Bestelling updateBestelling1111 = new Bestelling(1111, 1111, 9999, "ipad", 2000, 499.99);
		bestellingDaoImpl.update(updateBestelling1111);	// voeg een ipad artikel toe aan ordernummer 1111
		
		Bestelling updateBestelling9999 = new Bestelling(5555555, 0000, 9999, "ipad", 2000, 499.99);
		bestellingDaoImpl.update(updateBestelling9999);	// voeg een ipad artikel toe aan een niet bestaande bestelling (en een niet bestaande klant)
		
		Bestelling updateBestelling888888 = new Bestelling(888888, 0000, 9999, "ipad", 2000, 499.99);
		bestellingDaoImpl.update(updateBestelling888888);	// voeg een ipad artikel toe aan een niet bestaande bestelling (en een niet bestaande klant)
				
		Bestelling deleteBestelling1000 = new Bestelling (1000);
		bestellingDaoImpl.delete(deleteBestelling1000); // test - delete bestelling met bestelling_id = 1000
		
		Bestelling deleteBestelling2000 = new Bestelling (2000);
		bestellingDaoImpl.delete(deleteBestelling2000); // test - delete bestelling met bestelling_id = 2000
		
		Bestelling deleteBestelling30 = new Bestelling (30);
		bestellingDaoImpl.delete(deleteBestelling30); // test - delete bestelling met bestelling_id = 30 (die niet bestaat)
		
		Bestelling deleteBestelling50 = new Bestelling (50);
		bestellingDaoImpl.delete(deleteBestelling50); // test - delete bestelling met bestelling_id = 30 (die niet bestaat)
		
		bestellingDaoImpl.closeDBConnection(); 
	}

}
