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
								
								//TODO hier Tests einfügen
								
	                    }
	                });
	            }
	        });
	        thread.start();// Initialize the thread
	        Thread.sleep(10000); // Time to use the app, with out this, the thread
	                                // will be killed before you can tell.
	    }

}
	
