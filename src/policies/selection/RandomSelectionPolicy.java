package policies.selection;

import java.util.Random;

import elements.Population;
import elements.Skeleton;
import exceptions.EmptyCemeteryException;

public class RandomSelectionPolicy extends SelectionPolicy {

    public RandomSelectionPolicy() {
        super( "Random" );
    }

    @Override
    public Skeleton select( Population population ) {
        Skeleton res = null;
        if (population.getSize()>0){
            Random r = new Random();
            try {
                res = population.getSkeletonInPos( r.nextInt( population.getSize() ));             
            }
            catch (EmptyCemeteryException ece){
                System.out.println(ece.getMessage());
            }
        }
        return res;
    }

}
