package algorithms;

import java.util.Random;
import functions.Function;
import utils.Solution;

/**
 * Abstract class to define the algorithms structure
 * @author andres
 */
public abstract class Algorithm {

	protected Function function; // Function that optimice the algorithm
	protected int iters; // max iters for algorithm, 1000 per default
	protected Solution best; // best solution encountered

	public Algorithm() {
		function = null;
		iters = 1000;
		best = new Solution();
	}

	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}

	public int getIters() {
    	return iters;
    }
    public void setIters(int iters) {
    	this.iters = iters;
    }

    public Solution getBest() {
    	return best;
    }
    public void setBest(Solution best) {
    	this.best = best;
    }
    
    /**
     * Abstract function that defines the run method for algorithms
     * @param  random  a random object for aleatority
     */
	public abstract void run(Random random);

}