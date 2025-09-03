package edu.cpp.tictactoe;

import java.util.Arrays;

public class Board {
    // Fields
    private Mark[][] grid;
    private int size;

    // Default constructor — maybe allow for bigger sizes at one point?
    public Board() {
        size = 3;
        grid = new Mark[size][size];
        
        // Initializes the grid full of EMPTY marks
        reset();
    }

    // Get a copy of the grid
    public Mark[][] getGrid() {
        Mark[][] newGrid = new Mark[size][size];
        
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
                newGrid[row][col] = grid[row][col];
        
        return newGrid;
    }

    public void printBoard() {
        for(int row = 0; row < 3; row++){
            System.out.println("|---|---|---|");
            for (int col = 0; col < 3; col++) {
                System.out.print("| " + getSymbol(row, col) + " ");
            }
            System.out.println("|");
        }
        System.out.println("|---|---|---|");
    }

    // Prints a symbol through an index in the grid
    private String getSymbol(int row, int col) {
        Mark mark = grid[row][col];

        switch (mark) {
            case X:
                return "X";
            case O:
                return "O";
            case EMPTY:
                return Integer.toString(row * 3 + col + 1);
            default:
                return "";
        }
    }

    // Checks for winners
    public String winner(){
        for(int i = 0; i < 8; i++){
            String line = null;

            switch (i) {
                // Row 1
                case 0:
                    line = getSymbol(0, 0) + getSymbol(0, 1) + getSymbol(0, 2);
                    break;
                // Row 2
                case 1:
                    line = getSymbol(1, 0) + getSymbol(1, 1) + getSymbol(1, 2);
                    break;
                // Row 3
                case 2:
                    line = getSymbol(2, 0) + getSymbol(2, 1) + getSymbol(2, 2);
                    break;
                // Col 1
                case 3:
                    line = getSymbol(0, 0) + getSymbol(1, 0) + getSymbol(2, 0);
                    break;
                // Col 2
                case 4:
                    line = getSymbol(0, 1) + getSymbol(1, 1) + getSymbol(2, 1);
                    break;
                // Col 3
                case 5:
                    line = getSymbol(0, 2) + getSymbol(1, 2) + getSymbol(2, 2);
                    break;
                // Diagonal (Upper left to lower right)
                case 6:
                    line = getSymbol(0, 0) + getSymbol(1, 1) + getSymbol(2, 2);
                    break;
                // Diagonal (Upper right to lower left)
                case 7:
                    line = getSymbol(0, 2) + getSymbol(1, 1) + getSymbol(2, 0);
                    break;
            }

            if (line.equals("XXX")){
                return "X";
            } else if(line.equals("OOO")){
                return "O";
            }
        }

        // Checks if there is any empty spaces (only runs if there is no winner yet)
        if (!isFull())
            return "No Winner Yet";

        // If there is no remaining space, then return draw
        return "Draw";
    }

    // Checks if the the cell is empty (mainly used as a helper method)
    public boolean isCellEmpty(int row, int col){
        if(grid[row][col] == Mark.EMPTY) 
            return true;
        else 
            return false;
    }

    // Places a mark depending on the move
    // Returns true if placing is successful, otherwise false 
    public boolean place(Move move){
        if (isCellEmpty(move.row, move.col)) {
            grid[move.row][move.col] = move.mark;
            return true;
        }

        return false;
    }

    // Resets the board to only contain EMPTY marks
    public void reset(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = Mark.EMPTY;
            }
        }
    }

    // Checks if the board is full
    public boolean isFull() {
        for (Mark[] row : grid)
            for (Mark mark : row)
                if (mark == Mark.EMPTY)
                    return false;

        return true;
    }

}

