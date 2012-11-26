package algorithm;

import policies.BackpropagationPolicy;
import policies.CardgameSimulation;
import policies.ExpansionPolicy;
import policies.SelectionPolicy;
import policies.SimpleBackpropPolicy;
import policies.SimpleExpansionPolicy;
import policies.SimpleSimulation;
import policies.Simulation;
import policies.UCTSelectionPolicy;
import elements.Population;
import elements.Skeleton;
import grammar.Grammar;
import grammar.GrammarException;

public class Mcdts {
    
    private final int target_fitness = 0;
    private final int n_runs = 100;

    Population population;
    private SelectionPolicy selector;
    private ExpansionPolicy expander;
    private Simulation simulator;
    private BackpropagationPolicy updater;
    private Grammar grammar;
    
    public Mcdts(){
        this.init();
    }
    
    public boolean execute() {
        
        while (this.population.getBest().getFitness() > this.target_fitness){
            Skeleton candidate = null;
            while (candidate == null) candidate = this.selector.select( population );    
            candidate.setTimesVisited( candidate.getTimesVisited() + 1 );
            int fitness = Integer.MAX_VALUE;
            try {
                this.expander.expand( candidate.getTree(), grammar );
                fitness = this.simulator.playout( grammar, 500, candidate.getTree(), n_runs );
            }
            catch (GrammarException e) {
                System.out.println(e.getMessage());
                return false;
            }
            this.updater.update( this.population, candidate, fitness );
        }
        
        System.out.println(population.getBest().getTree());
        
        return true;        
        
    }

    private void init(){
        this.selector = new UCTSelectionPolicy(10);
        this.expander = new SimpleExpansionPolicy();
        this.simulator = new CardgameSimulation();
        this.updater = new SimpleBackpropPolicy();
        try {
            grammar = new Grammar( "Grammars/g4.gr" );
        }
        catch (final GrammarException e) {
            e.printStackTrace();
        }
        population = new Population(grammar);
    }
    
    

}
