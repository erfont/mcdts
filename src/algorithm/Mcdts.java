package algorithm;

import policies.RandomSelectionPolicy;
import policies.SimpleBackpropPolicy;
import policies.SimpleExpansionPolicy;
import policies.SimpleSimulation;
import elements.Population;
import elements.Skeleton;

public class Mcdts {
    
    private final int target_score = 0;
    private final int n_runs = 100;

    Population population;
    private RandomSelectionPolicy selector;
    private SimpleExpansionPolicy expander;
    private SimpleSimulation simulator;
    private SimpleBackpropPolicy updater;
    
    public Mcdts(){
        this.init();
        this.execute();
        this.selector = new RandomSelectionPolicy();
        this.expander = new SimpleExpansionPolicy();
        this.simulator = new SimpleSimulation();
        this.updater = new SimpleBackpropPolicy();
    }
    
    private void execute() {
        
        while (this.population.getBest().getScore()>this.target_score){
            Skeleton candidate = this.selector.select( population );
            this.expander.expand( candidate.getTree() );
            int score = this.simulator.playout( candidate.getTree(), n_runs );
            this.updater.update( this.population, candidate, score );
        }
        
        
    }

    private void init(){
        population = new Population();
    }
    
    

}
