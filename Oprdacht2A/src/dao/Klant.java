package dao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */
public class Klant {
int    klant_id;
String voornaam;
String achternaam;
String tussenvoegsel;
String email;

public Klant() {}


public int getKlant_id() {
    	return klant_id;
}
public void setKlant_id(int klant_id) {
	this.klant_id = klant_id;
}


public String getVoornaam() {
	return voornaam;
}
public void setVoornaam(String voornaam) {
	this.voornaam = voornaam;
}


public String getAchternaam() {
	return achternaam;
}
public void setAchternaam(String achternaam) {
	this.achternaam = achternaam;
}


public String getTussenvoegsel() {
	return tussenvoegsel;
}
public void setTussenvoegsel(String tussenvoegsel) {
	this.tussenvoegsel = tussenvoegsel;
}


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String toString(){
	return "Klant Id: " + klant_id + ". Klant gegevens: " + voornaam + " " + tussenvoegsel + " " + achternaam + " " + email; 
}

public boolean equals(Klant k){
    if(k == null)    {
    	return false;
    }
    if (!(k instanceof Klant)) {
    	return false;
    }
    
    
    Klant other = (Klant) k;
    if(this.klant_id != other.klant_id)      return false;
    if(! this.voornaam.equals(other.voornaam)) return false;
    if(! this.achternaam.equals(other.achternaam))   return false;

    return true;
  }

public int hashCode(){
     return (int) klant_id * voornaam.hashCode() * achternaam.hashCode();

}
}


