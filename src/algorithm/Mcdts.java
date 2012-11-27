package algorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import policies.BackpropagationPolicy;
import policies.CardgameSimulation;
import policies.ExpansionPolicy;
import policies.RandomSelectionPolicy;
import policies.SelectionPolicy;
import policies.SimpleBackpropPolicy;
import policies.SimpleExpansionPolicy;
import policies.Simulation;
import policies.UCTSelectionPolicy;
import elements.Population;
import elements.Skeleton;
import grammar.Grammar;
import grammar.GrammarException;

public class Mcdts {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat( "k:m:s:S" );

    private final int target_fitness = 25;
    private final int n_runs = 1000;

    Population population;
    private SelectionPolicy selector;
    private ExpansionPolicy expander;
    private Simulation simulator;
    private BackpropagationPolicy updater;
    private Grammar grammar;

    public Mcdts() {
        this.init();
    }

    public boolean execute() {

        Logger.getLogger( this.getClass().getName() ).info( "Starting MCDTS" );
        int iteration = 0;
        while (this.population.getBest().getFitness() > this.target_fitness) {
            Logger.getLogger( this.getClass().getName() ).info( "----------- ITERATION: " + iteration + " -------------" );
            Logger.getLogger( this.getClass().getName() ).info( "Population:\n" + this.population.toString() );

            Skeleton candidate = null;
            while (candidate == null) {
                candidate = this.selector.select( population );
                if (candidate.isCompleted()) candidate = null;
            }
            candidate.setTimesVisited( candidate.getTimesVisited() + 1 );

            Logger.getLogger( this.getClass().getName() ).info( "Candidate: " + candidate.getName() );

            int fitness = Integer.MAX_VALUE;
            Skeleton clone = null;
            try {
                clone = candidate.clone();
                if (this.expander.expand( clone.getTree(), grammar )) {
//                  Logger.getLogger( this.getClass().getName() ).info( "Expanded tree:\n "+clone.getTree().toString() );
                    
                    Logger.getLogger( "" ).setLevel( java.util.logging.Level.OFF );
                    
                    fitness = this.simulator.playout( grammar, 500, clone, n_runs );
                    
                    Logger.getLogger( "" ).setLevel( java.util.logging.Level.INFO );
                    
                    this.updater.update( this.population, clone, fitness );
                }
                else {
                    candidate.setCompleted( true );
                }
            }
            catch (GrammarException e) {
                System.out.println( e.getMessage() );
                return false;
            }

            iteration++;
        }

        System.out.println( population.getBest().getTree() );

        return true;

    }

    private void init() {

        final Formatter formatter = new Formatter() {

            @Override
            public String format( LogRecord arg0 ) {
                final StringBuilder b = new StringBuilder();
//                b.append( dateFormat.format( new Date() ) );
//                b.append( " " );
//                b.append( arg0.getLevel() );
//                b.append( " " );
//                b.append( arg0.getMessage() );
                b.append( System.getProperty( "line.separator" ) );
                return b.toString();
            }

        };

        final Handler ch = new ConsoleHandler();
        ch.setFormatter( formatter );
        Logger.getLogger( this.getClass().getName() ).addHandler( ch );

        this.selector = new UCTSelectionPolicy(100);
        this.expander = new SimpleExpansionPolicy();
        this.simulator = new CardgameSimulation();
        this.updater = new SimpleBackpropPolicy();
        try {
            grammar = new Grammar( "Grammars/g4.gr" );
        }
        catch (final GrammarException e) {
            e.printStackTrace();
        }
        population = new Population( grammar );
    }

}
