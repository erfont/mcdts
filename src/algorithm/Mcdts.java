package algorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import dk.itu.ccgr.evocardgame.game.CardGame;

import policies.BackpropagationPolicy;
import policies.ExpansionPolicy;
import policies.RandomSelectionPolicy;
import policies.SelectionPolicy;
import policies.SimpleBackpropPolicy;
import policies.SimpleExpansionPolicy;
import policies.Simulation;
import policies.UCTSelectionPolicy;
import policies.simulation.CardgameSimulation;
import elements.Population;
import elements.Skeleton;
import elements.Solution;
import grammar.Derivation;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.util.Trees;

public class Mcdts {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat( "k:m:s:S" );

    private final int target_fitness = 0;
    private final int n_runs = 25;

    Population population;
    private SelectionPolicy selector;
    private ExpansionPolicy expander;
    private Simulation simulator;
    private BackpropagationPolicy updater;
    private Grammar grammar;
    private Solution solution;    

    public Mcdts() {
        this.init();
    }

    public boolean execute() {

        Logger.getLogger( this.getClass().getName() ).info( "Starting MCDTS" );
        int iteration = 0;
        solution = new Solution();
        while (solution.getGame()==null) {
            Logger.getLogger( this.getClass().getName() ).info( "----------- ITERATION: " + iteration + " -------------" );
            Logger.getLogger( this.getClass().getName() ).info( "Population:\n" + this.population.toString() );

            Skeleton candidate = null;
            while (candidate == null) {
                candidate = this.selector.select( population );
            }
            candidate.setTimesVisited( candidate.getTimesVisited() + 1 );

            Logger.getLogger( this.getClass().getName() ).info( "Candidate: " + candidate.getName() );

            int fitness = Integer.MAX_VALUE;
            Skeleton clone = null;
            try {
                clone = candidate.clone();
                if (this.expander.expand( clone.getTree(), grammar )) {
//                  Logger.getLogger( this.getClass().getName() ).info( "Expanded tree:\n "+clone.getTree().toString() );
                    
                    clone.generateKey();
                    
                    Logger.getLogger( "" ).setLevel( java.util.logging.Level.OFF );
                    
                    fitness = this.simulator.playout( grammar, 500, clone, n_runs, this.target_fitness, solution );
                    
                    Logger.getLogger( "" ).setLevel( java.util.logging.Level.INFO );
                    
                    if (!this.updater.update( this.population, clone, fitness ))
                        Logger.getLogger( this.getClass().getName() ).info( "Skeleton already indexed" );
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
        
        Logger.getLogger( "" ).setLevel( java.util.logging.Level.OFF );

        
        System.out.println( population.getBest().toString());
        System.out.println( solution.getGame().toString());
//        Derivation auxD = null;
//        try {
//            auxD = new Derivation( grammar, 500, population.getBest().getTree(), Trees.depth( population.getBest().getTree(), population.getBest().getTree().root() ) );
//        }
//        catch (GrammarException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println( new CardGame(auxD).toString() );

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

        this.selector = new UCTSelectionPolicy(20);
        this.expander = new SimpleExpansionPolicy();
        this.simulator = new CardgameSimulation();
        this.updater = new SimpleBackpropPolicy();
        this.solution = null;
        try {
            grammar = new Grammar( "Grammars/g4.gr" );
        }
        catch (final GrammarException e) {
            e.printStackTrace();
        }
        population = new Population( grammar );
    }

}
