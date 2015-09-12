package utils;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import functions.Function;

/**
 * Class that implements a population structure
 * @author andres
 */
public class Population {

  	private int size; // Size of population, 100 per default
  	private List<Solution> solutions; // LIst of solutions in population

  	public Population() {
  		size = 100;
  		solutions = new ArrayList<>();
  	}

  	public Population(int size) {
  		this.size = size;
  		solutions = new ArrayList<>();
  	}

  	public int getSize() {
  		return size;
  	}
  	public void setSize(int size) {
  		this.size = size;
  	}

  	public List<Solution> getSolutions() {
  		return solutions;
  	}
  	public void setSolutions(List<Solution> solutions) {
  		this.solutions = solutions;
  	}

  	/**
  	 * Function that initialice the population with random solutions
  	 * @param  function  the function to initialice the solutions
  	 * @param  random    a random object to aletority
  	 */
  	public void init(Function function, Random random) {
  		solutions = new ArrayList<>();
  		for(int i = 0; i < size; i++) {
  			Solution sol = new Solution();
  			sol.init(function, random.nextDouble());
  			solutions.add(sol);
  		}
  	}

  	/**
  	 * Function that returns the best solution in the popuation
  	 * @return  the best solution in solutions set
  	 */
  	public Solution best() {
  		if (solutions.size() > 0) {
  			Solution best = solutions.get(0);
  			for(Solution sol: solutions) {
  				if(best.getFitness() > sol.getFitness()) {
                	best = (Solution) sol.clone();
                }
  			}
  			return best;
  		} else {
  			return null;
  		}
  	}

  	@Override
    public String toString() {
    	String retString = "Population: Size: "+size+", Solutions: \n";
    	for(Solution sol: solutions) {
    		retString += "  "+sol.toString()+"\n";
    	}
        return retString;
    }
    
}