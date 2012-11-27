package policies;

import java.util.ArrayList;
import java.util.Iterator;
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
    public boolean expand( DerivationTree tree, Grammar grammar ) throws GrammarException {
        
        //Get non terminal leaves
        ArrayList<Position> leaves = tree.getNonTerminalLeaves();
        
        if (leaves.size()>0){
            Iterator<Position> it = leaves.iterator();
//            while (it.hasNext()) System.out.println("Tree leaves: "+((Element)it.next().get( "Element" )).getSymbol());
            
            //Choose one as the node to expand
            Random r = new Random();
            Position pos = leaves.get( r.nextInt(leaves.size()) );       
            
//            System.out.println("Chosen NonTerminal: "+((Element)pos.get( "Element" )).getSymbol());
            //Get its associated productions
            ArrayList<Production> ps = grammar.getProductions().getProductionsWithLeft( (NonTerminal) pos.get( "Element" ) );
            
            Iterator<Production> it2 = ps.iterator();
//            while (it2.hasNext()) System.out.println("Productions available: "+it2.next().getSymbol());
            
            //Choose one
            Production p = ps.get( r.nextInt(ps.size()) );
//            System.out.println("Chosen production: "+p.getSymbol());
            
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
            return true;
        }
        else {
            return false;     
        }

    }

}
