package policies;

import elements.Population;
import elements.Skeleton;
import exceptions.EmptyCemeteryException;
import exceptions.NoSuchSkeletonException;

public class SimpleBackpropPolicy extends BackpropagationPolicy {

    public SimpleBackpropPolicy( ) {
        super( "Simple" );
    }

    @Override
    public void update( Population pop, Skeleton s, int fitness ) {
        try {
            pop.getSkeleton( s.getName() ).setFitness( fitness );
        }
        catch (NoSuchSkeletonException e) {
            System.out.println(e.getMessage());
        }
        catch (EmptyCemeteryException e) {
            System.out.println(e.getMessage());
        }
    }

}
