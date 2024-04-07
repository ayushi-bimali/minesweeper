package org.example.minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testIfBoardIsCorrectSizeAccordingToInput() {
        int size = 5;
        Board board = new Board(size);
        Assertions.assertEquals(size, board.getBoardSize());
        Assertions.assertEquals(size, board.getBoardSize());
    }
}
