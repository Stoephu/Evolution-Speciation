package simulation.creatureHandler;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataHandler.AliveCreatures;
import dataHandler.Properties;
import simulation.clientObjects.Creature;
import simulation.clientObjects.Environment;

public class Mover {
	public Mover() {

	}

	public void moveAll() {
		Properties properties = Properties.getInstance();
		List<Environment> environments = properties.getEnvironments();
		AliveCreatures aliveCreatures = properties.getAliveCreatures();
		List<Creature> creatures = aliveCreatures.getAliveCreatures();
		Random r = new Random();
		for (int i = 0; creatures.size() > i; i++) {
			Creature subjectCreature = creatures.get(i);
			Environment refEnvironment = subjectCreature.getEnvironment();
			if (r.nextInt(1000) < properties.getMoveChance()) {
				List<Environment> possibleEnv = new ArrayList<Environment>();
				for (int y = 0; environments.size() > y; y++) {
					Environment subjectEnvironment = environments.get(y);
					if (refEnvironment.getX() + 1 >= subjectEnvironment.getX()
							&& subjectEnvironment.getX() >= refEnvironment.getX() - 1
							&& refEnvironment.getY() + 1 >= subjectEnvironment.getY()
							&& subjectEnvironment.getY() >= refEnvironment.getY() - 1) {
						possibleEnv.add(subjectEnvironment);
					}
				}
				this.move(subjectCreature, possibleEnv,r);
			}
		}

	}
	private void move(Creature creature,List<Environment>possibleEnv,Random r){
		Environment lastEnv = creature.getEnvironment();
		Environment chosenEnv=possibleEnv.get(r.nextInt(possibleEnv.size()));
		if(chosenEnv!=creature.getEnvironment()){
		creature.getEnvironment().removeFromCreatureList(creature);
		creature.setEnvironment(chosenEnv);
		chosenEnv.AddToCreatureList(creature);
		creature.getTemperatureTol();
		System.out.println(creature.getId() + " moved from "+lastEnv.getX()+"/"+lastEnv.getY()+" to "+chosenEnv.getX()+"/"+chosenEnv.getY()+".");
		}
	}
}
