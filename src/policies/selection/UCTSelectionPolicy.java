package policies.selection;

import java.util.ArrayList;
import java.util.Random;

import elements.Population;
import elements.Skeleton;

public class UCTSelectionPolicy extends SelectionPolicy {

    private int C;

    public UCTSelectionPolicy( int C ) {
        super( "UCT" );
        this.C = C;
    }

    @Override
    public Skeleton select( Population population ) {        
        int UCTscore = Integer.MAX_VALUE, MINscore = Integer.MAX_VALUE;
        ArrayList<Skeleton> uPopulation = population.getNonCompleted();
        ArrayList<Skeleton> pool = new ArrayList<Skeleton>();
                
        for (Skeleton s:uPopulation){
            int fitness = s.getFitness();
            int timesV = s.getTimesVisited();            
            UCTscore = (int) (fitness + C * Math.sqrt( Math.log( timesV )));
            if (UCTscore < MINscore) {
                pool.clear();
                pool.add( s );
                MINscore = UCTscore;
            }
            else if (UCTscore == MINscore) {
                pool.add( s );                
            }
        }
        
        if (pool.size() > 1){
            Random r = new Random();
            return pool.get( r.nextInt(pool.size()) );
        }
        else return pool.get( 0 );
    }

}
