package algorithms;

import java.util.Random;
import java.util.Arrays;
import utils.Solution;

/**
 * Class that implements the Simulated Annealing algorithm
 * @author andres
 */
public class SimulatedAnnealing extends Algorithm {

    private double temp; // the temperature to fragment the search space, 0.8 per default

    public SimulatedAnnealing() {
    	temp = 0.8;
    }

    public double getTemp() {
    	return temp;
    }
    public void setTemp(double temp) {
    	this.temp = temp;
    }

    /**
     * Function that runs the simulated annealing algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	Solution s = new Solution();
        s.init(function, random.nextDouble()); // Initial candidate
        best = (Solution) s.clone(); // initialize the global best value
        double temperature = temp; // temperature for algorithm
        double dec = temperature/iters; // temperature decreasement
        int i = 0; // iterations counter
        do {
        	Solution currSol = (Solution) s.clone(); // current best solution
            currSol.tweak(function, random.nextDouble()); // tweak the current solution
            if ((s.getFitness() > currSol.getFitness()) || (random.nextDouble() < getP(currSol.getFitness(), s.getFitness(), temperature))) { // if current solution is better than best current solution or this solution is selected probabilistically to broken by temperature
                s = (Solution) currSol.clone(); // update the current best solution
            }
            temperature -= dec; // temperature decrement
            if (s.getFitness() < best.getFitness()) { // if curent best solition is better than global best solution
            	best = (Solution) s.clone(); // update the global best solution
            }
            i++;
        } while( i < iters && best.getFitness() != function.getOptimal() && temperature >= 0 ); // while not exceeding the max iterations argument or not encounter the optimal value of function or the temperature is 0

        best.setTotalIters(i); // set the total iterations for solution
    }

    /**
     * Function that returns the value of fraction
     * @param  qS           the quality of current best solution
     * @param  qR           the quality of current solution
     * @param  temperature  the current temperature
     * @return              the value of fraction for the temperature
     */
    public double getP(double qS, double qR, double temperature) {
    	return Math.exp((qR - qS) / temperature);
    }
    
}