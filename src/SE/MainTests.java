package SE;

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
                    		Mensch Klaus = new Mensch("Klaus", true); 
                    		Mensch Isabell = new Mensch("Isabell", false); 
                    		Mensch Harald = new Mensch("Harald", true);
                    		Mensch Peter = new Mensch("Peter", true);
                    		Lea.addChildren(Kevin);
                    		Lea.addChildren(Chantal); //Kevin und Chantal sind nun Kinder von Lea
                    		Kevin.addParent(Lea);
                    		Kevin.addParent(Tom); //Lea und Tom sind nun Eltern von Kevin
                    		Chantal.addParent(Lea);
                    		Chantal.addParent(Tom); //Lea und Tom sind nun Eltern von Chantal
                    		Lea.addGeschwister(Klaus);
                    		Klaus.addGeschwister(Lea);
                    		Tom.addGeschwister(Isabell);
                    		Isabell.addGeschwister(Tom);
                    		Kevin.addChildren(Harald);
                    		Kevin.addChildren(Peter);
                    		Peter.addParent(Kevin);
                    		Harald.addParent(Kevin);
                    		
                    		
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
                    		
                    		assertTrue(Kevin == Chantal.geschwister.get(0));
                    		assertTrue(Chantal == Kevin.geschwister.get(0)); //Kevin und Chantal sind Geschwister
                    		assertTrue(Klaus == Lea.geschwister.get(0)); 
                    		assertTrue(Lea == Klaus.geschwister.get(0)); //Lea und Klaus sind Geschwister 
                    		
                    		//Klaus ist Kevins und Chantals Onkel und Isabell ist Kevins und Chantals Tante
                    		assertTrue(Klaus == Chantal.getVerwandte(Chantal, 1).get(0));
                    		assertTrue(Isabell == Kevin.getVerwandte(Kevin, 0).get(0)); 
                    		System.out.println("Tante von Kevin: " + Kevin.getVerwandte(Kevin, 0));
                    		System.out.println("Tante von Chantal: " + Chantal.getVerwandte(Chantal, 0));
                    		
                    		//Die Großeltern von Peter und Harald sind Lea und Tom
                    		//System.out.println("Papa von Peter: " + Peter.getEltern()[0]);
                    		assertTrue(Tom == Peter.getGroßeltern(Peter, 0)[0]);
                    		assertTrue(Lea == Peter.getGroßeltern(Peter, 0)[1]);
                    		assertTrue(Tom == Harald.getGroßeltern(Harald, 0)[0]);
                    		assertTrue(Lea == Harald.getGroßeltern(Harald, 0)[1]);
                    		System.out.println("Großeltern von Peter & Harald: " + Peter.getGroßeltern(Peter, 0)[0] + " " + Peter.getGroßeltern(Peter, 0)[1]);
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
                                // will be killed before you can tell.
    }

}