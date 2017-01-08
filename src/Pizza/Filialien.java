package Pizza;

import java.util.ArrayList;

public class Filialien {
	
	// Vordefinierte Reihenfolge der Zutaten menge.get(0) = Zutat1 usw
	ArrayList<Double> menge;
	String standort;
	int id;
	ArrayList<Order> allOrders = new ArrayList<Order>();
	ArrayList<String> angebot = new ArrayList<String>();
	public Filialien(String standort, int id, ArrayList<Double> menge){
		this.menge = menge;
		this.standort = standort;
		this.id = id;
	}
	
	public void vorräteCheck(){
		
	}
	
	public void exportData(){
		
	}
	
	public void filterListe(){
		
	}
	

}
