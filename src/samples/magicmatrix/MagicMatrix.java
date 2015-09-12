package samples;

import java.util.Random;
import samples.Matrix;

/**
 *
 * @author andres
 */
public class MagicMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\nMagic Matrix\n");

        int n = Integer.parseInt(args[0]);
        //if (n % 2 == 0) throw new RuntimeException("n must be odd");

        long seed = System.nanoTime();//638146269046L;//971703296593L;
        //System.out.println("Seed: "+seed);

    	Matrix mat = new Matrix(n);
    	Random rand = new Random(seed);

        HillClimbing hc = new HillClimbing();
        double sol = hc.runTwo(mat, rand);

        //LateAcceptanceHillClimbing lahc = new LateAcceptanceHillClimbing();
        //double sol = lahc.run(mat, rand);

        //HillClimbingRandomRestarts hcrr = new HillClimbingRandomRestarts();
        //double sol = hcrr.run(mat, rand);

        System.out.println("Solution: "+sol+"\n");
        System.out.println("Seed: "+seed);
        System.out.println(mat.toString());

    }
    
}