package policies.expansion;

import java.util.ArrayList;
import java.util.Random;

import elements.ProductionRanking;

import jdsl.core.api.Position;
import grammar.DerivationTree;
import grammar.Element;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.NonTerminal;
import grammar.Production;

public class UCTExpansionPolicy extends ExpansionPolicy {

    private ProductionRanking productionsRanking;

    public UCTExpansionPolicy( Grammar g ) {
        super( "UCT based Expansion" );
        this.productionsRanking = new ProductionRanking(g);        
    }

    @Override
    public Production expand( DerivationTree tree, Grammar grammar) throws GrammarException {
        //Get non terminal leaves
        ArrayList<Position> leaves = tree.getNonTerminalLeaves();
        
        if (leaves.size()>0){
            
            //Choose one as the node to expand
            Random r = new Random();
            Position pos = leaves.get( r.nextInt(leaves.size()) );
            
            //Choose the best valued production for the node to expand
            Production p = this.productionsRanking.chooseBestFor( (NonTerminal) pos.get( "Element" ) );
            
            //Lower production score because it's been visited
            this.productionsRanking.updateProduction( p.getSymbol(), -5 );
            
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
            return p;
        }
        else {
            return null;     
        }
    }

}
