package entities;

import java.util.Random;

/**
 *
 * @author andres
 */
public abstract class Algorithm {
    
	public abstract double run(Function function);
	public abstract void setRandom(Random random);

}