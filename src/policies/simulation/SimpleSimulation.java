package policies.simulation;

import policies.Simulation;
import elements.Skeleton;
import grammar.DerivationTree;
import grammar.Grammar;

public class SimpleSimulation extends Simulation {

    public SimpleSimulation(  ) {
        super( "Simple" );
    }

    @Override
    public int playout( Grammar grammar,  int maxDepth, Skeleton s, int times, int targetFitness, Object solution ) {
        return 0;
    }

}
