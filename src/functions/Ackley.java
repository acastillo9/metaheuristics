package functions;

/**
 *
 * @author andres
 */
public class Ackley extends Function {

    public Ackley() {
        lowLim = -32;
        uppLim = 32;
    }

    public double run(double[] vals) {

    	double total = 0;
        double a = 20;
        double b = 0.2;
        double c = 2 * Math.PI;
        double x = 0;
        double y = 0;

        for (double val:vals) {
        	x += Math.pow(val, 2);
            y += Math.cos(c * val);
        }

        int valDimens = vals.length;
        total = -a * Math.exp(-b * Math.sqrt(x / valDimens)) - Math.exp(y / valDimens) + a + Math.exp(1);
    	return total;

    }
    
}