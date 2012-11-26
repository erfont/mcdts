package policies;

import elements.Population;
import elements.Skeleton;
import exceptions.EmptyCemeteryException;
import exceptions.NoSuchSkeletonException;

public class SimpleBackpropPolicy extends BackpropagationPolicy {

    public SimpleBackpropPolicy() {
        super( "Simple" );
    }

    @Override
    public void update( Population pop, Skeleton s, int fitness ) {
        Skeleton newbie = s.clone();
        newbie.setFitness( fitness );
        newbie.setName( s.getName() + ",*" );
        pop.addSkeleton( newbie );
    }

}
