package functions;

/**
 *
 * @author andres
 */
public class Rastrigin extends Function {

    public Rastrigin() {
        lowLim = -5.12;
        uppLim = 5.12;
    }

    public double run(double[] vals) {

    	double total = 0;
        for (double val:vals) {
            total += Math.pow(val, 2) - 10 * Math.cos(2 * Math.PI * val) + 10;
        }
    	return total;

    }
    
}