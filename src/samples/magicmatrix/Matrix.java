package samples;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import utils.Util;

/**
 *
 * @author andres
 */
public class Matrix {

	private int[][] matrix;
	private int n;
    private int max;
    private int min;
    private List<Point> tweakPoints;
    //private int tweakIter;

    public Matrix(int n) {
    	matrix = new int[n][n];
        this.n = n;
        min = 1;
        max = (int) Math.pow(n, 2);
        tweakPoints = new ArrayList<>();
        //tweakIter = 0;
    }

    public Matrix(int n, int[][] matrix) {
        this.matrix = matrix;
        this.n = n;
        min = 1;
        max = (int) Math.pow(n, 2);
        tweakPoints = new ArrayList<>();
        //tweakIter = 0;
    }

    public int getN() {
        return n;
    }

    /*public void restart() {
        tweakPoints = new ArrayList<>();
        tweakIter = 0;
    }*/

    public int[][] getMatrix() {
        int[][] auxMat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                auxMat[i][j] = matrix[i][j];
            }   
        }
        return auxMat;
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public void init(Random rand) {
        Set<Integer> genVals = genVals(max, max, rand);
        int i = 0, j = 0;
        for (Iterator<Integer> it = genVals.iterator(); it.hasNext(); ) {
            Integer val = it.next();
            matrix[i][j] = val;
            j++;
            if(j == n) {
                i++;
                j = 0;
            }
        }
        //System.out.println(genVals.toString());
    }

    public double deviation() {  	
        double sd = Util.standardDeviation(magicSums());
    	return sd;
    }

    public Integer[] magicSums() {
        Integer[] rowVals = new Integer[n];
        Integer[] colVals = new Integer[n];
        Integer[] diags = new Integer[2];
        int d1 = 0;
        int d2 = 0;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            rowVals[i] = sumRow(i);
            colVals[i] = sumColumn(i);
            d1 += matrix[i][i];
            d2 += matrix[i][j];
        }

        diags[0] = (Integer) d1;
        diags[1] = (Integer) d2;

        Stream<Integer> stream1 = Stream.of(rowVals);
        Stream<Integer> stream2 = Stream.of(colVals);
        Stream<Integer> stream3 = Stream.of(diags);
         
        Stream<Integer> stream = Stream.concat(Stream.concat(stream1, stream2), stream3);
        Integer[] finArr = stream.toArray(Integer[]::new);
        return finArr;
    }

    public int sumRow(int r) {
    	return Arrays.stream(matrix[r]).sum();
    }

    public int sumColumn(int c) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(j == c) {
                    sum += matrix[i][j];
                }
            }
        }
        return sum;
    }

    public Set<Integer> genVals(int num, int maxRand, Random rand) {
        Set<Integer> generated = new LinkedHashSet<>();
        while (generated.size() < num) {
            Integer next = rand.nextInt(maxRand) + 1;
            generated.add(next);
        }
        return generated;
    }

    public String toString() {
        String sMatrix = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sMatrix += matrix[i][j]+" ";
            }
            sMatrix += "\n";
        }
        return sMatrix;
    }

    public void tweak(Random rand) {
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        
        do {
            x1 = rand.nextInt(n);
            y1 = rand.nextInt(n);
            x2 = rand.nextInt(n);
            y2 = rand.nextInt(n);
        } while( x1 == x2 && y1 == y2 );

        //System.out.println("\nx1: "+x1+" - y1: "+y1);
        //System.out.println("x2: "+x2+" - y2: "+y2+"\n");

        int auxVal = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = auxVal;
    }

    public void tweak(Point point, List<Point> history, Random rand) {

        //System.out.println("llega: "+point.toString()+" val: "+matrix[point.getX()][point.getY()]);
        
        Point nPoint = new Point();
        do {
            nPoint.setX(rand.nextInt(n));
            nPoint.setY(rand.nextInt(n));
            //System.out.println(history.contains(matrix[nPoint.getX()][nPoint.getY()]));
        } while( nPoint.equals(point) || history.contains(nPoint) );

        //System.out.println("elige: "+nPoint.toString()+" val: "+matrix[nPoint.getX()][nPoint.getY()]);

        history.add(nPoint);

        int auxVal = matrix[point.getX()][point.getY()];
        matrix[point.getX()][point.getY()] = matrix[nPoint.getX()][nPoint.getY()];
        matrix[nPoint.getX()][nPoint.getY()] = auxVal;

        //System.out.println(history.toString());
    }

}