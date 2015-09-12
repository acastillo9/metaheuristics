# metaheuristics
This program implements meta heuristics from preconfigured algorithms and functions that can be mixed together.

## Usage
To run on linux: &nbsp;&nbsp;&nbsp;<b>./run.sh '[OPTIONS]...'</b>

## Options:
<b>-a</b><br><br> 
It indicates the algorithm to use.<br>
Values: [hc (hill climbing), hcrr (hill climbing with random restarts), sahc (steepest ascent hill climbing), sahcr (steepest ascent hill climbing with replacement), rs (random search), sa (simulated annealing), ts (tabu search), fbts (feature based tabu search), g (genetic)]<br>
Required<br><br>
<b>-i</b><br><br>
Indicates the number of iterations for the algorithm, stopping criterion.<br>
Value: Integer<br>
Optional - Default: 1000<br><br>
<b>-nt</b><br><br>
Indicates the number of tweaks for ajust the solution in some algorithms.<br>
Value: Integer<br>
Optional - Default: 10<br><br>
<b>-tl</b><br><br>
Indicates the lenght of tabu search list in related algorithms.<br>
Value: Integer<br>
Optional - Default: 10<br><br>
<b>-tp</b><br><br>
Indicates the temperature of fragmentation in simulated annealing related algorithms.<br>
Value: Double<br>
Optional - Default: 0.8<br><br>
<b>-p</b><br><br>
Indicates the population size in population related algorithms.<br>
Value: Integer<br>
Optional - Default: 100<br>
<b>-f</b><br><br>
It indicates the function that is applied the heuristics.<br>
Values: [sp (sphere), st (step), sc (schwefel), rt (rastrigin), gr (griewank), ac (ackley)]<br>
Required<br>
<b>-ll</b><br><br>
Indicates the lower limint for the algorithm.<br>
Value: Double<br>
Optional - Default: -100<br><br>
<b>-ul</b><br><br>
Indicates the upper limint for the algorithm.<br>
Value: Double<br>
Optional - Default: 100<br><br>
<b>-o</b><br><br>
Indicates the optimal value for the function.<br>
Value: Double<br>
Optional - Default: 0<br><br>
<b>-sr</b><br><br>
Indicates the search radio for the algorithm.<br>
Value: Double<br>
Optional - Default: 0.6<br><br>
<b>-d</b><br><br>
Indicates the number of dimensions of the function.<br>
Value: Integer<br>
Optional - Default: 2<br><br>
<b>-t</b><br><br>
It indicates the times that will be applied the metaheuristic.<br>
Value: Integer<br>
Optional - Default: 30<br><br>
<b>-s</b><br><br>
It indicates the seed for randoms values.<br>
Value: Long<br>
Optional - Default: system nanotime<br><br>
<b>--help</b><br><br>
More Help<br><br>
