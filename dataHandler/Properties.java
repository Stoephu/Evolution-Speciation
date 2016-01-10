package dataHandler;

import gui.Data.TableCreature;
import gui.Data.TableEnvironment;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableView;
import simulation.clientObjects.Environment;

//import AliveCreatures;
//import AllCreatures;

public class Properties {
	private static Properties instance = null;

	protected Properties() {
		// Exists only to defeat instantiation.
	}

	public static Properties getInstance() {
		if (instance == null) {
			instance = new Properties();
		}
		return instance;
	}

	// Creature
	private double foodUsageDamper=1;
	private long newestId;
	// Cycler
	private int cycles=2000;
	private int currentCycle;

	// DNA
	private int maxDnaLength;
	private int allelLength;
	// DnaSimCalc
	private int maxStrikes;
	private int maxGeneticDistance;
	// DnaToCreatureTranslator
	private int posLifeExpectancy;
	private int posHeight;
	private int posWidth;
	private int posSpeed;
	private int posFlight;
	private double flightDivider;
	private int posOptTemperature;
	private int posNumOffspring;
	private int posPairChance;
	private int posCarnivorous;
	// Environment
	private double maxLvlRatio;
	private double minLvlRatio;
	private int maxFoodSupply;
	private int minFoodSupply;
	private int maxMinFoodSupply;
	private int minMinFoodSupply;
	private int maxFoodRegenRatio;
	private int minFoodRegenRatio;
	private int maxTemp;
	private int maxWaterSupply;
	private int minWaterSupply;
	// FoodHandler
	private int foodWeight;
	private int lvl2Threshold;
	// Generator
	private int numberEnvironments;
	private int numberStartCreatures;
	// Lists
	private TableView<TableCreature> protoCreatures; 
	private AllCreatures allCreatures = new AllCreatures();
	private AliveCreatures aliveCreatures = new AliveCreatures();
	private List<Environment> environments = new ArrayList<Environment>();
	private List<TableEnvironment> protoEnvironments = new ArrayList<TableEnvironment>();
	private CreaturesPerCycleHolder creaturePerCycleHolder;
	// Mover
	private int moveChance=5;
	// PropertiesSet
	private boolean dnaSettingsSet;
	private boolean creatureSettingsSet;
	private boolean environmentsSettingsSet;
	private boolean propertiesSettingsSet;
	// Recombiner
	private int mutationChance=200;
	private int maxMutation=5;
	
	
	public List<TableEnvironment> getProtoEnvironments() {
		return protoEnvironments;
	}

	public void setProtoEnvironments(List<TableEnvironment> protoEnvironments) {
		this.protoEnvironments = protoEnvironments;
	}

	public TableView<TableCreature> getProtoCreatures() {
		return protoCreatures;
	}

	public void setProtoCreatures(TableView<TableCreature> protoCreatures) {
		this.protoCreatures = protoCreatures;
	}

	public boolean isDnaSettingsSet() {
		return dnaSettingsSet;
	}

	public void setDnaSettingsSet(boolean dnaSettingsSet) {
		this.dnaSettingsSet = dnaSettingsSet;
	}

	public boolean isCreatureSettingsSet() {
		return creatureSettingsSet;
	}

	public void setCreatureSettingsSet(boolean creatureSettingsSet) {
		this.creatureSettingsSet = creatureSettingsSet;
	}

	public boolean isEnvironmentsSettingsSet() {
		return environmentsSettingsSet;
	}

	public void setEnvironmentsSettingsSet(boolean environmentsSettingsSet) {
		this.environmentsSettingsSet = environmentsSettingsSet;
	}

	public boolean isPropertiesSettingsSet() {
		return propertiesSettingsSet;
	}

	public void setPropertiesSettingsSet(boolean propertiesSettingsSet) {
		this.propertiesSettingsSet = propertiesSettingsSet;
	}

	public CreaturesPerCycleHolder getCreaturePerCycleHolder() {
		return creaturePerCycleHolder;
	}

	public void setCreaturePerCycleHolder(CreaturesPerCycleHolder creaturePerCycleHolder) {
		this.creaturePerCycleHolder = creaturePerCycleHolder;
	}

	public int getAllelLength() {
		return allelLength;
	}

	public void setAllelLength(int allelLength) {
		this.allelLength = allelLength;
	}

	public double getFoodUsageDamper() {
		return foodUsageDamper;
	}

	public void setFoodUsageDamper(double foodUsageDamper) {
		this.foodUsageDamper = foodUsageDamper;
	}

	public long getNewestId() {
		newestId++;
		return newestId;
	}

	public void setCurrentId(long currentId) {
		this.newestId = currentId;
	}

	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(int currentCycle) {
		this.currentCycle = currentCycle;
	}

	public int getPosLifeExpectancy() {
		return posLifeExpectancy;
	}

	public void setPosLifeExpectancy(int posLifeExpectancy) {
		this.posLifeExpectancy = posLifeExpectancy;
	}

	public int getPosHeight() {
		return posHeight;
	}

	public void setPosHeight(int posHeight) {
		this.posHeight = posHeight;
	}

	public int getPosWidth() {
		return posWidth;
	}

	public void setPosWidth(int posWidth) {
		this.posWidth = posWidth;
	}

	public int getPosSpeed() {
		return posSpeed;
	}

	public void setPosSpeed(int posSpeed) {
		this.posSpeed = posSpeed;
	}

	public int getPosFlight() {
		return posFlight;
	}

	public void setPosFlight(int posFlight) {
		this.posFlight = posFlight;
	}

	public double getFlightDivider() {
		return flightDivider;
	}

	public void setFlightDivider(double flightDivider) {
		this.flightDivider = flightDivider;
	}

	public int getPosOptTemperature() {
		return posOptTemperature;
	}

	public void setPosOptTemperature(int posOptTemperature) {
		this.posOptTemperature = posOptTemperature;
	}

	public int getPosNumOffspring() {
		return posNumOffspring;
	}

	public void setPosNumOffspring(int posNumOffspring) {
		this.posNumOffspring = posNumOffspring;
	}

	public int getPosPairChance() {
		return posPairChance;
	}

	public void setPosPairChance(int posPairChance) {
		this.posPairChance = posPairChance;
	}

	public int getPosCarnivorous() {
		return posCarnivorous;
	}

	public void setPosCarnivorous(int posCarnivorous) {
		this.posCarnivorous = posCarnivorous;
	}

	public int getMaxDnaLength() {
		return maxDnaLength;
	}

	public void setMaxDnaLength(int maxDnaLength) {
		this.maxDnaLength = maxDnaLength;
	}

	public int getMaxStrikes() {
		return maxStrikes;
	}

	public void setMaxStrikes(int maxStrikes) {
		this.maxStrikes = maxStrikes;
	}

	public int getMaxGeneticDistance() {
		return maxGeneticDistance;
	}

	public void setMaxGeneticDistance(int maxGeneticDistance) {
		this.maxGeneticDistance = maxGeneticDistance;
	}

	public double getMaxLvlRatio() {
		return maxLvlRatio;
	}

	public void setMaxLvlRatio(double maxLvlRatio) {
		this.maxLvlRatio = maxLvlRatio;
	}

	public double getMinLvlRatio() {
		return minLvlRatio;
	}

	public void setMinLvlRatio(double minLvlRatio) {
		this.minLvlRatio = minLvlRatio;
	}

	public int getMaxFoodSupply() {
		return maxFoodSupply;
	}

	public void setMaxFoodSupply(int maxFoodSupply) {
		this.maxFoodSupply = maxFoodSupply;
	}

	public int getMinFoodSupply() {
		return minFoodSupply;
	}

	public void setMinFoodSupply(int minFoodSupply) {
		this.minFoodSupply = minFoodSupply;
	}

	public int getMaxMinFoodSupply() {
		return maxMinFoodSupply;
	}

	public void setMaxMinFoodSupply(int maxMinFoodSupply) {
		this.maxMinFoodSupply = maxMinFoodSupply;
	}

	public int getMinMinFoodSupply() {
		return minMinFoodSupply;
	}

	public void setMinMinFoodSupply(int minMinFoodSupply) {
		this.minMinFoodSupply = minMinFoodSupply;
	}

	public int getMaxFoodRegenRatio() {
		return maxFoodRegenRatio;
	}

	public void setMaxFoodRegenRatio(int maxFoodRegenRatio) {
		this.maxFoodRegenRatio = maxFoodRegenRatio;
	}

	public int getMinFoodRegenRatio() {
		return minFoodRegenRatio;
	}

	public void setMinFoodRegenRatio(int minFoodRegenRatio) {
		this.minFoodRegenRatio = minFoodRegenRatio;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public int getMaxWaterSupply() {
		return maxWaterSupply;
	}

	public void setMaxWaterSupply(int maxWaterSupply) {
		this.maxWaterSupply = maxWaterSupply;
	}

	public int getMinWaterSupply() {
		return minWaterSupply;
	}

	public void setMinWaterSupply(int minWaterSupply) {
		this.minWaterSupply = minWaterSupply;
	}

	public int getFoodWeight() {
		return foodWeight;
	}

	public void setFoodWeight(int foodWeight) {
		this.foodWeight = foodWeight;
	}

	public int getLvl2Threshold() {
		return lvl2Threshold;
	}

	public void setLvl2Threshold(int lvl2Threshold) {
		this.lvl2Threshold = lvl2Threshold;
	}

	public int getNumberEnvironments() {
		return numberEnvironments;
	}

	public void setNumberEnvironments(int numberEnvironments) {
		this.numberEnvironments = numberEnvironments;
	}

	public int getNumberStartCreatures() {
		return numberStartCreatures;
	}

	public void setNumberStartCreatures(int numberStartCreatures) {
		this.numberStartCreatures = numberStartCreatures;
	}

	public int getMoveChance() {
		return moveChance;
	}

	public void setMoveChance(int moveChance) {
		this.moveChance = moveChance;
	}

	public int getMutationChance() {
		return mutationChance;
	}

	public void setMutationChance(int mutationChance) {
		this.mutationChance = mutationChance;
	}

	public int getMaxMutation() {
		return maxMutation;
	}

	public void setMaxMutation(int maxMutation) {
		this.maxMutation = maxMutation;
	}

	public AllCreatures getAllCreatures() {
		return allCreatures;
	}

	public void setAllCreatures(AllCreatures allCreatures) {
		this.allCreatures = allCreatures;
	}

	public AliveCreatures getAliveCreatures() {
		return aliveCreatures;
	}

	public void setAliveCreatures(AliveCreatures aliveCreatures) {
		this.aliveCreatures = aliveCreatures;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}

}
