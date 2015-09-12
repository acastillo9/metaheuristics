package functions;

/**
 *
 * @author andres
 */
public class Step extends Function {

    public Step() {
        
    }

    public double run(double[] vals) {

    	double total = 0;
    	for(double val:vals) {
    		double floor = Math.floor(val + 0.5);
            total += Math.pow(floor, 2);
    	}
    	return total;

    }
    
}