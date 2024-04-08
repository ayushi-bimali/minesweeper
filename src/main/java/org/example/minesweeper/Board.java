package org.example.minesweeper;

public class Board {
    private Tiles[][] grid; //2d grid of tiles
    private int size;
    private boolean gameOver;
    private boolean gameWon;
    private int remainingFlags;
    private boolean firstMove;
    private int revealedTilesTotal;

    public Board(int size) { //initialises the board with chosen size
        this.size = size;
        grid = new Tiles[size + 1][size + 1];
        firstMove = true;
        revealedTilesTotal = 0;
    }

    public int getBoardSize() {
        return size;
    }

    public void initialiseTiles() { //initialises each tile on the board
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                grid[i][j] = new Tiles();
            }
        }

        putMinesRandomly();
        remainingFlags = mineCount;
    }

    int mineCount = 0;
    private void putMinesRandomly() {
        //reset all tiles to non-mine
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                grid[i][j].setMine(false);
            }
        }

        //position mines randomly
        mineCount = 0;
        while (mineCount < Math.ceil((size * size) * 1 / 7)) {
            int row = (int) (Math.random() * size) + 1;
            int col = (int) (Math.random() * size) + 1;
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                mineCount++;
            }
        }
    }


    public void revealTiles(int row, int col) {
        //code to make sure first move isnt mine
        if (firstMove && grid[row][col].isMine()) {
            while (grid[row][col].isMine()) {
                putMinesRandomly();
            }
            firstMove = false;
            revealTiles(row, col); // Recursively reveal the selected tile
            return;
        } else {
            firstMove = false;
        }

        if (row < 1 || row > size || col < 1 || col > size || grid[row][col].isTileRevealed() || grid[row][col].isTileFlagged()) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        grid[row][col].setTileRevealed(true);
        if (grid[row][col].isTileRevealed()) {
            revealedTilesTotal++;
        }

        //gameover if revealed tile is mine
        if (grid[row][col].isMine()) {
            gameOver = true;
            grid[row][col].setTileSymbol('M'); //sets mine symbol
            return;
        }

        //counts adjacent mines in 9 tile radius
        int adjacentMines = countAdjacentMines(row, col);
        grid[row][col].setTileSymbol(adjacentMines == 0 ? '0' : (char) (adjacentMines + '0')); //display num of adjacent mines

        if (adjacentMines == 0) {
            //if current tile has no adjacent mines, recursively reveal adjacent tiles
            revealAdjacentMineCount(row, col);
        }

        //winning condition
        if (revealedTilesTotal + mineCount == size*size) {
            gameWon = true;
            gameOver = true;
        }

    }


    public void flagTiles(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            return;
        }
        if (remainingFlags == 0) {
            System.out.println("No flags left!");
            grid[row][col].setTileRevealed(false);
            return;
        }
        if (grid[row][col].isTileFlagged()) { //if tile is already flagged, remove flag
            grid[row][col].setTileFlagged(false);
            grid[row][col].setTileRevealed(false); //reset revealed status of tile
            remainingFlags++;
        } else {
            grid[row][col].setTileFlagged(true);
            grid[row][col].setTileRevealed(false);
            remainingFlags--;
        }
    }


    private void revealAdjacentMineCount(int row, int col) {
        //reveals adjacent cells recursively
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 1 && i <= size && j >= 1 && j <= size) {
                    revealTiles(i, j);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        //counts the number of adjacent mines for given tile
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 1 && i <= size && j >= 1 && j <= size && grid[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void displayBoard() {
        System.out.print("   ");
        for (int j = 1; j <= size; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 1; i <= size; i++) {
            System.out.print(i + "| ");
            for (int j = 1; j <= size; j++) {
                //displays each tile based on its state
                if (grid[i][j].isTileRevealed()) {
                    System.out.print(grid[i][j].getTileSymbol() + " ");
                } else if (grid[i][j].isTileFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

        System.out.println("Remaining flags: " + remainingFlags);
    }

}
