package edu.cpp.tictactoe;

public abstract class Player {
    private Mark mark;

    public Player(Mark mark){
        this.mark = mark;
    }

    public Mark getMark(){
        return mark;
    }

    public abstract Move nextMove(Mark[] mark);
}
