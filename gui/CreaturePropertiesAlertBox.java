package gui;

import gui.Data.TableCreature;
import dataHandler.Properties;
import simulation.dnaHandler.DnaToCreatureTranslater;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreaturePropertiesAlertBox {
	Boolean blockSequenceListener = false;
	Boolean blockValueListener = false;

	public CreaturePropertiesAlertBox() {

	}

	private GridPane getLayout(TableCreature creature) {
		DnaToCreatureTranslater translater = new DnaToCreatureTranslater();
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(8);
		layout.setHgap(10);
		if (creature == null) {
			creature = new TableCreature();
			creature.setName("name");
			creature.setDnaSequence("null");
			creature.setAmount("0");
		}
		// -Label Label
		Label dnaSequenceLabel = new Label("DnaSequence ->");
		Label creatureLabelLabel = new Label("Creature");
		Label lifeExpectancyLabelLabel = new Label("LifeExpectancy");
		Label heightLabelLabel = new Label("Height");
		Label widthLabelLabel = new Label("Width");
		Label speedLabelLabel = new Label("Speed");
		Label flightLabelLabel = new Label("Flight");
		Label optTemperatureLabelLabel = new Label("OptTemperature");
		Label numOffspringLabelLabel = new Label("NumOffspring");
		Label pairChanceLabelLabel = new Label("PairChance");
		Label carnivorousLabelLabel = new Label("Carnivorous");
		// -value Label

		Label lifeExpectancyValueLabel = new Label("0");
		Label heightValueLabel = new Label("0");
		Label widthValueLabel = new Label("0");
		Label speedValueLabel = new Label("0");
		Label flightValueLabel = new Label("0");
		Label optTemperatureValueLabel = new Label("0");
		Label numOffspringValueLabel = new Label("0");
		Label pairChanceValueLabel = new Label("0");
		Label carnivorousValueLabel = new Label("0");
		// -Textfield
		TextField dnaSequenceField = new TextField(creature.getDnaSequence());
		dnaSequenceField.setId("notValueField");
		dnaSequenceField.textProperty().addListener(e -> {
			changedDnaSequence(layout, dnaSequenceField);
		});
		TextField nameField = new TextField(creature.getName());
		nameField.setId("notValueField");
		TextField amountField = new TextField(creature.getAmount());
		amountField.setId("notValueField");
		TextField lifeExpectancyField = new TextField("0");
		lifeExpectancyField.textProperty().addListener(e -> {
			changedValue(layout);
			lifeExpectancyValueLabel.setText(Integer.toString(translater.calcLifeExpectancy(intFromTextField(lifeExpectancyField))));
			;
		});
		TextField heightField = new TextField("0");
		heightField.textProperty().addListener(e -> {
			changedValue(layout);
			heightValueLabel.setText(Integer.toString(translater.calcHeight(intFromTextField(heightField))));
		});
		TextField widthField = new TextField("0");
		widthField.textProperty().addListener(e -> {
			changedValue(layout);
			widthValueLabel.setText(Integer.toString(translater.calcWidth(intFromTextField(widthField))));
		});
		TextField speedField = new TextField("0");
		speedField.textProperty().addListener(e -> {
			changedValue(layout);
			speedValueLabel.setText(Integer.toString(translater.calcSpeed(intFromTextField(speedField))));
		});
		TextField flightField = new TextField("0");
		flightField.textProperty().addListener(e -> {
			changedValue(layout);
			flightValueLabel.setText(Integer.toString(translater.calcFlyLvl(intFromTextField(flightField))));
		});
		TextField optTemperatureField = new TextField("0");
		optTemperatureField.textProperty().addListener(e -> {
			changedValue(layout);
			optTemperatureValueLabel.setText(Integer.toString(translater.calcOptTemperature(intFromTextField(optTemperatureField))));
		});
		TextField numOffspringField = new TextField("0");
		numOffspringField.textProperty().addListener(e -> {
			changedValue(layout);
			numOffspringValueLabel.setText(Integer.toString(translater.calcNumOffspring(intFromTextField(numOffspringField))));
		});
		TextField pairChanceField = new TextField("0");
		pairChanceField.textProperty().addListener(e -> {
			changedValue(layout);
			pairChanceValueLabel.setText(Integer.toString(translater.calcPairChance(intFromTextField(pairChanceField))));
		});
		TextField carnivorousField = new TextField("0");
		carnivorousField.textProperty().addListener(e -> {
			changedValue(layout);
			carnivorousValueLabel.setText(Integer.toString(translater.calcCarnivorous(intFromTextField(carnivorousField))));
		});
		// -Constraint
		GridPane.setConstraints(creatureLabelLabel, 0, 0);
		GridPane.setConstraints(dnaSequenceLabel, 1, 0);
		GridPane.setConstraints(dnaSequenceField, 2, 0);
		GridPane.setConstraints(nameField, 3, 0);
		GridPane.setConstraints(amountField, 4, 0);

		GridPane.setConstraints(lifeExpectancyLabelLabel, 0, 2);
		GridPane.setConstraints(lifeExpectancyField, 1, 2);
		GridPane.setConstraints(lifeExpectancyValueLabel, 2, 2);
		GridPane.setConstraints(heightLabelLabel, 0, 3);
		GridPane.setConstraints(heightField, 1, 3);
		GridPane.setConstraints(heightValueLabel, 2, 3);
		GridPane.setConstraints(widthLabelLabel, 0, 4);
		GridPane.setConstraints(widthField, 1, 4);
		GridPane.setConstraints(widthValueLabel, 2, 4);
		GridPane.setConstraints(speedLabelLabel, 0, 5);
		GridPane.setConstraints(speedField, 1, 5);
		GridPane.setConstraints(speedValueLabel, 2, 5);
		GridPane.setConstraints(flightLabelLabel, 0, 6);
		GridPane.setConstraints(flightField, 1, 6);
		GridPane.setConstraints(flightValueLabel, 2, 6);
		GridPane.setConstraints(optTemperatureLabelLabel, 0, 7);
		GridPane.setConstraints(optTemperatureField, 1, 7);
		GridPane.setConstraints(optTemperatureValueLabel, 2, 7);
		GridPane.setConstraints(numOffspringLabelLabel, 0, 8);
		GridPane.setConstraints(numOffspringField, 1, 8);
		GridPane.setConstraints(numOffspringValueLabel, 2, 8);
		GridPane.setConstraints(pairChanceLabelLabel, 0, 9);
		GridPane.setConstraints(pairChanceField, 1, 9);
		GridPane.setConstraints(pairChanceValueLabel, 2, 9);
		GridPane.setConstraints(carnivorousLabelLabel, 0, 10);
		GridPane.setConstraints(carnivorousField, 1, 10);
		GridPane.setConstraints(carnivorousValueLabel, 2, 10);

		layout.getChildren().addAll(dnaSequenceField, nameField, amountField, dnaSequenceLabel, creatureLabelLabel, lifeExpectancyField,
				lifeExpectancyLabelLabel, lifeExpectancyValueLabel, heightField, heightLabelLabel, heightValueLabel, widthField, widthLabelLabel,
				widthValueLabel, speedField, speedLabelLabel, speedValueLabel, flightField, flightLabelLabel, flightValueLabel, optTemperatureField,
				optTemperatureLabelLabel, optTemperatureValueLabel, numOffspringField, numOffspringLabelLabel, numOffspringValueLabel, pairChanceField,
				pairChanceLabelLabel, pairChanceValueLabel, carnivorousField, carnivorousLabelLabel, carnivorousValueLabel);
		changedDnaSequence(layout, dnaSequenceField);
		return layout;
	}

	private void changedDnaSequence(GridPane layout, TextField dnaSequenceField) {
		if (blockSequenceListener == false) {
			blockValueListener = true;

			Properties properties = Properties.getInstance();
			StringBuffer sequence = new StringBuffer(dnaSequenceField.getText());

			if (sequence.length() != properties.getMaxDnaLength()) {
				blockSequenceListener = true;
				for (int i = sequence.length(); i <= properties.getMaxDnaLength()-1; i++) {
					sequence.append("0");
				}
				sequence.setLength(properties.getMaxDnaLength());
				dnaSequenceField.setText(sequence.toString());
				
			}

			int j = 0;
			int value = 0;
			for (Node node : layout.getChildren()) {
				try {
					TextField textfield = (TextField) node;
					if (textfield.getId() != "notValueField") {
						try {
							int pos = j * properties.getAllelLength();
							value = Integer.valueOf(sequence.substring(pos, pos + properties.getAllelLength()), 16);
							textfield.setText(Integer.toString(value));
						} catch (NumberFormatException e) {
							blockValueListener = false;
							changedValue(layout);
							
							blockValueListener = true;
						}
						j++;

					}

				} catch (ClassCastException e) {

				}
			}
			blockValueListener = false;
			blockSequenceListener = false;
		}
	}

	private void changedValue(GridPane layout) {
		if (blockValueListener == false) {
			blockSequenceListener = true;
			StringBuffer protoDnaSequence = new StringBuffer();
			int value;
			for (int i = 0; i < layout.getChildren().size(); i++) {
				Node node = layout.getChildren().get(i);
				try {
					TextField textfield = (TextField) node;
					if (textfield.getId() != "notValueField") {
						String textString = textfield.getText();
						try {
							value = Integer.valueOf(textString);
						} catch (Exception numberFormatException) {
							// TODO: handle exception
							value = 0;
							textfield.textProperty().set("0");
						}

						protoDnaSequence.append(convertIntToHexString(value));
					}

				} catch (Exception classcastException) {

				}
			}
			TextField dnaSequenceField = (TextField) layout.getChildren().get(0);
			dnaSequenceField.setText(protoDnaSequence.toString());
			blockSequenceListener = false;
		}
	}

	private int intFromTextField(TextField textfield) {
		int value;
		try {
			value = Integer.valueOf(textfield.getText());
		} catch (NumberFormatException e) {
			value = 0;
		}
		return value;
	}

	private String convertIntToHexString(int integer) {
		Properties properties = Properties.getInstance();
		StringBuffer allel = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		sb.append(Integer.toHexString(integer));
		if (properties.getAllelLength() > sb.length()) {
			for (int i = sb.length(); i < properties.getAllelLength(); i++) {
				allel.append("0");
			}

		}
		allel.append(sb);
		if (properties.getAllelLength() < sb.length()) {
			allel.setLength(properties.getAllelLength());
		}
		String hexString = allel.toString();
		return hexString;
	}

	private void showEditWindow(TableCreature creature, TableView<TableCreature> table) {
		Stage stage = new Stage();
		GridPane layout = getLayout(creature);
		Button doneButton = new Button("Done");
		GridPane.setConstraints(doneButton, 0, 11);
		doneButton.setOnAction(e -> {
			stage.close();
			TextField dnaSequenceField = (TextField) layout.getChildren().get(0);
			creature.setDnaSequence(dnaSequenceField.getText());
			TextField nameField = (TextField) layout.getChildren().get(1);
			creature.setName(nameField.getText());
			TextField amountField = (TextField) layout.getChildren().get(2);
			creature.setAmount(amountField.getText());
			table.getColumns().get(0).setVisible(false);
			table.getColumns().get(0).setVisible(true);
		});
		layout.getChildren().add(doneButton);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();

	}

	private void showAddWindow(TableView<TableCreature> table) {
		Stage stage = new Stage();
		GridPane layout = getLayout(null);
		Button doneButton = new Button("Done");
		GridPane.setConstraints(doneButton, 0, 11);
		doneButton.setOnAction(e -> {
			stage.close();
			TableCreature creature = new TableCreature();
			TextField dnaSequenceField = (TextField) layout.getChildren().get(0);
			creature.setDnaSequence(dnaSequenceField.getText());
			TextField nameField = (TextField) layout.getChildren().get(1);
			creature.setName(nameField.getText());
			TextField amountField = (TextField) layout.getChildren().get(2);
			creature.setAmount(amountField.getText());
			table.getItems().add(creature);
		});
		layout.getChildren().add(doneButton);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();

	}

	public void add(TableView<TableCreature> table) {

		showAddWindow(table);

	}

	public void edit(TableCreature creature, TableView<TableCreature> table) {

		showEditWindow(creature, table);

	}
}
