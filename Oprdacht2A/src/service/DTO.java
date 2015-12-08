package service;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import business.Adres;
import dao.ArtikelDaoImpl;

public class DTO {
	private static final Logger logger =  LoggerFactory.getLogger(ArtikelDaoImpl.class);
	Scanner input = new Scanner(System.in);
	Check check = new Check();

	public Adres createAdresObject() {		
	    System.out.println("Klantnummer: ");
			int klant_id = input.nextInt();
				while (check.checkKlant_id(klant_id)!= true){
					System.out.println("Klantnummer: ");
					klant_id = input.nextInt();
				}
		System.out.println("Straatnaam: ");
			String straatnaam = input.next();
				while (straatnaam.length() > 26){
				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
					straatnaam = input.next();
				}			
		System.out.println("Postcode:\t\t( zonder spatie ) ");
			String postcode = input.next();
				while (postcode.length() > 6){
				System.out.println("Een postcode mag niet meer dan 6 karakters bevatten! \nPostcode: ");
					straatnaam = input.next();
				}
		System.out.println("Toevoeging:\t\t( als geen toevoeging vul - in ) ");
			String toevoeging = input.next();
				while (toevoeging.length() > 6){
				System.out.println("Een toevoeging mag niet meer dan 6 karakters bevatten! \nToevoeging: ");
					straatnaam = input.next();
				}
		System.out.println("Huisnummer: ");
			int huisnummer = input.nextInt();
				while (Integer.toString(huisnummer).length() > 6){
				System.out.println("Een huisnummer mag niet meer dan 6 karakters bevatten! \nHuisnummer: ");
					huisnummer = input.nextInt();				
				}
		System.out.println("Woonplaats: ");
			String woonplaats = input.next();
				while (woonplaats.length() > 26){
				System.out.println("Een woonplaats mag niet meer dan 26 karakters bevatten! \nWoonplaats: ");
					straatnaam = input.next();
				}		
		Adres adres = new Adres(klant_id, straatnaam, postcode, toevoeging, huisnummer, woonplaats);
		logger.debug("Het zojuist gecreëerde adres is: " + adres);
		return adres;
	    }

}
