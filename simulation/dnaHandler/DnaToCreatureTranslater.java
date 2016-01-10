package simulation.dnaHandler;

import java.util.List;
import java.util.Random;

import dataHandler.Properties;
import dataHandler.StringReader;
import simulation.clientObjects.Creature;
import simulation.clientObjects.Dna;
import simulation.clientObjects.Environment;

public class DnaToCreatureTranslater {
	Properties properties;
	StringReader stringReader;

	public DnaToCreatureTranslater() {
		properties = Properties.getInstance();
		stringReader = new StringReader();
	}

	public Creature translateDnaToCreature(Dna dna, Creature parent1, Creature parent2) {
		Creature creature = new Creature();
		creature.setDna(dna);
		creature.setParent1(parent1);
		creature.setParent2(parent2);
		if(parent1!=null){
		creature.setEnvironment(parent1.getEnvironment());
		}
		else{
			Random r = new Random();
			List<Environment> environments = properties.getEnvironments();
			Environment environment = environments.get(r.nextInt(environments.size()));
			//Environment environment = environments.get(0);
			creature.setEnvironment(environment);
		}
		creature.getEnvironment().AddToCreatureList(creature);
		creature.setId(properties.getNewestId());
		creature.setCycleBorn(properties.getCurrentCycle());
		creature.setCarnivorous(calcCarnivorous(dna));
		creature.setOptTemperature(calcOptTemperature(dna));
		creature.setSpeed(calcSpeed(dna));
		creature.setHeight(calcHeight(dna));
		creature.setWidth(calcWidth(dna));
		creature.setFlyLvl(calcFlyLvl(dna));
		creature.setLifeExpectancy(calcLifeExpectancy(dna));
		creature.setPairChance(calcPairChance(dna));
		creature.setNumOffspring(calcNumOffspring(dna));
		creature.getFoodUsage();
		return creature;
	}
	public int calcNumOffspring(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosNumOffspring(), properties.getAllelLength())/10;
		return rawValue;
	}
	public int calcPairChance(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosPairChance(), properties.getAllelLength());
		int value = (int)(((double)(rawValue) / (Math.pow(16,properties.getAllelLength()))) * 100);
		return value;
	}
	public int calcLifeExpectancy(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosLifeExpectancy(), properties.getAllelLength());
		int value = rawValue/2+1;
		return value;
	}
	public int calcFlyLvl(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosFlight(), properties.getAllelLength());
		int value = (int)((double)(rawValue));
		return value;
	}
	public int calcWidth(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosWidth(), properties.getAllelLength());
		return rawValue;
	}
	public int calcHeight(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosHeight(), properties.getAllelLength());
	return rawValue;
	}
	public int calcSpeed(Dna dna){
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosSpeed(), properties.getAllelLength());
		return rawValue;
	}
	public int calcCarnivorous(Dna dna) {
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosCarnivorous(), properties.getAllelLength());
		int value = (int) ( ((double)(rawValue) / (Math.pow(16,properties.getAllelLength()))) * 100);
		return value;
	}

	public int calcOptTemperature(Dna dna) {
		int rawValue = stringReader.readHexStringToInt(dna.getSequence(), properties.getPosOptTemperature(), properties.getAllelLength());
		return rawValue;
	}
	// int values
	public int calcNumOffspring(int rawValue){
		 int value = rawValue/10;
		return value;
	}
	public int calcPairChance(int rawValue){
		int value = (int)(((double)(rawValue) / (Math.pow(16,properties.getAllelLength()))) * 100);
		return value;
	}
	public int calcLifeExpectancy(int rawValue){
		 
		int value = rawValue/2+1;
		return value;
	}
	public int calcFlyLvl(int rawValue){
		
		int value = (int)((double)(rawValue));
		return value;
	}
	public int calcWidth(int rawValue){
		
		return rawValue;
	}
	public int calcHeight(int rawValue){
		 
	return rawValue;
	}
	public int calcSpeed(int rawValue){
		 
		return rawValue;
	}
	public int calcCarnivorous(int rawValue) {
		 
		int value = (int) ( ((double)(rawValue) / (Math.pow(16,properties.getAllelLength()))) * 100);
		return value;
	}

	public int calcOptTemperature(int rawValue) {
		 
		return rawValue;
	}
	
}
