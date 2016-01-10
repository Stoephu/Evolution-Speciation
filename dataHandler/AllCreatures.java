package dataHandler;

import java.util.ArrayList;
import java.util.List;

import simulation.clientObjects.Creature;

/**
 * Object fur einfacheres Listen handling.
 * 
 * @author StoephuAdmin
 *
 */
public class AllCreatures {
	List<Creature> allCreatures = new ArrayList<Creature>();

	public AllCreatures() {
	}

	public void add(Creature creature) {
		if (creature != null) {
			this.allCreatures.add(creature);
		}

	}

	public Creature get(int index) {
		Creature subject = this.allCreatures.get(index);
		return subject;
	}

	public List<Creature> getAllCreatures() {
		return this.allCreatures;
	}

	public void setAllCreatures(List<Creature> creatures) {
		this.allCreatures = creatures;
	}
}
