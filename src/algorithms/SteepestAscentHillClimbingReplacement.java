package algorithms;

import java.util.Random;
import utils.Solution;
import java.util.Arrays;

/**
 * Class that implements the Steepest Ascent Hill Climbing algorithm with replacement
 * @author andres
 */
public class SteepestAscentHillClimbingReplacement extends Algorithm {

    private int numTweaks; // the number of tweaks to ajust the solution in each iteration, 10 per default

    public SteepestAscentHillClimbingReplacement() {
    	numTweaks = 10;
    }

    public int getNumTweaks() {
    	return numTweaks;
    }
    public void setNumTweaks(int numTweaks) {
    	this.numTweaks = numTweaks;
    }

    /**
     * Function that runs the Steepest Ascent Hill Climbing algorithm with replacement
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	Solution s = new Solution();
    	s.init(function, random.nextDouble()); // Initial candidate
        best = (Solution) s.clone(); // best solution
        int i = 0; // iterations counter
        do {
        	Solution currSol = (Solution) s.clone(); // clone the current best solution
            currSol.tweak(function, random.nextDouble()); // tweak the current solution
            for(int j = 0; j < numTweaks; j++) { // Adjust the current solution with teh number of tweaks predefined
            	Solution adjustSol = (Solution) s.clone(); // clone the best current solution
            	adjustSol.tweak(function, random.nextDouble()); // tweak the solution to adjust
            	if(currSol.getFitness() > adjustSol.getFitness()) { // replace the current solution if adjusted solution is better
            		currSol = (Solution) adjustSol.clone(); // adjust the current solution
            	}
            }
            s = (Solution) currSol.clone(); // replacement
            if (best.getFitness() > s.getFitness()) { // if the replace solutoon is better of global best
                best = (Solution) s.clone(); // update the global best solution
            }
            i++;
        } while( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

        best.setTotalIters(i); // set the total iterations for solution
    }
    
}