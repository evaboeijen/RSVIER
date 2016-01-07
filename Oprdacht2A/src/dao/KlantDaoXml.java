package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Klant;
import menu.DBConnectivityManagement;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class KlantDaoXml  {
	
	private static final Logger logger =  LoggerFactory.getLogger(KlantDaoXml.class);
	
	
	
    public void create(Klant klant) {
    	
		logger.info("Create Klant methode start");
    	
    	System.out.println();
    	System.out.println();
    	System.out.println("----------------------------------------------------------");
    	System.out.println("Er zal een klant?.xml bestand aangemaakt worden, voor de nieuwe klant in uw project folder.");
    	System.out.println("? staat voor het klant_id van de nieuwe klant.");
    	System.out.println("----------------------------------------------------------");
    	System.out.println();
    	System.out.println();
    	
    	File fileToCreate = new File("");
    	int i;
					
		for (i = 1 ; i >= 1 ; i++) {
			logger.info("i: " + i);
			File file = new File("klant" + i + ".xml");
			logger.info("file: " + file);
			if (!file.exists()) {
				fileToCreate = file;
				logger.info("fileToCreate: " + fileToCreate);
				break;
			}
		}
		
		klant.setKlant_id(i);
		
		logger.info("Inhoud van klant: " + klant);
						
		try {
			FileOutputStream fos = new FileOutputStream(fileToCreate);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			XMLEncoder xmlEncoder = new XMLEncoder(bos);
			xmlEncoder.writeObject(klant);
			xmlEncoder.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				      
		logger.info("Create Klant methode ends");	
    }
	
	
	
	public void read() {
		
		logger.info("Read Klant methode start");
		
		
		FileInputStream fis;
		File file = new File("");
		int i=0;
		
		try {			
			for (i=1; i >= 1 ; i++) {
				logger.info("i: " + i);
				file = new File("klant" + i + ".xml");
				logger.info("file: " + file);	
					if(!file.exists()) {
						logger.info("file: " + file + " bestaat niet. Alle klanten bestanden zijn uitgelezen en getoond.");	
						break;
					}					
				fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				XMLDecoder xmlDecoder = new XMLDecoder(bis);
				Klant klant = (Klant) xmlDecoder.readObject();
				System.out.println(klant);
			} 						
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Read Klant methode ends");		
	}
 
	
	public void update(int klant_id) {
		logger.info("update Klant methode start"); 
		
		Scanner input = new Scanner(System.in);
		int klant_id_final;
		Klant klant = new Klant();
		String voornaam = null;
		String achternaam = null;
		String tussenvoegsel = null;
		String email = null;
		
		File file = new File("klant" + klant_id + ".xml");
		while(!file.exists()) {
			System.out.println("Het door u opgegeven klant_id bestaat niet.");
			System.out.println("Voer een ander klant_id in en druk op Enter.");
			klant_id_final = input.nextInt(); 
			file = new File("klant" + klant_id_final + ".xml");
		}
		
		FileInputStream fis;
		
		
		try {						
				fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				XMLDecoder xmlDecoder = new XMLDecoder(bis);
				klant = (Klant) xmlDecoder.readObject();
				System.out.println(klant);			 						
		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		System.out.println("Voornaam van deze klant (klant_id: " + klant.getKlant_id() + ") luidt: " + klant.getVoornaam());
		/* System.out.println();
		input.nextLine();
		System.out.println("Wil je deze veranderen? (J/N)");
		String jaOfNee = input.nextLine();
			if (jaOfNee.toLowerCase() == "j") { */
				System.out.println("Voer de nieuwe gewenste voornaam in en druk op enter: ");
				voornaam = input.nextLine();
						while (voornaam.length() > 50) {
							System.out.println("Een voornaam mag niet meer dan 50 karakters bevatten! \n Voornaam: ");
							voornaam = input.nextLine();
						}
						
		//	}
		
			
		System.out.println("Tussenvoegsel luidt: " + klant.getTussenvoegsel());	
		/* System.out.println();
		System.out.println("Wil je deze veranderen? (J/N)");
		jaOfNee = input.nextLine();
			if (jaOfNee.toLowerCase() == "j") {  */
				System.out.println("Voer het nieuwe tussenvoegsel in en druk op enter: ");			
				tussenvoegsel = input.nextLine();
				while (tussenvoegsel.length() > 12) {
				System.out.println("Een tussenvoegsel niet meer dan 12 karakters bevatten! \n Tussenvoegsel: ");
					tussenvoegsel = input.nextLine();
				}						
		//	}
		
			
		System.out.println();	
		System.out.println("Achternaam luidt: " + klant.getAchternaam());
		System.out.println();
		/* System.out.println("Wil je deze veranderen? (J/N)");
		jaOfNee = input.nextLine();
			if (jaOfNee.toLowerCase() == "j") { */
				System.out.println("Voor de nieuwe achternaam in en druk op enter: ");
				achternaam = input.nextLine();
					while (achternaam.length() > 51) {
					System.out.println("Een achternaam mag niet meer dan 51 karakters bevatten! \n Achternaam: ");
						achternaam = input.nextLine();
					}
				
		//	}
		
		
		System.out.println("Email luidt: " + klant.getEmail());
		System.out.println();
		/* System.out.println("Wil je deze veranderen? (J/N)");
		jaOfNee = input.nextLine();
			if (jaOfNee.toLowerCase() == "j") { */
				System.out.println("Voer het nieuwe emailadres in en druk op enter: ");
					email =  input.nextLine();
						while (email.length() > 80){
						System.out.println("Een email mag niet meer dan 80 karakters bevatten! \n Emailadres: ");
							email = input.nextLine();
						}
		//	}
			
			
		klant.setVoornaam(voornaam);
		klant.setTussenvoegsel(tussenvoegsel);
		klant.setAchternaam(achternaam);
		klant.setEmail(email); 
				
				
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			XMLEncoder xmlEncoder = new XMLEncoder(bos);
			xmlEncoder.writeObject(klant);
			xmlEncoder.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();				
		System.out.println();
		System.out.println("De update is voltooid, de wijzigingen zijn opgeslagen.");
		System.out.println();
		System.out.println();

        logger.info("update Klant methode eindigt");
	}

	

   
	public void delete(int klant_id) {
	
    logger.info("delete Klant methode start");  
    
    Scanner input = new Scanner(System.in);
	int klant_id_final;
	Klant klant = new Klant();

	
	File file = new File("klant" + klant_id + ".xml");
	while(!file.exists()) {
		System.out.println("Het door u opgegeven klant_id bestaat niet.");
		System.out.println("Voer een ander klant_id in en druk op Enter.");
		klant_id_final = input.nextInt(); 
		file = new File("klant" + klant_id_final + ".xml");
	}
	
	FileInputStream fis;
	
	
	try {						
			fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			XMLDecoder xmlDecoder = new XMLDecoder(bis);
			klant = (Klant) xmlDecoder.readObject();		 						
	} 
	
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
	System.out.println("Dit is de klant die u wil verwijderen" + klant);
	System.out.println();
	System.out.println("Weet u zeker dat u deze klant wil verwijderen? (J/N)");
	
	
	String jaOfNee = input.nextLine();
	logger.info("jaOfNee " + jaOfNee);
	logger.info("jaOfNee " + jaOfNee.equalsIgnoreCase("j"));
	
		if (jaOfNee.equalsIgnoreCase("j")) {
    		if(file.delete()) {
    			System.out.println();
    			System.out.println(file.getName() + " is verwijderd!");
    			System.out.println();
    		}
    		else {
    			System.out.println();
    			System.out.println("Het verwijderen is mislukt.");
    			System.out.println();
    		}
		}
		else {
			System.out.println();
			System.out.println("Het verwijderen is geannuleerd.");
			System.out.println();
		}
	
	
    logger.info("delete Klant methode eindigt");     
    
        }    
}
