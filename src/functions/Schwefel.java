package functions;

/**
 *
 * @author andres
 */
public class Schwefel extends Function {

    public Schwefel() {
        
    }

    public double run(double[] vals) {

    	double total = 0;
        for(int i = 0; i < vals.length; i++) {
            double sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += vals[j];
            }
            total += Math.pow(sum, 2);
        }
    	return total;

    }
    
}