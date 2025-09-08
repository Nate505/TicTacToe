package edu.cpp.tictactoe;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        int player = 0;
        int boardSize = 0;
        int[] scores = {0, 0};
        int input = 0;
        Scanner kbm = new Scanner(System.in);

        System.out.println("WELCOME TO TIC TAC TOE!");

        // Ask player for either 1 player mode or 2 player mode
        player = askPlayerCount(kbm);

        do {
            Mark winner;

            // Ask player for board size
            boardSize = askBoardSize(kbm);

            // Start the game on the player's preferences
            winner = createGame(boardSize, player);

            // Increment score based on the winner and print it
            incrementScore(winner, scores);
            printScore(scores);

            // Ask the player if they wanted to restart or exit the game
            input = askRestart(kbm);
        } while (input != 2);

        kbm.close();
    }

    // Ask the player for how many human players are in the game
    private static int askPlayerCount(Scanner kbm) {
        int player = 0;
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

        return player;
    }

    // Ask the player for the size of the board
    private static int askBoardSize(Scanner kbm) {
        int boardSize = 0;

        System.out.println("How big of a board would you like? (n x n) (Minimum of 3 x 3) \nPlease enter a number: ");

        while (true) {
            if (kbm.hasNextInt()) {
                boardSize = kbm.nextInt();
                if (boardSize >= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a board size >= 3:");
                }
            } else {
                System.out.println("That's not a number. Please enter a board size >= 3:");
                kbm.next();
            }
        }

        return boardSize;
    }

    // Increment score based on who won
    private static void incrementScore(Mark winner, int[] scores) {
        switch (winner) {
            case X -> scores[0]++;
            case O -> scores[1]++;
        }
    }

    // Print the score
    private static void printScore(int[] scores) {
        System.out.printf("Score: %d - %d\n", scores[0], scores[1]);
    }

    // Ask the player if they want to restart or exit the game
    private static int askRestart(Scanner kbm) {
        int input = 0;

        System.out.println("Do you want to play another game?\n" +
                "(1) Play another game\n" +
                "(2) Exit");

        while (true) {
            if (kbm.hasNextInt()) {
                input = kbm.nextInt();
                if (input == 1 || input == 2)
                    break;
                else
                    System.out.println("Invalid choice. Please select one of the options: ");
            }
            else {
                System.out.println("That's not a number. Please select one of the options: ");
                kbm.next();
            }
        }

        return input;
    }

    // Create the game given the player's input
    private static Mark createGame(int boardSize, int numHumanPlayers) {
        Game game;
        switch (numHumanPlayers) {
            case 1:
                game = new Game(new Board(boardSize), new HumanPlayer(Mark.X, boardSize), new RandomAIPlayer(Mark.O));
                break;
            case 2:
                game = new Game(new Board(boardSize), new HumanPlayer(Mark.X, boardSize), new HumanPlayer(Mark.O, boardSize));
                break;
            default:
                throw new RuntimeException("Something went wrong!");
        }

        return game.run();
    }
}
