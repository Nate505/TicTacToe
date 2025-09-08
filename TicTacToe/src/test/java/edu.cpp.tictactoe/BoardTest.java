package edu.cpp.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    void boardChangeDetected() {
        Board b = new Board(3);
        assertEquals(Mark.EMPTY, b.getCell(0, 0));

        b.place(new Move(0, 0, Mark.X));
        assertEquals(Mark.X, b.getCell(0, 0));
    }

    @Test
    void boardNoWinnerYetDetected() {
        Board b = new Board(3);
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(0, 2, Mark.O));
        b.place(new Move(1, 0, Mark.X));
        b.place(new Move(1, 1, Mark.O));

        // Checks if the board has no decided winner yet and is not a draw
        assertEquals(Mark.EMPTY, b.winner());
        assertFalse(b.isFull());
    }

    @Test
    void boardDrawDetected(){
        Board b = new Board(3);
        /**
         * Board will be:
         * XXO
         * OOX
         * XOX
         */
        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(0, 1, Mark.X));
        b.place(new Move(0, 2, Mark.O));
        b.place(new Move(1, 0, Mark.O));
        b.place(new Move(1, 1, Mark.O));
        b.place(new Move(1, 2, Mark.X));
        b.place(new Move(2, 0, Mark.X));
        b.place(new Move(2, 1, Mark.O));
        b.place(new Move(2, 2, Mark.X));

        // Makes sure it is both full and has no winner
        assertEquals(Mark.EMPTY, b.winner());
        assertTrue(b.isFull());
    }

    @Test
    void rowWinDetected() {
        Board b = new Board(3);
        b.place(new Move(0,0, Mark.X));
        b.place(new Move(1,0, Mark.O));
        b.place(new Move(0,1, Mark.X));
        b.place(new Move(1,1, Mark.O));
        b.place(new Move(0,2, Mark.X));
        assertEquals(Mark.X, b.winner());
    }

    @Test
    void detectMoveOverlap() {
        Board b = new Board(3);

        // Place returns true if the placing succeeded, and false if not
        assertTrue(b.place(new Move(0, 0, Mark.X)));
        assertFalse(b.place(new Move(0, 0, Mark.O)));
    }

    @Test
    void moveOverlapHandling() {
        Board b = new Board(3);

        b.place(new Move(0, 0, Mark.X));
        b.place(new Move(0, 0, Mark.O));

        // The cell must have the original mark even after trying to place another on top
        assertEquals(Mark.X, b.getCell(0, 0));
    }
}
