package policies.backpropagation;

import elements.Population;
import elements.Skeleton;

public class SimpleBackpropPolicy extends BackpropagationPolicy {

    public SimpleBackpropPolicy() {
        super( "Simple" );
    }

    @Override
    public boolean update( Population pop, Skeleton s, int fitness ) {
        s.setFitness( fitness );
        s.setName( Integer.toString( pop.getSize() ) );
        return pop.insertNewSkeleton( s );
    }

}
