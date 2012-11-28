package policies;

import elements.Population;
import elements.Skeleton;

public abstract class BackpropagationPolicy {
    
    private String name;

    public BackpropagationPolicy(String name){
        this.name = name;
    }
    
    /**
     * Updates pop with Skeleton s, which has just been expanded and played 
     * @param pop
     * @param s
     * @param score
     * @return 
     */
    public abstract boolean update(Population pop, Skeleton s, int score);
    
    public String getName() {
        return name;
    }

}
