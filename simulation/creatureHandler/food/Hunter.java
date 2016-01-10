package simulation.creatureHandler.food;

import java.util.List;
import java.util.Random;

import javafx.scene.effect.Light.Distant;
import simulation.clientObjects.Creature;
import simulation.dnaHandler.DnaDistanceCalc;

public class Hunter {
	public Hunter() {

	}

	public Creature searchForSuitable(Creature hunter, List<Creature> creatures) {
		Random r = new Random();
		Creature target = null;
		DnaDistanceCalc distanceCalc = new DnaDistanceCalc();
		for (int i = 0; i < 10; i++) {
			target = creatures.get(r.nextInt(creatures.size()));
			if (distanceCalc.calcMateSuitable(hunter, target)) {
				target = null;
			}
			if (target != null) {
				if (checkHuntFail(target, hunter)) {
					target = null;
				}
			}
			if (target != null) {
				return target;
			}
		}
		return target;
	}

	private boolean checkHuntFail(Creature target, Creature hunter) {
		boolean failed = false;
		failed = escaped(hunter, target);
		if (failed) {
			return failed;
		}
		failed = failedBattle(hunter, target);
		return failed;
	}

	private boolean failedBattle(Creature hunter, Creature target) {
		Random r = new Random();
		int sizeDifference = (int) (Math.cbrt(hunter.getVolume()) - Math.cbrt(target.getVolume()));
		if (r.nextInt(200) > sizeDifference + 150) {
			return true;
		}
		return false;
	}

	private boolean escaped(Creature hunter, Creature target) {
		Random r = new Random();
		if (-80 > (hunter.getSpeed() - target.getSpeed())) {
			if (r.nextInt(100) < 30) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
