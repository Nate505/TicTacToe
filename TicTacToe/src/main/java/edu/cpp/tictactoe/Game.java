package edu.cpp.tictactoe;

import java.util.Scanner;

public class Game {
    private static String[] symbols; 
    private static String turn;
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int test = 0;
        turn = "X";
        symbols = new String[9];
        for(int i = 0; i < symbols.length; i++){
            symbols[i] = String.valueOf(i + 1);
        }
        Board board = new Board();

        System.out.println("WELCOME TO TIC TAC TOE!");

        board.printBoard(symbols);

        do{
            System.out.println("PLEASE SELECT WHICH CELL:");
            test = kb.nextInt();

            if(board.getCell(test, symbols)){
                System.out.println("PLEAE RE-ENTER CELL");
            }else if(turn.equals("X")){
                board.place(test, Mark.X, symbols);
                turn = "O";
            }else{
                board.place(test, Mark.O, symbols);
                turn = "X";
            }

            board.printBoard(symbols);

            
            
        }while (board.winner(symbols).equals("No Winner Yet"));

        switch (board.winner(symbols)) {
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
}
