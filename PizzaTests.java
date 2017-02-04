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
								//zum testen 2 verschiedene Topping-Listen 
								ArrayList<Toppings> leereList  = new ArrayList<>();
								ArrayList<Toppings> volleList  = new ArrayList<>();
								volleList.add(Toppings.BACON);
								volleList.add(Toppings.MAIS);
								
								
								//SMALL Salami-Pizza ohne Toppings
								Pizza p1 = new Pizza(PizzaArt.SALAMI, PizzaGröße.SMALL, leereList);
								assertTrue(PizzaArt.SALAMI == p1.pizzaArt);
								assertTrue(7.00 == p1.endPreis);
								System.out.println("SMALL Salami-Pizza ohne Toppings: \n" + p1.pizzaArt + ", " + p1.endPreis);
								
								//LARGE Salami-Pizza ohne Toppings
								Pizza p2 = new Pizza(PizzaArt.SALAMI, PizzaGröße.LARGE, leereList);
								assertTrue(PizzaArt.SALAMI == p2.pizzaArt);
								assertTrue(8.00 == p2.endPreis);
								System.out.println("LARGE Salami-Pizza ohne Toppings: \n" + p2.pizzaArt + ", " + p2.endPreis);
								
								//LARGE Salami-Pizza mit 2x Toppings
								Pizza p3 = new Pizza(PizzaArt.SALAMI, PizzaGröße.LARGE, volleList);
								assertTrue(PizzaArt.SALAMI == p3.pizzaArt);
								assertTrue(9.00 == p3.endPreis);
								System.out.println("LARGE Salami-Pizza mit 2x Toppings: \n" + p3.pizzaArt + ", " + p3.endPreis);
								System.out.println("Toppings: " + p3.toppings.get(0) + ", " + p3.toppings.get(1));
								
								//X_LARGE Salami-Pizza ohne Toppings
								Pizza p4 = new Pizza(PizzaArt.SALAMI, PizzaGröße.X_LARGE, leereList);
								assertTrue(PizzaArt.SALAMI == p4.pizzaArt);
								assertTrue(9.00 == p4.endPreis);
								System.out.println("X_LARGE Salami-Pizza ohne Toppings: \n" + p4.pizzaArt + ", " + p4.endPreis);
								
	                    }
	                });
	            }
	        });
	        thread.start();// Initialize the thread
	        Thread.sleep(10000); // Time to use the app, with out this, the thread
	                                // will be killed before you can tell.
	    }

}
	
