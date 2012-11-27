package elements;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import jdsl.core.api.Position;

import exceptions.EmptyCemeteryException;
import exceptions.NoSuchSkeletonException;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.NonTerminal;

import utils.PopIterator;
import utils.SkeletonLogic;

public class Population implements Iterable<Skeleton>{
    
    private ArrayList<Skeleton> cemetery;
    
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
        final SortedSet<Skeleton> rankOrder = new TreeSet<Skeleton>( SkeletonLogic.FITNESS_COMPARATOR );
        rankOrder.addAll( this.cemetery );        
        return rankOrder.first();
    }
    
    @Override
    public String toString(){
        if (this.cemetery.size() == 0) return "Empty cemetery";
        else return this.cemetery.size()+" skeletons. Best: "+this.getBest().getFitness()+". % Completed: "+this.getPercentageFinished();
    }

    @Override
    public Iterator<Skeleton> iterator() {
        return new PopIterator(this.cemetery);
    }
    
    public double getPercentageFinished(){
        double res = 0;
        
        for (Skeleton s:this.cemetery)
            if (s.isCompleted()) res++;
        
        res = res*100/this.cemetery.size();
        return res;
    }
}
