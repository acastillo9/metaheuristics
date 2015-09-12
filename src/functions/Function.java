package functions;

/**
 * Abstarct class that defines the functions structure
 * @author andres
 */
public abstract class Function {

	protected double lowLim; // Low limit of function, -100 per default
	protected double uppLim; // Upper limit of function, 100 per default
	protected double optimal; // Optimal value of function, 0 per default

	public Function() {
		lowLim = -100;
		uppLim = 100;
		optimal = 0;
	}

	public double getLowLim() {
		return lowLim;
	}
	public void setLowLim(double lowLim) {
		this.lowLim = lowLim;
	}

	public double getUppLim() {
		return uppLim;
	}
	public void setUppLim(double uppLim) {
		this.uppLim = uppLim;
	}

	public double getOptimal() {
		return optimal;
	}
	public void setOptimal(double optimal) {
		this.optimal = optimal;
	}

	/**
     * Abstract function that defines the run method for functions
     * @param  values  an array of values in each dimension
     */
    public abstract double run(double[] values);
    
}