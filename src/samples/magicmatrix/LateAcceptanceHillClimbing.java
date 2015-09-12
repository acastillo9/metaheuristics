package samples;

import java.util.Random;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 *
 * @author andres
 */
public class LateAcceptanceHillClimbing {

	private int l;

	public LateAcceptanceHillClimbing() {
		l = 10;
	}

    public double run(Matrix matrix, Random rand) {
        
        double[] solsVals = new double[l];
        matrix.init(rand); // Initial solution random
        double auxVal = matrix.deviation(); // initial value objective function

        for (int j = 0; j < l; j++) {
        	solsVals[j] = auxVal;
        }
        
        int i = 0;
        int c = -1;
        do {
        	Matrix auxMat = new Matrix(matrix.getN(), matrix.getMatrix());
        	auxMat.tweak(rand);
        	auxVal = auxMat.deviation();
        	c = i % l;
        	if( solsVals[c] > auxVal ) {
        		matrix.setMatrix(auxMat.getMatrix());
        	}
        	solsVals[c] = auxVal;
            //solsVals = addFirst(solsVals, auxVal);
            //System.out.println(auxVal);
        	i++;
        } while( solsVals[c] > 0 );

        //System.out.println(Arrays.toString(solsVals));
        System.out.println("Iterations: "+i);

        return solsVals[c];
    }

    public double[] addFirst(double[] array, double val) {
        double[] newArr = new double[array.length];
        newArr[0] = val;
        for (int i = 1; i < array.length; i++) {
            newArr[i] = array[i];
        }
        return newArr;
    }
    
}