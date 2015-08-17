package entities;

import localsearch.HillClimbing;
import java.util.Random;

/**
 *
 * @author andres
 */
public class LocalSearch {

	private int times;
    private Algorithm algorithm;
    private Function function;
    private Random random;

    public LocalSearch() {
    	times = 0;
    	algorithm = null;
    	function = null;
    	random = new Random(12345);
    }

    public LocalSearch( int times, Algorithm algorithm, Function function, Random random ) {
    	this.times = times;
    	this.algorithm = algorithm;
    	this.function = function;
    	this.random = random;
    }

    public int getTimes() {
    	return times;
    }
    public void setTimes(int times) {
    	this.times = times;
    } 

    public Algorithm getAlgorithm() {
    	return algorithm;
    }
    public void setAlgorithm(Algorithm algorithm) {
    	this.algorithm = algorithm;
    }

    public Function getFunction() {
    	return function;
    }
    public void setFunction(Function function) {
    	this.function = function;
    }

    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }

    public double run() {

    	algorithm.setRandom(random);

    	double average = 0;

    	for (int i = 0; i < times; i++) {
    		System.out.print("\n* Value in time "+(i+1)+": ");
    		average += algorithm.run(function);
    	}

    	return average / times;
    }

}