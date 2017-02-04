package Pizza;

import java.io.IOException;
import java.net.URL;
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
