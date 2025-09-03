package edu.cpp.tictactoe;

public abstract class Player {
    protected final Mark mark;

    public Player(Mark mark){
        this.mark = mark;
    }

    public Mark getMark(){
        return mark;
    }

    public abstract Move nextMove(Board b);
}
