package edu.cpp.tictactoe;

public class HumanPlayer extends Player{
    
    public HumanPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Mark[] mark) {
        throw new UnsupportedOperationException("Unimplemented method 'nextMove'");
    }
}