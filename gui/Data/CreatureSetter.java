package gui.Data;

import java.util.List;

import dataHandler.Properties;
import simulation.clientObjects.Creature;
import simulation.clientObjects.Dna;
import simulation.dnaHandler.DnaToCreatureTranslater;

public class CreatureSetter {
	public CreatureSetter() {

	}

	public void setStartCreatures(TableCreature creatures) {
		Properties properties  = Properties.getInstance();
		DnaToCreatureTranslater translater = new DnaToCreatureTranslater();
		int amount = Integer.valueOf(creatures.getAmount());
		String dnaSequence = creatures.getDnaSequence();
		Dna ProtoDna = new Dna(dnaSequence);
		if (dnaSequence == "random") {
			for(int i = 0; i<amount;i++){
				Dna dna = new Dna(properties.getMaxDnaLength());
				Creature creature = translater.translateDnaToCreature(dna, null, null);
				properties.getAliveCreatures().add(creature);
			}
		}
		else {
			for(int i = 0; i<amount;i++){
				Creature creature = translater.translateDnaToCreature(ProtoDna, null, null);
				properties.getAliveCreatures().add(creature);
			}
		}
	}
}
