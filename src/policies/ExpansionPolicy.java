package policies;

import grammar.DerivationTree;

public abstract class ExpansionPolicy {
    
    private String name;

    public ExpansionPolicy(String name){
        this.name = name;
    }
    
    /**
     * Expands tree by adding a child node to one of its leaves
     * @param tree
     */
    public abstract void expand(DerivationTree tree);
    
    public String getName() {
        return name;
    }

}
