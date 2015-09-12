package functions;

import java.util.Arrays;

/**
 *
 * @author andres
 */
public class Griewank extends Function {

    public Griewank() {
        lowLim = -600;
        uppLim = 600;
    }

    public double run(double[] vals) {

    	double total = 0;
        double a = 4000;                
        double b = 1;

        double sum = Arrays.stream(vals).map(v -> Math.pow(v, 2)).sum();

        for(int i = 0; i < vals.length; i++) {
        	b *= Math.cos(vals[i] / (Math.sqrt(i + 1)));	
        }

        total = (sum/a) - b + 1;
    	return total;

    }
    
}