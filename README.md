# metaheuristics
This program implements meta heuristics from preconfigured algorithms and functions that can be mixed together.

## Usage
To run on linux: &nbsp;&nbsp;&nbsp;<b>./run.sh '[OPTIONS]...'</b>

## Options:
<b>-ty</b><br><br>
It indicates the type of meta heuristics to use.<br>
Values: [localsearch]<br>
Required<br><br>
<b>-alg</b><br><br> 
It indicates the algorithm to use.<br>
Values: [hillclimbing]<br>
Required<br><br>
<b>-i</b><br><br>
Indicates the number of iterations for the algorithm, stopping criterion.<br>
Value: Integer<br>
Optional - Default: 1000<br><br>
<b>-ll</b><br><br>
Indicates the lower limint for the algorithm.<br>
Value: Integer<br>
Optional - Default: -100<br><br>
<b>-ul</b><br><br>
Indicates the upper limint for the algorithm.<br>
Value: Integer<br>
Optional - Default: 100<br><br>
<b>-sr</b><br><br>
Indicates the search radio for the algorithm.<br>
Value: Double<br>
Optional - Default: 0.6<br><br>
<b>-fun</b><br><br>
It indicates the function that is applied the heuristics.<br>
Values: [sphere]<br>
Required<br><br>
<b>-d</b><br><br>
Indicates the number of dimensions of the function.<br>
Value: Integer<br>
Optional - Default: 1<br><br>
<b>-t</b><br><br>
It indicates the times that will be applied the metaheuristic.<br>
Value: Integer<br>
Optional - Default: 1<br><br>
<b>-s</b><br><br>
It indicates the seed for randoms values.<br>
Value: Long<br>
Optional - Default: 12345<br><br>
