package edu.cpp.tictactoe;
import java.util.*;

public class Game {
    private Player turn;
    private Board board;
    private Player player1;
    private Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void run() {
        turn = player1;

        do {
            Move move = turn.nextMove(board);

            // Tries to place a mark on the given input place
            if (board.place(move)) {
                switch_turns();
            }
            // Fail placing the mark 
            else {
                System.out.println("PLEASE RE-ENTER CELL.");
            }

        } while (board.winner().equals("No Winner Yet"));

        board.printBoard();

        switch (board.winner()) {
            case "X":
                System.out.println("Player X Wins the Tic Tac Toe!");
                break;
            case "O":
                System.out.println("Player O Wins the Tic Tac Toe!");
                break;
            case "Draw":
                System.out.println("This Game Ended in a Draw!");
                break;
        }
    }

    private void switch_turns() {
        if (turn == player1) {
            turn = player2;
        }
        else
            turn = player1;
    }
    
    public static void main(String[] args) {
        int player = 0;
        Scanner kbm = new Scanner(System.in);
        Game game;

        System.out.println("WELCOME TO TIC TAC TOE!");
        System.out.println("Please Select 1 Player or 2 Player\n(1) 1 Player\n(2) 2 Player");

        while (true) {
            if (kbm.hasNextInt()) {
                player = kbm.nextInt();
                if (player == 1 || player == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2:");
                }
            } else {
                System.out.println("That's not a number. Please enter 1 or 2:");
                kbm.next();
            }
        }

        if(player == 1){
            game = new Game(new Board(), new HumanPlayer(Mark.X), new RandomAIPlayer(Mark.O));
        } else {
            game = new Game(new Board(), new HumanPlayer(Mark.X), new HumanPlayer(Mark.O));
        }
        game.run();
    }
}
