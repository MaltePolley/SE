package Pizza;

import java.util.ArrayList;

public class Kunde {


	ArrayList<Order> orders = new ArrayList<Order>();
	String vorname, nachname, adresse, handynummer ;
	Boolean neukunde;
	int sperrMonat;
	
	/**
	 * Konstruktor
	 */
	public Kunde(){
		
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

}
