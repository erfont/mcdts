package elements;

import java.util.ArrayList;
import java.util.Random;

import grammar.Grammar;
import grammar.GrammarException;
import grammar.NonTerminal;
import grammar.Production;

public class ProductionRanking {
    
    private Grammar grammar;
    
    public ProductionRanking ( Grammar g ){
        this.grammar = g;
    }
    
    public Production chooseBestFor (NonTerminal nt) throws GrammarException{
        Production res = null;
        if (nt.getSymbol().equals( "ZEROAMOUNT" )){
            System.out.println();
        }
        ArrayList<Production> ps = grammar.getProductions().getProductionsWithLeft( nt );
        ArrayList<Production> pool = new ArrayList<Production>();
        int maxScore = 0;
        for (Production p:ps){
            int p_score = p.getScore();
            if (p_score > maxScore){
                pool.clear();
                pool.add( p );
                maxScore = p_score;                
            }
            else if (p_score == maxScore){
                pool.add( p );
            }
        }
        
        if (pool.size() > 1){
            Random r = new Random();
            res = pool.get( r.nextInt(pool.size()) );
        }
        else res = pool.get( 0 );
        return res;
    }
    
    public void updateProduction (String prod, int score){
        this.grammar.getProductions().getThisProduction( prod ).addScore( score );
    }

}
