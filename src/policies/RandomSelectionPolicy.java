package policies;

import java.util.Random;

import elements.Population;
import elements.Skeleton;

public class RandomSelectionPolicy extends SelectionPolicy {

    public RandomSelectionPolicy() {
        super( "Random" );
    }

    @Override
    public Skeleton select( Population population ) {
        if (population.getSize()>0){
            Random r = new Random();
            return population.getSkeletonInPos( r.nextInt( population.getSize() ));
        }
        return null;
    }

}
