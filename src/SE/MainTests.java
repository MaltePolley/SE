import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MainTests {
	

	@Test 
	public void addMensch() {
		Mensch Tom = new Mensch("Tom", true);
		Mensch Lea = new Mensch("Lea", false); 
		
		assertEquals("Tom", Tom.name); 
		assertEquals(true, Tom.bisteM());
		assertEquals(false, Tom.bisteW()); //Tom ist männlich, daher bei bisteM true und bei bisteW false.
		
		assertEquals(true, Lea.bisteW());
		assertEquals(false, Lea.bisteM()); //Lea ist weiblich, daher bei bisteW true und bei bisteM false.	
	}
	
	@Test
	public void verheiraten() {
		Mensch Tom = new Mensch("Tom", true);
		Mensch Lea = new Mensch("Lea", false);
		Mensch Sabrina = new Mensch("Sabrina", false);
		Tom.heirate(Lea); //Tom und Lea heiraten
		Tom.heirate(Sabrina); //Tom und Sabrina heiraten (fail)
		
		assertEquals(Tom, Lea.partner); 
		assertEquals(Lea, Tom.partner);
		assertEquals(null, Sabrina.partner); 
	}
	
	@Test
	public void addKind() {
		
	}
	
}
