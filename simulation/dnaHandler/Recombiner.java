package simulation.dnaHandler;

import java.util.Random;

import dataHandler.Properties;
import dataHandler.StringReader;
import simulation.clientObjects.Dna;

public class Recombiner {
	public Dna recombine(Dna dna1, Dna dna2) {
		StringReader stringReader = new StringReader();
		String sequence1 = dna1.getSequence();
		String sequence2 = dna2.getSequence();
		String sequence3;
		StringBuffer sb = new StringBuffer();
		Properties properties = Properties.getInstance();
		int maxDnaLength = properties.getMaxDnaLength();
		int takenFrom1 = 0;
		int takenFrom2 = 0;
		Random r = new Random();
		// takes randomly 50/50 from parents dna
		for (int i = 0; i < maxDnaLength; i++) {
			// checks if daughter sequence already got 50% of one parent, and
			// fills rest with the other parent.
			if (takenFrom1 < maxDnaLength / 2) {
				if (takenFrom2 < maxDnaLength / 2) {
					if (r.nextInt(99) < 49) {
						sb.append(stringReader.readFromString(sequence1, i+1, 1));
						takenFrom1++;
					}
					else {
						sb.append(stringReader.readFromString(sequence2, i+1, 1));
						takenFrom2++;
					}
				}
				else {
					sb.append(stringReader.readFromString(sequence1, i+1, 1));
				}
			}
			else {
				sb.append(stringReader.readFromString(sequence2, i+1, 1));
			}
		}
		sequence3 = sb.toString();
		Dna dna3 = new Dna(sequence3);
		return dna3;
	}
}
