package functions;

/**
 * class that implements the Sphere function
 * @author andres
 */
public class Sphere extends Function {

	/**
	 * Function that run the function Sphere operation from point in space
	 * @param  vals  an array of values, one for each dimension
	 * @return       the value for function evaluates in point received
	 */
    public double run(double[] vals) {
        double total = 0;
        for(double val:vals) {
            total += Math.pow(val, 2);
        }
        return total;
    }
}