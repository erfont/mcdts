package policies.backpropagation;

import elements.Population;
import elements.Skeleton;
import grammar.Production;

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

    public boolean update( Population pop, Skeleton s, int fitness, Production p ) {
        // TODO Auto-generated method stub
        return false;
    }

}
