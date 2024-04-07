package org.example.minesweeper;

public class Tiles {
    private boolean isMine;
    private boolean tileRevealed;
    private char tileSymbol;

    private boolean tileFlagged;

    public Tiles() {
        //initialises a tile with default values
        isMine = false;
        tileRevealed = false;
        tileSymbol = '*';
        tileFlagged = false;
    }

    public boolean isMine() {
        //checks if the tile contains a mine
        return isMine;
    }

    public void setMine(boolean mine) {
        //sets the mine status of the tile
        this.isMine = mine;
    }

    public boolean isTileRevealed() {
        //checks if the tile has been revealed
        return tileRevealed;
    }

    public void setTileRevealed(boolean tileRevealed) {
        //sets the revealed status of the tile
        this.tileRevealed = tileRevealed;
    }

    public char getTileSymbol() {
        return tileSymbol;
    }

    public void setTileSymbol(char tileSymbol) {
        this.tileSymbol = tileSymbol;
    }

    public boolean isTileFlagged() {
        return tileFlagged;
    }

    public void setTileFlagged(boolean tileFlagged) {
        this.tileFlagged = tileFlagged;
    }


}
