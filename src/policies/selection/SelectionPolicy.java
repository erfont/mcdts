package policies.selection;

import elements.Population;
import elements.Skeleton;

public abstract class SelectionPolicy {
    
    private String name;

    public SelectionPolicy(String name){
        this.name = name;
    }
    
    /**
     * Selects the skeleton that is going to be expanded and played
     * @param population
     * @return the skeleton
     */
    public abstract Skeleton select(Population population);

    public String getName() {
        return name;
    }

}
