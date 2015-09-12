package utils;

import java.util.Random;

/**
 *
 * @author andres
 */
public class Util {

    public static double searchRadio = 0.6; // search radio used for regular tweak, 0.6 per default
    public static int dimens = 2; // the number of dimensions of vector solution, 1 per default

    public static double doubleRandomInRange(double min, double max, Random rand) {  		
  		return (rand.nextDouble() * (max-min)) + min;
	}

	public static int intRandomInRange(int min, int max, Random rand) {
    	return rand.nextInt((max - min) + 1) + min;
	}

	public static double standardDeviation(Integer[] valueList) {
        double m = 0.0;
        double s = 0.0;
        int k = 1;
        for (int value:valueList) {
            double tmpM = m;
            m += (value - tmpM) / k;
            s += (value - tmpM) * (value - m);
            k++;
        }
        return Math.sqrt(s / (k-2));
    }
    
}