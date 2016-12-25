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
	final double firstDown = 50;
	final double firstBreite = 50;
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
		window.autosize();
		
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
		sp.setPrefSize(1000, 800);
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
		if(vater.getKinder().isEmpty()){
		Mensch kind = new Mensch(kindName, m);
		vater.addChildren(kind);
		double[] vaterCoordinates = vater.getBottomCoordinates();
		// Erste Linie die vom Vater abwärts geht
			Line firstDownLine = new Line();
			firstDownLine.setStartX(vaterCoordinates[0]);
			firstDownLine.setStartY(vaterCoordinates[1]);
			firstDownLine.setEndX(vaterCoordinates[0]);
			firstDownLine.setEndY(vaterCoordinates[1]+firstDown);
			window.getChildren().add(firstDownLine);
			
		// Zweite Linie die abhängig vom Geschlecht nach links oder rechts geht
			if(vater.m){
			Line firstLineQuer = new Line();
			firstLineQuer.setStartX(vaterCoordinates[0]);
			firstLineQuer.setStartY(vaterCoordinates[1]+firstDown);
			firstLineQuer.setEndX(vaterCoordinates[0]+firstBreite);
			firstLineQuer.setEndY(vaterCoordinates[1]+firstDown);
			window.getChildren().add(firstLineQuer);
			
			//Dritte Linie
			Line secondLineDown = new Line();
			secondLineDown.setStartX(vaterCoordinates[0]+firstBreite);
			secondLineDown.setStartY(vaterCoordinates[1]+firstDown);
			secondLineDown.setEndX(vaterCoordinates[0]+firstBreite);
			secondLineDown.setEndY(vaterCoordinates[1]+firstDown+firstDown);
			window.getChildren().add(secondLineDown);
			
			//Kind hinzufügen
			kind.setLayoutX(vaterCoordinates[0]+firstBreite-(kind.sizeX/2));
			kind.setLayoutY(vaterCoordinates[1]+firstDown+firstDown);
			window.getChildren().add(kind);
			}else{
				Line firstLineQuer = new Line();
				firstLineQuer.setStartX(vaterCoordinates[0]);
				firstLineQuer.setStartY(vaterCoordinates[1]+firstDown);
				firstLineQuer.setEndX(vaterCoordinates[0]-firstBreite);
				firstLineQuer.setEndY(vaterCoordinates[1]+firstDown);
				window.getChildren().add(firstLineQuer);
				
				//Dritte Linie
				Line secondLineDown = new Line();
				secondLineDown.setStartX(vaterCoordinates[0]-firstBreite);
				secondLineDown.setStartY(vaterCoordinates[1]+firstDown);
				secondLineDown.setEndX(vaterCoordinates[0]-firstBreite);
				secondLineDown.setEndY(vaterCoordinates[1]+firstDown+firstDown);
				window.getChildren().add(secondLineDown);
				
				//Kind hinzufügen
				kind.setLayoutX(vaterCoordinates[0]-firstBreite-(kind.sizeX/2));
				kind.setLayoutY(vaterCoordinates[1]+firstDown+firstDown);
				window.getChildren().add(kind);
			}
			
		
			return kind;
		}
		
		return null;

		
	}
	
	public void actionNewRoot(){
		NewMensch.display();
		addRoot(NewMensch.name, NewMensch.m);
	}
	
	public void actionNewChildren(){
		if(selected != null){
		NewMensch.display();
		addChildren(selected, NewMensch.name, NewMensch.m);
		}
	}

}
