package gui;

import simulation.clientObjects.Environment;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gui.Data.TableEnvironment;

public class EnvironmentAlertBox {
	public EnvironmentAlertBox() {

	}

	private GridPane getWindowLayout(TableView<TableEnvironment> table, TableEnvironment tableEnvironment) {
		TableEnvironment tEnvironment;
		String name = new String("null");
		String minFoodRegen = new String("0");
		String regenRatio = new String("0");
		String lvlRatio = new String("0");
		String temperature = new String("0");
		String maxWaterSupply = new String("0");
		if (tableEnvironment != null) {
			tEnvironment = tableEnvironment;
			Environment environment = tEnvironment.getEnvironment();
			name = tEnvironment.getNameProperty().getValue().toString();
			minFoodRegen = Integer.toString(environment.getRegenFoodSupply()); 
			regenRatio = Double.toString(environment.getRegenRatio()); 
			lvlRatio = Double.toString(environment.getLvlRatio()); 
			temperature = Integer.toString(environment.getTemperature()); 
			maxWaterSupply = Integer.toString(environment.getMaxWaterSupply()); 
		}
		else {
			tEnvironment = new TableEnvironment();
			
		}
		GridPane layout = new GridPane();
		HBox hBox = new HBox();
		VBox labelBox = new VBox(16);
		labelBox.setPadding(new Insets(10));
		VBox fieldBox = new VBox(8);
		fieldBox.setPadding(new Insets(10));
		hBox.getChildren().addAll(labelBox, fieldBox);
		// Top

		// Mid Labels
		Label nameLabel = new Label("Name:");
		Label minFoodRegenLabel = new Label("MinFood Regen");
		Label regenRatioLabel = new Label("regenRatio");
		Label lvlRatioLabel = new Label("LvlRatio");
		Label temperatureLabel = new Label("Temperature");
		Label maxWaterSupplyLabel = new Label("MaxWaterSupply");
		labelBox.getChildren().addAll(nameLabel, minFoodRegenLabel, regenRatioLabel, lvlRatioLabel, temperatureLabel, maxWaterSupplyLabel);
		// Mid Fields
		TextField nameField = new TextField(name);
		TextField minFoodRegenField = new TextField(minFoodRegen);
		TextField regenRatioField = new TextField(regenRatio);
		TextField lvlRatioField = new TextField(lvlRatio);
		TextField temperatureField = new TextField(temperature);
		TextField maxWaterSupplyField = new TextField(maxWaterSupply);
		fieldBox.getChildren().addAll(nameField, minFoodRegenField, regenRatioField, lvlRatioField, temperatureField, maxWaterSupplyField);
		// Bottom
		Button doneButton = new Button("Done");
		doneButton.setOnAction(e -> handle(table, fieldBox, e));
		GridPane.setConstraints(doneButton, 0, 1);
		layout.getChildren().addAll(hBox, doneButton);
		return layout;
	}

	public void handle(TableView<TableEnvironment> table, VBox fieldBox, ActionEvent e) {
		Environment environment = new Environment();
		TableEnvironment tEnvironment = new TableEnvironment();
		TextField minFoodRegenField = (TextField) (fieldBox.getChildren().get(1));
		environment.setMinRegenFoodSupply(Integer.valueOf(minFoodRegenField.getText()));
		TextField regenRatioField = (TextField) (fieldBox.getChildren().get(2));
		environment.setRegenRatio(Double.valueOf(regenRatioField.getText()));
		TextField lvlRatioField = (TextField) (fieldBox.getChildren().get(3));
		environment.setLvlRatio(Double.valueOf(lvlRatioField.getText()));
		TextField temperatureField = (TextField) (fieldBox.getChildren().get(4));
		environment.setTemperature(Integer.valueOf(temperatureField.getText()));
		TextField maxWaterSupplyField = (TextField) (fieldBox.getChildren().get(5));
		environment.setMaxWaterSupply(Integer.valueOf(maxWaterSupplyField.getText()));
		environment.regenFood();
		TextField nameField = (TextField) (fieldBox.getChildren().get(0));
		if (table.getItems().contains(tEnvironment)) {
			tEnvironment.setEnvironment(environment);
			SimpleStringProperty string = new SimpleStringProperty(nameField.getText());
			tEnvironment.setNameProperty(string);
			table.getColumns().get(0).setVisible(false);
			table.getColumns().get(0).setVisible(true);
		}
		else {
			tEnvironment.setEnvironment(environment);
			SimpleStringProperty string = new SimpleStringProperty(nameField.getText());
			tEnvironment.setNameProperty(string);
			table.getItems().addAll(tEnvironment);
		}
		Node source = (Node) e.getSource();
		Stage window = (Stage) source.getScene().getWindow();
		window.close();
	}

	public void showAddWindow(TableView<TableEnvironment> table) {
		Stage window = new Stage();
		Scene scene = new Scene(getWindowLayout(table, null));
		window.setScene(scene);
		window.show();

	}
	public void showEditWindow(TableView<TableEnvironment> table,TableEnvironment tEnvironment){
		Stage window = new Stage();
		Scene scene = new Scene(getWindowLayout(table, tEnvironment));
		window.setScene(scene);
		window.show();
	}

}
