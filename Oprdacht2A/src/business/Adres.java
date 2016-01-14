package business;


public class Adres {
	private int klant_id;
	private int adres_id;
	String straatnaam;
	String postcode;
	String toevoeging;
	int    huisnummer;
	String woonplaats;

	public Adres(){}
	
	public Adres(int klant_id, String straatnaam, String postcode, String toevoeging, int huisnummer, String woonplaats ){
	this.klant_id   = klant_id;
	this.straatnaam = straatnaam;
	this.postcode   = postcode;
	this.toevoeging = toevoeging;
	this.huisnummer = huisnummer;
	this.woonplaats = woonplaats;
	}
	
	public Adres(int adres_id) {
		
	}
	
	public int getAdres_id() {
		return adres_id;
	}
	public void setAdres_id(int adres_id) {
		this.adres_id = adres_id;
	}

	public String getStraatnaam() {
		return straatnaam;
	}
	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}
	
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	public String getToevoeging() {
		return toevoeging;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}
	
	
	public int getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}
	
	
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	
	
	public int getKlant_id() {
		return klant_id;
	}
	
	public void setKlant_id(int klant_id){
		this.klant_id = klant_id;
	}

	@Override
	public String toString() {
		return  "\nAdresnummer: "   + getAdres_id() +
				"\nStraatnaam: " 	+ getStraatnaam() + 
				"\nPostcode: " 		+ getPostcode() + 
				"\nToevoeging: " 	+ getToevoeging() + 
				"\nHuisnummer: " 	+ getHuisnummer() + 
				"\nWoonplaats: " 	+ getWoonplaats() + 
				"\n";
	}
	
	

}
