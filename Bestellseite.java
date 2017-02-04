package Pizza;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

		//TODO In Datei speichern
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
}
