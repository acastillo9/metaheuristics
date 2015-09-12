package algorithms;

import java.util.Random;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import utils.Solution;

/**
 * Class that implements the Featured-based Tabu Search algorithm
 * @author andres
 */
public class FeatureBasedTabuSearch extends Algorithm {

	private int tabuListLenght; // max lenght of tabu search list, 10 per default
	private int numTweaks; // number of tweaks to adjust the solution, 10 per default

	public FeatureBasedTabuSearch() {
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
     * Function that runs the Featured-Based Tabu Search algorithm
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

    	Solution s = new Solution();
    	s.init(function, random.nextDouble()); // Initial candidate
    	best = (Solution) s.clone(); // best global solution
    	Map<Double, Integer> tabuList = new HashMap<Double, Integer>(); // the tabu list queue of tuples
        int c = 0; // timestamp counter
    	int i = 0; // iterations counter
    	do {
            c++; // timestamp increment
            removeOld(tabuList, c); // remove the old tuples of tabu list
    		Solution currSol = (Solution) s.clone(); // the current solution
    	    currSol.tweak(function, tabuList, random); // tweak the current solution with tabu list
            tabuList.put(currSol.getFitness(), c); // add the feature changed
    		for(int j = 0; j < numTweaks; j++) { // run the predefined number of tweaks
             	Solution ajustSol = (Solution) s.clone(); // clone the current best solution to ajust
             	ajustSol.tweak(function, tabuList, random); // tweak the solution to adjust with tabu list
             	if(ajustSol.getFitness() < currSol.getFitness()) { // if tabu list not contanins que ajust solution and the ajust solution is better than current solution or tabu list contains the current solution
             		currSol = (Solution) ajustSol.clone(); // replace the current best solution
                    tabuList.put(currSol.getFitness(), c); // add the feature changed
             	}
            }
            s = (Solution) currSol.clone(); // replace the current best solution
            if(s.getFitness() < best.getFitness()) { // if the current best solution is better than the global best solution
                best = (Solution) s.clone(); // replace the global best solution
            }
    		i++;
    	} while ( i < iters && best.getFitness() != function.getOptimal() ); // while not exceeding the max iterations argument or not encounter the optimal value of function

    	best.setTotalIters(i); // save the total iters for this solution

    }

    /**
     * Function that removes all the old tuples of tabu list from determinated timestamp
     * @param  tuplesList  the tabu list of tuples
     * @param  timestamp   the timestamp to calculate the old ones
     */
    public void removeOld(Map tuplesList, int timestamp) {
        Iterator it = tuplesList.entrySet().iterator();
        while (it.hasNext()) {
            Entry item = (Entry) it.next();
            int d = (int) item.getValue();
            if( (timestamp - d) > tabuListLenght ) {
                it.remove();
            }
        }
    }
    
}