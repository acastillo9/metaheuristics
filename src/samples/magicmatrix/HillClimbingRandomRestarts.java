package samples;

import java.util.Random;
import utils.Util;

/**
 *
 * @author andres
 */
public class HillClimbingRandomRestarts {

	private int t;

	public HillClimbingRandomRestarts() {
		t = 100000;
	}

    public double run(Matrix matrix, Random rand) {
        matrix.init(rand);
        double currVal = matrix.deviation();
        double best = currVal;
        int i = 0;
        Matrix currMat = new Matrix(matrix.getN(), matrix.getMatrix());
        int times = t;
        do {        	
        	int currTimes = Util.intRandomInRange(1, times, rand);
        	int j = 0;
        	do {
	        	Matrix auxMat = new Matrix(currMat.getN(), currMat.getMatrix());
	        	auxMat.tweak(rand);
	        	double auxVal = auxMat.deviation();
	        	if( currVal > auxVal ) {
	        		currVal = auxVal;
	        		matrix.setMatrix(auxMat.getMatrix());
	        	}
	        	currTimes--;
	        	j++;
	            //System.out.println(currVal);
	        } while( currVal > 0 && currTimes > 0);
	        times -= j;
	        i += j;
	        if(currVal < best) {
	        	best = currVal;
	        }
	        currMat.tweak(rand);
	        j = 0;
        } while( best > 0 && i < t );

        System.out.println("Iterations: "+i);

        return currVal;
    }
    
}