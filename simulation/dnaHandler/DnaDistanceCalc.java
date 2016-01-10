package simulation.dnaHandler;

import dataHandler.Properties;
import dataHandler.StringReader;
import simulation.clientObjects.Creature;


public class DnaDistanceCalc {
	public DnaDistanceCalc() {
	}

	public boolean calcMateSuitable(Creature creature1, Creature creature2) {
		Properties properties = Properties.getInstance();
		StringReader stringReader = new StringReader();
		String sequence1 = creature1.getDna().getSequence();
		String sequence2 = creature2.getDna().getSequence();
		int allelValue1;
		int allelValue2;
		int maxDnaLength = properties.getMaxDnaLength();
		int maxGeneticDistance = properties.getMaxGeneticDistance();
		int allelLength = properties.getAllelLength();
		int maxNumberOfStrikes = properties.getMaxStrikes();
		int numberOfStrikes = 0;
		int geneticDistance;
		boolean suitable = true;
		for (int position = 0; position < maxDnaLength/allelLength; position++) {
			allelValue1 = stringReader.readHexStringToInt(sequence1, position+1, allelLength);
			allelValue2 = stringReader.readHexStringToInt(sequence2, position+1, allelLength);
			geneticDistance = Math.abs(allelValue1 - allelValue2);
			if (geneticDistance > maxGeneticDistance) {
				numberOfStrikes++;
			}
			if (numberOfStrikes > maxNumberOfStrikes) {
				suitable = false;
				break;
			}
		}
		return suitable;

	}
}
