package simulation;

import java.util.ArrayList;
import java.util.List;

import simulation.clientObjects.Creature;
import simulation.clientObjects.Environment;
import simulation.creatureHandler.Killer;
import simulation.creatureHandler.Mover;
import simulation.creatureHandler.Pairer;
import simulation.creatureHandler.food.Feeder;
import dataHandler.AliveCreatures;
import dataHandler.CreaturesPerCycleHolder;
import dataHandler.DataWrapper;
import dataHandler.Properties;

public class Cycler {
	private int numberCycles;
	private Properties properties;
	public Cycler(int cycles){
		properties= Properties.getInstance();
		numberCycles=cycles;
	}
	public void cycle(){
		CreaturesPerCycleHolder cycleHolder = new CreaturesPerCycleHolder();
		properties.setCreaturePerCycleHolder(cycleHolder);
		Pairer pairer = new Pairer();
		Killer killer= new Killer();
		Mover mover= new Mover();
		Feeder feeder= new Feeder();
		AliveCreatures aliveCreatures =	properties.getAliveCreatures();
		properties.getAllCreatures().setAllCreatures(aliveCreatures.getAliveCreatures());
for(int i=0;i<numberCycles;i++){
		properties.setCurrentCycle(i);
		List<List<Creature>> aliveCreaturesInEnvironments = new ArrayList<List<Creature>>();
		for(Environment environment: properties.getEnvironments()){
			List<Creature> creaturelist = new ArrayList<Creature>(environment.getCreatureList());
			aliveCreaturesInEnvironments.add(creaturelist);
		}
		cycleHolder.addCycle(aliveCreaturesInEnvironments);
		System.out.println("Cycle: "+i+" AliveCreatures:"+cycleHolder.getCreaturesFromCycle(i).size());

		feeder.getFoodForEveryone();
		killer.checkForDeaths();
		pairer.pairAllCreatures();
		mover.moveAll();
		}
System.out.println("done");
DataWrapper wrapper = new DataWrapper();
wrapper.wrapData(cycleHolder);
	}
}
