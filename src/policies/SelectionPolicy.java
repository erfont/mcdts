package policies;

import elements.Population;
import elements.Skeleton;

public abstract class SelectionPolicy {
    
    private String name;

    public SelectionPolicy(String name){
        this.name = name;
    }
    
    public abstract Skeleton select(Population population);

}
