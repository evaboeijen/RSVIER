package service;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import business.Adres;
import business.Artikel;
import business.Bestelling;
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
				input.nextLine();
		System.out.println("\nStraatnaam: ");
			String straatnaam = input.nextLine();
				while (straatnaam.length() > 26){
				System.out.println("Een straatnaam mag niet meer dan 26 karakters bevatten! \nStraatnaam: ");
					straatnaam = input.nextLine();
				}			
		System.out.println("\nPostcode:\t\t( zonder spatie ) ");
			String postcode = input.nextLine();
				while (postcode.length() > 6){
				System.out.println("Een postcode mag niet meer dan 6 karakters bevatten! \nPostcode: ");
					straatnaam = input.nextLine();
				}
		System.out.println("\nToevoeging:\t\t( als geen toevoeging vul - in ) ");
			String toevoeging = input.nextLine();
				while (toevoeging.length() > 6){
				System.out.println("Een toevoeging mag niet meer dan 6 karakters bevatten! \nToevoeging: ");
					straatnaam = input.nextLine();
				}
		System.out.println("\nHuisnummer: ");
			int huisnummer = input.nextInt();
				while (Integer.toString(huisnummer).length() > 6){
				System.out.println("Een huisnummer mag niet meer dan 6 karakters bevatten! \nHuisnummer: ");
					huisnummer = input.nextInt();				
				}
		System.out.println("\nWoonplaats: ");
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

	public Bestelling createBestellingObject() {
		logger.info("createBestellingObject methode voor gebruiker input in DTO start");				
		
		Bestelling bestelling = new Bestelling();
		
		bestelling.setBestelling_id(check.checkHoogste_Bestelling_id() + 1);
		
	    System.out.println("Vul de gewenste gegevens : ");

	    System.out.println("\nKlantnummer: ");
		int klant_id = input.nextInt();
			while (check.checkKlant_id(klant_id)!= true){
				System.out.println("Klantnummer: ");
				klant_id = input.nextInt();
			}
		bestelling.setKlant_id(klant_id);	
			
				
        System.out.print("\nVoer het artikel ID in dat je in de bestelling wil plaatsen: ");
        System.out.println(); 
        int artikel_id = input.nextInt();
           	while (check.checkArtikel_id(artikel_id)!= true) { 
            	System.out.print("\nVoer een ander artikelnummer in: ");
            	artikel_id = input.nextInt();
            	}    
        bestelling.setArtikel_id(artikel_id);	
				
		System.out.println("Aantal: ");
		int artikel_aantal = input.nextInt();
		bestelling.setArtikel_aantal(artikel_aantal);	
		
		//Bestelling bestelling = new Bestelling(klant_id, artikel_id, artikel_aantal);
				
		logger.info("createBestellingObject methode in DTO eindigt");
		
		return bestelling;
	}	
	
}
