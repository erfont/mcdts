package policies.simulation;

import utils.Quicksort;
import utils.TreeCloner;
import dk.itu.ccgr.evocardgame.application.SingleGameAnalysisApplication;
import dk.itu.ccgr.evocardgame.evolutionary.Individual;
import dk.itu.ccgr.evocardgame.game.CardGame;
import dk.itu.ccgr.evocardgame.game.logic.GameLogic;
import dk.itu.ccgr.evocardgame.game.logic.combinations.PokerCombinations;
import dk.itu.ccgr.evocardgame.utils.Statistics;
import elements.Skeleton;
import elements.Solution;
import grammar.Derivation;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.util.Trees;

public class CardgameSimulation extends Simulation {
    
    private int tests;

    public CardgameSimulation() {
        super( "Cardgame" );
        this.tests = 100;
    }

    @Override
    public int playout( Grammar grammar, int maxDepth, Skeleton clonedS, int times, int targetFitness, Object solution ) throws GrammarException,
                    IllegalStateException {
        
        int bestFitness = 10000;

        GameLogic.AUXILLARY_COMBINATIONS = new PokerCombinations();

        DerivationTree treeToClone = clonedS.getTree();
        
        DerivationTree tree = null;
        Derivation auxD = null;
        CardGame game = null;        
        Statistics stats = null;
        Individual individual = null;
        System.out.print( "Testing expanded derivation");
        for (int i = 0; i < times; i++) {
            System.out.print( ", "+i);
            stats = new Statistics( "AuxInd" + i );
            tree = TreeCloner.cloneThisTree( tree, null, treeToClone, treeToClone.root() );
            auxD = new Derivation( grammar, maxDepth, tree, Trees.depth( tree, tree.root() ) );
            for (int j = 0; j < tests; j++) {
                try {
                    individual = new Individual( "ind", auxD );
                    game = new CardGame( individual.getDerivation() );
                    new SingleGameAnalysisApplication();
                    SingleGameAnalysisApplication.playGame( game, stats, false );
                }
                catch (Exception e) {
//                    System.out.println( e.getMessage() );
                    stats.addTimesCrashed();
                }    
            }
            final int[] values = stats.getTimesWon();
            Quicksort.sort( values );
            int fitness;

            if (stats.getTimesCrashed() == 0) {
                fitness = stats.getTimesDraw() + (Math.abs( (values[2] - values[0]) - 25 )) + Math.abs( stats.getAvgTurns() - 20 );
            }
            else {
                fitness = stats.getTimesCrashed() * 2 + stats.getTimesDraw() + (Math.abs( (values[2] - values[0]) - 25 ));
            }
            
            if (fitness < bestFitness) bestFitness = fitness;
            
            if (fitness <= targetFitness) {
                ((Solution) solution).setGame( game );
                ((Solution) solution).setFitness( bestFitness );
                System.out.print(" FOUND IT!\n");
                return bestFitness;
            }                
            tree = null;
            auxD = null;
            stats = null;
        }
        System.out.print( "\n");
        return bestFitness;
    }

}
