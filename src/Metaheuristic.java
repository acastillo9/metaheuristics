import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import utils.Algorithms;
import utils.Functions;
import utils.Util;
import utils.Solution;
import functions.Function;
import functions.Sphere;
import functions.Step;
import functions.Schwefel;
import functions.Rastrigin;
import functions.Griewank;
import functions.Ackley;
import algorithms.Algorithm;
import algorithms.HillClimbing;
import algorithms.HillClimbingRandomRestarts;
import algorithms.SteepestAscentHillClimbing;
import algorithms.SteepestAscentHillClimbingReplacement;
import algorithms.RandomSearch;
import algorithms.SimulatedAnnealing;
import algorithms.TabuSearch;
import algorithms.FeatureBasedTabuSearch;
import algorithms.IteratedLocalSearch;
import algorithms.Genetic;

/**
 * Class that controls the execution of a metaheuristic method
 * @author andres
 */
public class Metaheuristic {

    private Algorithm algorithm; // Algorithm tobe used in the metaheuristic method
    private int times; // number of times that the algorithm runs, 30 per default
    private long seed; // seed of ramdom object to aleatority, current system nano time per default
    private List<Solution> solutions; // list of solutions in all execution times

    public Metaheuristic() {
    	algorithm = null;
        times = 30;
    	seed = System.nanoTime();
        solutions = new ArrayList<>();
    }

    public Metaheuristic(Map<String, Object> params) {

        System.out.println("\n* METAHEURISTIC *");

        // Algorithm
        Algorithms alg = (Algorithms) params.get("algorithm");
        System.out.println("- Algorithm: "+alg);
        switch (alg) {
            case HC:
                algorithm = new HillClimbing();

                break;
            case HCRR:
                algorithm = new HillClimbingRandomRestarts();

                break;
            case SAHC:
                algorithm = new SteepestAscentHillClimbing();

                // Number of tweaks
                if (params.containsKey("numberTweaks")) {
                    ((SteepestAscentHillClimbing)algorithm).setNumTweaks((int)params.get("numberTweaks"));
                }
                System.out.println("  -- Number of tweaks: "+((SteepestAscentHillClimbing)algorithm).getNumTweaks());

                break;
            case SAHCR:
                algorithm = new SteepestAscentHillClimbingReplacement();

                // Number of tweaks
                if (params.containsKey("numberTweaks")) {
                    ((SteepestAscentHillClimbingReplacement)algorithm).setNumTweaks((int)params.get("numberTweaks"));
                }
                System.out.println("  -- Number of tweaks: "+((SteepestAscentHillClimbingReplacement)algorithm).getNumTweaks());

                break;
            case RS:
                algorithm = new RandomSearch();

                break;
            case SA:
                algorithm = new SimulatedAnnealing();

                // Temperature
                if (params.containsKey("temperature")) {
                    ((SimulatedAnnealing)algorithm).setTemp((double)params.get("temperature"));
                }
                System.out.println("  -- Temperature: "+((SimulatedAnnealing)algorithm).getTemp());

                break;
            case TS:
                algorithm = new TabuSearch();

                // Number of tweaks
                if (params.containsKey("numberTweaks")) {
                    ((TabuSearch)algorithm).setNumTweaks((int)params.get("numberTweaks"));
                }
                System.out.println("  -- Number of tweaks: "+((TabuSearch)algorithm).getNumTweaks());

                // Tabu List Length
                if (params.containsKey("tabuListLength")) {
                    ((TabuSearch)algorithm).setTabuListLenght((int)params.get("tabuListLength"));
                }
                System.out.println("  -- Tabu List Length: "+((TabuSearch)algorithm).getTabuListLenght());

                break;
            case FBTS:
                algorithm = new FeatureBasedTabuSearch();

                // Number of tweaks
                if (params.containsKey("numberTweaks")) {
                    ((FeatureBasedTabuSearch)algorithm).setNumTweaks((int)params.get("numberTweaks"));
                }
                System.out.println("  -- Number of tweaks: "+((FeatureBasedTabuSearch)algorithm).getNumTweaks());

                // Tabu List Length
                if (params.containsKey("tabuListLength")) {
                    ((FeatureBasedTabuSearch)algorithm).setTabuListLenght((int)params.get("tabuListLength"));
                }
                System.out.println("  -- Tabu List Length: "+((FeatureBasedTabuSearch)algorithm).getTabuListLenght());

                break;
            case ILS:
                algorithm = new IteratedLocalSearch();

                break;
            case G:
                algorithm = new Genetic();

                // Population Size
                if (params.containsKey("populationSize")) {
                    ((Genetic)algorithm).getPopulation().setSize((int)params.get("populationSize"));
                }
                System.out.println("  -- Population Size: "+((Genetic)algorithm).getPopulation().getSize());

                break;
            default:
                algorithm = null;        
        }

        // Iterations
        if (params.containsKey("iterations")) {
            algorithm.setIters((int)params.get("iterations"));
        }
        System.out.println("  -- Iterations: "+algorithm.getIters());

        // Function
        Functions fun = (Functions) params.get("function");
        System.out.println("- Function: "+fun);
        switch (fun) {
            case SP:
                
                algorithm.setFunction(new Sphere());

                break;
            case ST:
                
                algorithm.setFunction(new Step());

                break;
            case SC:
                
                algorithm.setFunction(new Schwefel());

                break;
            case RT:
                
                algorithm.setFunction(new Rastrigin());

                break;
            case GR:
                
                algorithm.setFunction(new Griewank());

                break;
            case AC:
                
                algorithm.setFunction(new Ackley());

                break;
            default:
                algorithm.setFunction(null);
        }

        // Lower Limit
        if (params.containsKey("lowerLimit")) {
            algorithm.getFunction().setLowLim((double)params.get("lowerLimit"));
        }
        System.out.println("  -- Lower Limit: "+algorithm.getFunction().getLowLim());

        // Upper Limit
        if (params.containsKey("upperLimit")) {
            algorithm.getFunction().setUppLim((double)params.get("upperLimit"));
        }
        System.out.println("  -- Upper Limit: "+algorithm.getFunction().getUppLim());

        // Optimal Value
        if (params.containsKey("optimalValue")) {
            algorithm.getFunction().setOptimal((double)params.get("optimalValue"));
        }
        System.out.println("  -- Optimal Value: "+algorithm.getFunction().getOptimal());

        // Search Radio
        if (params.containsKey("searchRadio")) {
            Util.searchRadio = (double)params.get("searchRadio");
        }
        System.out.println("- Search Radio: "+Util.searchRadio);

        // Dimensions
        if (params.containsKey("dimensions")) {
            Util.dimens = (int)params.get("dimensions");
        }
        System.out.println("- Dimensions: "+Util.dimens);

        // Times
        times = 30;
        if (params.containsKey("times")) {
            times = (int)params.get("times");
        }
        System.out.println("- Times: "+times);

        // Seed
        seed = System.nanoTime();
        if (params.containsKey("seed")) {
            seed = (long)params.get("seed");
        }
        System.out.println("- Seed: "+seed);     
        
        // Solutions set
        solutions = new ArrayList<>();
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getTimes() {
    	return times;
    }
    public void setTimes(int times) {
    	this.times = times;
    }

    public long getSeed() {
        return seed;
    }
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * Function that runs the metaheuristic method
     */
    public void run() {

        Random random = new Random(seed); // initilice the random object to aleatority
    	for (int i = 0; i < times; i++) { // times that algorithm runs
            algorithm.run(random); // run the algorithm
            solutions.add(algorithm.getBest()); // add the current solution to the solutions space
            algorithm.setBest(new Solution()); // reset the solution for others runs
    	}

    }

    /**
     * Returns the best solution in solutions space
     * @return  the best solution in solutions array
     */
    public Solution best() {
        if (solutions.size() > 0) {
            Solution best = solutions.get(0);
            for(Solution sol:solutions) {
                if(best.getFitness() > sol.getFitness()) {
                    best = (Solution) sol.clone();
                }
            }
            return best;
        } else {
            return null;
        }
    }

    /**
     * Returns the worst solution in solutions space
     * @return  the worst solution in solutions array
     */
    public Solution worst() {
        if (solutions.size() > 0) {
            Solution worst = solutions.get(0);
            for(Solution sol:solutions) {
                if(worst.getFitness() < sol.getFitness()) {
                    worst = (Solution) sol.clone();
                }
            }
            return worst;
        } else {
            return null;
        }
    }

    /**
     * Returns the average fitness for solutions
     * @return  the average for all solutions
     */
    public double average() {
        double average = 0;
        for(Solution sol:solutions) {
            average += sol.getFitness();
        }
        return average / times;
    }

}