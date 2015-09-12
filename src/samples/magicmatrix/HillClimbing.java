package samples;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author andres
 */
public class HillClimbing {

    private List<Integer> historicVals;
    private List<Point> historicPoints;

    public HillClimbing() {
        historicVals = new ArrayList<>();
        historicPoints = new ArrayList<>();
    }

    public double run(Matrix matrix, Random rand) {
        matrix.init(rand);
        double currVal = matrix.deviation();
        int i = 0;
        do {
        	Matrix auxMat = new Matrix(matrix.getN(), matrix.getMatrix());
        	auxMat.tweak(rand);
        	double auxVal = auxMat.deviation();
        	if( currVal > auxVal ) {
        		currVal = auxVal;
        		matrix.setMatrix(auxMat.getMatrix());
        	}
        	i++;
            System.out.println(currVal);
        } while( currVal > 0 );

        System.out.println("Iterations: "+i);

        return currVal;
    }

    public double runTwo(Matrix matrix, Random rand) {
        matrix.init(rand);
        double currVal = matrix.deviation();
        int i = 0;
        do {
            Matrix auxMat = new Matrix(matrix.getN(), matrix.getMatrix());
            bestNeighbour(auxMat, rand);
            //auxMat.tweak(rand);
            double auxVal = auxMat.deviation();
            if( currVal > auxVal ) {
                currVal = auxVal;
                matrix.setMatrix(auxMat.getMatrix());
            }
            i++;
            System.out.println(currVal);
        } while( currVal > 0 );

        System.out.println("Iterations: "+i);

        return currVal;
    }

    public void bestNeighbour(Matrix matrix, Random rand) {
        int len = matrix.getMatrix().length;
        Point p = new Point();
        double currVal = matrix.deviation();
        //Matrix auxMat = new Matrix(matrix.getN(), matrix.getMatrix());
        //double auxVal = -1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                p.setX(i);
                p.setY(j);
                //historicVals.add(matrix.getMatrix()[i][j]);
                //historicPoints.add(p);
                List<Point> hisP = new ArrayList<>();
                Matrix newMat = new Matrix(matrix.getN(), matrix.getMatrix());
                for (int k = 0; k < (len*len)-1; k++) {
                    newMat.tweak(p, hisP, rand);
                    double newVal = newMat.deviation();
                    if(newVal < currVal) {
                        currVal = newVal;
                        matrix.setMatrix(newMat.getMatrix());
                    }   
                    newMat = new Matrix(matrix.getN(), matrix.getMatrix());
                }

            }            
        }
    }
    
}