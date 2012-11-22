package policies;

import java.util.ArrayList;
import java.util.Random;

import jdsl.core.api.Position;
import grammar.DerivationTree;
import grammar.Element;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.NonTerminal;
import grammar.Production;

public class SimpleExpansionPolicy extends ExpansionPolicy {

    public SimpleExpansionPolicy(  ) {
        super( "Simple" );
    }

    @Override
    public void expand( DerivationTree tree, Grammar grammar ) throws GrammarException {
        
        //Get non terminal leaves
        ArrayList<Position> leaves = tree.getNonTerminalLeaves();
        
        //Choose one as the node to expand
        Random r = new Random();
        Position pos = leaves.get( r.nextInt(leaves.size()) );       
        
        //Get its associated productions
        ArrayList<Production> ps = grammar.getProductions().getProductionsWithLeft( (NonTerminal) pos.get( "Element" ) );
        
        //Choose one
        Production p = ps.get( r.nextInt(ps.size()) );
        
        //Apply it
        pos.set( "Production", p );
        
        //Add its right elements as children of the expanded node
        ArrayList<Element> rights = p.getRight();        
        Position child = tree.insertFirstChild( pos, null );
        child.set( "Element", rights.remove( 0 ) );
        child.set( "Production", null );        
        while (rights.size() > 0){
            child = tree.insertLastChild( pos, null );
            child.set( "Element", rights.remove( 0 ) );
            child.set( "Production", null ); 
        }        

    }

}
