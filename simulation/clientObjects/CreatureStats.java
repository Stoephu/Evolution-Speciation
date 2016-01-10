package simulation.clientObjects;

class CreatureStats {
	private Dna dna;
	private long id;
	private Creature parent1;
	private Creature parent2;
	private int carnivorous;
	private int optTemperature;
	private int speed;
	private int height;
	private int width;
	private int flyLvl;
	private double lifeExpactancy;
	private Environment environment;
	private int pairChance;
	private int numOffspring;
	private String causeDeath;

	// Getter&Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Creature getParent1() {
		return parent1;
	}

	public void setParent1(Creature parent1) {
		this.parent1 = parent1;
	}

	public Creature getParent2() {
		return parent2;
	}

	public void setParent2(Creature parent2) {
		this.parent2 = parent2;
	}

	public int getCarnivorous() {
		return carnivorous;
	}

	public void setCarnivorous(int carnivorous) {
		this.carnivorous = carnivorous;
	}

	public int getOptTemperature() {
		return optTemperature;
	}

	public void setOptTemperature(int optTemperature) {
		this.optTemperature = optTemperature;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getFlyLvl() {
		return flyLvl;
	}

	public void setFlyLvl(int flyLvl) {
		this.flyLvl = flyLvl;
	}

	public double getLifeExpectancy() {
		return lifeExpactancy;
	}

	public void setLifeExpectancy(double lifeExpectancy) {
		this.lifeExpactancy = lifeExpectancy;
	}

	public String getCauseDeath() {
		return causeDeath;
	}

	public void setCauseDeath(String causeDeath) {
		this.causeDeath = causeDeath;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public int getPairChance() {
		return pairChance;
	}

	public void setPairChance(int pairChance) {
		this.pairChance = pairChance;
	}

	public int getNumOffspring() {
		return numOffspring;
	}

	public void setNumOffspring(int numOffspring) {
		this.numOffspring = numOffspring;
	}

	public Dna getDna() {
		return dna;
	}

	public void setDna(Dna dna) {
		this.dna = dna;
	}

}
