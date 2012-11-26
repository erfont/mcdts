package elements;

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
        this.fitness = Integer.MAX_VALUE;
        this.name = Integer.toString( id );
    }
    
    @Override 
    public Skeleton clone(){
        try {
            return (Skeleton) super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    public String toString(){
        return this.name+" "+this.fitness;
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
