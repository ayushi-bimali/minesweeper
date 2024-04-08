package org.example.minesweeper;

import java.util.Scanner;

public class Game {
    private Board board;

    public Game(int size) {
        board = new Board(size);
    }

    public void startGame() {
        board.initialiseTiles();
        board.displayBoard();

        //continues the game until it's over
        while (!board.isGameOver()) {
            //get player's move and reveal the corresponding cell
            int[] move = getPlayerMove();
            board.revealTiles(move[0], move[1]);
            board.displayBoard();
        }

        //displays the game result
        if (board.isGameWon()) {
            System.out.println("CONGRATULATIONS! You won!");
        } else {
            System.out.println("GAME OVER! You hit a mine.");
        }
    }

    private int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2]; //new array w 2 inputs
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a row and column separated by a space (add an F to flag tiles): ");
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");

            if (splitInput.length >= 2) {
                try {
                    int row = Integer.parseInt(splitInput[0]);
                    int col = Integer.parseInt(splitInput[1]);

                    if (row >= 1 && row <= board.getBoardSize() && col >= 1 && col <= board.getBoardSize()) { //if input within valid range
                        move[0] = row;
                        move[1] = col;
                        validInput = true;

                        if (splitInput.length > 2 && splitInput[2].equalsIgnoreCase("F")) {
                            board.flagTiles(row, col);
                        }
                    } else {
                        System.out.println("Invalid input. Row and column must be within the board size.");
                    }
                } catch (NumberFormatException e) { //non int numbers catches
                    System.out.println("Invalid input format. Please enter numbers separated by a space.");
                }
            } else {
                System.out.println("Invalid input format. Please enter row and column numbers separated by a space.");
            }
        }
        return move;
    }

}
