package Pizza;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PizzaTests {
	    @Test
	    public void testA() throws InterruptedException {
	        Thread thread = new Thread(new Runnable() {

	            @Override
	            public void run() {
	                new JFXPanel(); // Initializes the JavaFx Platform
	                Platform.runLater(new Runnable() {

	                    @Override
	                    public void run() {
								try {
									new Main().start(new Stage());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.print("eigene Exception: ");
									e.printStackTrace();
								}
								
								//Pizza-Erzeugen Test
								//zum testen 2 verschiedene Topping-Listen 
								ArrayList<Toppings> leereList  = new ArrayList<>();
								ArrayList<Toppings> volleList  = new ArrayList<>();
								volleList.add(Toppings.BACON);
								volleList.add(Toppings.MAIS);
								
								//Pizza in verschiedenen Größen und verschiedenen Toppings - Preisberechnung
								//SMALL Salami-Pizza ohne Toppings
								Pizza p1 = new Pizza(PizzaArt.SALAMI, PizzaGröße.SMALL, leereList);
								assertTrue(PizzaArt.SALAMI == p1.pizzaArt);
								assertTrue(7.00 == p1.endPreis);
								System.out.println("SMALL Salami-Pizza ohne Toppings: \n" + p1.pizzaArt + ", " + p1.endPreis);
								System.out.print("\n");
								
								//LARGE Salami-Pizza ohne Toppings
								Pizza p2 = new Pizza(PizzaArt.SALAMI, PizzaGröße.LARGE, leereList);
								assertTrue(PizzaArt.SALAMI == p2.pizzaArt);
								assertTrue(8.00 == p2.endPreis);
								System.out.println("LARGE Salami-Pizza ohne Toppings: \n" + p2.pizzaArt + ", " + p2.endPreis);
								System.out.print("\n");
								
								//LARGE Salami-Pizza mit 2x Toppings
								Pizza p3 = new Pizza(PizzaArt.SALAMI, PizzaGröße.LARGE, volleList);
								assertTrue(PizzaArt.SALAMI == p3.pizzaArt);
								assertTrue(9.00 == p3.endPreis);
								System.out.println("LARGE Salami-Pizza mit 2x Toppings: \n" + p3.pizzaArt + ", " + p3.endPreis);
								System.out.println("Toppings: " + p3.toppings.get(0) + ", " + p3.toppings.get(1));
								System.out.print("\n");
								
								//X_LARGE Salami-Pizza ohne Toppings
								Pizza p4 = new Pizza(PizzaArt.SALAMI, PizzaGröße.X_LARGE, leereList);
								assertTrue(PizzaArt.SALAMI == p4.pizzaArt);
								assertTrue(9.00 == p4.endPreis);
								System.out.println("X_LARGE Salami-Pizza ohne Toppings: \n" + p4.pizzaArt + ", " + p4.endPreis);
								System.out.print("\n");
								
								
								
								//Testen von Bestellung
								ArrayList<Pizza> bestellung1  = new ArrayList<>();
								bestellung1.add(p1);
								bestellung1.add(p2);
								bestellung1.add(p3);
								bestellung1.add(p4); //Pizza 1-4 der Bestellung hinzugefügt
								
								ArrayList<Double> vorratF1  = new ArrayList<>();
								vorratF1.add(10.00);
								vorratF1.add(10.00);
								vorratF1.add(10.00);
								vorratF1.add(10.00);
								vorratF1.add(10.00); //Vorratsstände der Filiale1 wurden 10.00EInheiten pro Zutat erhöht
								Filialien fTest = new Filialien("Berlin", 01, vorratF1); //Filiale F1 erzeugt 
								
								Kunde k1 = new Kunde("Test", "Kunde", "Teststraße", "Testort", "017634637", 13591); //Testkunde für die Bestellung
								
								Order b1 = new Order(); 			//Bestellung erstellt
								b1.pizzen = bestellung1;			//Pizzaliste hinzugefügt
								b1.filialie = fTest;				//Filiale der hinzugefügt
								b1.kunde = k1; 						//Kunde hinzugefügt
								b1.handynummer = k1.handynummer; 	//Handynr des Kunden hinzugefügt
								
								b1.berechnePreis(); //Preis der Bestellung wird berechnet
								
								assertTrue(33.00 == b1.endPreis);
								System.out.println("1. " + bestellung1.get(0) + ": " + bestellung1.get(0).endPreis);
								System.out.println("2. " + bestellung1.get(1) + ": " + bestellung1.get(1).endPreis);
								System.out.println("3. " + bestellung1.get(2) + ": " + bestellung1.get(2).endPreis);
								System.out.println("4. " + bestellung1.get(3) + ": " + bestellung1.get(3).endPreis);
								System.out.println(b1.endPreis);
								
								
								
								
								
	                    }
	                });
	            }
	        });
	        thread.start();// Initialize the thread
	        Thread.sleep(10000); // Time to use the app, with out this, the thread
	                                // will be killed before you can tell.
	    }

}
	
