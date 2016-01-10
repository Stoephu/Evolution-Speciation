package simulation.creatureHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataHandler.Properties;
import exceptions.MutaterDerpedException;
import simulation.clientObjects.Creature;
import simulation.clientObjects.Dna;
import simulation.dnaHandler.DnaDistanceCalc;
import simulation.dnaHandler.DnaToCreatureTranslater;
import simulation.dnaHandler.Mutater;
import simulation.dnaHandler.Recombiner;

public class Pairer {
	Properties properties;

	public Pairer() {

	}

	public void pairAllCreatures() {
		properties = Properties.getInstance();
		Random r = new Random();
		List<Creature> aliveCreatures = new ArrayList<Creature>(properties.getAliveCreatures().getAliveCreatures());
		for (Creature creature : aliveCreatures) {
			if (1 < creature.getAge() && creature.getFoodUsage() < creature.getFoodSupply()) {
				if (r.nextInt(100) < creature.getPairChance()) {
					List<Creature> suitableCreatures = searchForSuitableMates(creature, creature.getEnvironment().getCreatureList());
					chooseAndMate(creature, suitableCreatures);
				}
			}
		}
	}

	private List<Creature> searchForSuitableMates(Creature referenceCreature, List<Creature> possibleCreatures) {
		DnaDistanceCalc dnaDistanceCalc = new DnaDistanceCalc();
		List<Creature> suitableCreatures = new ArrayList<Creature>();
		for (Creature selectedCreature : possibleCreatures) {
			if (selectedCreature != referenceCreature) {
				if (dnaDistanceCalc.calcMateSuitable(referenceCreature, selectedCreature)) {
					suitableCreatures.add(selectedCreature);
				}
			}
		}
		return suitableCreatures;
	}

	private void chooseAndMate(Creature referenceCreature, List<Creature> suitableCreatues) {
		if (suitableCreatues != null) {
			if (suitableCreatues.size() > 0) {
				DnaToCreatureTranslater translater = new DnaToCreatureTranslater();
				Mutater mutater = new Mutater();
				Recombiner recombiner = new Recombiner();
				Random r = new Random();
				Creature chosenCreature = suitableCreatues.get(r.nextInt(suitableCreatues.size()));
				int averageNumberOffspring = (chosenCreature.getNumOffspring() + referenceCreature.getNumOffspring()) / 2;
				for (int i = 0; i < averageNumberOffspring; i++) {
					Dna dnaOffspring = recombiner.recombine(referenceCreature.getDna(), chosenCreature.getDna());
					Creature Offspring;
					try {
						dnaOffspring = mutater.mutate(dnaOffspring);
						Offspring = translater.translateDnaToCreature(dnaOffspring, referenceCreature, chosenCreature);
					} catch (MutaterDerpedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Offspring = null;
					}
					properties.getAllCreatures().add(Offspring);
					properties.getAliveCreatures().add(Offspring);
				}
			}
		}
	}
}
