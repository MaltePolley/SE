package Pizza;

import java.util.ArrayList;

public class Kunde {


	ArrayList<Order> orders = new ArrayList<Order>();
	String vorname, nachname, straße, ort, handynummer ;
	int plz;
	Boolean neukunde;
	int sperrMonat;
	
	/**
	 * Konstruktor
	 */
	public Kunde(String vorname, String nachname, String straße, String ort, String handynummer){
		this.vorname = vorname;
		this.nachname = nachname;
		this.straße = straße;
		this.ort = ort;
		this.handynummer = handynummer;
	}
	
	/**
	 *  Weisst dem Kunden eine Bestellung zu
	 * @param order - Die Bestellung welche zugeordnet wird
	 */
	public void setBestellung(Order order){
		orders.add(order);
	}
	
	/**
	 * Wählt Pizza aus
	 */
	public void selectPizza(){
		
	}
	
	/**
	 *  Gibt die Bestellung auf
	 */
	public void placeOrder(){
		
	}
	
	/**
	 * Change the Order
	 */
	public void changeOrder(){
		
	}
	
	public void storniereOrder(){
		
	}

	public void checkHandynummer(String handynummer){
		
	}
}
