package simulation.clientObjects;

import java.util.Random;

public class Dna {
	private String sequence;
	public Dna(String sequence){
		this.sequence = sequence;
	}
	public Dna(int maxDnaLength){
		generateRandomSequence(maxDnaLength);
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public void generateRandomSequence(int maxDnaLength) {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < maxDnaLength; i++) {
			sb.append(Integer.toHexString(r.nextInt()));
		}
		this.sequence = sb.toString().substring(0, maxDnaLength);
	}
}
