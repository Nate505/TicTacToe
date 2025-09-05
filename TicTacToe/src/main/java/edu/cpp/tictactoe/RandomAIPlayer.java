package edu.cpp.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class RandomAIPlayer extends Player{

    public RandomAIPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public Move nextMove(Board b) {
        ArrayList<Integer> emptyCells = getEmptyCells(b);
        Random rnd = new Random();
        int place = emptyCells.get(rnd.nextInt(emptyCells.size()));

        b.printBoard();
        System.out.printf("AI Player chose %d.\n", place + 1);

        return new Move(place / b.getSize(), place % b.getSize(), mark); 
    }

    // Returns places of the board where the mark is EMPTY
    private ArrayList<Integer> getEmptyCells(Board b) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int row = 0; row < b.getSize(); row++)
            for (int col = 0; col < b.getSize(); col++)
                if (b.getCell(row, col) == Mark.EMPTY)
                    list.add(row * b.getSize() + col);
        
        return list;
    }
}
