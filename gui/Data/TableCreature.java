package gui.Data;

import javafx.beans.property.StringProperty;

public class TableCreature {
	public String name;
	public String dnaSequence;
	public String amount;
	
	public TableCreature(){
		
	}
	public TableCreature(String name, String dnaSequence, String amount){
		setName(name);
		setDnaSequence(dnaSequence);
		setAmount(amount);
	}
	public String dnaSequence(){
		return this.dnaSequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDnaSequence() {
		return dnaSequence;
	}
	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
