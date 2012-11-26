package policies;

import elements.Population;
import elements.Skeleton;

public class SimpleBackpropPolicy extends BackpropagationPolicy {

    public SimpleBackpropPolicy() {
        super( "Simple" );
    }

    @Override
    public void update( Population pop, Skeleton s, int fitness ) {
        s.setFitness( fitness );
        s.setName( Integer.toString( pop.getSize() ) );
        pop.addSkeleton( s );
    }

}
