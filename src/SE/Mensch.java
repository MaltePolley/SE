package SE;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class Mensch extends Label{
	
	ArrayList<Mensch> kinder = new ArrayList<Mensch>();		// Kinder Liste
	Mensch vater = null; 																// Vater
	Mensch mutter = null;														    // Mutter
	Mensch partner = null; 															// Ehepartner
	Boolean m = false; 																// Männliche
	Boolean w = false;																	// oder Weiblich
	String name;																			// Name
	
	
	public Mensch(String name, Boolean m){
		super();	
		this.name = name;	
		this.m = m;
		
		//Wenn nicht m dann ist w true
		if(!m){
		w = true;
		}
		
		this.setOnMouseClicked(e->Select());  							// Beim Klick auf das Objekt wird Select() ausgeführt
		setPrefSize(50, 30);															// Setzt Größe (Breite,Höhe)
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
		kinder.add(kind);
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
	
	public void Select(){
		Main.secondSelected = Main.selected;
		Main.selected = this;
		System.out.println("DEBUG-Selected = " + Main.selected.name);
		if(Main.secondSelected!=null)
			System.out.println("DEBUG-SecondSelected = " + Main.secondSelected.name);
	}
}
