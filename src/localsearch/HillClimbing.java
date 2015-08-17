package localsearch;

import entities.Algorithm;
import entities.Function;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author andres
 */
public class HillClimbing extends Algorithm {

	private int iters;
    private int lowLim;
    private int uppLim;
    private double searchRadio;
    private Random random;

    public HillClimbing() {
    	iters = 1000;
    	lowLim = -100;
    	uppLim = 100;
    	searchRadio = 0.6;
        random = new Random(12345);
    }

    public HillClimbing(int iters, int lowLim, int uppLim, double searchRadio, Random random) {
    	this.iters = iters;
    	this.lowLim = lowLim;
    	this.uppLim = uppLim;
    	this.searchRadio = searchRadio;
        this.random = random;
    }

    public int getIters() {
    	return iters;
    }
    public void setIters(int iters) {
    	this.iters = iters;
    }

    public int getLowLim() {
    	return lowLim;
    }
    public void setLowLim(int lowLim) {
    	this.lowLim = lowLim;
    }

    public int getUppLim() {
    	return uppLim;
    }
    public void setUppLim(int uppLim) {
    	this.uppLim = uppLim;
    }

    public double getSearchRadio() {
    	return searchRadio;
    }
    public void setSearchRadio(double searchRadio) {
    	this.searchRadio = searchRadio;
    }

    public Random getRandom() {
        return random;
    }
    public void setRandom(Random random) {
        this.random = random;
    }

    public double run(Function function) {

        int dimens = function.getDimens(); // Número de dimensiones
        double[] s = init(dimens); // Candidato a solucion inicial
        
        for(int i = 0; i < iters; i++) {
            double[] R = tweak(s);
            if (function.run(s) > function.run(R)) {               
                s = Arrays.copyOf(R, R.length);
            }
        }

        double finVal = function.run(s);
        System.out.println(finVal+" *");

        System.out.print("    - Point: f( ");
        boolean first = true;
        for(double x:s) {
            if (!first) {
                System.out.print(", "+x);
            } else {
                System.out.print(x);
                first = false;
            }
        }
        System.out.println(" )");

        return finVal;
    }

    public double[] init(int dimens) {
        double[] array = new double[dimens];

        for(int i = 0; i<dimens; i++) {
            double ran = random.nextDouble();
            array[i] = lowLim + (ran * (uppLim - lowLim));
        }
        
        return array;
    }
    
    public double[] tweak(double[] s) {
        
        double[] array = new double[s.length];
        
        for(int i=0; i<s.length; i++) {
            double ajuste = -searchRadio + ((2*searchRadio) * random.nextDouble());
            array[i] = s[i] + ajuste;
        }
        
        return array;
    }
    
}