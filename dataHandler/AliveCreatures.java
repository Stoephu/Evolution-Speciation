package dataHandler;
import java.util.ArrayList;
import java.util.List;

import simulation.clientObjects.Creature;
/**
 * Object fur einfacheres Listen handling.
 * @author StoephuAdmin
 *
 */

public class AliveCreatures {
	List<Creature> aliveCreatures = new ArrayList<Creature>();

	public AliveCreatures(){
		
	}
	public void add(Creature creature){
		if (creature != null) {
			this.aliveCreatures.add(creature);
		}
	}
	public void remove(int index){
		this.aliveCreatures.remove(index);
	}
	public void remove(Creature creature){
		this.aliveCreatures.remove(creature);
	}
	public List<Creature> getAliveCreatures(){
		return aliveCreatures;
	}
	public Creature getCreature(int index){
		return this.aliveCreatures.get(index);
	}
	public void setList(List<Creature> list){
		this.aliveCreatures = list;
	}
}