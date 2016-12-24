package SE;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class Mensch extends Label{
	
	ArrayList<Mensch> kinder = new ArrayList<Mensch>();		// Kinder Liste
	Mensch vater = null; 																// Vater
	Mensch mutter = null;														    // Mutter
	Mensch partner = null; 															// Ehepartner
	Boolean m; 																				// Männliche
	Boolean w;																				// oder Weiblich
	String name;																			// Name
	
	
	public Mensch(String name){
		super();																		
		this.name = name;																
		this.setText(name);															// Setzt den Labeltext auf den Namen
		CSS(); 																					// Wendet CSS an
	}

	public void CSS(){
		
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
	
	public ArrayList<Mensch> getKinder(){
		return kinder;
	}
}
