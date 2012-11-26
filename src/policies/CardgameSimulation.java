package policies;

import utils.Quicksort;
import dk.itu.ccgr.evocardgame.application.SingleGameAnalysisApplication;
import dk.itu.ccgr.evocardgame.evolutionary.Individual;
import dk.itu.ccgr.evocardgame.game.CardGame;
import dk.itu.ccgr.evocardgame.game.logic.GameLogic;
import dk.itu.ccgr.evocardgame.game.logic.combinations.PokerCombinations;
import dk.itu.ccgr.evocardgame.utils.Statistics;
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
    public int playout( Grammar grammar,  int maxDepth,  DerivationTree tree, int times ) throws GrammarException {       
        
        GameLogic.AUXILLARY_COMBINATIONS = new PokerCombinations();
        
        Derivation auxD = new Derivation( grammar, maxDepth, tree, Trees.depth( tree, tree.root() ) );
        
        Individual individual = new Individual("ind", auxD);
        
        Statistics stats = new Statistics( individual.getId() );
        CardGame game = null;

        for (int i = 0; i < times; i++) {
            try {
                game = new CardGame( individual.getDerivation() );
                new SingleGameAnalysisApplication();
                SingleGameAnalysisApplication.playGame( game, stats );
            }
            catch (final Exception e) {
                stats.addTimesCrashed();
            }
        }
        final int[] values = stats.getTimesWon();
        Quicksort.sort( values );
        int fitness;

        if (stats.getTimesCrashed() == 0) {
            fitness = stats.getTimesDraw() + Math.abs( (values[2] - values[0]) - 50) + Math.abs( stats.getAvgTurns() - 18 );
        }
        else {
            fitness = stats.getTimesCrashed() * 2 + stats.getTimesDraw() + values[2] - values[0];
        }

        individual.setFitness( fitness );
        individual.setEvaluated( true );
        
        
        return 0;
    }

}
