package elements;

import utils.TreeCloner;
import grammar.DerivationTree;

public class Skeleton implements Cloneable {

    private int id;
    private DerivationTree tree;
    private int timesVisited;
    private int fitness;
    private String name;

    public Skeleton( int id ) {
        this.id = id;
        this.tree = null;
        this.timesVisited = 0;
        this.fitness = 1000;
        this.name = Integer.toString( id );
    }

    @Override
    public Skeleton clone() {
        Skeleton res = new Skeleton( this.id );
        res.setTree( TreeCloner.cloneThisTree( res.getTree(), null, this.tree, this.tree.root() ) );
        return res;
    }

    @Override
    public String toString() {
        return this.name + " " + this.fitness;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public DerivationTree getTree() {
        return tree;
    }

    public void setTree( DerivationTree tree ) {
        this.tree = tree;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited( int timesVisited ) {
        this.timesVisited = timesVisited;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness( int fitness ) {
        this.fitness = fitness;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
