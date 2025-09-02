package edu.cpp.tictactoe;

import java.util.Scanner;

public class Game {
    private static Mark turn;
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int input = 0;
        turn = Mark.X;
        Board board = new Board();

        System.out.println("WELCOME TO TIC TAC TOE!");

        board.printBoard();

        do {
            System.out.println("PLEASE SELECT WHICH CELL:");
            input = kb.nextInt() - 1;

            // Tries to place a mark on the given input place
            if (board.place(new Move(input / 3, input % 3, turn))) {
                switch (turn) {
                    case X:
                        turn = Mark.O;
                        break;
                    case O:
                        turn = Mark.X;
                        break;
                }
            }
            // Fail placing the mark 
            else {
                System.out.println("PLEASE RE-ENTER CELL.");
            }

            board.printBoard();

        } while (board.winner().equals("No Winner Yet"));

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

        kb.close();
    }
}
