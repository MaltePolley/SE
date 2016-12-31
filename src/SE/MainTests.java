import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.Test;
import java.util.ArrayList;

public class MainTests {

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
						
							//Mensch erzeugen 
                    		Mensch Tom = new Mensch("Tom", true);
                    		Mensch Lea = new Mensch("Lea", false); 
                    		
                    		//Namen und Geschlecht von Menschen abfragen
                    		assertTrue("Tom".equals(Tom.name)); 
                    		assertTrue(Tom.bisteM());
                    		assertFalse(Tom.bisteW()); //Tom ist männlich, daher bei bisteM true und bei bisteW false.
                    		
                    		assertTrue(Lea.bisteW());
                    		assertFalse(Lea.bisteM()); //Lea ist weiblich, daher bei bisteW true und bei bisteM false.	
                    		
                    		//Menschen verheiraten [Noch buggy]
                    	 	Mensch Sabrina = new Mensch("Sabrina", false);
                    		//Tom.heirate(Lea); //Tom und Lea heiraten
                    		Lea.partner = Tom; 
                    		Tom.partner = Lea; 
                    		System.out.println("Leas Partner: " +Lea.partner);
                    		System.out.println("Toms Partner: " +Tom.partner);
                    		assertTrue(Tom == Lea.partner); 
                    		assertTrue(Lea == Tom.partner);
                    		assertNull(Sabrina.partner);  //hier aber nochmal die Methode zum verheiraten überarbeiten!
                    		
                    		//Kind einem Menschen hinzufügen
                    		Mensch Kevin = new Mensch("Kevin", true);
                    		Mensch Chantal = new Mensch("Chantal", false);
                    		Lea.addChildren(Kevin);
                    		Lea.addChildren(Chantal); //Kevin und Chantal sind nun Kinder von Lea
                    		Kevin.addParent(Lea);
                    		Kevin.addParent(Tom); //Lea und Tom sind nun Eltern von Kevin
                    		Chantal.addParent(Lea);
                    		Chantal.addParent(Tom); //Lea und Tom sind nun Eltern von Chantal
                    		
                    		assertTrue(Tom == Kevin.getEltern()[0]);
                    		assertTrue(Lea == Kevin.getEltern()[1]); // überprüft Eltern von Kevin
                    		System.out.println("Eltern von Kevin: " + "Vater: " + Kevin.getEltern()[0] + "   Mutter :" + Kevin.getEltern()[1]);
                    		assertTrue(Tom == Chantal.getEltern()[0]);
                    		assertTrue(Lea == Chantal.getEltern()[1]); // überprüft Eltern von Chantal
                    		System.out.println("Eltern von Chantal: " + "Vater: " + Chantal.getEltern()[0] + "   Mutter :" + Chantal.getEltern()[1]);
                    		assertFalse(Tom == Kevin.getEltern()[1]); // Tom kann nicht als Mutter gespeichert werden!
                    		
                    		assertTrue(Kevin == Lea.kinder.get(0));
                    		assertTrue(Chantal == Lea.kinder.get(1)); //Kevin ist Leas erstes Kind und Chantal ihr zweites
                    		System.out.println("Kinder von Lea: " + Lea.kinder.get(0) + ", " + Lea.kinder.get(1));
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
                                // will be killed before you can tell.
    }

}