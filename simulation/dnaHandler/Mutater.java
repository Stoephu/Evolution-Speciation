package simulation.dnaHandler;

import java.util.Random;

import simulation.clientObjects.Dna;
import dataHandler.Properties;
import dataHandler.StringReader;
import exceptions.MutaterDerpedException;

public class Mutater {
	public Mutater() {

	}

	public Dna mutate(Dna dna) throws MutaterDerpedException {
		Properties properties = Properties.getInstance();
		StringReader stringReader = new StringReader();
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int mutationChance = properties.getMutationChance();
		int maxMutation = properties.getMaxMutation();
		String startSequence = dna.getSequence();
		String newSequence="";
		for (int i = 0; i < startSequence.length()/properties.getAllelLength(); i++) {
			int value = stringReader.readHexStringToInt(startSequence, i+1, properties.getAllelLength());
			if (r.nextInt(1000) < mutationChance) {
				if (r.nextBoolean()) {
					value=value+r.nextInt(maxMutation-1)+1;
					if(value>255){
						value=255;
					}
				}
				else {
					value=value-r.nextInt(maxMutation-1)+1;
					if(value<=0){
						value=1;
					}
				}
			}
			if(value<=0){
				value = 1;
			}
			StringBuffer allel= new StringBuffer(Integer.toHexString(value));
			if(allel.length()<properties.getAllelLength()){
				for(int j =allel.length();j<properties.getAllelLength();j++){
				allel.insert(0, 0);
				}
				allel.setLength(properties.getAllelLength());
			}
			sb.append(allel);			
		}
		newSequence = sb.toString();
		if(newSequence.length()!=properties.getMaxDnaLength()){
			throw new MutaterDerpedException();
		}
		Dna newDna = new Dna( sb.toString());
		return newDna;
		
	}
}
