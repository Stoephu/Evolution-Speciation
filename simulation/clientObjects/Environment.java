package simulation.clientObjects;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataHandler.Properties;

public class Environment {
	private Random r = new Random();
	private int x;
	private int y;
	private int foodSupply0;
	private int foodSupply1;
	private int maxWaterSupply;
	private int currentWaterSupply;
	private int minRegenFoodSupply;
	private double lvlRatio;
	private double regenRatio;
	private int temperature;
	private List<Creature> creatureList = new ArrayList<Creature>();

	public Environment() {

	}
	
	public Environment(boolean test) {
		Properties properties = Properties.getInstance();
		this.lvlRatio = r.nextDouble() * properties.getMaxLvlRatio() + properties.getMinLvlRatio();
		this.foodSupply0 = r.nextInt(properties.getMaxFoodSupply() - properties.getMinFoodSupply()) + properties.getMinFoodSupply();
		this.foodSupply1 = (int) (this.foodSupply0 * this.lvlRatio);
		this.minRegenFoodSupply = r.nextInt(properties.getMaxMinFoodSupply() - properties.getMinMinFoodSupply()) + properties.getMinMinFoodSupply();
		this.regenRatio = r.nextInt(properties.getMaxFoodRegenRatio() - properties.getMinFoodRegenRatio()) + properties.getMinFoodRegenRatio();
		if (r.nextInt(100) < 70) {
			this.temperature = r.nextInt(properties.getMaxTemp());
		}
		else {
			this.temperature = (-1) * r.nextInt(properties.getMaxTemp());
		}
		this.maxWaterSupply=r.nextInt(properties.getMaxWaterSupply()-properties.getMinWaterSupply())+properties.getMinWaterSupply();
		this.currentWaterSupply=this.maxWaterSupply;
	}

	public void regenFood() {
		if (this.foodSupply0 > this.minRegenFoodSupply) {
			this.foodSupply0 = (int) (this.foodSupply0 * this.regenRatio);
		}
		else {
			this.foodSupply0 = this.minRegenFoodSupply;
		}
		if (this.foodSupply1 > this.minRegenFoodSupply*this.lvlRatio) {
			this.foodSupply1 = (int) (this.foodSupply1 * ((this.regenRatio) * this.lvlRatio));
		}
		else {
			this.foodSupply1 = (int)(this.minRegenFoodSupply*this.lvlRatio);
		}
		this.currentWaterSupply=this.maxWaterSupply;
	}

	public void setMaxWaterSupply(int maxWaterSupply) {
		this.maxWaterSupply = maxWaterSupply;
	}

	public double getLvlRatio() {
		return lvlRatio;
	}

	public void setLvlRatio(double lvlRatio) {
		this.lvlRatio = lvlRatio;
	}

	public double getRegenRatio() {
		return regenRatio;
	}

	public void setRegenRatio(double regenRatio) {
		this.regenRatio = regenRatio;
	}

	public void AddToCreatureList(Creature creature) {
		creatureList.add(creature);
	}

	public void removeFromCreatureList(Creature creature) {
		creatureList.remove(creature);
	}

	public List<Creature> getCreatureList() {
		return creatureList;
	}

	public void setCreatureList(List<Creature> livingCreatures) {
		this.creatureList = livingCreatures;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFoodSupply0() {
		return foodSupply0;
	}

	public void setFoodSupply0(int foodSupply0) {
		this.foodSupply0 = foodSupply0;
	}

	public int getFoodSupply1() {
		return foodSupply1;
	}

	public void setFoodSupply1(int foodSupply1) {
		this.foodSupply1 = foodSupply1;
	}

	public int getRegenFoodSupply() {
		return minRegenFoodSupply;
	}

	public void setRegenFoodSupply(int regenFoodSupply) {
		this.minRegenFoodSupply = regenFoodSupply;
	}

	public double getRatio() {
		return lvlRatio;
	}

	public void setRatio(double ratio) {
		this.lvlRatio = ratio;
	}

	public int getMinRegenFoodSupply() {
		return minRegenFoodSupply;
	}

	public void setMinRegenFoodSupply(int minRegenFoodSupply) {
		this.minRegenFoodSupply = minRegenFoodSupply;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getMaxWaterSupply() {
		return maxWaterSupply;
	}

	public int getCurrentWaterSupply() {
		return currentWaterSupply;
	}

	public void setCurrentWaterSupply(int currentWaterSupply) {
		this.currentWaterSupply = currentWaterSupply;
	}
}
