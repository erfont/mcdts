package policies;

import grammar.DerivationTree;

public class SimpleSimulation extends Simulation {

    public SimpleSimulation(  ) {
        super( "Simple" );
    }

    @Override
    public int playout( DerivationTree tree, int times ) {
        return 0;
    }

}
