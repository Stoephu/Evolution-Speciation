package simulation.clientObjects;

import java.util.ArrayList;
import java.util.List;

import dataHandler.Properties;

public class Creature extends CreatureStats {
	private int foodUsage;
	private int waterUsage;
	private double temperatureMultiplier;
	private int volume;
	private double temperatureTol;
	private int foodSupply;
	private int waterSupply;
	private boolean alive = true;
	private int cycleBorn;
	private int cycleDied;
	private List<Creature> Offspring = new ArrayList<Creature>();

	public Creature() {
	}

	public void setCycleDied(int cycle) {
		this.cycleDied = cycle;
	}

	public boolean isAlive() {
		return alive;
	}

	public List<Creature> getOffspring() {
		return Offspring;
	}

	public int getCycleBorn() {
		return cycleBorn;
	}

	public void setCycleBorn(int cycleBorn) {
		this.cycleBorn = cycleBorn;
	}

	public int getCycleDied() {
		return cycleDied;
	}

	public void addOffspring(Creature creature) {
		Offspring.add(creature);
	}

	public void calcVolume() {
		volume = this.getHeight() * this.getWidth()*this.getWidth();
	}

	public int getVolume() {
		calcVolume();
		return volume;
	}

	private double getTemperatureMultiplier() {
		double Multiplier;
		int minTemperature = (int) (getOptTemperature() - 10);
		int maxTemperature = (int) (getOptTemperature() + 10);
		int envTemperature = getEnvironment().getTemperature();
		if (minTemperature <= envTemperature && envTemperature <= maxTemperature) {
			Multiplier = 1;
		}
		else {
			Multiplier = (envTemperature-getOptTemperature()) / (double) (10);
		}
		this.temperatureMultiplier = Multiplier;
		return Multiplier;

	}

	private void calcWaterUsage() {
		if (getTemperatureMultiplier() > 0) {
			waterUsage = (int) Math.abs((Math.cbrt(getVolume()) *(1+getTemperatureMultiplier())));
		}
		else {
			waterUsage = (int)(Math.cbrt(getVolume()));
		}
	}

	public int getWaterUsage() {
		calcWaterUsage();
		return this.waterUsage;
	}

	private void calcFoodUsage() {
		double temperature;
		calcVolume();
		getTemperatureMultiplier();
		getWaterUsage();
		Properties properties = Properties.getInstance();
		if (getTemperatureMultiplier()< 0) {
			temperature = Math.abs(10*temperatureMultiplier/Math.cbrt(volume));
		}
		else {
			temperature = 10;
		}
		double body = Math.sqrt(Math.cbrt(volume) * this.getSpeed());
		double lifespan = Math.pow(this.getLifeExpectancy(),2)/Math.cbrt(volume);
		double pairing = this.getPairChance()/2;
		double flycost = Math.sqrt(Math.cbrt(volume)*getFlyLvl());
		this.foodUsage = (int) ((temperature + body + lifespan + pairing+flycost)*properties.getFoodUsageDamper());
	}

	public int getFoodUsage() {
		calcFoodUsage();
		return this.foodUsage;
	}

	public void setDead() {
		this.alive = false;
	}

	public int getAge() {
		Properties properties = Properties.getInstance();
		int currentCycle = properties.getCurrentCycle();
		int age = currentCycle - this.cycleBorn;
		return age;
	}

	public double getTemperatureTol() {
		temperatureTol = Math.cbrt(getVolume());
		return temperatureTol;
	}

	public void setTemperatureTol(double temperatureTol) {
		this.temperatureTol = temperatureTol;
	}

	public int getFoodSupply() {
		return foodSupply;
	}

	public void setFoodSupply(int foodSupply) {
		this.foodSupply = foodSupply;
	}

	public int getWaterSupply() {
		return waterSupply;
	}

	public void setWaterSupply(int waterSupply) {
		this.waterSupply = waterSupply;
	}
	
}
