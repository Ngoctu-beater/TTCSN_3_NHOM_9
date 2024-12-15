package ulti;

public interface Prototype {
	public void initializePopulation ( Population population);
	public void evaluateFitness ( Population population );
	public void selection(Population population);
	public void crossover ( Population population );
	public void mutation (Population population);
	public boolean stopCondition ( int generation, int maxGenerations, int bestFitness, int threshold, int noImprovement, int maxNoImprovement);
}
