package general;

import java.util.logging.Logger;

import org.junit.Test;

import dk.itu.ccgr.evocardgame.evolutionary.Individual;
import dk.itu.ccgr.evocardgame.game.CardGame;

import elements.Population;
import elements.Skeleton;
import grammar.Derivation;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.util.Trees;

import policies.SimpleExpansionPolicy;

public class ExpandAndComplete {
    
    private SimpleExpansionPolicy expander;
    private Population population;
    private Grammar grammar;
    
    public ExpandAndComplete(){
        try {
            grammar = new Grammar( "Grammars/g4.gr" );
        }
        catch (final GrammarException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void expandANDcomplete(){
        Logger.getLogger( "" ).setLevel( java.util.logging.Level.OFF );
        
        this.population = new Population( grammar );
        this.expander = new SimpleExpansionPolicy();
        
        Skeleton s = population.getBest();
        System.out.println(s.toString());
        try {
            for (int i=0; i<10; i++){
                System.out.println("------------------ EXPANSION "+i+" ---------------");
                this.expander.expand( s.getTree(), grammar );
                System.out.println(s.toString());
            }           
            System.out.println("------------------ FINISHING ---------------");
            Derivation auxD = new Derivation( grammar, 500, s.getTree(), Trees.depth( s.getTree(), s.getTree().root() ) );
            System.out.println(s.toString());
            System.out.println(auxD.getWord());
            Individual individual = new Individual("ind", auxD);                
            CardGame game = new CardGame( individual.getDerivation() );
            System.out.println(game.toString());

        }
        catch (GrammarException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
