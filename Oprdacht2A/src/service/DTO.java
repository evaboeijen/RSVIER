package service;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import business.Adres;
import business.Artikel;
import business.Klant;
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

	public Klant createKlantObject() {
		logger.info("createKlantObject methode voor gebruiker input in DTO start");
		
		Scanner input = new Scanner(System.in);					
		
	    System.out.println("Vul uw gegevens in. Voer in uw voornaam, en druk op enter: ");
		String voornaam = input.nextLine();
		
				while (voornaam.length() > 50){
					System.out.println("Een voornaam mag niet meer dan 50 karakters bevatten! \n Voornaam: ");
					voornaam = input.nextLine();
				}
				
		System.out.println("Tussenvoegsel: ");
			String tussenvoegsel = input.nextLine();
				while (tussenvoegsel.length() > 12){
				System.out.println("Een tussenvoegsel niet meer dan 12 karakters bevatten! \n Tussenvoegsel: ");
					tussenvoegsel = input.nextLine();
				}			
		System.out.println("Achternaam: ");
			String achternaam = input.nextLine();
				while (achternaam.length() > 51){
				System.out.println("Een achternaam mag niet meer dan 51 karakters bevatten! \n Achternaam: ");
					achternaam = input.nextLine();
				}
		System.out.println("Emailadres: ");
			String email =  input.nextLine();
				while (email.length() > 80){
				System.out.println("Een email mag niet meer dan 80 karakters bevatten! \n Emailadres: ");
					email = input.nextLine();
				}
		
				Klant klant = new Klant();
				klant.setKlant_id(klant.getKlant_id());
				klant.setVoornaam(voornaam);
				klant.setTussenvoegsel(tussenvoegsel);
				klant.setAchternaam(achternaam);
				klant.setEmail(email);
				
		logger.info("createKlantObject methode in DTO eindigt");	
				return klant;
				}	
	
	public Artikel createArtikelObject(){
		
		logger.info("createArtikelObject methode voor gebruiker input in ArtikelMenu start");
	
			Scanner input = new Scanner(System.in);	
		
			Artikel artikel = new Artikel();
			
			
			System.out.println("Vul de naam van het artikel in: ");
			String artikel_naam = input.nextLine(); 
			
			System.out.println("Wat is de prijs van het artikel: ");
			double artikel_prijs = input.nextDouble();

		
			artikel.setArtikel_naam(artikel_naam);
			artikel.setArtikel_prijs(artikel_prijs);
			
			logger.info("createArtikelObject methode input eindigt");
			
			return artikel;
		}
	
}
