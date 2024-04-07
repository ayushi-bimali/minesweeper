package org.example.minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validBoardSizeInput = false;
        int size = 0;

        while (!validBoardSizeInput) {
            System.out.print("How big would you like the board to be? \nEnter a number: ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size > 0) {
                    validBoardSizeInput = true;
                } else {
                    System.out.println("Invalid input. Please try again by entering a positive number. ");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number. ");
                scanner.next();
            }

        }

        //creates a new game instance and start it
        Game game = new Game(size);
        game.startGame();
    }
}
