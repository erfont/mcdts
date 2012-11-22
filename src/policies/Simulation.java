package policies;

import grammar.DerivationTree;
import grammar.Grammar;

public abstract class Simulation {
    
    private String name;

    public Simulation(String name){
        this.name = name;
    }
    
    /**
     * Creates a full derivation from the unfinished tree and tests it a given number of times.
     * @param tree
     * @return The score obtained by the tree
     */
    public abstract int playout(Grammar grammar, DerivationTree tree, int times);
    
    public String getName() {
        return name;
    }

}
