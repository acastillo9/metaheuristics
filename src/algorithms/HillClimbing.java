package algorithms;

import java.util.Random;
import java.util.Arrays;
import functions.Function;
import utils.Solution;

/**
 * Class that implements the Hill Climbing algorithm
 * @author andres
 */
public class HillClimbing extends Algorithm {

    public HillClimbing() {}

    public HillClimbing(Function function, int iters) {
        this.function = function;
        this.iters = iters;
        best = new Solution();
    }

    /**
     * Function that runs the Hill Climbing algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

        best.init(function, random.nextDouble()); // Initial candidate
        int i = 0; // iterations counter
        do {
            Solution currSol = (Solution) best.clone(); // Copy of best solution
            currSol.tweak(function, random.nextDouble()); // tweak the current solution
            if (best.getFitness() > currSol.getFitness()) { // conditional to minimize the value
                best = (Solution) currSol.clone(); // Update the best solution
            }
            i++;
        } while( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

        best.setTotalIters(i); // save the total iters for this solution

    }
    
}