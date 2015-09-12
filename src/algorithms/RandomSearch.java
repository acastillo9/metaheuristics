package algorithms;

import java.util.Random;
import java.util.Arrays;
import utils.Solution;

/**
 * Class that implements the Random Search algorithm
 * @author andres
 */
public class RandomSearch extends Algorithm {

	/**
     * Function that runs the random search algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	best.init(function, random.nextDouble()); // initial candidate
        int i = 0; // iterations counter
        do {
        	Solution currSol = new Solution();
        	currSol.init(function, random.nextDouble()); // new random solution
			if (currSol.getFitness() < best.getFitness()) { // if the new solution is better than global best solution
				best = (Solution) currSol.clone(); // update the global best
			}
            i++;
		} while( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

        best.setTotalIters(i); // set the total iterations for solution
    }
    
}