import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.Test;

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
						
                    	
                    		Mensch Tom = new Mensch("Tom", true);
                    		Mensch Lea = new Mensch("Lea", false); 
                    		
                    		assertTrue("Tom".equals(Tom.name)); 
                    		assertTrue(Tom.bisteM());
                    		assertFalse(Tom.bisteW()); //Tom ist männlich, daher bei bisteM true und bei bisteW false.
                    		
                    		assertTrue(Lea.bisteW());
                    		assertFalse(Lea.bisteM()); //Lea ist weiblich, daher bei bisteW true und bei bisteM false.	

                    		Mensch Sabrina = new Mensch("Sabrina", false);
                    		Tom.heirate(Lea); //Tom und Lea heiraten
                    		Tom.heirate(Sabrina); //Tom und Sabrina heiraten (fail)
                    		//System.out.println(Lea.partner.name);
                    		System.out.println("Tom: " + Tom);
                    		System.out.println("Lea: " + Lea);
                    		System.out.println("Leas Partner: " +Lea.partner);
                    		assertTrue(Tom == Lea.partner); 
                    		assertTrue(Lea == Tom.partner);
                    		assertNull(Sabrina.partner); 

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
                                // will be killed before you can tell.
    }

}