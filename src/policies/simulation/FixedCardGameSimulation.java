package policies.simulation;

import org.apache.commons.lang3.StringUtils;

import dk.itu.ccgr.evocardgame.evolutionary.Individual;
import dk.itu.ccgr.evocardgame.game.CardGame;
import dk.itu.ccgr.evocardgame.utils.Statistics;

import utils.TreeCloner;

import elements.Skeleton;
import elements.Solution;
import grammar.Derivation;
import grammar.DerivationTree;
import grammar.Grammar;
import grammar.GrammarException;
import grammar.util.Trees;

public class FixedCardGameSimulation extends Simulation{

    public FixedCardGameSimulation( ) {
        super( "Fixed card game" );
        // TODO Auto-generated constructor stub
    }

    @Override
    public int playout( Grammar grammar, int maxDepth, Skeleton skeleton, int times, int targetFitness, Object solution ) throws GrammarException {
        String target = "com_lambda_deal,allplayers,7;com_lambda_deal,T0,1:show,samesuit,T0_playit;show,samenumber,T0_playit;mandatory_have,lambda_win;draw_next$lambda$tokens*0;playerplaying*0";
        
        DerivationTree treeToClone = skeleton.getTree();
        
        DerivationTree tree = null;
        Derivation auxD = null;
        CardGame game = null;        
        Individual individual = null;
        int bestFitness = 10000;
        
        System.out.print( "Testing expanded derivation");
        for (int i = 0; i < times; i++) {
            System.out.print( ", "+i);
            tree = TreeCloner.cloneThisTree( tree, null, treeToClone, treeToClone.root() );
            auxD = new Derivation( grammar, maxDepth, tree, Trees.depth( tree, tree.root() ) );
            int fitness = StringUtils.getLevenshteinDistance( auxD.getWord(), target );
            
            if (fitness < bestFitness) bestFitness = fitness;
            
            if (fitness <= targetFitness) {
                try {
                    individual = new Individual( "ind", auxD );
                    game = new CardGame( individual.getDerivation() );
                }catch (Exception e) {
                  System.out.println( e.getMessage() );
                } 
                ((Solution) solution).setGame( game );
                ((Solution) solution).setFitness( bestFitness );
                System.out.print(" FOUND IT!\n");
                return bestFitness;
            }
            tree = null;
            auxD = null;
            
        }
        
        return bestFitness;
        
    }

}
