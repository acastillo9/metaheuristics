import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import utils.Algorithms;
import utils.Functions;

/**
 *
 * @author andres
 */
public class Main {

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
	    		" -a      It indicates the algorithm to use.                \n"+
	    		"         Values: [hc (hill climbing),                      \n"+
	    		"         hcrr (hill climbing with random restarts),        \n"+
	    		"         sahc (steepest ascent hill climbing),             \n"+
	    		"         sahcr (steepest ascent hill climbing with         \n"+
	    		"         replacement), rs (random search),                 \n"+
				"         sa (simulated annealing), ts (tabu search),       \n"+
				"         fbts (feature based tabu search), g (genetic)]    \n"+
	    		"         Required                                          \n"+
	    		" -i      Indicates the number of iterations for the        \n"+
	    		"         algorithm, stopping criterion.                    \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 1000                          \n"+
	    		" -nt     Indicates the number of tweaks for ajust the      \n"+
	    		"         solution in some algorithms                       \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 10                            \n"+
	    		" -tl     Indicates the lenght of tabu search list in       \n"+
	    		"         related algorithms                                \n"+
	    		"         Value: Integer                                    \n"+
	    		"         Optional - Default: 10                            \n"+
	    		" -tp     Indicates the temperature of fragmentation in     \n"+
	    		"         simulated annealing related algorithms            \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: 0.8                           \n"+
	    		" -f      It indicates the function that is applied the     \n"+
	    		"         heuristics.                                       \n"+
	    		"         Values: [sp (sphere), st (step), sc (schwefel),   \n"+
	    		"         rt (rastrigin), gr (griewank), ac (ackley)]       \n"+
	    		"         Required                                          \n"+
	    		" -ll     Indicates the lower limint for the function.      \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: -100                          \n"+
	    		" -ul     Indicates the upper limint for the function.      \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: 100                           \n"+
	    		" -o      Indicates the optimal value for the function.     \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: 0                             \n"+
	    		" -sr     Indicates the search radio for the algorithm.     \n"+
	    		"         Value: Double                                     \n"+
	    		"         Optional - Default: 0.6                           \n"+
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

	    // Recolector of initialization parameters
	    Map<String, Object> params = new HashMap<>();

	    // Algorithm
	    String a;
	    if ( (a = optsMap.get("-a")) != null ) {

	    	try {
	    		params.put("algorithm", Algorithms.valueOf(a.toUpperCase()));
	    	} catch (Exception e) {
	    		throw new IllegalArgumentException("Not a valid algorithm: "+a);
	    	}

	    	// Number of tweaks
	    	String numTweaks;
			if( (numTweaks = optsMap.get("-nt")) != null ) {
				try {
					params.put("numberTweaks", Integer.parseInt(numTweaks));
				} catch (Exception e) {
					throw new NumberFormatException("Expected an integer value for flag -nt (number tweaks)");
				}
			}

			// Temperature
			String temp;
			if( (temp = optsMap.get("-tp")) != null ) {
				try {
					params.put("temperature", Double.parseDouble(temp));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double value for flag -tp (temperature)");
				}
			}

			// Tabu list length
			String tabuListLength;
			if( (tabuListLength = optsMap.get("-tl")) != null ) {
				try {
					params.put("tabuListLength", Integer.parseInt(tabuListLength));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double value for flag -tl (tabu list lenght)");
				}
			}

			// Population Size
			String popSize;
			if( (popSize = optsMap.get("-p")) != null ) {
				try {
					params.put("populationSize", Integer.parseInt(popSize));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double value for flag -p (population size)");
				}
			}

			// Iterations
			String iters;
			if( (iters = optsMap.get("-i")) != null ) {
				try {
					params.put("iterations", Integer.parseInt(iters));
				} catch (Exception e) {
					throw new NumberFormatException("Expected an integer number for flag -i (iterations)");
				}
			}

	    } else {
	    	throw new IllegalArgumentException("You must pass the algorithm name to be used after the flag -a");
	    }

	   	// Function
	    String fun;
		if( (fun = optsMap.get("-f")) != null ) {

			Functions function;
			try {
				params.put("function", Functions.valueOf(fun.toUpperCase()));
			} catch (Exception e) {
				throw new IllegalArgumentException("Not a valid function: "+fun);
			}

			// Lower Limit
			String lowLim;
			if( (lowLim = optsMap.get("-ll")) != null ) {
				try {
					params.put("lowerLimit", Double.parseDouble(lowLim));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double number for flag -ll (lower limit)");
				}
			}

			// Upper Limit
			String uppLim;
			if( (uppLim = optsMap.get("-ul")) != null ) {
				try {
					params.put("upperLimit", Double.parseDouble(uppLim));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double number for flag -ul (upper limit)");
				}
			}

			// Optimal Value
			String optVal;
			if( (optVal = optsMap.get("-o")) != null ) {
				try {
					params.put("optimalValue", Double.parseDouble(optVal));
				} catch (Exception e) {
					throw new NumberFormatException("Expected a double number for flag -o (optimal value)");
				}
			}
			
		} else {
			throw new IllegalArgumentException("You must pass the function name to be used after the flag -f");		
		}

	    // Search Radio
	    String searchRadio;
		if( (searchRadio = optsMap.get("-sr")) != null ) {
			try {
				params.put("searchRadio", Double.parseDouble(searchRadio));
			} catch (Exception e) {
				throw new NumberFormatException("Expected an double value for flag -sr (search radio)");
			}
		}

		// Dimensions
		String dimens;
		if( (dimens = optsMap.get("-d")) != null ) {
			try {
				params.put("dimensions", Integer.parseInt(dimens));
			} catch (Exception e) {
				throw new NumberFormatException("Expected an integer number for flag -d (dimensions)");
			}
		}

		// Times
	    String t;
	    if ( (t = optsMap.get("-t")) != null ) {
	    	try {
	    		params.put("times", Integer.parseInt(t));
			} catch (Exception e) {
				throw new NumberFormatException("Expected an integer number for flag -t (times)");
			}
	    }

	   	// Seed
	    String s;
	    if ( (s = optsMap.get("-s")) != null ) {
	    	try {
	    		params.put("seed", Long.parseLong(s));
			} catch (Exception e) {
				throw new NumberFormatException("Expected an integer long number for flag -s (seed)");
			}
	    }

	    // Metaheuristic object that defines the method
	    Metaheuristic mh = new Metaheuristic(params);
		mh.run(); // run the metaheuristic
		
		// Results
		System.out.println("\nBest: "+mh.best());
		System.out.println("Worst: "+mh.worst());
		System.out.println("Average: "+mh.average());
	}
}