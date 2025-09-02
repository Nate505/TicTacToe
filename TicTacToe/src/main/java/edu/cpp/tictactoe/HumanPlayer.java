package edu.cpp.tictactoe;
import java.util.Scanner;

public class HumanPlayer extends Player{
    
    public HumanPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Board board) {
        Scanner kbd = new Scanner(System.in);
        int input = 0;
        board.printBoard();
        System.out.println("PLEASE SELECT WHICH CELL:");

        input = kbd.nextInt() - 1;
        
        return new Move(input / 3, input % 3, getMark());
    }
}