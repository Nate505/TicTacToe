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

        return new Move(place / 3, place % 3, getMark()); 
    }

    // Returns places of the board where the mark is EMPTY
    public ArrayList<Integer> getEmptyCells(Board b) {
        ArrayList<Integer> list = new ArrayList<>();
        Mark[][] grid = b.getGrid();

        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                if (grid[row][col] == Mark.EMPTY)
                    list.add(row * 3 + col);
        
        return list;
    }
}
