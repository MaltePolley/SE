import java.util.ArrayList;

//TODO Irgendwo muss man das Rezept verändern, sodass man bei Large und X_large die Hälfte der Zutaten ändern kann.
//TODO Irgendwo müssen wir noch fragen, welche Toppings hinzugefügt werden.  
public class Pizza {
	
	double aufPreis;
	double preis;
	double endPreis;
	PizzaGröße pg;
	ArrayList<Toppings> toppings = new ArrayList<Toppings>();
	PizzaArt pizzaArt;
	Rezept rezept; 
	boolean rezeptChanged; //Wenn Rezept verändert, neuer Preis (teuerste Variante)
	
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
	
	//Die verschiedene Größen der Pizzen haben einen Preisunterschied von 1,00€
	public void berechnePreis(){
		switch(pizzaArt){
		case SALAMI: 								//Standartpreis 7,00€
			switch(pg){
			case SMALL: 	this.preis = 7.00; 
			break;
			case LARGE:		this.preis = 8.00;
			break;
			case X_LARGE:	this.preis = 9.00;
			break;
			}
			break;
		case SPEZIALE: 								//Standartpreis 9,00€
			switch(pg){
			case SMALL:		this.preis = 9.00;
			break;
			case LARGE:		this.preis = 10.00;
			break;
			case X_LARGE:	this.preis = 11.00;
			break;
		} break;
		case THUNFISCH: 							//Standartpreis 8,00€
			switch(pg){
			case SMALL:		this.preis = 8.00;
			break;							
			case LARGE:		this.preis = 9.00;
			break;
			case X_LARGE:	this.preis = 10.00;
			break;
		}break; 
		case MOZARELLA: 							//Standartpreis 7,50€
			switch(pg){
			case SMALL:		this.preis = 7.50;
			break;
			case LARGE:		this.preis = 8.50;
			break;
			case X_LARGE:	this.preis = 9.50;
			break;
		} break;
		}

		if(this.rezeptChanged == true){
			switch(pizzaArt){
			case SALAMI: this.preis = 9.00; 
			break;
			case SPEZIALE: this.preis = 11.00;
			break;
			case THUNFISCH: this.preis = 10.00;
			break;
			case MOZARELLA: this.preis = 9.50;
			break;
			}  //Wenn die Zutaten der Pizza verändert wurden, muss max Preis der Pizza gewählt werden
		}
		
		if(this.toppings.isEmpty() != true){
			int anzahlToppings = this.toppings.size();
			this.preis = this.preis + (0.5*anzahlToppings); 
			//Für jedes Topping wird ein Aufpreis berechnet von 0,50€. 
		}
		
	}

	public void setAufPreis(double preis) {
		this.aufPreis = preis;
	}
	
	

}
