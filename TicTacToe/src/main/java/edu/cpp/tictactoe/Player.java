package edu.cpp.tictactoe;

public abstract class Player {
    private String symbol;

    public Player(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public abstract int nextMove(String[] symbol);
}
