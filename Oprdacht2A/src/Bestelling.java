public class Bestelling {
	// Klant besteller;
	// int   artikel_aantal;
	// ArrayList <Artikel> bestellingsLijst = null;
	int bestelling_id;
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


	
	// default constructor
	public Bestelling() {		
	}
	
	// constructor voor 1 artikel
	public Bestelling(int bestelling_id, int klant_id, int artikel1_id, String artikel1_naam, int artikel1_aantal, double artikel1_prijs) {		
		this.bestelling_id = bestelling_id;
		this.klant_id = klant_id;
		this.artikel1_id = artikel1_id;
		this.artikel1_naam = artikel1_naam;
		this.artikel1_aantal = artikel1_aantal;
		this.artikel1_prijs = artikel1_prijs;		
	}
	
	// constructor voor 2 artikelen
	public Bestelling(int bestelling_id, int klant_id, int artikel1_id, String artikel1_naam, int artikel1_aantal, double artikel1_prijs, int artikel2_id, String artikel2_naam, int artikel2_aantal, double artikel2_prijs) {		
		this.bestelling_id = bestelling_id;
		this.klant_id = klant_id;
		this.artikel1_id = artikel1_id;
		this.artikel1_naam = artikel1_naam;
		this.artikel1_aantal = artikel1_aantal;
		this.artikel1_prijs = artikel1_prijs;	
		this.artikel2_id = artikel2_id;
		this.artikel2_naam = artikel2_naam;
		this.artikel2_aantal = artikel2_aantal;
		this.artikel2_prijs = artikel2_prijs;		
	}
	
	// constructor voor 3 artikelen
	public Bestelling(int newBestelling_id, int klant_id, int artikel1_id, String artikel1_naam, int artikel1_aantal, double artikel1_prijs, int artikel2_id, String artikel2_naam, int artikel2_aantal, double artikel2_prijs, int artikel3_id, String artikel3_naam, int artikel3_aantal, double artikel3_prijs) {		
		bestelling_id = newBestelling_id;
		this.klant_id = klant_id;
		this.artikel1_id = artikel1_id;
		this.artikel1_naam = artikel1_naam;
		this.artikel1_aantal = artikel1_aantal;
		this.artikel1_prijs = artikel1_prijs;	
		this.artikel2_id = artikel2_id;
		this.artikel2_naam = artikel2_naam;
		this.artikel2_aantal = artikel2_aantal;
		this.artikel2_prijs = artikel2_prijs;	
		this.artikel3_id = artikel3_id;
		this.artikel3_naam = artikel3_naam;
		this.artikel3_aantal = artikel3_aantal;
		this.artikel3_prijs = artikel3_prijs;			
	}
	
	
	public int getBestelling_id() {
		return bestelling_id;
	}
	
	public void setBestelling_id(int bestelling_id) {
    	this.bestelling_id = bestelling_id;
		
	}
		
    public int getKlant_id() {
    	return klant_id;
    }     
    
    public void setKlant_id(int klant_id) {
    	this.klant_id = klant_id;
    }  
    
    public int getArtikel1_id() {
    	return artikel1_id;
    }
   
    public void setArtikel1_id(int artikel1_id) {
    	this.artikel1_id = artikel1_id;
    }
    
    public String getArtikel1_naam() {
    	return artikel1_naam;
    }
    
    public void setArtikel1_naam(String artikel1_naam) {
    	this.artikel1_naam = artikel1_naam;
    }
 
    public int getArtikel1_aantal() {
    	return artikel1_aantal;
    }
    
    public void setArtikel1_aantal(int artikel1_aantal) {
    	this.artikel1_aantal = artikel1_aantal;
    }
    
    public double getArtikel1_prijs() {
    	return artikel1_prijs;
    }
    
    public void setArtikel1_prijs(double artikel1_prijs) {
    	this.artikel1_prijs = artikel1_prijs;
    }
    
    public int getArtikel2_id() {
    	return artikel2_id;
    }
    
    public void setArtikel2_id(int artikel2_id) {
    	this.artikel2_id = artikel2_id;
    }
   
    public String getArtikel2_naam() {
    	return artikel2_naam;
    }
    
    public void setArtikel2_naam(String artikel2_naam) {
    	this.artikel2_naam = artikel2_naam;
    }
 
    public int getArtikel2_aantal() {
    	return artikel2_aantal;
    }
    
    public void setArtikel2_aantal(int artikel2_aantal) {
    	this.artikel2_aantal = artikel2_aantal;
    }
    
    public double getArtikel2_prijs() {
    	return artikel2_prijs;
    }

    public void setArtikel2_prijs(double artikel2_prijs) {
    	this.artikel2_prijs = artikel2_prijs;
    }
     
    
    public int getArtikel3_id() {
    	return artikel3_id;
    }
    
    public void setArtikel3_id(int artikel3_id) {
    	this.artikel3_id = artikel3_id;
    }
   
    public String getArtikel3_naam() {
    	return artikel3_naam;
    }
    
    public void setArtikel3_naam(String artikel3_naam) {
    	this.artikel3_naam = artikel3_naam;
    }
 
    public int getArtikel3_aantal() {
    	return artikel3_aantal;
    }
    
    public void setArtikel3_aantal(int artikel3_aantal) {
    	this.artikel3_aantal = artikel3_aantal;
    }
    
    public double getArtikel3_prijs() {
    	return artikel3_prijs;
    }

    public void setArtikel3_prijs(double artikel3_prijs) {
    	this.artikel3_prijs = artikel3_prijs;
    }


    
    
    
}


    
