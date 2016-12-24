package SE;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

	final double baumBreite = 400;
	final double baumTiefe = 50;
	double breite = 400;
	double aktuelleBreite = 0;
	double aktuelleTiefe = 0;
	ArrayList<Mensch> rootNodes = new ArrayList<Mensch>();
	Pane window;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Stammbaum");
		window = new Pane();
		window.setPrefSize(500, 500);


		ScrollPane sp = new ScrollPane();
		sp.setContent(window);
		Scene scene = new Scene(window);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Mensch addRoot(String name) {
		Mensch root = new Mensch(name);
		rootNodes.add(root);
		return root;
	}

	public Mensch addChildren(Mensch vater, String kindName) {
		Mensch kind = new Mensch(kindName);
		vater.addChildren(kind);
		return kind;
	}


}
