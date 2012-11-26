package policies;

import grammar.DerivationTree;
import grammar.Grammar;

public class SimpleSimulation extends Simulation {

    public SimpleSimulation(  ) {
        super( "Simple" );
    }

    @Override
    public int playout( Grammar grammar,  int maxDepth, DerivationTree tree, int times ) {
        return 0;
    }

}
