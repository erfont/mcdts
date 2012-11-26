package policies;

import elements.Skeleton;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;

public abstract class Simulation {
    
    private String name;

    public Simulation(String name){
        this.name = name;
    }
    
    /**
     * Creates a full derivation from the unfinished tree and tests it a given number of times.
     * @param skeleton
     * @return The score obtained by the tree
     */
    public abstract int playout(Grammar grammar,  int maxDepth, Skeleton skeleton, int times) throws GrammarException;
    
    public String getName() {
        return name;
    }

}
