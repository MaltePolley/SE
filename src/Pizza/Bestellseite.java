package Pizza;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Bestellseite implements Initializable {

	public Stage primaryStage;
	FXMLLoader loader = new FXMLLoader();
	Kunde currentK;
	Order currentO = new Order();

	@FXML
	TextField vorname;

	@FXML
	TextField nachname;

	@FXML
	TextField straße;

	@FXML
	TextField ort;

	@FXML
	TextField plz;

	@FXML
	TextField handynr;

	@FXML
	ComboBox pizza;

	@FXML
	ComboBox pizzaGr;

	@FXML
	ChoiceBox toppings;

	@FXML
	Button hinzufügenK;

	@FXML
	Button hinzufügenB;

	@FXML
	Button hinzufügenR;

	@FXML
	Label text;
	
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
	protected void getKData() {
		if (vorname.getText() != null && nachname.getText() != null && ort.getText() != null
				&& handynr.getText() != null && plz.getText() != null) {
			int post = Integer.parseInt(plz.getText());
			currentK = new Kunde(vorname.getText(), nachname.getText(), straße.getText(), ort.getText(),
					handynr.getText(), post);
			fillText();
		} else {
			System.out.println("Bitte alle Felder ausfüllen");
		}
	}

	@FXML
	protected void getBData() {
		PizzaArt pa = (PizzaArt) pizza.getValue();
		PizzaGröße pg = (PizzaGröße) pizzaGr.getValue();
		// TODO Toppings
		ArrayList<Toppings> tops = new ArrayList<Toppings>();
		tops.add((Toppings) toppings.getValue());
		Pizza newPizza = new Pizza(pa, pg, tops);
		currentO.pizzen.add(newPizza);
	
		fillText();
	}

	@FXML
	protected void saveRData() {
		try {
			checkPfad("Bestellungen.txt");
			speichern();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pizza.getItems().addAll(PizzaArt.MOZARELLA, PizzaArt.SALAMI, PizzaArt.SPEZIALE, PizzaArt.THUNFISCH);
		pizzaGr.getItems().addAll(PizzaGröße.SMALL, PizzaGröße.LARGE, PizzaGröße.X_LARGE);
		toppings.getItems().addAll(Toppings.BACON, Toppings.KNOBLAUCH, Toppings.MAIS, Toppings.ROHESEI,
				Toppings.THUNFISCH);

	}
	
	public void fillText(){
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
					// TODO Preis setzen
	}
	
	/** Überprüft ob eine Datei existiert.
	 * @param String pfad - Zu überprüfende Datei
	 */
	public void checkPfad(String pfad){
		File f = new File(pfad);
		if(!f.exists()){
			try{
				Formatter format = new Formatter(pfad);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	
	/** Speichert alle Karteikarten der Karteibox in die Datei zur Karteibox.
	 * @throws IOException
	 */
	public void speichern() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("Bestellungen.txt")); // eventuell true
		if(!currentK.vorname.isEmpty() && !currentK.nachname.isEmpty() && !currentK.straße.isEmpty()  && !currentK.ort.isEmpty() && !currentK.handynummer.isEmpty()){	
		bw.write(currentK.vorname + "," + currentK.nachname + "," + currentK.straße + "," + currentK.ort + "," + currentK.plz + "," + currentK.handynummer);
		for (int i = 0; i < currentO.pizzen.size(); i++) {
			bw.write("," + currentO.pizzen.get(i).pizzaArt.toString() + "," +  currentO.pizzen.get(i).pg.toString());
			if (currentO.pizzen.get(i).toppings != null) {
				for (int x = 0; x < currentO.pizzen.get(i).toppings.size(); x++) {
					 bw.write("," + currentO.pizzen.get(i).toppings.get(x).toString());
				}
			}
		}
		bw.write("\n");
		}else{
			System.out.println("Einige Zeilen sind leer");
		}
		bw.close();
	}
	
	
	
}// End Bestellseite
