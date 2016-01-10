package gui.Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import simulation.clientObjects.Environment;

public class TableEnvironment {
	Environment environment;
	StringProperty nameProperty;
	IntegerProperty xProperty;
	IntegerProperty yProperty;
	public TableEnvironment(){
		environment = null;
		nameProperty=null;
		xProperty=null;
		yProperty=null;
	}
	public void setDefault1(){
		Environment environment = new Environment();
		environment.setMinRegenFoodSupply(8000);
		environment.setRegenRatio(1.2);
		environment.setLvlRatio(0.8);
		environment.setTemperature(1);
		environment.setMaxWaterSupply(8000);
		environment.regenFood();
		this.setNameProperty(new SimpleStringProperty("Default"));
		this.setEnvironment(environment);
	}
	public void setDefault2(){
		Environment environment = new Environment();
		environment.setMinRegenFoodSupply(8000);
		environment.setRegenRatio(1.2);
		environment.setLvlRatio(0.8);
		environment.setTemperature(200);
		environment.setMaxWaterSupply(8000);
		environment.regenFood();
		this.setNameProperty(new SimpleStringProperty("Default"));
		this.setEnvironment(environment);
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public StringProperty getNameProperty() {
		return nameProperty;
	}
	public void setNameProperty(StringProperty nameProperty) {
		this.nameProperty = nameProperty;
	}
	public IntegerProperty getxProperty() {
		return xProperty;
	}
	public void setxProperty(IntegerProperty xProperty) {
		this.xProperty = xProperty;
	}
	public IntegerProperty getyProperty() {
		return yProperty;
	}
	public void setyProperty(IntegerProperty yProperty) {
		this.yProperty = yProperty;
	}
	
}
