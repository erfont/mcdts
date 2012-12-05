package policies.backpropagation;

import elements.Population;
import elements.Skeleton;
import grammar.Production;

public class SimpleBackpropPolicy extends BackpropagationPolicy {

    public SimpleBackpropPolicy() {
        super( "Simple" );
    }

    @Override
    public boolean update( Population pop, Skeleton s, int fitness, Production p ) {
        if (s.getFitness() > fitness){
            p.addScore( 15 );            
        }
        else p.addScore( -10 );        
        s.setFitness( fitness );
        s.setName( Integer.toString( pop.getSize() ) );        
        return pop.insertNewSkeleton( s );
    }

    @Override
    public boolean update( Population pop, Skeleton s, int score ) {
        // TODO Auto-generated method stub
        return false;
    }

}
