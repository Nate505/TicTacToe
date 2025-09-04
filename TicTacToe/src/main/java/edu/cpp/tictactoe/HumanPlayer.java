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
        
        System.out.println("PLEASE SELECT WHICH CELL:");

        // Try to catch invalid types for ints
        try {
            input = kbd.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Invalid input.");
            input = -1;
        }

        return new Move(input / 3, input % 3, mark);
    }
}