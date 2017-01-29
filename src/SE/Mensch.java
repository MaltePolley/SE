package SE;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Mensch extends Label{
	
	ArrayList<Mensch> geschwister = new ArrayList<Mensch>();	// Geschwister
	ArrayList<Mensch> kinder = new ArrayList<Mensch>();			// Kinder Liste
	Mensch vater = null; 										// Vater
	Mensch mutter = null;								    	// Mutter
	Mensch partner = null;										// Ehepartner
	boolean m = false; 											// Männliche
	boolean w = false;											// oder Weiblich
	String name;												// Name
	double sizeX = 100;
	double sizeY = 35;
	boolean wertSwitch = true;
	Line childLine = new Line();
	double aktuelleChildBreite = 0;

	
	public Mensch(String name, Boolean m){
		super();	
		this.name = name;	
		this.m = m;
		
		//Wenn nicht m dann ist w true
		if(!m){
		w = true;
		}
		
		this.setOnMouseClicked(e->Select());  							// Beim Klick auf das Objekt wird Select() ausgeführt
		setPrefSize(sizeX, sizeY);															// Setzt Größe (Breite,Höhe)
		this.setText(name);															// Setzt den Labeltext auf den Namen
		CSS(); 																					// Wendet CSS an
	}

	public void CSS(){
		setStyle("-fx-background-color: skyblue;"						// Hintergrund Farbe
				+ "-fx-background-radius: 50;");								// Abgerundete Ecken ( höherer Wert umso runder)
	}


	/**
	 *  Fügt einem Knoten ein Kind hinzu
	 * @param kind
	 */
	public void addChildren(Mensch kind){
		if(kinder != null){
			for(int i=0; i==(kinder.size()-1);i++){
				kinder.get(i).geschwister.add(kind); // wenn kein EInzelkind, füge jedem Kind neues Kind als Geschwisterkind hinzu.
				kind.geschwister.add(kinder.get(i)); // füge neuem Kind jedes vorhandene Kind als Geschwisterkind hinzu; 
			}
		}
		kinder.add(kind);
	}
	
	public void addGeschwister(Mensch mensch){
		geschwister.add(mensch);
	}

	/**
	 * Fügt einem Knoten ein Elternteil hinzu
	 * @param mensch
	 */
	public void addParent(Mensch mensch){
		if(mensch.bisteM() && (vater==null)){
			vater = mensch;
		} 
		if(mensch.bisteW() && (mutter==null)){
			mutter = mensch;
		}
	}
	
	/**
	 * 
	 * @return Wahr wenn w, falsch wenn m
	 */
	public Boolean bisteW(){
		if(w){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return Wahr wenn m, falsch wenn w
	 */
	public Boolean bisteM(){
		if(m){
			return true;
		}
		return false;
	}
	
	/**
	 * Verheiratet eine Person, wenn diese nicht schon verheiratet ist
	 * @param mensch
	 */
	public void heirate(Mensch mensch){
		if(partner==null){
		this.partner = mensch;
		} else{
			System.out.println("Mensch bereits verheiratet!");
		}
	}
	
	/**eltern[0] = vater
	 *  eltern[1] = mutter
	 * @return Mensch Array der Größe 2
	 */
	public Mensch[] getEltern(){
		Mensch[] eltern = new Mensch[2];
		eltern[0] = vater;
		eltern[1] = mutter;
		return eltern;
		
	}
	
	/**
	 * 
	 * @return Liste der Kinder dieses Knoten
	 */
	public ArrayList<Mensch> getKinder(){
		return kinder;
	}
	
	public ArrayList<Mensch> getGeschwister(){
		return geschwister;
	}
	

	public ArrayList<Mensch> getVerwandte(Mensch mensch, int i){	//wobei i = 0 Vater & i = 1 Mutter
		return mensch.getEltern()[i].geschwister; 				//Gibt Geschwister der Eltern zurück (Onkel & Tannte)
	}
	
	public ArrayList<Mensch> getVerwandte(int i){	//wobei i = 0 Vater & i = 1 Mutter
		return this.getEltern()[i].geschwister;  					//Gibt Geschwister der Eltern zurück (Onkel & Tannte)
	}
	
	public Mensch[] getGroßeltern(int i){			//wobei i = 0 Vater & i = 1 Mutter
		return this.getEltern()[i].getEltern(); 					//gibt Eltern der Eltern zurück 
	}
	
	public ArrayList<Mensch> getEnkel(){
			 ArrayList<Mensch> enkel = new ArrayList<Mensch>();
			 for (Mensch kind : this.kinder) {
			  enkel.addAll(kind.kinder);
			 }
			 return enkel; 
	}
	
	/** Gibt die Coordinaten des Labels an der mittig untersten Stelle zurück
	 * 
	 * @return btmCoordiantes[0] = x, btmCoordiantes[1]=y
	 */
	public double[] getBottomCoordinates(){
		double [] btmCoordinates = new double[2];
		btmCoordinates[0] = this.getLayoutX() + (this.sizeX/2);
		btmCoordinates[1] = this.getLayoutY() + (this.sizeY);
		
		
		return btmCoordinates; 
				
	}
	
	
	/** Heirats Koordinaten für Männer
	 *  
	 * @return Koordinaten die zum verheiraten gebraucht werden
	 */
	public double[] getHCM(){
		double[] hcm = new double[2];
		hcm[0] = this.getLayoutX() + (this.sizeX);
		hcm[1] = this.getLayoutY() + (this.sizeY/2);
		return hcm;
	}
	
	/** Heirats Koordinaten für Frauen
	 * 
	 * @return Koordinaten die zum verheiraten gebraucht werden
	 */
	public double[] getHCW(){
		double[] hcw = new double[2];
		hcw[0] = this.getLayoutX();
		hcw[1] = this.getLayoutY() + (this.sizeY/2);
		return hcw;
	}
	
	
	public void Select(){
		Main.secondSelected = Main.selected;
		Main.selected = this;
		Main.log.setText("DEBUG-Selected = " + Main.selected.name + "\n");
		if(Main.secondSelected!=null)
			Main.log.setText(Main.log.getText() + "DEBUG-SecondSelected = " + Main.secondSelected.name);
	}
}
