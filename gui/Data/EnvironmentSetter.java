package gui.Data;

import dataHandler.Properties;
import simulation.clientObjects.Environment;

public class EnvironmentSetter {
	public EnvironmentSetter(){
		
	}
	public Environment setEnvironment(TableEnvironment tEnvironment){
		Environment environment = tEnvironment.getEnvironment();
		environment.setY(tEnvironment.getyProperty().getValue());
		environment.setX(tEnvironment.getxProperty().getValue());
		return environment;
	}
}
