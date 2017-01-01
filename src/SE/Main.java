package SE;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	double aktuelleBreite = 600;
	double aktuelleTiefe = 0;
	ArrayList<Mensch> rootNodes = new ArrayList<Mensch>();
	Pane window;
	static Mensch selected = null;
	static Mensch secondSelected = null;

	// Menü
	VBox hb = new VBox(10);
	Button addRoot = new Button();
	Button addChildren = new Button();
	Button heirate = new Button();
	Button reDrawButton = new Button();
	Button addNewRootParent = new Button();
	Button addChildTree = new Button();
	Button beziehungen = new Button();
	static Label log = new Label();

	// Main
	public static void main(String[] args) {
		launch(args);
	}

	// Javafx-Main
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Stammbaum");
		window = new Pane();
		window.autosize();

		// Menü
		addRoot.setPrefSize(200, 50);
		addRoot.setText("Add Root");
		addRoot.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50");
		addRoot.setOnAction(e -> actionNewRoot());
		Tooltip addRootT = new Tooltip();
		addRootT.setText("Click here to add a new Root Node");
		addRoot.setTooltip(addRootT);
		addChildren.setPrefSize(200, 50);
		addChildren.setText("Add Children");
		addChildren.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50");
		addChildren.setOnAction(e -> actionNewChildren());
		Tooltip addChildrenT = new Tooltip();
		addChildrenT.setText("Click on a Node and then this Button\n" + "to add a Childnode");
		addChildren.setTooltip(addChildrenT);
		heirate.setPrefSize(200, 50);
		heirate.setText("heirate");
		heirate.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50;");
		heirate.setOnAction(e -> actionHeirate());
		Tooltip heirateT = new Tooltip();
		heirateT.setText("Click on a first Node and then on a second single Node to marry them.");
		heirate.setTooltip(heirateT);
		reDrawButton.setPrefSize(200, 50);
		reDrawButton.setText("Redraw");
		reDrawButton.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50;");
		reDrawButton.setOnAction(e -> {
			redraw();
		});
		Tooltip reDrawButtonT = new Tooltip();
		reDrawButtonT.setText("Click to redraw the Trees");
		reDrawButton.setTooltip(reDrawButtonT);
		addNewRootParent.setPrefSize(200, 50);
		addNewRootParent.setText("addNewRootParent");
		addNewRootParent.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50;");
		addNewRootParent.setOnAction(e -> {
			actionAddRootParent();
		});
		Tooltip addNewRootParentT = new Tooltip();
		addNewRootParentT.setText("Click to add a new Parent to a Root Node");
		addNewRootParent.setTooltip(addNewRootParentT);
		addChildTree.setPrefSize(200, 50);
		addChildTree.setText("addChildTree");
		addChildTree.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50;");
		addChildTree.setOnAction(e -> {
			actionAddChildTree();
		});
		Tooltip addChildTreeT = new Tooltip();
		addChildTreeT.setText("Click on a first Node and then on a second Node to:\n"
				+ "add the second Node to the first Node as a Child");
		beziehungen.setPrefSize(200, 50);
		beziehungen.setText("Beziehungen");
		beziehungen.setStyle("-fx-background-color: skyblue;" + "-fx-background-radius: 50;");
		beziehungen.setOnAction(e -> {
			actionBeziehungen();
		});
		Tooltip beziehungenT = new Tooltip();
		beziehungenT.setText("Zeigt Beziehungen zu einem Knoten an.");
		beziehungen.setTooltip(beziehungenT);
		log.setText("Log");
		hb.getChildren().addAll(addRoot, addChildren, heirate, reDrawButton, addNewRootParent, addChildTree,beziehungen, log);

		Mensch thomas = addRoot(new Mensch("Thomas", true));

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

	public Mensch addRoot(Mensch root) {
		rootNodes.add(root);
		root.setLayoutX(aktuelleBreite);
		root.setLayoutY(rootHoehe);
		aktuelleBreite += baumAbstand;
		window.getChildren().add(root);
		return root;
	}

	public Mensch addChildren(Mensch vater, String kindName, Boolean m) {
		Mensch kind = new Mensch(kindName, m);

		if (!window.getChildren().contains(vater.childLine)) {
			window.getChildren().add(vater.childLine);
		}

		vater.addChildren(kind);

		if (vater.m) {
			kind.vater = vater;
		} else {
			kind.mutter = vater;
		}
		double[] vaterCoordinates = vater.getBottomCoordinates();
		// Erste Linie die vom Vater abwärts geht
		Line firstDownLine = new Line();
		firstDownLine.setStartX(vaterCoordinates[0]);
		firstDownLine.setStartY(vaterCoordinates[1]);
		firstDownLine.setEndX(vaterCoordinates[0]);
		firstDownLine.setEndY(vaterCoordinates[1] + firstDown);
		window.getChildren().add(firstDownLine);

		// Zweite Linie die abhängig vom Geschlecht nach links oder rechts geht
		if (vater.m) {
			Line firstLineQuer = new Line();
			firstLineQuer.setStartX(vaterCoordinates[0]);
			firstLineQuer.setStartY(vaterCoordinates[1] + firstDown);
			firstLineQuer.setEndX(vaterCoordinates[0] + firstBreite);
			firstLineQuer.setEndY(vaterCoordinates[1] + firstDown);
			window.getChildren().add(firstLineQuer);

			// Dritte Linie
			Line secondLineDown = new Line();
			secondLineDown.setStartX(vaterCoordinates[0] + firstBreite);
			secondLineDown.setStartY(vaterCoordinates[1] + firstDown);
			double endX = vaterCoordinates[0] + firstBreite;
			double endY = vaterCoordinates[1] + firstDown + firstDown;
			secondLineDown.setEndX(endX);
			secondLineDown.setEndY(endY);
			window.getChildren().add(secondLineDown);

			Line childLine = vater.childLine;
			if (vater.wertSwitch) {
				Line underLine = new Line();
				underLine.setStartX(endX + vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX + vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX + vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setStartX(endX + vater.aktuelleChildBreite);
				vater.childLine.setStartY(endY);
				if (vater.childLine.getEndY() == 0 || vater.childLine.getEndX() == 0) {
					vater.childLine.setEndX(endX);
					vater.childLine.setEndY(endY);
				}

				vater.aktuelleChildBreite += baumAbstand;
				vater.wertSwitch = false;
			} else {
				Line underLine = new Line();
				underLine.setStartX(endX - vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX - vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX - vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setEndX(endX - vater.aktuelleChildBreite);
				vater.childLine.setEndY(endY);

				vater.wertSwitch = true;
			}
		} else {
			Line firstLineQuer = new Line();
			firstLineQuer.setStartX(vaterCoordinates[0]);
			firstLineQuer.setStartY(vaterCoordinates[1] + firstDown);
			firstLineQuer.setEndX(vaterCoordinates[0] - firstBreite);
			firstLineQuer.setEndY(vaterCoordinates[1] + firstDown);
			window.getChildren().add(firstLineQuer);

			// Dritte Linie
			Line secondLineDown = new Line();
			secondLineDown.setStartX(vaterCoordinates[0] - firstBreite);
			secondLineDown.setStartY(vaterCoordinates[1] + firstDown);
			double endX = vaterCoordinates[0] - firstBreite;
			double endY = vaterCoordinates[1] + firstDown + firstDown;
			secondLineDown.setEndX(endX);
			secondLineDown.setEndY(endY);
			window.getChildren().add(secondLineDown);

			if (vater.wertSwitch) {
				Line underLine = new Line();
				underLine.setStartX(endX + vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX + vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX + vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setStartX(endX + vater.aktuelleChildBreite);
				vater.childLine.setStartY(endY);
				if (vater.childLine.getEndY() == 0 || vater.childLine.getEndX() == 0) {
					vater.childLine.setEndX(endX);
					vater.childLine.setEndY(endY);
				}

				vater.aktuelleChildBreite += baumAbstand;
				vater.wertSwitch = false;
			} else {
				Line underLine = new Line();
				underLine.setStartX(endX - vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX - vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX - vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setEndX(endX - vater.aktuelleChildBreite);
				vater.childLine.setEndY(endY);

				vater.wertSwitch = true;
			}

			return kind;
		}

		return null;

	}

	public void actionNewRoot() {
		NewMensch.display();
		Mensch root = new Mensch(NewMensch.name, NewMensch.m);
		addRoot(root);
	}

	public void actionNewChildren() {
		if (selected != null) {
			NewMensch.display();
			addChildren(selected, NewMensch.name, NewMensch.m);
		}
	}

	/**
	 * Fügt selected ein neuen Eltern Knoten hinzu Entfernt selected aus
	 * rootList und fügt den neuen Knoten dort hinzu
	 */
	public void actionAddRootParent() {
		if (selected != null && rootNodes.contains(selected)) {
			NewMensch.display();
			Mensch newParent = new Mensch(NewMensch.name, NewMensch.m);

			// Selected Vater oder Mutter zuweisen
			if (newParent.m) {
				selected.vater = newParent;
			} else {
				selected.mutter = newParent;
			}
			// Fügt new Parent, Selected als Kind hinzu
			newParent.addChildren(selected);
			// Selected aus rootNodes entfernen und newParent hinzufügen
			rootNodes.remove(selected);
			addRoot(newParent);
			// Bäume neu zeichnen
			redraw();
		}

	}

	public void actionHeirate() {
		if ((selected != null) && (secondSelected != null) && (selected.getKinder().isEmpty())
				&& (selected.vater == null) && (selected.mutter == null) && (selected.partner == null)) {
			if (secondSelected.partner == null) {
				selected.partner = secondSelected;
				secondSelected.partner = selected;
				if (secondSelected.m && !selected.m) { // wenn secondSelected m
					// Koordinaten neu setzen
					selected.setLayoutX(secondSelected.getLayoutX() + heiratBreite);
					selected.setLayoutY(secondSelected.getLayoutY());
					// Heiratslinie zeichnen
					Line heiratsLine = new Line();
					heiratsLine.setStartX(secondSelected.getHCM()[0]);
					heiratsLine.setStartY(secondSelected.getHCM()[1]);
					heiratsLine.setEndX(selected.getHCW()[0]);
					heiratsLine.setEndY(selected.getHCW()[1]);
					window.getChildren().add(heiratsLine);
				} else if (!secondSelected.m && selected.m) { // Wenn
																// secondSelected
																// w und
																// selected m
					// Koordinaten neu setzen
					selected.setLayoutX(secondSelected.getLayoutX() - heiratBreite);
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
		} else {
			log.setText("Bedingung nicht erfüllt!");
		}

	}

	public void actionAddChildTree() {
		if (selected != null && secondSelected != null && (!checkForCycle(selected, secondSelected))) {
			if (rootNodes.contains(secondSelected)) {
				rootNodes.remove(secondSelected);
			}
			selected.getKinder().add(secondSelected);
			if (selected.m) {
				secondSelected.vater = selected;
			} else {
				secondSelected.mutter = selected;
			}

			redraw();
		}
	}

	public void actionBeziehungen() {
		if (selected.vater != null) { // Wenn er einen Vater hat
			// Geschwister setzen
			for (int i = 0; i < selected.vater.getKinder().size(); i++) {
				log.setText(log.getText() + "\nGeschwister: ");
				if (selected != selected.vater.getKinder().get(i)) { // Ausschließen
																		// dass
																		// selected
																		// selbst
																		// als
																		// Geschwister
																		// zählt
					log.setText(log.getText() + selected.vater.getKinder().get(i).name + ", ");
				}
			}
			if (selected.vater.vater != null) { // Wenn er einen Großvater hat
				log.setText(log.getText() + "\nGroßvater väterlicherseits: " + selected.vater.vater.name);
				// Onkel Tanten väterlicherseits setzen
				log.setText("\nOnkel/Tanten väterlicherseits(Großvater): ");
				for (int x = 0; x < selected.vater.vater.getKinder().size(); x++) {
					if (selected.vater != selected.vater.vater.getKinder().get(x)) {
						log.setText(log.getText() + selected.vater.vater.getKinder().get(x).name + ", ");
					}
				}

				// Cousins väterlicherseits setzen
				log.setText(log.getText() + "\nCousins väterlicherseits(Großmutter): ");
				for (int i = 0; i < selected.vater.vater.getKinder().size(); i++) {
					for (int x = 0; x < selected.vater.vater.getKinder().get(i).getKinder().size(); x++) {
						if (selected.vater != selected.vater.vater.getKinder().get(i)) {
							log.setText(log.getText() + selected.vater.vater.getKinder().get(i).getKinder().get(x).name
									+ ", ");
						}
					}
				}
			} // Ende wenn er Großvater hat
			if (selected.vater.mutter != null) {
				log.setText("\nGroßmutter väterlicherseits: " + selected.vater.mutter.name);
				// Onkel Tanten väterlicherseits setzen
				log.setText(log.getText() + "\nOnkel/Tanten väterlicherseits(Großmutter): ");
				for (int y = 0; y < selected.vater.mutter.getKinder().size(); y++) {
					if (selected.vater != selected.vater.mutter.getKinder().get(y)) {
						log.setText(log.getText() + selected.vater.mutter.getKinder().get(y).name + ", ");
					}
				}
				// Cousins väterlicherseits setzen
				log.setText(log.getText() + "\nCousins väterlicherseits(Großmutter): ");
				for (int i = 0; i < selected.vater.mutter.getKinder().size(); i++) {
					for (int x = 0; x < selected.vater.mutter.getKinder().get(i).getKinder().size(); x++) {
						if (selected.vater != selected.vater.mutter.getKinder().get(i)) {
							log.setText(log.getText() + selected.vater.mutter.getKinder().get(i).getKinder().get(x).name
									+ ", ");
						}
					}
				}
			}

		} // Ende väterlicherseits
		// Mütterlicherseits
		if (selected.mutter != null) { // Wenn er eine Mutter hat
			// Geschwister setzen
			for (int i = 0; i < selected.mutter.getKinder().size(); i++) {
				log.setText(log.getText() + "\nGeschwister: ");
				if (selected != selected.mutter.getKinder().get(i)) { 
					log.setText(log.getText() + selected.mutter.getKinder().get(i).name + ", ");
				}
			}
			if (selected.mutter.vater != null) { // Wenn er einen Großvater hat
				log.setText(log.getText() + "\nGroßvater mütterlicherseits: " + selected.mutter.vater.name);
				// Onkel Tanten mütterlicherseits setzen
				log.setText("\nOnkel/Tanten mütterlicherseits(Großvater): ");
				for (int x = 0; x < selected.mutter.vater.getKinder().size(); x++) {
					if (selected.mutter != selected.mutter.vater.getKinder().get(x)) {
						log.setText(log.getText() + selected.mutter.vater.getKinder().get(x).name + ", ");
					}
				}
			}
			// Cousins mütterlicherseits setzen
			log.setText(log.getText() + "\nCousins mütterlicherseits(Großmutter): ");
			for (int i = 0; i < selected.mutter.vater.getKinder().size(); i++) {
				for (int x = 0; x < selected.mutter.vater.getKinder().get(i).getKinder().size(); x++) {
					if (selected.mutter != selected.mutter.vater.getKinder().get(i)) {
						log.setText(log.getText() + selected.mutter.vater.getKinder().get(i).getKinder().get(x).name
								+ ", ");
					}
				}
			}
			if (selected.mutter.mutter != null) {
				log.setText("\nGroßmutter mütterlicherseits: " + selected.mutter.mutter.name);
				// Onkel Tanten väterlicherseits setzen
				log.setText(log.getText() + "\nOnkel/Tanten mütterlicherseits(Großmutter): ");
				for (int y = 0; y < selected.mutter.mutter.getKinder().size(); y++) {
					if (selected.mutter != selected.mutter.mutter.getKinder().get(y)) {
						log.setText(log.getText() + selected.mutter.mutter.getKinder().get(y).name + ", ");
					}
				}
				// Cousins mütterlicherseits setzen
				log.setText(log.getText() + "\nCousins mütterlicherseits(Großmutter): ");
				for (int i = 0; i < selected.mutter.mutter.getKinder().size(); i++) {
					for (int x = 0; x < selected.mutter.mutter.getKinder().get(i).getKinder().size(); x++) {
						if (selected.mutter != selected.mutter.mutter.getKinder().get(i)) {
							log.setText(log.getText() + selected.mutter.mutter.getKinder().get(i).getKinder().get(x).name
									+ ", ");
						}
					}
				}
			}
		
		} // Ende mütterlicherseits
	}

	/**
	 * Zeichnet die Bäume neu
	 */
	public void redraw() {
		window.getChildren().clear();
		window.getChildren().add(hb);
		aktuelleBreite = 600;
		for (int i = 0; i < rootNodes.size(); i++) {
			traverseAndAddChildren(rootNodes.get(i));

		}
	}

	public void traverseAndAddChildren(Mensch root) {
		ArrayList<Mensch> kindListe = root.getKinder();
		root.aktuelleChildBreite = 0;
		root.wertSwitch = true;
		if (rootNodes.contains(root)) {
			root.setLayoutX(aktuelleBreite);
			aktuelleBreite += baumAbstand;
		}
		if (!window.getChildren().contains(root)) {
			window.getChildren().add(root);
		}
		for (int i = 0; i < kindListe.size(); i++) {
			addChildren(root, kindListe.get(i));
			traverseAndAddChildren(kindListe.get(i));

		}
	}

	public boolean checkForCycle(Mensch m1, Mensch m2) {
		for (int i = 0; i < rootNodes.size(); i++) {
			if (traverseAndCheckCycle(rootNodes.get(i), m1, m2, false, false)) {
				return true;
			}
		}
		return false;
	}

	public boolean traverseAndCheckCycle(Mensch root, Mensch m1, Mensch m2, boolean b1, boolean b2) {
		if (root == m1) {
			b1 = true;
		}
		if (root == m2) {
			b2 = true;
		}
		if (b1 && b2) {
			return true;
		} else {
			for (int i = 0; i < root.getKinder().size(); i++) {
				if (traverseAndCheckCycle(root.getKinder().get(i), m1, m2, b1, b2)) {
					return true;
				}
			}
		}
		return false;
	}

	public Mensch addChildren(Mensch vater, Mensch kind) {

		if (!window.getChildren().contains(vater.childLine)) {
			window.getChildren().add(vater.childLine);
		}

		if (!vater.getKinder().contains(kind)) {
			vater.addChildren(kind);
		}

		if (vater.m) {
			kind.vater = vater;
		} else {
			kind.mutter = vater;
		}
		double[] vaterCoordinates = vater.getBottomCoordinates();
		// Erste Linie die vom Vater abwärts geht
		Line firstDownLine = new Line();
		firstDownLine.setStartX(vaterCoordinates[0]);
		firstDownLine.setStartY(vaterCoordinates[1]);
		firstDownLine.setEndX(vaterCoordinates[0]);
		firstDownLine.setEndY(vaterCoordinates[1] + firstDown);
		window.getChildren().add(firstDownLine);

		// Zweite Linie die abhängig vom Geschlecht nach links oder rechts geht
		if (vater.m) {
			Line firstLineQuer = new Line();
			firstLineQuer.setStartX(vaterCoordinates[0]);
			firstLineQuer.setStartY(vaterCoordinates[1] + firstDown);
			firstLineQuer.setEndX(vaterCoordinates[0] + firstBreite);
			firstLineQuer.setEndY(vaterCoordinates[1] + firstDown);
			window.getChildren().add(firstLineQuer);

			// Dritte Linie
			Line secondLineDown = new Line();
			secondLineDown.setStartX(vaterCoordinates[0] + firstBreite);
			secondLineDown.setStartY(vaterCoordinates[1] + firstDown);
			double endX = vaterCoordinates[0] + firstBreite;
			double endY = vaterCoordinates[1] + firstDown + firstDown;
			secondLineDown.setEndX(endX);
			secondLineDown.setEndY(endY);
			window.getChildren().add(secondLineDown);

			Line childLine = vater.childLine;
			if (vater.wertSwitch) {
				Line underLine = new Line();
				underLine.setStartX(endX + vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX + vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX + vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setStartX(endX + vater.aktuelleChildBreite);
				vater.childLine.setStartY(endY);
				if (vater.childLine.getEndY() == 0 || vater.childLine.getEndX() == 0) {
					vater.childLine.setEndX(endX);
					vater.childLine.setEndY(endY);
				}

				vater.aktuelleChildBreite += baumAbstand;
				vater.wertSwitch = false;
			} else {
				Line underLine = new Line();
				underLine.setStartX(endX - vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX - vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX - vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setEndX(endX - vater.aktuelleChildBreite);
				vater.childLine.setEndY(endY);

				vater.wertSwitch = true;
			}
		} else {
			Line firstLineQuer = new Line();
			firstLineQuer.setStartX(vaterCoordinates[0]);
			firstLineQuer.setStartY(vaterCoordinates[1] + firstDown);
			firstLineQuer.setEndX(vaterCoordinates[0] - firstBreite);
			firstLineQuer.setEndY(vaterCoordinates[1] + firstDown);
			window.getChildren().add(firstLineQuer);

			// Dritte Linie
			Line secondLineDown = new Line();
			secondLineDown.setStartX(vaterCoordinates[0] - firstBreite);
			secondLineDown.setStartY(vaterCoordinates[1] + firstDown);
			double endX = vaterCoordinates[0] - firstBreite;
			double endY = vaterCoordinates[1] + firstDown + firstDown;
			secondLineDown.setEndX(endX);
			secondLineDown.setEndY(endY);
			window.getChildren().add(secondLineDown);

			if (vater.wertSwitch) {
				Line underLine = new Line();
				underLine.setStartX(endX + vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX + vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX + vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setStartX(endX + vater.aktuelleChildBreite);
				vater.childLine.setStartY(endY);
				if (vater.childLine.getEndY() == 0 || vater.childLine.getEndX() == 0) {
					vater.childLine.setEndX(endX);
					vater.childLine.setEndY(endY);
				}

				vater.aktuelleChildBreite += baumAbstand;
				vater.wertSwitch = false;
			} else {
				Line underLine = new Line();
				underLine.setStartX(endX - vater.aktuelleChildBreite);
				underLine.setStartY(endY);
				underLine.setEndX(endX - vater.aktuelleChildBreite);
				underLine.setEndY(endY + firstDown + firstDown);
				window.getChildren().add(underLine);

				// Kind hinzufügen
				kind.setLayoutX(endX - vater.aktuelleChildBreite - (kind.sizeX / 2));
				kind.setLayoutY(endY + firstDown + firstDown);
				window.getChildren().add(kind);

				// QuerLinie
				vater.childLine.setEndX(endX - vater.aktuelleChildBreite);
				vater.childLine.setEndY(endY);

				vater.wertSwitch = true;
			}

			return kind;
		}

		return null;

	}

}
