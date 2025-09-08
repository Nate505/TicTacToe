package edu.cpp.tictactoe;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        int player = 0;
        int boardSize = 0;
        Scanner kbm = new Scanner(System.in);
        Game game;

        System.out.println("WELCOME TO TIC TAC TOE!");
        System.out.println("Please Select 1 Player or 2 Player\n(1) 1 Player\n(2) 2 Player");

        // Ask player for either 1 player mode or 2 player mode
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

        System.out.println("How big of a board would you like? (n x n) (Minimum of 3 x 3) \nPlease enter a number: ");

        // Ask player for either 1 player mode or 2 player mode
        while (true) {
            if (kbm.hasNextInt()) {
                boardSize = kbm.nextInt();
                if (boardSize >= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number equal or greater than 3:");
                }
            } else {
                System.out.println("That's not a number. Please enter a number equal or greater than 3:");
                kbm.next();
            }
        }

        // Start the game on the player's preferences
        if(player == 1){
            game = new Game(new Board(boardSize), new HumanPlayer(Mark.X, boardSize), new RandomAIPlayer(Mark.O));
        } else {
            game = new Game(new Board(boardSize), new HumanPlayer(Mark.X, boardSize), new HumanPlayer(Mark.O, boardSize));
        }

        game.run();
        kbm.close();
    }
}
