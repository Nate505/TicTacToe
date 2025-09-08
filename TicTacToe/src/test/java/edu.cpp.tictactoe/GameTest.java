package edu.cpp.tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void scriptedGameWin() {
        ScriptedPlayer player1 = new ScriptedPlayer(Mark.X, new int[]{1, 2, 3});
        ScriptedPlayer player2 = new ScriptedPlayer(Mark.O, new int[]{4, 6, 7});
        Board b = new Board(3);
        Game g = new Game(b, player1, player2);
        g.run();

        assertEquals(Mark.X, b.winner());
    }

    @Test
    void scriptedGameDraw() {
        ScriptedPlayer player1 = new ScriptedPlayer(Mark.X, new int[]{2, 3, 4, 8, 9});
        ScriptedPlayer player2 = new ScriptedPlayer(Mark.O, new int[]{1, 5, 6, 7});
        Board b = new Board(3);
        Game g = new Game(b, player1, player2);
        g.run();

        assertEquals(Mark.EMPTY, b.winner());
        assertTrue(b.isFull());
    }

    // ScriptedPlayer acts similar to a human player but has given preset moves
    private class ScriptedPlayer extends Player{
        int turn;
        int[] moveSet;

        public ScriptedPlayer(Mark mark, int[] moveSet) {
            super(mark);
            this.moveSet = moveSet;
            turn = 0;
        }

        @Override
        public Move nextMove(Board b) {
            int place = moveSet[turn++] - 1;
            return new Move(place / 3, place % 3, mark);
        }
    }
}
