package simulation.creatureHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataHandler.Properties;
import simulation.clientObjects.Creature;

public class Killer {
	Properties properties;
	public Killer() {
	properties = Properties.getInstance();
	}

	public void kill(Creature creature) {
		Properties.getInstance().getAliveCreatures().remove(creature);
		creature.setCauseDeath("Killed");
		creature.setCycleDied(properties.getCurrentCycle());
		System.out.println("Was Killed "+creature.getId());
		removeFromEnvironment(creature);
	}

	public void checkForDeaths() {
		List<Creature> creatures = Properties.getInstance().getAliveCreatures().getAliveCreatures();
		List<Creature> postKillerCreatures = new ArrayList<Creature>(creatures);
		for (Creature creature : creatures) {
			boolean alive = true;
			if (alive) {
				alive = checkFood(creature);
			}
			if (alive) {
				alive = checkAge(creature);
			}
			if (alive) {
				alive = checkHydration(creature);
			}
			if (alive == false) {
				postKillerCreatures.remove(creature);
			}
		}
		Properties.getInstance().getAliveCreatures().setList(postKillerCreatures);
	}

	private boolean checkFood(Creature creature) {
		// check if enough food
		int foodUsage = creature.getFoodUsage();
		if (creature.getFoodSupply() < foodUsage) {
			creature.setDead();
			creature.setCauseDeath("Starvation");
			creature.setCycleDied(properties.getCurrentCycle());
			System.out.println("Died of Starvation "+creature.getId());
			this.removeFromEnvironment(creature);
			return false;
		}
		// if enough food, use food
		else {
			creature.setFoodSupply(creature.getFoodSupply() - foodUsage);
			return true;
		}
	}

	private boolean checkAge(Creature creature) {
		int age = creature.getAge();
		double chanceDeath = age / creature.getLifeExpectancy();
		if (chanceDeath > 0.9) {
			chanceDeath = 0.9;
		}
		Random r = new Random();
		if (r.nextDouble() < chanceDeath) {
			creature.setDead();
			creature.setCauseDeath("Old Age");
			creature.setCycleDied(properties.getCurrentCycle());
			System.out.println("Died of old Age "+creature.getId());
			removeFromEnvironment(creature);
			return false;
		}
		return true;
	}

	private boolean checkHydration(Creature creature) {
		int waterUsage = creature.getWaterUsage();
		if (creature.getWaterSupply() < waterUsage) {
			creature.setDead();
			creature.setCauseDeath("Dehydration");
			System.out.println("Died of Dehydration "+creature.getId());
			creature.setCycleDied(properties.getCurrentCycle());
			this.removeFromEnvironment(creature);
			return false;
		}
		// if enough water, use water
		else {
			creature.setWaterSupply(creature.getWaterSupply() - waterUsage);
			return true;
		}
	}

	private void removeFromEnvironment(Creature creature) {
		creature.getEnvironment().removeFromCreatureList(creature);
	}
}
