package org.example.minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TilesTest {

    @Test
    public void testIfTileIsMine() {
        Tiles tile = new Tiles();
        Assertions.assertFalse(tile.isMine()); //tile shouldnt be mine initially
        tile.setMine(true); //set mine true
        Assertions.assertTrue(tile.isMine()); //now mine is true
    }

    @Test
    public void testIfTileIsFlagged() {
        Tiles tile = new Tiles();
        Assertions.assertFalse(tile.isTileFlagged());
        tile.setTileFlagged(true);
        Assertions.assertTrue(tile.isTileFlagged());
    }

    @Test
    public void testsAssignmentOfTileSymbols() {
        Tiles tiles = new Tiles();
        Assertions.assertEquals('*', tiles.getTileSymbol());
        tiles.setTileSymbol('X');
        Assertions.assertEquals('X', tiles.getTileSymbol());
    }
}
