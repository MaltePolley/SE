package Pizza;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Bestellseite implements Initializable {

	public Stage primaryStage;
	Kunde currentK;
	Order currentO;

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
		//ArrayList<Toppings> tops = toppings.getSelectionModel().ge
		ArrayList<Toppings> tops = new ArrayList<Toppings>();
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
					text.setText(currentK.vorname + " " + currentK.nachname + "\n");
					text.setText(text.getText() + currentK.straße + "\n");
					text.setText(text.getText() + currentK.ort + "\n");
					text.setText(text.getText() + currentK.handynummer + "\n");
					text.setText(text.getText() + currentK.plz + "\n");
					if (currentO != null && currentO.pizzen != null) {
						text.setText(text.getText() + "\n");
						for (int i = 0; i < currentO.pizzen.size(); i++) {
							text.setText(text.getText() + currentO.pizzen.get(i));
							if (currentO.pizzen.get(i).toppings != null) {
								for (int x = 0; x < currentO.pizzen.get(i).toppings.size(); x++) {
									text.setText(text.getText() + currentO.pizzen.get(i).toppings.get(x));
								}
							}
							text.setText(text.getText() + "\n");
						}

					}
	}
}
