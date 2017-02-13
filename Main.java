package Pizza;


import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	Scene mainView;
	AnchorPane menu;
	FXMLLoader loader = new FXMLLoader();
	static Stage window;
	
	public static void main(String[] args){
		launch(args);
	}

	public void start(Stage stage) throws IOException {
		window = stage;
		loader.setLocation(getClass().getResource("Haupt.fxml"));
		menu =  (AnchorPane) FXMLLoader.load(getClass().getResource("Haupt.fxml"));
		mainView = new Scene(menu);
		window.setScene(mainView);
		window.show();
		
	}
	
	@FXML
	Button kunde;
	
	@FXML
	Button koch;
	
	
	@FXML
	protected void getToKunde(){
		try {
			SplitPane playerNames = (SplitPane) loader.load(getClass().getResource("Kunde.fxml"));
			loader.setLocation(getClass().getResource("Kunde.fxml"));
			Scene scenePlayerName = new Scene(playerNames);
			window.setScene(scenePlayerName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void getToKoch(){
		try {
			Pane playerNames = (Pane) loader.load(getClass().getResource("Koch.fxml"));
			loader.setLocation(getClass().getResource("Koch.fxml"));
			Scene scenePlayerName = new Scene(playerNames);
			window.setScene(scenePlayerName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage(){
		return window;
	}

}
