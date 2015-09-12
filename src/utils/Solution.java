package utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import functions.Function;

/**
 * The Class representation for solutions of algorithms
 * @author andres
 */
public class Solution implements Cloneable {

    private double[] point; // the point in space for this solution
    private Double fitness; // the function value for this solution
    private int totalIters; // total number or iterations to reach the current solution

    public Solution() {
    	point = null;
    	fitness = null;
        totalIters = 0;
    }

    public double[] getPoint() {
        return point;
    }
    public void setPoint(double[] point) {
        this.point = point;
    }

    public Double getFitness() {
        return fitness;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getTotalIters() {
        return totalIters;
    }
    public void setTotalIters(int totalIters) {
        this.totalIters = totalIters;
    }

    /**
     * Function that initializes ramdomly the solution vector
     * @param  function  a function to evaluates the solution
     * @param  ran       random number between 0 and 1
     */
    public void init(Function function, double ran) {
        int dimens = Util.dimens;
        point = new double[dimens];

        for(int i = 0; i < dimens; i++) {
            point[i] = function.getLowLim() + (ran * (function.getUppLim() - function.getLowLim()));
        }

        fitness = function.run(point);
    }

    /**
     * Function that tweaks the current solution in a range of determinated radio
     * @param  function  a function to evaluates the solution
     * @param  ran       random number between 0 and 1
     */
    public void tweak(Function function, double ran) {
        int dimens = Util.dimens;
        double searchRadio = Util.searchRadio;

        for(int i = 0; i < dimens; i++) {
            double ajuste = - searchRadio + ((2*searchRadio) * ran);
            double newVal = point[i] + ajuste;
            if( newVal > function.getLowLim() && newVal < function.getUppLim() ) {
                point[i] = newVal;
            } else {
                i--;
            }
        }

        fitness = function.run(point);
    }

    /**
     * Function that tweaks the current solution in a range of determinated radio and not in tabu list
     * finally the function adds the new feature to tabu list
     * @param  function   a function to evaluates the solution
     * @param  tabuList   a tabu list
     * @param  random     random object for aleatority
     */
    public void tweak(Function function, Map<Double, Integer> tabuList, Random random) {
        int dimens = Util.dimens;
        double searchRadio = Util.searchRadio;

        for(int i = 0; i < dimens; i++) {
            double ajuste = - searchRadio + ((2*searchRadio) * random.nextDouble());
            double newVal = point[i] + ajuste;
            while (tabuList.containsKey(newVal)) {
                ajuste = - searchRadio + ((2*searchRadio) * random.nextDouble());
                newVal = point[i] + ajuste;
            }
            if( newVal > function.getLowLim() && newVal < function.getUppLim() ) {
                point[i] = newVal;
            } else {
                i--;
            }
        }

        fitness = function.run(point);
    }

    public Object clone() {
        Solution obj = null;
        try {
            obj = (Solution) super.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println("Not clonable object");
        }
        obj.setPoint(Arrays.copyOf(obj.getPoint(), obj.getPoint().length));
        return obj;
    }

    @Override
    public String toString() {
        return "Solution: Point: "+Arrays.toString(point)+", Fitness: "+fitness+", Iterations: "+totalIters;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solution other = (Solution) obj;
        if ((this.fitness == null) ? (other.fitness != null) : !(this.fitness == other.fitness)) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Double.valueOf(this.fitness).hashCode();
        return hash;
    }
    
}