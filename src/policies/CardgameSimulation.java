package policies;

import utils.Quicksort;
import utils.TreeCloner;
import dk.itu.ccgr.evocardgame.application.SingleGameAnalysisApplication;
import dk.itu.ccgr.evocardgame.evolutionary.Individual;
import dk.itu.ccgr.evocardgame.game.CardGame;
import dk.itu.ccgr.evocardgame.game.logic.GameLogic;
import dk.itu.ccgr.evocardgame.game.logic.combinations.PokerCombinations;
import dk.itu.ccgr.evocardgame.utils.Statistics;
import elements.Skeleton;
import grammar.Derivation;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.util.Trees;

public class CardgameSimulation extends Simulation{

    public CardgameSimulation( ) {
        super( "Cardgame" );
        // TODO Auto-generated constructor stub
    }

    @Override
    public int playout( Grammar grammar,  int maxDepth,  Skeleton clonedS, int times ) throws GrammarException, IllegalStateException {       
        
        GameLogic.AUXILLARY_COMBINATIONS = new PokerCombinations();
        
        DerivationTree tree = null;
        DerivationTree treeToClone = clonedS.getTree();
        
        Statistics stats = new Statistics( "AuxInd" );
        Individual individual = null;

        for (int i = 0; i < times; i++) {
            try {
                tree = TreeCloner.cloneThisTree( tree, null, treeToClone, treeToClone.root());
                Derivation auxD = new Derivation( grammar, maxDepth, tree, Trees.depth( tree, tree.root() ) );                
                individual = new Individual("ind", auxD);                
                CardGame game = null;
                game = new CardGame( individual.getDerivation() );
                new SingleGameAnalysisApplication();
                SingleGameAnalysisApplication.playGame( game, stats, false );
                tree = null;
            }           
            catch (Exception e) {
//                System.out.println( e.getMessage() );
                stats.addTimesCrashed();
            }
        }
        final int[] values = stats.getTimesWon();
        Quicksort.sort( values );
        int fitness;

        if (stats.getTimesCrashed() == 0) {
            fitness = stats.getTimesDraw() + ( values[2] - values[0] ) + Math.abs( stats.getAvgTurns() );
        }
        else {
            fitness = stats.getTimesCrashed() * 2 + stats.getTimesDraw() + values[2] - values[0];
        }

        return fitness;
    }

}
