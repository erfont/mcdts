package elements;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import jdsl.core.api.Position;

import exceptions.EmptyCemeteryException;
import exceptions.NoSuchSkeletonException;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.NonTerminal;

import utils.SkeletonLogic;

public class Population {
    
    private List<Skeleton> cemetery;
    
    public Population(Grammar grammar){
        this.cemetery = new ArrayList<Skeleton>();
        this.startCemetery(grammar);
    }
    
    /**Cemetery starts with one skeleton whose only node (root) is the axiom of the grammar 
     * @param grammar
     * The grammar
     */
    private void startCemetery(Grammar grammar) {
        Skeleton first = new Skeleton(0);
        DerivationTree fTree = new DerivationTree();
        Position pos = fTree.root();
        NonTerminal s = grammar.getAxiom();
        try {
            pos.set( "Element", s );
            pos.set( "Production", grammar.getProductions().getProductionsWithLeft( s ));            
        }
        catch (GrammarException e) {
            e.printStackTrace();
        }
        first.setTree( fTree );
        this.cemetery.add( first );
    }

    public void addSkeleton(Skeleton s){
        this.cemetery.add( s );
    }
    
    public Skeleton getSkeleton(String name) throws NoSuchSkeletonException, EmptyCemeteryException {
        if (this.cemetery.size() == 0) throw new EmptyCemeteryException("You are attempting to access an empty cemetery.");
        Iterator<Skeleton> it = this.cemetery.iterator();
        while (it.hasNext()){
            Skeleton aux = it.next();
            if (aux.getName().equals( name )) return aux;
        }
        throw new NoSuchSkeletonException("Skeleton "+name+" is not in the cemetery.");
    }
    
    public int getSize(){
        return this.cemetery.size();
    }

    public Skeleton getSkeletonInPos( int pos ) throws EmptyCemeteryException {
        if (this.cemetery.size() == 0) throw new EmptyCemeteryException("You are attempting to access an empty cemetery.");
        return this.cemetery.get( pos );        
    }
    
    public Skeleton getBest(){
        final SortedSet<Skeleton> rankOrder = new TreeSet<Skeleton>( SkeletonLogic.SCORE_COMPARATOR );
        rankOrder.addAll( this.cemetery );        
        return rankOrder.first();
    }
}
