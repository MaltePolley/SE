package Pizza;

import java.util.ArrayList;

public class Pizza {
	
	double aufPreis;
	double preis;
	double endPreis;
	PizzaGröße pg;
	ArrayList<Toppings> toppings = new ArrayList<Toppings>();
	PizzaArt pizzaArt;
	Rezept rezept;
	
	
	/**
	 * Konstruktor
	 */
	public Pizza(PizzaArt pa, PizzaGröße pg, ArrayList<Toppings> toppings, Rezept rezept){
		this.pizzaArt = pa;
		this.pg = pg;
		this.toppings = toppings;
		this.rezept = rezept;
		berechnePreis();
	}


	public double getAufPreis() {
		return aufPreis;
	}
	
	public void berechnePreis(){
		switch(pizzaArt){
		case SALAMI: switch(pg){
										case SMALL: 	//TODO Preise eintragen
											break;
										case LARGE:		break;
										case X_LARGE:	break;
										}break;
		case SPEZIALE: switch(pg){
										case SMALL: 		break;
										case LARGE:		break;
										case X_LARGE:	break;
		} break;
		case THUNFISCH: switch(pg){
										case SMALL: 		break;
										case LARGE:		break;
										case X_LARGE:	break;
		}break; 
		case MOZARELLA: switch(pg){
										case SMALL: 		break;
										case LARGE:		break;
										case X_LARGE:	break;
		} break;
		}
		// TODO Toppings mit einberechnen
	}


	public void setAufPreis(double preis) {
		this.aufPreis = preis;
	}
	
	

}
