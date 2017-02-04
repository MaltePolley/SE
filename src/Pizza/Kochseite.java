package Pizza;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Kochseite implements Initializable{
	
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
	protected void display(){
		// TODO Datei auslesen und text + status setzen
	}
	
	@FXML
	protected void setStatus(){
		// TODO status setzen je nach Combobox auswahl
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Initialisieren
		
	}
	
	
	
}
