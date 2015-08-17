import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import utils.Algorithms;
import utils.Functions;
import utils.Types;
import functions.Sphere;
import localsearch.HillClimbing;
import entities.LocalSearch;
import entities.Algorithm;
import entities.Function;

/**
 *
 * @author andres
 */
public class MetaHeuristics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    	List<String> argsList = new ArrayList<String>();  
    	Map<String, String> optsMap = new HashMap<String, String>();
    	List<String> doubleOptsList = new ArrayList<String>();

	    for (int i = 0; i < args.length; i++) {
	        switch (args[i].charAt(0)) {
	        case '-':
	        	int argLength = args[i].length();
	            if (argLength < 2)
	                throw new IllegalArgumentException("Not a valid argument: "+args[i]);
	            if (args[i].charAt(1) == '-') {
	                if (argLength < 3)
	                    throw new IllegalArgumentException("Not a valid argument: "+args[i]);
	                // --opt
	                doubleOptsList.add(args[i].substring(2, argLength));
	            } else {
	                if (args.length - 1 == i)
	                    throw new IllegalArgumentException("Expected arg after: "+args[i]);
	                // -opt
	                optsMap.put(args[i], args[i+1]);
	                i++;
	            }
	            break;
	        default:
	            // arg
	            argsList.add(args[i]);
	            break;
	        }
	    }

	    if (doubleOptsList.contains("help")) {
	    	System.out.println(
	    		"\n ******************* METAHEURISTICS ******************** \n"+
	    		"\n This program implements meta heuristics from            \n"+
	    		" preconfigured algorithms and functions that can be mixed  \n"+
	    		" together.                                                 \n"+
	    		"\n USAGE: ./run.sh '[OPTIONS]...'                          \n"+
	    		"\n OPTIONS:                                                \n"+
	    		" -ty     It indicates the type of meta heuristics to use.  \n"+
	    		"         Values: [localsearch]                             \n"+
	    		"         Required                                          \n"+
	    		" -alg    It indicates the algorithm to use.                \n"+
	    		"         Values: [hillclimbing]                            \n"+
	    		"         Required                                          \n"+
	    		" -i      Indicates the number of iterations for the        \n"+
	    		"         algorithm, stopping criterion.                    \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 1000                          \n"+
	    		" -ll     Indicates the lower limint for the algorithm.     \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: -100                          \n"+
	    		" -ul     Indicates the upper limint for the algorithm.     \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 100                           \n"+
	    		" -sr     Indicates the search radio for the algorithm.     \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: 0.6                           \n"+
	    		" -fun    It indicates the function that is applied the     \n"+
	    		"         heuristics.                                       \n"+
	    		"         Values: [sphere]                                  \n"+
	    		"         Required                                          \n"+
	    		" -d      Indicates the number of dimensions of the         \n"+
	    		"         function.                                         \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 1                             \n"+
	    		" -t      It indicates the times that will be applied the   \n"+
	    		"         metaheuristic.                                    \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 1                             \n"+
	    		" -s      It indicates the seed for randoms values.         \n"+
	    		"         Value: Long                                       \n"+
	    		"         Optional - Default: 12345                         \n"+
	    		" --help  More Help                                         \n"
	    	);
	    	return;
	    }

	    Random random;
	    String ty;
	    if ( (ty = optsMap.get("-ty")) != null ) {
	    	
	    	Types type;
	    	try {
	    		type = Types.valueOf(ty.toUpperCase());
	    	} catch (Exception e) {
	    		throw new IllegalArgumentException("Not a valid type: "+ty);
	    	}

	    	switch (type) {
	    		case LOCALSEARCH:
	    			System.out.println("\n- Type: "+type);

	    			LocalSearch ls = new LocalSearch();

	    			// Algorithm
				    String alg;
				    if ( (alg = optsMap.get("-alg")) != null ) {

				    	Algorithms algorithm;
				    	try {
				    		algorithm = Algorithms.valueOf(alg.toUpperCase());
				    	} catch (Exception e) {
				    		throw new IllegalArgumentException("Not a valid algorithm: "+alg);
				    	}

				    	switch (algorithm) {
				    		case HILLCLIMBING:
				    			System.out.println("  -- Algorithm: "+algorithm);

				    			Algorithm hc = new HillClimbing();

				    			String iters;
								if( (iters = optsMap.get("-i")) != null ) {
									try {
										((HillClimbing)hc).setIters(Integer.parseInt(iters));
									} catch (Exception e) {
										throw new NumberFormatException("Expected an integer number for flag -i (iterations)");
									}
								}
								System.out.println("    --- Iterations: "+((HillClimbing)hc).getIters());

								String lowLim;
								if( (lowLim = optsMap.get("-ll")) != null ) {
									try {
										((HillClimbing)hc).setLowLim(Integer.parseInt(lowLim));
									} catch (Exception e) {
										throw new NumberFormatException("Expected an integer number for flag -ll (lower limit)");
									}
								}
								System.out.println("    --- Lower Limit: "+((HillClimbing)hc).getLowLim());

								String uppLim;
								if( (uppLim = optsMap.get("-ul")) != null ) {
									try {
										((HillClimbing)hc).setUppLim(Integer.parseInt(uppLim));
									} catch (Exception e) {
										throw new NumberFormatException("Expected an integer number for flag -ul (upper limit)");
									}
								}
								System.out.println("    --- Upper Limit: "+((HillClimbing)hc).getUppLim());

								String searchRadio;
								if( (searchRadio = optsMap.get("-sr")) != null ) {
									try {
										((HillClimbing)hc).setSearchRadio(Double.parseDouble(searchRadio));
									} catch (Exception e) {
										throw new NumberFormatException("Expected an double value for flag -sr (search radio)");
									}
								}
								System.out.println("    --- Search Radio: "+((HillClimbing)hc).getSearchRadio());

								ls.setAlgorithm(hc);

					    		break;
					    	default:
					    		throw new IllegalArgumentException("Not a valid algorithm: "+alg);
				    	}
				    } else {
				    	throw new IllegalArgumentException("You must pass the algorithm name to be used after the flag -alg");
				    }

				    // Function
				    String fun;
					if( (fun = optsMap.get("-fun")) != null ) {

						Functions function;
						try {
							function = Functions.valueOf(fun.toUpperCase());
						} catch (Exception e) {
							throw new IllegalArgumentException("Not a valid function: "+fun);
						}

						switch (function) {
							case SPHERE:
								System.out.println("  -- Function: "+function);

								Function sp = new Sphere();

								String dimens;
								if( (dimens = optsMap.get("-d")) != null ) {
									try {
										((Sphere)sp).setDimens(Integer.parseInt(dimens));
									} catch (Exception e) {
										throw new NumberFormatException("Expected an integer number for flag -d (dimensions)");
									}
								}
								System.out.println("    --- Dimensions: "+((Sphere)sp).getDimens());

								ls.setFunction(sp);

								break;
							default:
								throw new IllegalArgumentException("Not a valid function: "+fun);
						}
					} else {
						throw new IllegalArgumentException("You must pass the function name to be used after the flag -fun");		
					}

					// Times
				    int times = 1;
				    String t;
				    if ( (t = optsMap.get("-t")) != null ) {
				    	try {
							times = Integer.parseInt(t);
						} catch (Exception e) {
							throw new NumberFormatException("Expected an integer number for flag -t (times)");
						}
				    }
				    System.out.println("  -- Times: "+times);

				    ls.setTimes(times);

				    // Seed
				    long seed = 12345;
				    String s;
				    if ( (s = optsMap.get("-s")) != null ) {
				    	try {
							seed = Long.parseLong(s);
						} catch (Exception e) {
							throw new NumberFormatException("Expected an integer long number for flag -s (seed)");
						}
				    }
				    System.out.println("  -- Seed: "+seed);

				    random = new Random(seed);
				    ls.setRandom(random);

				    double solution = ls.run();

				    System.out.println("\nSolution: "+solution);

		    		break;
		    	default:
		    		throw new IllegalArgumentException("Not a valid type: "+ty);
	    	}
	    } else {
	    	throw new IllegalArgumentException("You must pass the type of algorithm to be used after the flag -ty");
	    }
        
    }
    
}