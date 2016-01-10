package gui;

import gui.Data.CreatureSetter;
import gui.Data.EnvironmentSetter;
import gui.Data.TableCreature;
import gui.Data.TableEnvironment;

import java.util.ArrayList;
import java.util.List;

import simulation.clientObjects.Environment;
import dataHandler.Properties;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertiesSetter {
	public PropertiesSetter() {

	}
	public void setPropertyProperty(GridPane layout){
		Properties properties = Properties.getInstance();
		HBox boxbox = (HBox) layout.getChildren().get(0);
		VBox fieldbox = (VBox)boxbox.getChildren().get(1);
		TextField cycleField = (TextField)fieldbox.getChildren().get(0);
		TextField moveChanceField = (TextField)fieldbox.getChildren().get(1);
		TextField foodDamperField = (TextField)fieldbox.getChildren().get(2);
		properties.setCycles(Integer.valueOf(cycleField.getText()));
		properties.setMoveChance(Integer.valueOf(moveChanceField.getText()));
		properties.setFoodUsageDamper(Integer.valueOf(foodDamperField.getText()));
	}

	public void setDnaProperties(List<Node> children) {
		Properties properties = Properties.getInstance();
		int numTextField = 0;
		for (Node node : children) {
			try {
				TextField textfield = (TextField) node;
				numTextField++;
				switch (numTextField) {
				case 1:
					properties.setMaxDnaLength(this.getIntFromTextField(textfield));
					break;
				case 2:
					properties.setAllelLength(this.getIntFromTextField(textfield));
					break;
				case 3:
					properties.setMaxGeneticDistance(this.getIntFromTextField(textfield));
					break;
				case 4:
					properties.setMaxStrikes(this.getIntFromTextField(textfield));
					break;
				case 5:
					properties.setPosLifeExpectancy(this.getIntFromTextField(textfield));
					break;
				case 6:
					properties.setPosHeight(this.getIntFromTextField(textfield));
					break;
				case 7:
					properties.setPosWidth(this.getIntFromTextField(textfield));
					break;
				case 8:
					properties.setPosSpeed(this.getIntFromTextField(textfield));
					break;
				case 9:
					properties.setPosFlight(this.getIntFromTextField(textfield));
					break;
				case 10:
					properties.setPosOptTemperature(this.getIntFromTextField(textfield));
					break;
				case 11:
					properties.setPosNumOffspring(this.getIntFromTextField(textfield));
					break;
				case 12:
					properties.setPosPairChance(this.getIntFromTextField(textfield));
					break;
				case 13:
					properties.setPosCarnivorous(this.getIntFromTextField(textfield));
					break;
				default:
					break;
				}
			} catch (ClassCastException e) {
				// TODO: handle exception

			}
			properties.setDnaSettingsSet(true);
		}
	}

	public int getIntFromTextField(TextField textfield) {
		int value;
		try {
			value = Integer.valueOf(textfield.getText());
		} catch (NumberFormatException e) {
			value = 0;
		}
		return value;
	}

	public void setCreatureProperties(TableView<TableCreature> table) {
		CreatureSetter setter = new CreatureSetter();
		List<TableCreature> creatures = table.getItems();
		for (TableCreature protoCreature : creatures) {
			setter.setStartCreatures(protoCreature);
		}

	}
	public void setEnvironments(List<TableEnvironment> protoEnvironments){
		EnvironmentSetter setter = new EnvironmentSetter();
		List<Environment> environmentList = new ArrayList<Environment>();
		for(int i=0;i<protoEnvironments.size();i++){
			TableEnvironment tEnvironment = protoEnvironments.get(i);
			Environment environment = setter.setEnvironment(tEnvironment);
			environmentList.add(environment);
		}
		Properties properties = Properties.getInstance();
		properties.setEnvironments(environmentList);
	}
}
