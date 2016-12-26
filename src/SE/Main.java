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
	final double baumAbstand = 300;
	final double baumTiefe = 50;
	final double firstDown = 50;
	final double firstBreite = 75;
	final double heiratBreite = 150;
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
	Button heirate = new Button();

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
		heirate.setPrefSize(200, 50);
		heirate.setText("heirate");
		heirate.setStyle("-fx-background-color: skyblue;"
				+ "-fx-background-radius: 50;");
		heirate.setOnAction(e->actionHeirate());
		hb.getChildren().addAll(addRoot, addChildren, heirate);
		
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
		if(vater.m){
			kind.vater = vater;
		}else{
			kind.mutter = vater;
		}
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
	
	public void actionHeirate(){
		if(selected != null && selected.getKinder().isEmpty() && (selected.vater == null) && (selected.mutter == null) && (selected.partner == null)){
			if(secondSelected.partner == null){
				selected.partner = secondSelected;
				secondSelected.partner = selected;
				if(secondSelected.m && !selected.m){ // wenn secondSelected m
					// Koordinaten neu setzen
					selected.setLayoutX(secondSelected.getLayoutX()+heiratBreite);
					selected.setLayoutY(secondSelected.getLayoutY());
					// Heiratslinie zeichnen
					Line heiratsLine = new Line();
					heiratsLine.setStartX(secondSelected.getHCM()[0]);
					heiratsLine.setStartY(secondSelected.getHCM()[1]);
					heiratsLine.setEndX(selected.getHCW()[0]);
					heiratsLine.setEndY(selected.getHCW()[1]);
					window.getChildren().add(heiratsLine);
				}else if(!secondSelected.m && selected.m){ // Wenn secondSelected w und selected m
					// Koordinaten neu setzen
					selected.setLayoutX(secondSelected.getLayoutX()-heiratBreite);
					selected.setLayoutY(secondSelected.getLayoutY());
					// Heiratslinie zeichnen
					Line heiratsLine = new Line();
					heiratsLine.setStartX(secondSelected.getHCW()[0]);
					heiratsLine.setStartY(secondSelected.getHCW()[1]);
					heiratsLine.setEndX(selected.getHCM()[0]);
					heiratsLine.setEndY(selected.getHCM()[1]);
					window.getChildren().add(heiratsLine);
				}
			}
		}
	}

}
