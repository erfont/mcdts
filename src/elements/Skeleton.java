package elements;

import grammar.DerivationTree;

public class Skeleton {

    private int id;
    private DerivationTree tree;
    private int timesVisited;
    private int score;
    private String name;

    public Skeleton( int id ) {
        this.id = id;
        this.tree = null;
        this.timesVisited = 0;
        this.score = 0;
        this.name = "";
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

    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
