package algorithm;

import policies.RandomSelectionPolicy;
import policies.SimpleBackpropPolicy;
import policies.SimpleExpansionPolicy;
import policies.SimpleSimulation;
import elements.Population;
import elements.Skeleton;
import grammar.Grammar;
import grammar.GrammarException;

public class Mcdts {
    
    private final int target_score = 0;
    private final int n_runs = 100;

    Population population;
    private RandomSelectionPolicy selector;
    private SimpleExpansionPolicy expander;
    private SimpleSimulation simulator;
    private SimpleBackpropPolicy updater;
    private Grammar grammar;
    
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
            Skeleton candidate = null;
            while (candidate == null) candidate = this.selector.select( population );            
            try {
                this.expander.expand( candidate.getTree(), grammar );
            }
            catch (GrammarException e) {
                System.out.println(e.getMessage());
            }
            int score = this.simulator.playout( candidate.getTree(), n_runs );
            this.updater.update( this.population, candidate, score );
        }
        
        
    }

    private void init(){
        try {
            grammar = new Grammar( "Grammars/g4.gr" );
        }
        catch (final GrammarException e) {
            e.printStackTrace();
        }
        population = new Population(grammar);
    }
    
    

}
