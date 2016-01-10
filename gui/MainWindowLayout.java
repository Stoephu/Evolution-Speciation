package gui;


import gui.Data.TableCreature;
import gui.Data.TableEnvironment;
import dataHandler.Properties;
import simulation.Cycler;
import simulation.clientObjects.Environment;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainWindowLayout {
	Properties properties;

	public MainWindowLayout() {
		properties = Properties.getInstance();
	}

	public HBox getTopLayout() {
		PropertiesSetter propertiesSetter = new PropertiesSetter();
		HBox layout = new HBox();
		Button launchButton = new Button("Launch Simulation");
		launchButton.setOnAction(e -> {
			propertiesSetter.setCreatureProperties(properties.getProtoCreatures());
			Cycler cycler = new Cycler(properties.getCycles());
			cycler.cycle();
			Node source = (Node) e.getSource();
			Stage window = (Stage) source.getScene().getWindow();
			window.close();
		});
		layout.getChildren().add(launchButton);
		return layout;

	}

	public TabPane getCenterLayout() {
		TabPane layout = new TabPane();

		Tab environmentTab = new Tab("Environments");
		GridPane environmentsGridPane = getEnvironmentsGridPane();
		environmentTab.setContent(environmentsGridPane);

		Tab dnaTab = new Tab("DNA");
		GridPane dnaGridPane = getDnaGridPane();
		Button dnaApplyButto = new Button("Apply");
		PropertiesSetter propertiesSetter = new PropertiesSetter();
		GridPane.setConstraints(dnaApplyButto, 0, 20);
		dnaGridPane.getChildren().addAll(dnaApplyButto);
		dnaApplyButto.setOnAction(e -> {
			propertiesSetter.setDnaProperties(dnaGridPane.getChildren());
		});
		dnaTab.setContent(dnaGridPane);
		propertiesSetter.setDnaProperties(dnaGridPane.getChildren());

		Tab creatureTab = new Tab("Creatures");
		GridPane creatureGridPane = getCreatureGridPane();
		Button creatureApplyButton = new Button("Apply");
		GridPane.setConstraints(creatureApplyButton, 0, 20);
		creatureGridPane.getChildren().addAll(creatureApplyButton);
		creatureApplyButton.setOnAction(e -> {
			try{
			TableView<TableCreature> table = (TableView<TableCreature>)(creatureGridPane.getChildren().get(creatureGridPane.getChildren().size()-1));
			properties.setProtoCreatures(table);}
			catch(ClassCastException exception){
				
			}
		});
		creatureTab.setContent(creatureGridPane);


		Tab propertiesTab = new Tab("Properties");
		GridPane propertiesGridPane = getPropertiesPane();
		Button propertiesApplyButton = new Button("Apply");
		GridPane.setConstraints(propertiesApplyButton, 0, 20);
		propertiesGridPane.getChildren().addAll(propertiesApplyButton);
		propertiesApplyButton.setOnAction(e -> {
			propertiesSetter.setPropertyProperty(propertiesGridPane);
		});
		propertiesTab.setContent(propertiesGridPane);
		propertiesSetter.setPropertyProperty(propertiesGridPane);

		layout.getTabs().addAll(dnaTab, creatureTab, environmentTab, propertiesTab);
		layout.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		return layout;
	}

	private GridPane getCreatureGridPane() {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		layout = getCreatureLayout(layout);

		return layout;
	}

	private GridPane getDnaGridPane() {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		layout = getDnaLayout(layout);

		return layout;
	}

	private void updateNameColumn(TableColumn<TableCreature, String> nameColumn) {
		nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableCreature, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<TableCreature, String> p) {
				return new SimpleStringProperty(p.getValue().name);
			}
		});
	}

	private void updateDnaSequenceColumn(TableColumn<TableCreature, String> dnaSequenceColumn) {
		dnaSequenceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableCreature, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<TableCreature, String> p) {
				return new SimpleStringProperty(p.getValue().dnaSequence);
			}
		});
	}

	private void updateAmountColumn(TableColumn<TableCreature, String> amountColumn) {
		amountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableCreature, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<TableCreature, String> p) {
				return new SimpleStringProperty(p.getValue().amount);
			}
		});
	}

	private GridPane getCreatureLayout(GridPane layout) {
		VBox vBox = new VBox(8);
		HBox hBox = new HBox(10);
		TableView<TableCreature> table = new TableView<TableCreature>();
		
		TableColumn<TableCreature, String> nameColumn = new TableColumn<TableCreature, String>("Name");
		TableColumn<TableCreature, String> dnaSequenceColumn = new TableColumn<TableCreature, String>("DnaSequence");
		TableColumn<TableCreature, String> amountColumn = new TableColumn<TableCreature, String>("Amount");
		updateNameColumn(nameColumn);
		updateDnaSequenceColumn(dnaSequenceColumn);
		updateAmountColumn(amountColumn);
		table.getColumns().addAll(nameColumn, dnaSequenceColumn, amountColumn);
		
		TableCreature randomCreature = new TableCreature("Random", "random", "0");
		TableCreature defaultCreature = new TableCreature("Default", "1e101099107d289610", "20");
		
		table.getItems().addAll(randomCreature,defaultCreature);
		
		Button addButton = new Button("Add");
		addButton.setMinWidth(60);
		addButton.setOnAction(e -> {
			CreaturePropertiesAlertBox alertBox = new CreaturePropertiesAlertBox();
			alertBox.add(table);
		});
		
		Button editButton = new Button("Edit");
		editButton.setMinWidth(60);
		editButton.setOnAction(e -> {
			
				CreaturePropertiesAlertBox alertBox = new CreaturePropertiesAlertBox();
				TableCreature creature = table.getSelectionModel().getSelectedItem();
				alertBox.edit(creature, table);
		});
		
		Button removeButton = new Button("Remove");
		removeButton.setMinWidth(60);
		removeButton.setOnAction(e -> {
			TableCreature creature = table.getSelectionModel().getSelectedItem();
			table.getItems().remove(creature);
		});
		
		vBox.getChildren().addAll(addButton, editButton, removeButton);
		hBox.getChildren().addAll(table, vBox);
		layout.getChildren().addAll(hBox);
		properties.setProtoCreatures(table);
		return layout;
	}

	private GridPane getDnaLayout(GridPane layout) {

		// First Column
		// -Label
		Label maxDnaLenghtLabel = new Label("MaxDnaLength");
		Label allelLengthLabel = new Label("AllelLength");
		Label maxGeneticDistanceLabel = new Label("MaxGeneticDistance");
		Label maxStrikesLabel = new Label("MaxStrikes");
		Label posLifeExpectancyLabel = new Label("PosLifeExpectancy");
		Label posHeightLabel = new Label("PosHeight");
		Label posWidthLabel = new Label("PosWidth");
		Label posSpeedLabel = new Label("PosSpeed");
		Label posFlightLabel = new Label("PosFlight");
		Label posOptTemperatureLabel = new Label("PosOptTemperatureField");
		Label posNumOffspringLabel = new Label("PosNumOffspring");
		Label posPairChanceLabel = new Label("PosPairChance");
		Label posCarnivorousLabel = new Label("PosCarnivorous");
		// -Textfield
		TextField maxDnaLengthField = new TextField("18");
		maxDnaLengthField.setEditable(false);
		TextField allelLengthField = new TextField("2");
		allelLengthField.textProperty().addListener(e -> {
		try {
			maxDnaLengthField.setText(Integer.toString(Integer.valueOf(allelLengthField.getText())*9));
		} catch (NumberFormatException e2) {
			maxDnaLengthField.setText("0");
		}	
		});
		TextField maxGeneticDistanceField = new TextField("40");
		TextField maxStrikesField = new TextField("1");
		TextField posLifeExpectancyField = new TextField("1");
		TextField posHeightField = new TextField("2");
		TextField posWidthField = new TextField("3");
		TextField posSpeedField = new TextField("4");
		TextField posFlightField = new TextField("5");
		TextField posOptTemperatureField = new TextField("6");
		TextField posNumOffspringField = new TextField("7");
		TextField posPairChanceField = new TextField("8");
		TextField posCarnivorousField = new TextField("9");
		// -Constraint
		GridPane.setConstraints(maxDnaLenghtLabel, 0, 1);
		GridPane.setConstraints(maxDnaLengthField, 1, 1);
		GridPane.setConstraints(allelLengthLabel, 0, 2);
		GridPane.setConstraints(allelLengthField, 1, 2);
		GridPane.setConstraints(maxGeneticDistanceLabel, 0, 3);
		GridPane.setConstraints(maxGeneticDistanceField, 1, 3);
		GridPane.setConstraints(maxStrikesLabel, 0, 4);
		GridPane.setConstraints(maxStrikesField, 1, 4);
		GridPane.setConstraints(posLifeExpectancyLabel, 0, 5);
		GridPane.setConstraints(posLifeExpectancyField, 1, 5);
		GridPane.setConstraints(posHeightLabel, 0, 6);
		GridPane.setConstraints(posHeightField, 1, 6);
		GridPane.setConstraints(posWidthLabel, 0, 7);
		GridPane.setConstraints(posWidthField, 1, 7);
		GridPane.setConstraints(posSpeedLabel, 0, 8);
		GridPane.setConstraints(posSpeedField, 1, 8);
		GridPane.setConstraints(posFlightLabel, 0, 9);
		GridPane.setConstraints(posFlightField, 1, 9);
		GridPane.setConstraints(posOptTemperatureLabel, 0, 10);
		GridPane.setConstraints(posOptTemperatureField, 1, 10);
		GridPane.setConstraints(posNumOffspringLabel, 0, 11);
		GridPane.setConstraints(posNumOffspringField, 1, 11);
		GridPane.setConstraints(posPairChanceLabel, 0, 12);
		GridPane.setConstraints(posPairChanceField, 1, 12);
		GridPane.setConstraints(posCarnivorousLabel, 0, 13);
		GridPane.setConstraints(posCarnivorousField, 1, 13);

		layout.getChildren().addAll( maxDnaLenghtLabel, maxDnaLengthField, allelLengthLabel, allelLengthField, maxGeneticDistanceLabel,
				maxGeneticDistanceField, maxStrikesLabel, maxStrikesField, posLifeExpectancyLabel, posLifeExpectancyField, posHeightLabel, posHeightField,
				posWidthLabel, posWidthField, posSpeedLabel, posSpeedField, posFlightLabel, posFlightField, posOptTemperatureLabel, posOptTemperatureField,
				posNumOffspringLabel, posNumOffspringField, posPairChanceLabel, posPairChanceField, posCarnivorousLabel, posCarnivorousField);
		return layout;
	}

	private GridPane getEnvironmentsGridPane() {
		GridPane layout = getEnvironmentLayout();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		TextField field1 = new TextField("Test");
		GridPane.setConstraints(field1, 0, 0);
		return layout;
	}
	private GridPane getEnvironmentLayout(){
		final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
		GridPane layout = new GridPane();
		HBox hBox= new HBox(10);
		VBox buttonBox = new VBox(8);
		
		
		TableView<TableEnvironment> table = new TableView<TableEnvironment>();
		TableEnvironment defaultEnvironment = new TableEnvironment();
		defaultEnvironment.setDefault1();
		table.getItems().add(defaultEnvironment);
		TableEnvironment defaultEnvironment1 = new TableEnvironment();
		defaultEnvironment1.setDefault2();
		table.getItems().add(defaultEnvironment1);
		
		TableColumn<TableEnvironment, String> nameColumn = new TableColumn<TableEnvironment, String>("Name");
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		nameColumn.setSortable(false);
		
		TableColumn<TableEnvironment, Number> xCoordinateColumn = new TableColumn<TableEnvironment, Number>("X");
		xCoordinateColumn.setCellValueFactory(cellData -> cellData.getValue().getxProperty());
		xCoordinateColumn.setSortable(false);
		
		TableColumn<TableEnvironment, Number> yCoordinateColumn = new TableColumn<TableEnvironment, Number>("Y");
		yCoordinateColumn.setSortable(false);
		yCoordinateColumn.setCellValueFactory(cellData -> cellData.getValue().getyProperty());
		table.getColumns().addAll(nameColumn,xCoordinateColumn,yCoordinateColumn);
		ObservableList<TableEnvironment> obList = table.getItems();
		updateEnvironmentList(obList);
		obList.addListener((Observable hi) -> updateEnvironmentList(obList));
		table.setRowFactory(tv -> {
			TableRow<TableEnvironment> row = new TableRow<TableEnvironment>();
			
			row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    TableEnvironment tEnvironment = table.getItems().remove(draggedIndex);

                    int dropIndex ; 

                    if (row.isEmpty()) {
                        dropIndex = table.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    table.getItems().add(dropIndex, tEnvironment);

                    event.setDropCompleted(true);
                    table.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });
			return row;
		});
		
		Button addButton = new Button("Add");
		addButton.setMinWidth(60);
		addButton.setOnAction(e -> {
			EnvironmentAlertBox alertBox = new EnvironmentAlertBox();
			alertBox.showAddWindow(table);
		});
		
		Button editButton = new Button("Edit");
		editButton.setMinWidth(60);
		editButton.setOnAction(e-> {
			EnvironmentAlertBox alertBox = new EnvironmentAlertBox();
			TableEnvironment tEnvironment = table.getSelectionModel().getSelectedItem();
			alertBox.showEditWindow(table, tEnvironment);
		});
		
		Button removeButton = new Button("Remove");
		removeButton.setMinWidth(60);
		removeButton.setOnAction(e-> {
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
		});
		

		buttonBox.getChildren().addAll(addButton,editButton,removeButton);
		hBox.getChildren().addAll(table,buttonBox);
		layout.getChildren().addAll(hBox);
		
		Button applyButton = new Button("Apply");
		GridPane.setConstraints(applyButton, 0, 1);
		layout.getChildren().addAll(applyButton);
		applyButton.setOnAction(e->environmentApplyButtonAction(obList));
		environmentApplyButtonAction(obList);
		
		return layout;
	}
	private void environmentApplyButtonAction(ObservableList<TableEnvironment> obList){
		PropertiesSetter setter = new PropertiesSetter();
		setter.setEnvironments(obList);
	}
	private GridPane getPropertiesPane() {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		VBox labelbox = new VBox(18);
		VBox fieldbox = new VBox(8);
		HBox boxbox = new HBox(10);
		TextField cycleField = new TextField("2000");
		TextField moverChanceField = new TextField("5");
		TextField foodDamperField = new TextField("1");
		Label cycleLabel = new Label("Cycles");
		Label moveChanceLabel = new Label("Move chance");
		Label foodDamperLabel = new Label("Food dampener");
		labelbox.getChildren().addAll(cycleLabel,moveChanceLabel,foodDamperLabel);
		fieldbox.getChildren().addAll(cycleField,moverChanceField,foodDamperField);
		boxbox.getChildren().addAll(labelbox,fieldbox);
		layout.getChildren().add(boxbox);
		

		return layout;
	}
	private void updateEnvironmentList(ObservableList<TableEnvironment> obList){
		int maxXValue = (int)Math.sqrt((double)(obList.size()));
		for(int i = 0; i<obList.size();i++){
			TableEnvironment tEnvironment = obList.get(i);
			tEnvironment.setxProperty(new SimpleIntegerProperty(i%maxXValue));
			tEnvironment.setyProperty(new SimpleIntegerProperty((int)(i/maxXValue)));
		}
		
	}
}
