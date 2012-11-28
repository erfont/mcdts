package elements;

import utils.TreeCloner;
import grammar.DerivationTree;

public class Skeleton implements Cloneable {

    private int id;
    private DerivationTree tree;
    private int timesVisited;
    private int fitness;
    private String name;
    private boolean completed;
    private String key;

    public Skeleton( int id ) {
        this.id = id;
        this.tree = null;
        this.timesVisited = 1;
        this.fitness = 10000;
        this.name = Integer.toString( id );
        this.completed = false;
        this.key = "";
    }

    @Override
    public Skeleton clone() {
        Skeleton res = new Skeleton( this.id );
        res.setTree( TreeCloner.cloneThisTree( res.getTree(), null, this.tree, this.tree.root() ) );
        return res;
    }

    @Override
    public String toString() {
        return "Name: "+this.name + "\tFitness: " + this.fitness + "\tTimes visited: "+this.timesVisited+"\nTree: \n"+this.tree.toString();
    }
    
    public void generateKey(){
        this.key = this.tree.toElementKey();
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

    public void setCompleted( boolean b ) {
        this.completed = b;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getKey() {
        return key;
    }
}
