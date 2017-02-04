package Pizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Kochseite implements Initializable{
	
	public Stage primaryStage;
	FXMLLoader loader = new FXMLLoader();
	Kunde currentK;
	Order currentO = new Order();
	
	@FXML
	ComboBox orders;
	
	@FXML
	Button anzeige;
	
	@FXML
	Label text;
	
	@FXML
	ComboBox status;
	
	@FXML
	Button ok;
	
	@FXML
	Button back;
	
	@FXML
	protected void getBack(){
		try {
			AnchorPane menu = (AnchorPane) loader.load(getClass().getResource("Haupt.fxml"));
			loader.setLocation(getClass().getResource("org/ttr/blue/view/menu.fxml"));
			Scene sceneMenu = new Scene(menu);
			Main.getPrimaryStage().setScene(sceneMenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	protected void display(){
		try {
			laden();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Display
				text.setText("");
						if(currentK!=null){
							text.setText(text.getText() + currentK.vorname + " " + currentK.nachname + "\n");
							text.setText(text.getText() + currentK.straße + "\n");
							text.setText(text.getText() + currentK.ort + "\n");
							text.setText(text.getText() + currentK.handynummer + "\n");
							text.setText(text.getText() + currentK.plz + "\n");
						}
							if (currentO != null && currentO.pizzen != null) {
								text.setText(text.getText() + "\n");
								for (int i = 0; i < currentO.pizzen.size(); i++) {
									text.setText(text.getText() + currentO.pizzen.get(i).pizzaArt.toString() + "-" +  currentO.pizzen.get(i).pg.toString());
									if (currentO.pizzen.get(i).toppings != null) {
										for (int x = 0; x < currentO.pizzen.get(i).toppings.size(); x++) {
											text.setText(text.getText() + "\n"+ currentO.pizzen.get(i).toppings.get(x).toString());
										}
									}
									text.setText(text.getText() + "\n");
								}

							}
	}
	
	@FXML
	protected void setStatus(){
		// TODO status setzen je nach Combobox auswahl
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Initialisieren
		
	}
	
	/** Läd alle Karteikarten aus der Datei in die Karteibox Instanz.
	 * @throws IOException
	 */
	public void laden() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("Bestellungen.txt"));
		String alles = "";
		alles = br.readLine();

		//String splitten
		if(alles != null){
			System.out.println(alles);
		String splitter[] = alles.split(",");
		currentK = new Kunde(splitter[0], splitter[1], splitter[2], splitter[3], splitter[5], Integer.parseInt(splitter[4]));
//		currentK.vorname = splitter[0];
//		currentK.nachname = splitter[1];
//		currentK.straße = splitter[2];
//		currentK.ort = splitter[3];
//		currentK.plz = Integer.parseInt(splitter[4]);
//		currentK.handynummer = splitter[5];
		for(int i = 6; i<splitter.length;i+=3){
			PizzaArt pa = convertPA(splitter[i]);
			PizzaGröße pg = convertPG(splitter[i+1]);
			ArrayList<Toppings> topps = new ArrayList<Toppings>();
			topps.add(convertTopps(splitter[i+2]));
			Pizza p = new Pizza(pa, pg, topps);
			currentO.pizzen.add(p);
		}
		System.out.println(alles);
		}
		br.close();
		
	}
	
	public PizzaArt convertPA(String pa){
		if(pa.equals("MOZARELLA")){
			return PizzaArt.MOZARELLA;
		}
		if(pa.equals("SALAMI")){
			return PizzaArt.SALAMI;
		}
		if(pa.equals("SPEZIALE")){
			return PizzaArt.SPEZIALE;
		}
		if(pa.equals("THUNFISCH")){
			return PizzaArt.THUNFISCH;
		}
		return null;
	}
	
	public PizzaGröße convertPG(String pg){
		if(pg.equals("SMALL")){
		return PizzaGröße.SMALL;
		}
		if(pg.equals("LARGE")){
			return PizzaGröße.LARGE;
		}
		if(pg.equals("X_LARGE")){
			return PizzaGröße.X_LARGE;
		}
		return null;
	}
	
	public Toppings convertTopps(String top){
		if(top.equals("BACON")){
			return Toppings.BACON;
		}
		if(top.equals("KNOBLAUCH")){
			return Toppings.KNOBLAUCH;
		}
		if(top.equals("MAIS")){
			return Toppings.MAIS;
		}
		if(top.equals("ROHESEI")){
			return Toppings.ROHESEI;
		}
		if(top.equals("THUNFISCH")){
			return Toppings.THUNFISCH;
		}
		return null;
	}
	
	

}


