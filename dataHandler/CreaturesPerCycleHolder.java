package dataHandler;

import java.util.ArrayList;
import java.util.List;

import simulation.clientObjects.Creature;

public class CreaturesPerCycleHolder {
	List<List<List<Creature>>> CreaturesPerCycle = new ArrayList<List<List<Creature>>>();

	public CreaturesPerCycleHolder() {

	}

	public List<List<List<Creature>>> get(){
		return CreaturesPerCycle;
	}
	
	public void addCycle(List<List<Creature>> aliveCreatures) {
		CreaturesPerCycle.add(aliveCreatures);
	}
	public List<List<Creature>> getCreaturesFromCycle(int desiredCycle){
		List<List<Creature>> creaturesFromCycle=CreaturesPerCycle.get(desiredCycle);
		return creaturesFromCycle;
	}
	public void setCreaturesPerCycle(List<List<List<Creature>>> listCycles){
		this.CreaturesPerCycle= listCycles;
	}
}
