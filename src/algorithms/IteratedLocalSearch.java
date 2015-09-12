package algorithms;

import java.util.Random;
import utils.Solution;
import utils.Util;

/**
 * Class that implements the Iterated Local Search algorithm with random restarts
 * @author andres
 */
public class IteratedLocalSearch extends Algorithm {

    /**
     * Function that runs the Iterarted Local Search algorithm with random restarts
     * @param  random  a random object to aleatority
     */
    public void run(Random random) {

        Solution s = new Solution();
    	s.init(function, random.nextDouble()); // Initial candidate
        Solution h = (Solution) s.clone(); // the home solution
        best = (Solution) s.clone(); // global best solution
        int times = 0; // number of times that current hill climbing runs
        do {
        	int currTimes = Util.intRandomInRange(1, (iters - times), random); // select times from an interval of iterations            
            int i = 0;
            do {
                Solution currSol = (Solution) s.clone(); // the current best solution
                currSol.tweak(function, random.nextDouble()); // tweak the current best solution
                if (currSol.getFitness() < s.getFitness()) { // if the current solution is better than the current best solution
                    s = (Solution) currSol.clone(); // replace the current best solution
                }
                i++;
            } while (i < currTimes && s.getFitness() != function.getOptimal()); // while not exced the current times or not encounter the optimal solution

            if (s.getFitness() < best.getFitness()) { // if the solution of current hill climbing is better than global best value
                best = (Solution) s.clone(); // update the best global solution
            }

            h = newHomeBase(h, s); // select a new home base
            s = perturb(h, random); // perturb the home base and update this on s

            times += currTimes; // increment global times with current times
        } while (best.getFitness() != function.getOptimal() && times < iters); // while not encountered the optimal for function and not exceeding the max iterations

        best.setTotalIters(times); // set the total iterations used by algorithm
    }

    public Solution newHomeBase(Solution h, Solution s) {
        return s;
    }

    public Solution perturb(Solution h, Random ran) {
        Solution ps = (Solution) h.clone();
        ps.tweak(function, ran.nextDouble());
        return ps;
    }
    
}