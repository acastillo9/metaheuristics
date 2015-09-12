package algorithms;

import java.util.Random;
import utils.Solution;
import java.util.Arrays;

/**
 * Class that implements the Steepest Ascent Hill Climbing algorithm
 * @author andres
 */
public class SteepestAscentHillClimbing extends Algorithm {

    private int numTweaks; // the number of tweaks to ajust the solution in each iteration, 10 per default

    public SteepestAscentHillClimbing() {
    	numTweaks = 10;
    }

    public int getNumTweaks() {
    	return numTweaks;
    }
    public void setNumTweaks(int numTweaks) {
    	this.numTweaks = numTweaks;
    }

    /**
     * Function that runs the Steepest Ascent Hill Climbing algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

        best.init(function, random.nextDouble()); // Initial candidate
        int i = 0; // iterations counter
        do {
        	Solution currSol = (Solution) best.clone(); // clone the global best solution
        	currSol.tweak(function, random.nextDouble()); // tweak the current solution
            for(int j = 0; j < numTweaks; j++) { // run the predefined number of tweaks
            	Solution ajustSol = (Solution) best.clone(); // clone the best solution to ajust
            	ajustSol.tweak(function, random.nextDouble()); // tweak the solution to adjust
            	if(currSol.getFitness() > ajustSol.getFitness()) { // adjust the solution if that is better of current solution
            		currSol = (Solution) ajustSol.clone(); // replace current with adjust solution
            	}
            }
            if (best.getFitness() > currSol.getFitness()) { // conditional to change the global best solution
                best = (Solution) currSol.clone(); // replace the global best solution
            }
            i++;
        } while( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

        best.setTotalIters(i); // set the total iterations for solution
    }
    
}