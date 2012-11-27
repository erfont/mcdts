package policies;

import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;

public abstract class ExpansionPolicy {
    
    private String name;

    public ExpansionPolicy(String name){
        this.name = name;
    }
    
    /**
     * Expands tree by adding a child node to one of its leaves
     * @param tree
     * @return 
     * @throws GrammarException 
     */
    public abstract boolean expand(DerivationTree tree, Grammar grammar) throws GrammarException;
    
    public String getName() {
        return name;
    }

}
