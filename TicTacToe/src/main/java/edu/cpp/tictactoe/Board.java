package edu.cpp.tictactoe;
import java.util.Arrays;

public class Board {
    // Fields
    private Mark[][] grid;
    private int moves;
    private int size;

    // Default constructor — maybe allow for bigger sizes at one point?
    public Board() {
        size = 3;
        grid = new Mark[size][size];
        
        // Initializes the grid full of EMPTY marks
        reset();
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

        if (mark != Mark.EMPTY)
            return mark.name();
        else
            return Integer.toString(row * 3 + col + 1);
    }

    // Returns a mark of the given cell
    public Mark getCell(int row, int col) {
        return grid[row][col];
    }

    // Returns the size of the board
    public int getSize() {
        return size;
    }

    // Checks for winners
    public Mark winner(){
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
                return Mark.X;
            } else if(line.equals("OOO")){
                return Mark.O;
            }
        }

        // No winner, then return EMPTY
        return Mark.EMPTY;
    }

    // Checks if the the cell is empty (mainly used as a helper method)
    public boolean isCellEmpty(int row, int col){
        try {
            if(grid[row][col] == Mark.EMPTY) 
                return true;
            else 
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Places a mark depending on the move
    // Returns true if placing is successful, otherwise false 
    public boolean place(Move move){
        // Checks if the cell is empty
        if (isCellEmpty(move.row, move.col)) {
            grid[move.row][move.col] = move.mark;
            moves += 1;
            return true;
        }

        return false;
    }

    // Resets the cell to EMPTY
    // Used for undo
    public void clearCell(int row, int col) {
        if (grid[row][col] != Mark.EMPTY) {
            grid[row][col] = Mark.EMPTY;
            moves -= 1;
        }
    }

    // Resets the board to only contain EMPTY marks
    public void reset(){
        for (Mark[] row : grid) Arrays.fill(row, Mark.EMPTY);
        moves = 0;
    }

    // Checks if the board is full
    public boolean isFull() {
        return moves >= size * size;
    }
}

