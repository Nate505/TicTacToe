package edu.cpp.tictactoe;
import java.util.Arrays;

public class Board {
    // Fields
    private Mark[][] grid;
    private int moves;
    private int boardSize;

    // Default constructor — maybe allow for bigger sizes at one point?
    public Board(int boardSize) {
        this.boardSize = boardSize;
        grid = new Mark[boardSize][boardSize];
        
        // Initializes the grid full of EMPTY marks
        reset();
    }

    public void printBoard() {
        for(int i = 0; i < boardSize; i++){
            for(int c = 0; c < boardSize; c++){
                System.out.print("|----");
            }
            System.out.println("|");

            for (int col = 0; col < boardSize; col++) {
                System.out.printf("| " + String.format("%2s", getSymbol(i, col)) + " ");
            }
            System.out.println("|");
        }
        for(int c = 0; c < boardSize; c++){
            System.out.print("|----");
        }
        System.out.println("|");
    }

    // Prints a symbol through an index in the grid
    private String getSymbol(int row, int col) {
        Mark mark = grid[row][col];

        if (mark != Mark.EMPTY)
            return mark.name();
        else
            return Integer.toString(row * boardSize + col + 1);
    }

    // Returns a mark of the given cell
    public Mark getCell(int row, int col) {
        return grid[row][col];
    }

    // Returns the size of the board
    public int getSize() {
        return boardSize;
    }

    // Checks for winners
    public Mark winner() {
        // Check each row
        for (int r = 0; r < boardSize; r++) {
            if (allSameMark(grid[r][0], r, 0, 0, 1)) {
                return grid[r][0];
            }
        }

        // Check each column
        for (int c = 0; c < boardSize; c++) {
            if (allSameMark(grid[0][c], 0, c, 1, 0)) {
                return grid[0][c];
            }
        }

        // Main diagonal
        if (allSameMark(grid[0][0], 0, 0, 1, 1)) {
            return grid[0][0];
        }

        // Anti-diagonal
        if (allSameMark(grid[0][boardSize - 1], 0, boardSize - 1, 1, -1)) {
            return grid[0][boardSize - 1];
        }

        return Mark.EMPTY; // no winner
    }

    // Walk along a line and confirm every cell matches the first
    private boolean allSameMark(Mark first, int startRow, int startCol, int dr, int dc) {
        if (first == Mark.EMPTY) return false;
        for (int i = 1; i < boardSize; i++) {
            int r = startRow + dr * i;
            int c = startCol + dc * i;
            if (grid[r][c] != first) return false;
        }
        return true;
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
        return moves >= boardSize * boardSize;
    }
}

