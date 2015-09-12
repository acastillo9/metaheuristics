package algorithms;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import utils.Solution;

/**
 * Class that implements the Tabu Search algorithm
 * @author andres
 */
public class TabuSearch extends Algorithm {

	private int tabuListLenght; // max lenght of tabu search list, 10 per default
	private int numTweaks; // number of tweaks to adjust the solution, 10 per default

	public TabuSearch() {
		tabuListLenght = 10; 
		numTweaks = 10;
	}

	public int getTabuListLenght() {
		return tabuListLenght;
	}
	public void setTabuListLenght(int tabuListLenght) {
		this.tabuListLenght = tabuListLenght;
	}

	public int getNumTweaks() {
    	return numTweaks;
    }
    public void setNumTweaks(int numTweaks) {
    	this.numTweaks = numTweaks;
    }

    /**
     * Function that runs the Tabu Search algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	Solution s = new Solution();
    	s.init(function, random.nextDouble()); // Initial candidate
    	best = (Solution) s.clone(); // best global solution
    	Queue<Solution> tabuList = new LinkedList<>(); // the tabu list queue
    	tabuList.add(s); // add initial candidate to tabu list
    	int i = 0; // iterations counter
    	do {
    		if (tabuList.size() > tabuListLenght) { // if tabu list is greater than tabu list lenght attribute
    			tabuList.poll(); // remove the oldest element from tabu list
    		}
    		Solution currSol = (Solution) s.clone(); // the current solution
    		currSol.tweak(function, random.nextDouble()); // tweak the current solution
    		for(int j = 0; j < numTweaks; j++) { // run the predefined number of tweaks
            	Solution ajustSol = (Solution) s.clone(); // clone the current best solution to ajust
            	ajustSol.tweak(function, random.nextDouble()); // tweak the solution to adjust
            	if(!tabuList.contains(ajustSol) && (ajustSol.getFitness() < currSol.getFitness() || tabuList.contains(currSol))) { // if tabu list not contanins que ajust solution and the ajust solution is better than current solution or tabu list contains the current solution
            		currSol = (Solution) ajustSol.clone(); // replace the current best solution
            	}
            }
            if(!tabuList.contains(currSol) && currSol.getFitness() < s.getFitness()) { // if tabu list not contains the current solution and the current solution is better than the current best solution
            	s = (Solution) currSol.clone(); // replace the current best solution
            	tabuList.add(currSol); // enqueue the current solution into tabu list
            }
            if(s.getFitness() < best.getFitness()) { // if the current best solution is better than the global best solution
            	best = (Solution) s.clone(); // replace the global best solution
            }
    		i++;
    	} while ( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

    	best.setTotalIters(i); // save the total iters for this solution

    }
    
}