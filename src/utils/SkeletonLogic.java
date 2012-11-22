package utils;

import java.util.Comparator;

import elements.Skeleton;

public class SkeletonLogic {
    
    public final static Comparator<Skeleton> FITNESS_COMPARATOR = new Comparator<Skeleton>() {
        @Override
        public int compare( Skeleton s1, Skeleton s2 ) {
            return Integer.compare( s1.getFitness(), s2.getFitness() );
        }
    };

}
