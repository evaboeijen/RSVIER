
public class Adres {
	
	static int klant_id;
	
	String straatnaam;
	String postcode;
	String toevoeging;
	int    huisnummer;
	String woonplaats;

	public Adres(){}
	
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


	@Override
	public String toString() {
		return"Straatnaam: " +  getStraatnaam() + 
				"\nPostcode: " + getPostcode() + 
				"\nToevoeging: " + 	getToevoeging() + 
				"\nHuisnummer: " + getHuisnummer() + 
				"\nWoonplaats: " + getWoonplaats() + 
				"\n\nKlant_id: " + getKlant_id();
	}
	

}
