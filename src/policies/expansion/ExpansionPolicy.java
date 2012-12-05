package policies.expansion;

import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.Production;

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
    public abstract Production expand(DerivationTree tree, Grammar grammar) throws GrammarException;
    
    public String getName() {
        return name;
    }
}
