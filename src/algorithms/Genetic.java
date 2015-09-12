package algorithms;

import java.util.Random;
import utils.Population;
import utils.Solution;

/**
 * Class that implements the Genetic algorithm
 * @author andres
 */
public class Genetic extends Algorithm {

    private Population population; // The population to be modificated

    public Genetic() {
    	population = new Population();
    }

    public Population getPopulation() {
    	return population;
    }
    public void setPopulation(Population population) {
    	this.population = population;
    }

    /**
     * Function that runs the Genetic algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	population.init(function, random); // Initialice the population
    	int i = 0; // iterations counter
    	do {
    		Solution currBest = population.best(); // get the best solution of current population
    		if(best.getFitness() == null || best.getFitness() > currBest.getFitness()) { // if best is empty or current best is better than global best solution
    			best = (Solution) currBest.clone(); // replace global best solution
    		}

    		Population q = new Population(population.getSize()); // new population
    		for(int j = 0; j < population.getSize() / 2; j++) { // for each tuples of parents to create the new generation
    			
    		}
    		i++;
    	} while( i < iters && best.getFitness() != function.getOptimal() ); // while not exced the max of iterations and not encounter the optimal solution for function

    }
    
}