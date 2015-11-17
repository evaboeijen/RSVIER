import dao.*;

public class TestAdres {

	public static void main(String[] args) {
		AdresDaoImpl adresDaoImpl = new AdresDaoImpl();
		adresDaoImpl.getConnection();
		
		System.out.println("");
		
		Adres adres1 = new Adres(1,"Teststraat","1234AB", "boven", 5 ,"Ergens");
		Adres adres2 = new Adres(2,"Tweede teststraat","1234AB", "onder", 10 ,"Nergens");
		Adres adres3 = new Adres(3,"Derdede teststraat","1234AB", "onder", 10 ,"Nergens");
		Adres adres4 = new Adres(4,"Vierde teststraat","1234AB", "onder", 10 ,"Nergens");
		
		adresDaoImpl.insert(adres1);
		adresDaoImpl.insert(adres2);
		adresDaoImpl.insert(adres3);
		adresDaoImpl.insert(adres4);
		
		System.out.println("\n ________________________________________");
		
		adresDaoImpl.readAllAdresses();
		
		System.out.println("\n ________________________________________");
		
		
		
		
	}

}
