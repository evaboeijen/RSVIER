import java.util.ArrayList;

public class Bestelling {
	Klant besteller;
	int   artikel_aantal;
	ArrayList <Artikel> bestellingsLijst = null;
	int bestelling_id = (int)(Math.random() * 10000);;
	int klant_id;
	int artikel1_id;
	String artikel1_naam;
	int artikel1_aantal;
	double artikel1_prijs; 
	int artikel2_id;
	String artikel2_naam;
	int artikel2_aantal;
	double artikel2_prijs; 
	int artikel3_id;
	String artikel3_naam;
	int artikel3_aantal;
	double artikel3_prijs; 

	
	public Bestelling() {		//constructor
	}
	
	
	public int getBestelling_id() {
		return bestelling_id;
	}
		
    public int getKlant_id() {
    	return klant_id;
    }     
    
    public int getArtikel1_id() {
    	return artikel1_id;
    }
   
    public String getArtikel1_naam() {
    	return artikel1_naam;
    }
 
    public int getArtikel1_aantal() {
    	return artikel1_aantal;
    }
    
    public double getArtikel1_prijs() {
    	return artikel1_prijs;
    }
    
    public int getArtikel2_id() {
    	return artikel2_id;
    }
   
    public String getArtikel2_naam() {
    	return artikel2_naam;
    }
 
    public int getArtikel2_aantal() {
    	return artikel2_aantal;
    }
    
    public double getArtikel2_prijs() {
    	return artikel2_prijs;
    }

    public int getArtikel3_id() {
    	return artikel3_id;
    }
   
    public String getArtikel3_naam() {
    	return artikel3_naam;
    }
 
    public int getArtikel3_aantal() {
    	return artikel3_aantal;
    }
    
    public double getArtikel3_prijs() {
    	return artikel3_prijs;
    }
    
	public void setBestelling_id(int bestelling_id) {
    	this.bestelling_id = bestelling_id;
		
	}
    
    
    
}


    
