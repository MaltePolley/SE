import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewMensch {
	
	static String name = "default";
	static boolean m = false;
	
	public static void display(){
	Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Neuer Mensch");
		window.setMinWidth(250);
		
		TextField getName = new TextField();
		getName.setPrefSize(200, 50);
		getName.setPromptText("Namen eingeben");
		
		HBox mOderW = new HBox(10);
		Button mButton = new Button("Männlich");
		mButton.setOnAction(e->{m = true;});
		Button wButton = new Button("Weiblich");
		wButton.setOnAction(e->{m = false;});
		mOderW.getChildren().addAll(mButton, wButton);
		
		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e->{
			if(getName!=null) {name = getName.getText();}
			
			window.close();
			});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(getName, mOderW, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
