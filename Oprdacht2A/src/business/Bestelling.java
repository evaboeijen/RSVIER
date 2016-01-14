package business;

import annotations.*;

public class Bestelling {
	private int bestelling_id;
	private int klant_id;
	// de volgende variabelen zijn tevoegd bij opdracht 5 - AU 26/11/15
	private int artikel_id;
	private String artikel_naam;
	private int artikel_aantal;
	private double artikel_prijs; 
	
	
	
	/* de volgende variabelen werden gebruikt tot en met opdracht 2 - AU 26/11/15
	 * en derhalve uitgecommentarieerd voor de opdrachten daarna
	private int artikel1_id;
	private String artikel1_naam;
	private int artikel1_aantal;
	private double artikel1_prijs; 
	private int artikel2_id;
	private String artikel2_naam;
	private int artikel2_aantal;
	private double artikel2_prijs; 
	private int artikel3_id;
	private String artikel3_naam;
	private int artikel3_aantal;
	private double artikel3_prijs;
	*/

	
	// default constructor
	public Bestelling() {		
	}
	
	// constructor alleen voor bestelling_id
	public Bestelling(int bestelling_id) {		
		this.bestelling_id = bestelling_id;
	}
		
	// constructor voor 1 artikel
	// uitgecomment tbv opdracht 5 | 26/11/15 AU : public Bestelling(int bestelling_id, int klant_id, int artikel1_id, String artikel1_naam, int artikel1_aantal, double artikel1_prijs) {		
	// nieuwe parameters voor deze constructor tbv opdracht 5 | 26/11/15 AU
	public Bestelling(int bestelling_id, int klant_id, int artikel_id, String artikel_naam, int artikel_aantal, double artikel_prijs) {		
		this.bestelling_id = bestelling_id;
		this.klant_id = klant_id;		
		/* uitgecomment tbv opdracht 5 | 26/11/15 AU
		this.artikel1_id = artikel1_id;
		this.artikel1_naam = artikel1_naam;
		this.artikel1_aantal = artikel1_aantal;
		this.artikel1_prijs = artikel1_prijs;		*/			
		// nieuwe variabelen voor deze constructor tbv opdracht 5 | 26/11/15 AU
		this.artikel_id = artikel_id;
		this.artikel_naam = artikel_naam;
		this.artikel_aantal = artikel_aantal;
		this.artikel_prijs = artikel_prijs;
	}
	
	/* uitgecomment tbv opdracht 5 | 26/11/15 AU
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
	} */
	
	/* uitgecomment tbv opdracht 5 | 26/11/15 AU
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
	} */
					
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
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public int getArtikel_id() {
    	return artikel_id;
    }
   
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public void setArtikel_id(int artikel_id) {
    	this.artikel_id = artikel_id;
    }
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public String getArtikel_naam() {
    	return artikel_naam;
    }
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public void setArtikel_naam(String artikel_naam) {
    	this.artikel_naam = artikel_naam;
    }
 
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public int getArtikel_aantal() {
    	return artikel_aantal;
    }
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public void setArtikel_aantal(int artikel_aantal) {
    	this.artikel_aantal = artikel_aantal;
    }
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public double getArtikel_prijs() {
    	return artikel_prijs;
    }
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    public void setArtikel_prijs(double artikel_prijs) {
    	this.artikel_prijs = artikel_prijs;
    }
    
    /* uitgecomment tbv opdracht 5 | 26/11/15 AU
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
    } */
    
    
    // aangepast tbv opdracht 5 | 26/11/15 AU
    @Override
    public String toString(){
    	return 	 
        "\nKlantnummer : " + klant_id + ". Ordernummer : " + bestelling_id + 
        "\n--------------------------------------------------------------------------" +
        "\nArtikelnummer: " + artikel_id+ "\tArtikelnaam: " + artikel_naam + "\tAantal: " + artikel_aantal + "\tPrijs: " + artikel_prijs +   
        //   "\nArtikelnummer: " + artikel1_id+ "\tArtikelnaam: " + artikel1_naam + "\tAantal: " + artikel1_aantal + "\tPrijs: " + artikel1_prijs +         
     //   "\nArtikelnummer: " + artikel2_id+ "\tArtikelnaam: " + artikel2_naam + "\tAantal: " + artikel2_aantal + "\tPrijs: " + artikel2_prijs + 
     //   "\nArtikelnummer: " + artikel3_id+ "\tArtikelnaam: " + artikel3_naam + "\tAantal: " + artikel3_aantal + "\tPrijs: " + artikel3_prijs + 
        "\n\n";
            			    			   			
    }          
}


    
