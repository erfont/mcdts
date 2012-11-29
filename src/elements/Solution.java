package elements;

import dk.itu.ccgr.evocardgame.game.CardGame;

public class Solution {
    
    private CardGame game;
    private int fitness;

    public Solution(){
        this.game = null;
        this.fitness = 10000;
    }
    
    public Solution(CardGame c, int f){
        this.setGame( c );
        this.setFitness( f );
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness( int fitness ) {
        this.fitness = fitness;
    }

    public CardGame getGame() {
        return game;
    }

    public void setGame( CardGame game ) {
        this.game = game;
    }

}
