package SE;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
	
	final double rootHoehe = 100;
	final double baumAbstand = 250;
	final double baumTiefe = 50;
	double breite = 400;
	double aktuelleBreite = 250;
	double aktuelleTiefe = 0;
	ArrayList<Mensch> rootNodes = new ArrayList<Mensch>();
	Pane window;
	static Mensch selected = null;
	static Mensch secondSelected = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Stammbaum");
		window = new Pane();
		window.setPrefSize(500, 500);
		
		Mensch thomas = addRoot("Thomas", true);

		window.getChildren().add(thomas);
		ScrollPane sp = new ScrollPane();
		sp.setContent(window);
		sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		Scene scene = new Scene(sp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Mensch addRoot(String name, Boolean m) {
		Mensch root = new Mensch(name, m);
		rootNodes.add(root);
		root.setLayoutX(aktuelleBreite);
		root.setLayoutY(rootHoehe);
		aktuelleBreite += baumAbstand;
		return root;
	}

	public Mensch addChildren(Mensch vater, String kindName, Boolean m) {
		Mensch kind = new Mensch(kindName, m);
		vater.addChildren(kind);
		return kind;
	}


}
