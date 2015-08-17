package functions;

import entities.Function;

/**
 *
 * @author andres
 */
public class Sphere extends Function {

    private int dimens;

    public Sphere() {
        dimens = 1;
    }

    public Sphere(int dimens) {
        this.dimens = dimens;
    }

    public int getDimens() {
        return dimens;
    }
    public void setDimens(int dimens) {
        this.dimens = dimens;
    }

    public double run(double[] vals) {
        double total = 0;
        for(double val:vals) {
            total += val*val;
        }
        return total;
    }
}