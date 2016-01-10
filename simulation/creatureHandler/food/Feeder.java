package simulation.creatureHandler.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import simulation.clientObjects.Creature;
import simulation.clientObjects.Environment;
import simulation.creatureHandler.Killer;
import dataHandler.Properties;

public class Feeder {
	Properties properties;

	public Feeder() {
		properties = Properties.getInstance();

	}

	public void getFoodForEveryone() {
		List<Environment> environments = properties.getEnvironments();
		for (Environment environment : environments) {
			getFoodForEnvironment(environment);
			environment.regenFood();
		}
	}
	

	private void getFoodForEnvironment(Environment environment) {
		List<Creature> creatures = environment.getCreatureList();
		List<Creature> herbivores = new ArrayList<Creature>();
		List<Creature> hunters = new ArrayList<Creature>();
		for (Creature creature : creatures) {
			if (creature.getFoodSupply() < creature.getFoodUsage() * 2) {
				if (checkIfHerbi(creature)) {
					herbivores.add(creature);
				}
				else {
					hunters.add(creature);
				}
			}
			hydrate(creature);
		}
		int addedSpeedsHerbis = addSpeeds(herbivores);
		startTheHunt(hunters, herbivores, creatures);
		herbiEat(herbivores, addedSpeedsHerbis);
		
	}

	private void herbiEat(List<Creature> herbivores, int addedSpeedsHerbis) {
		for (Creature creature : herbivores) {
			Environment enviroment = creature.getEnvironment();
			double relativeGatherPower = (double) (creature.getSpeed()*1.5) / addedSpeedsHerbis;
			//int amount0 = getFoodAmount0(relativeGatherPower, enviroment);
			//int amount1 = getFoodAmount1(relativeGatherPower, enviroment);
			feed(creature, 0, 0);
		}
	}

	private void feed(Creature creature, int amount0, int amount1) {
		int flylvl = creature.getFlyLvl();
		double portionOf1 = flylvl/Math.pow(16,properties.getAllelLength());
		int toGatherAmount = creature.getSpeed();
		
		Environment environment = creature.getEnvironment();
		if (flylvl > 0) {
			if (environment.getFoodSupply1()*portionOf1 >= toGatherAmount) {
				creature.setFoodSupply(creature.getFoodSupply() + toGatherAmount);
				environment.setFoodSupply1(environment.getFoodSupply1() - toGatherAmount);
				toGatherAmount = 0;
			}
			else {
				creature.setFoodSupply((int)(creature.getFoodSupply() + environment.getFoodSupply1()*portionOf1));
				toGatherAmount -= environment.getFoodSupply1()*portionOf1;
				environment.setFoodSupply1((int)(environment.getFoodSupply1()-environment.getFoodSupply1()*portionOf1));
			}
		}
		if (toGatherAmount != 0) {
			if (environment.getFoodSupply0() >= toGatherAmount) {
				creature.setFoodSupply(creature.getFoodSupply() + toGatherAmount);
				environment.setFoodSupply0(environment.getFoodSupply0() - toGatherAmount);
				toGatherAmount = 0;
			}
			else {
				creature.setFoodSupply(creature.getFoodSupply() + environment.getFoodSupply0());
				environment.setFoodSupply0(0);
				toGatherAmount -= environment.getFoodSupply0();
			}
		}
	}

	private int getFoodAmount1(double relativeGatherPower, Environment environment) {
		int amount = (int) (environment.getFoodSupply1() * relativeGatherPower);
		return amount;
	}

	private int getFoodAmount0(double relativeGatherPower, Environment environment) {
		int amount = (int) (environment.getFoodSupply0() * relativeGatherPower);
		return amount;
	}

	private boolean checkIfHerbi(Creature creature) {
		Random r = new Random();
		if (creature.getCarnivorous() < 42) {
			return true;
		}
		else if (creature.getCarnivorous() > 41 && creature.getCarnivorous() < 70 && r.nextBoolean()) {
			return true;
		}
		else {
			return false;
		}
	}

	private void startTheHunt(List<Creature> hunters, List<Creature> herbivores, List<Creature> creatures) {
		Hunter hunt = new Hunter();
		List<Creature> temphunters = new ArrayList<Creature>(hunters);
		for (Creature hunter : hunters) {
			Creature chosenCreature = hunt.searchForSuitable(hunter, creatures);
			if (chosenCreature != null) {
				if (herbivores.contains(chosenCreature)) {
					herbivores.remove(chosenCreature);
				}
				else if (hunters.contains(chosenCreature)) {
					temphunters.remove(chosenCreature);
				}
				eatCreature(hunter, chosenCreature);
			}
			else if (hunter.getCarnivorous() < 70) {
				temphunters.remove(hunter);
				herbivores.add(hunter);
			}
		}
		hunters = temphunters;
	}
	private void hydrate(Creature currentCreature) {
		Environment currentEnv = currentCreature.getEnvironment();
		if (currentCreature.getWaterSupply() < Math.sqrt(currentCreature.getWidth() * currentCreature.getHeight())) {
			if (currentEnv.getCurrentWaterSupply() > currentCreature.getSpeed()) {
				currentCreature.setWaterSupply(currentCreature.getWaterSupply() + currentCreature.getSpeed());
				currentEnv.setCurrentWaterSupply(currentEnv.getCurrentWaterSupply() - currentCreature.getSpeed());
			}
			else {
				currentCreature.setWaterSupply(currentCreature.getWaterSupply() + currentEnv.getCurrentWaterSupply());
				currentEnv.setCurrentWaterSupply(0);
			}
		}
	}
	private void eatCreature(Creature hunter, Creature target) {
		Killer killer = new Killer();
		killer.kill(target);
		hunter.setFoodSupply(hunter.getFoodSupply() + target.getVolume());
	}

	private int addSpeeds(List<Creature> speedies) {
		int addedSpeeds = 0;
		for (Creature creature : speedies) {
			addedSpeeds += creature.getSpeed();
		}
		return addedSpeeds;
	}
}
