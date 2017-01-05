package Pizza;

import java.util.ArrayList;

public class Order {
	
	ArrayList<Pizza> pizzen = new ArrayList<Pizza>();
	String handynummer;
	double endPreis = 0;
	Status status;
	
	/**
	 * Konstruktor
	 */
	public Order(){
		
	}
	
	/**
	 *  Berechnet den BestellungsendPreis
	 * @return
	 */
	public double berechnePreis(){
		for(int i = 0; i<pizzen.size();i++){
			endPreis += pizzen.get(i).endPreis;
		}
		return endPreis;
	}

}
