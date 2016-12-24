package SE;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
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
	
	//Menü
	HBox hb = new HBox(10);
	Button addRoot = new Button();
	Button addChildren = new Button();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Stammbaum");
		window = new Pane();
		window.setPrefSize(1000, 800);
		
		//Menü
		addRoot.setPrefSize(200, 50);
		addRoot.setText("Add Root");
		addRoot.setStyle("-fx-background-color: skyblue;"
				+ "-fx-background-radius: 50");
		addRoot.setOnAction(e->actionNewRoot());
		addChildren.setPrefSize(200, 50);
		addChildren.setText("Add Children");
		addChildren.setStyle("-fx-background-color: skyblue;"
				+ "-fx-background-radius: 50");
		addChildren.setOnAction(e->actionNewChildren());
		hb.getChildren().addAll(addRoot, addChildren);
		
		Mensch thomas = addRoot("Thomas", true);

		window.getChildren().add(hb);
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
		window.getChildren().add(root);
		return root;
	}

	public Mensch addChildren(Mensch vater, String kindName, Boolean m) {
		Mensch kind = new Mensch(kindName, m);
		vater.addChildren(kind);
		return kind;
	}
	
	public void actionNewRoot(){
		NewMensch.display();
		addRoot(NewMensch.name, NewMensch.m);
	}
	
	public void actionNewChildren(){
		NewMensch.display();
		addChildren(selected, NewMensch.name, NewMensch.m);
	}


}
