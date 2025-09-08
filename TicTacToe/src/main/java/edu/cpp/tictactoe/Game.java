package edu.cpp.tictactoe;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turn;
    private Stack<Move> undoStack;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.undoStack = new Stack<>();
    }

    public void run() {
        Scanner kbm = new Scanner(System.in);
        int input = 0;
        
        turn = player1;

        // Ask the player for a choice
        // Runs until the game is over, or the player exits
        do {
            // If the player is human, then ask them for a choice
            if (turn instanceof HumanPlayer)
                input = choice(kbm);
            else
                move();
        } while (board.winner() == Mark.EMPTY && !board.isFull() && input != 3);

        // IF the player did not exit:
        if (input != 3) {
            board.printBoard();

            switch (board.winner()) {
                case X:
                    System.out.println("Player X Wins the Tic Tac Toe!");
                    break;
                case O:
                    System.out.println("Player O Wins the Tic Tac Toe!");
                    break;
                case EMPTY:
                    System.out.println("This Game Ended in a Draw!");
                    break;
            }
        }
        // If the player exited:
        else {
            System.out.println("Exiting game...");
            return;
        }
    }

    // Switches the players turn
    private void switch_turns() {
        if (turn == player1) {
            turn = player2;
        }
        else
            turn = player1;
    }

    // Undoes a move
    private void undo() {
        if (!undoStack.empty()) {
            Move move = undoStack.pop();
            board.clearCell(move.row, move.col);
        }
    }
    
    // Asks the human player a choice on what to do
    private int choice(Scanner kbm) {
        int input = 0;

        board.printBoard();

        System.out.println("What do you want to do? \n" +
                    "(1) Move\n" +
                    "(2) Undo\n" + 
                    "(3) Quit game");

        input = kbm.nextInt();

        switch (input) {
            case 1:
                move();
                break;
            case 2:
                undo();
                undo();
                break;
            case 3:
                break;
            case 4:
                System.out.println("Invalid input!");
        }

        return input;
    }

    // Makes the player move
    private void move() {
        Move move = turn.nextMove(board);

        // Tries to place a mark on the given input place
        if (board.place(move)) {
            switch_turns();
            undoStack.push(move);
        }
        // Fail placing the mark 
        else {
            System.out.println("PLEASE RE-ENTER CELL.");
        }
    }
}
