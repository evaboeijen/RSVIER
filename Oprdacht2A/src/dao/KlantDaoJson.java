package dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Klant;
import menu.DBConnectivityManagement;

public class KlantDaoJson {

	private static final Logger logger =  LoggerFactory.getLogger(KlantDaoJson.class);
	
	public String create(Klant klant){
		
		File fileToCreate = new File("");
    	int i;
					
		for (i = 1 ; i >= 1 ; i++) {
			logger.info("i: " + i);
			File file = new File("klant" + i + ".json");
			logger.info("file: " + file);
			if (!file.exists()) {
				fileToCreate = file;
				logger.info("fileToCreate: " + fileToCreate);
				break;
			}
		}
		
		klant.setKlant_id(i);

		try (FileWriter jsonFile = new FileWriter(fileToCreate)){
				
				JSONObject obj = new JSONObject();
				
				obj.put("klant_id", i);
				obj.put("voornaam", klant.getVoornaam());
				obj.put("tussenvoegsel", klant.getTussenvoegsel());
				obj.put("achternaam", klant.getAchternaam());
				obj.put("email", klant.getEmail());
				
				jsonFile.write(obj.toJSONString());
				jsonFile.flush();
				jsonFile.close();

			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			finally{
			//zinnige code
			} 
		return klant.toString();
	}
	
	
	public Klant read(Klant klant){
		Scanner input = new Scanner(System.in);
		JSONParser parser = new JSONParser();
		Klant readKlant;
		
		File file = new File("klant" + klant.getKlant_id() + ".json");
		logger.info("file: " + file);
		
		int klant_id_final;
		
		while(!file.exists()) {
			System.out.println("Het door u opgegeven klant_id bestaat niet.");
			System.out.println("Voer een ander klant_id in en druk op Enter.");
			klant_id_final = input.nextInt(); 
			file = new File("klant" + klant_id_final + ".json");
		}
		
		try {					
					Object obj = parser.parse(new FileReader(file));

					JSONObject jsonObject = (JSONObject) obj;
					
					readKlant = new Klant();
					
					readKlant.setKlant_id(klant.getKlant_id());
                    readKlant.setVoornaam((String) jsonObject.get("voornaam"));
                    readKlant.setTussenvoegsel((String) jsonObject.get("tussenvoegsel"));
                    readKlant.setAchternaam((String) jsonObject.get("achternaam"));
                    readKlant.setEmail((String) jsonObject.get("email"));
                    
                    System.out.println(readKlant);
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return klant;
	 }
	
	public List<Klant> read() {
		   logger.info("read all Klanten methode start");
		   
		   JSONParser parser = new JSONParser(); 
	       List<Klant> klanten = new ArrayList<Klant>();      
	        
	       int i = 1;
	       File file = new File("klant" + i + ".json");
	        
	        
	       while(file.exists()){
	        	
	        	try { 
	        		Object obj = parser.parse(new FileReader(file));

	        		JSONObject jsonObject = (JSONObject) obj;
				
	        		Klant klant = new Klant();
				
	        		klant.setKlant_id(i);
	        		klant.setVoornaam((String) jsonObject.get("voornaam"));
	        		klant.setTussenvoegsel((String) jsonObject.get("tussenvoegsel"));
	        		klant.setAchternaam((String) jsonObject.get("achternaam"));
	        		klant.setEmail((String) jsonObject.get("email"));
	        	
	        		klanten.add(klant);
	        		i += 1;
	        		file = new File("klant" + i + ".json");
	        
	 		  
	 		   
	        	}catch(Exception ex){
	        		ex.printStackTrace();
	 		   
	        	} finally{
	 		   //zinnige code*/
	        	} 
	       }
	 	  return klanten;
	}
	
	
	public String update(Klant klant){
		Scanner input = new Scanner(System.in);
		
		File file = new File("klant" + klant.getKlant_id() + ".json");
		logger.info("file: " + file);
		
		
		File updateFile = file;
		file.delete();
		logger.info("de doelfile die gewist moet worden bestaat niet: " + file.exists());

		try (FileWriter jsonFile = new FileWriter(updateFile)){
				
				JSONObject obj = new JSONObject();
				
				obj.put("klant_id", klant.getKlant_id());
				obj.put("voornaam", klant.getVoornaam());
				obj.put("tussenvoegsel", klant.getTussenvoegsel());
				obj.put("achternaam", klant.getAchternaam());
				obj.put("email", klant.getEmail());
				
				jsonFile.write(obj.toJSONString());
				jsonFile.flush();
				jsonFile.close();

			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			finally{
			//zinnige code
			//System.out.print(klant);
			} 
		return "De gegevens van Klant_id: " + klant.getKlant_id() + "zijn aangepast";
	}
	
	public String delete(Klant klant) {
		
	    logger.info("delete Klant methode start");  
	    
	    File file = new File("klant" + klant.getKlant_id() + ".json");
		logger.info("file: " + file);
		
		File updateFile = file;
		file.delete();
		logger.info("de doelfile die gewist moet worden bestaat niet: " + file.exists());
			
		
	    logger.info("delete Klant methode eindigt");     
	    return "De gegevens van Klant_id: " + klant.getKlant_id() + " en bijbehorende file " + updateFile + " zijn gewist";
	    
	        }    
	}

	
	

	
	

