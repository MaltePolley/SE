package Pizza;

import java.util.ArrayList;

public class Order {
	
	ArrayList<Pizza> pizzen = new ArrayList<Pizza>();
	Filialien filialie;
	Kunde kunde;
	String handynummer;
	double endPreis = 0;
	Status status;
	String datum;
	String erwarteteLieferzeit;
	boolean lieferung;
	boolean locked = false;
	
	
	/**
	 * Konstruktor
	 */
	public Order(Filialien filialie, String datum){
		this.filialie = filialie;
		this.datum = datum;
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
	
	public void lockOrder(){
		locked = true;
	}
	
	public void verSpätung(){
		
	}

}
