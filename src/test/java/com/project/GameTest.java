package com.project;

import com.project.impl.BoardImpl;
import com.project.impl.BoardRendererImpl;
import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Player;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private static int DEFAULT_DOT_COUNT = 4;

    @Test
    public void playerGetsExtraTurnAfterCompletingBoxTest() {
        String moves = String.join("\n",
                "B0",
                "A1",
                "B2",
                "C1"    //Player 2 gives the last turn and completes a box
        );


        Scanner scanner = new Scanner(moves);
        Board board             = new BoardImpl(4);
        BoardRenderer renderer = new BoardRendererImpl();
        Player player1          = new Player('1', 1, 0);
        Player player2          = new Player('2', 2, 0);

        Game game = new Game(scanner, board, renderer, player1, player2);
        game.start();

        // Assert — after completing the box, it should still be Player 1's turn
        assertEquals(2, game.getCurrentPlayerId());

        scanner.close();
    }

    @Test
    public void fullGamePlayerTwoWins() {
        String moves = String.join("\n",
                "B0", "A1", "B2", "C1",
                "D6", "C5", "D4", "E5",
                "C3", "D2", "F2", "F4",
                "G3", "E3",
                "G1", "F0", "D0", "E1",
                "A3", "B4", "A5", "B6",
                "G5", "F6"
        );
        //PLAYER 2 SHOULD WIN

        Scanner scanner = new Scanner(moves);
        Board board             = new BoardImpl(DEFAULT_DOT_COUNT);
        BoardRenderer renderer = new BoardRendererImpl();
        Player player1          = new Player('1', 1, 0);
        Player player2          = new Player('2', 2, 0);

        Game game = new Game(scanner, board, renderer, player1, player2);
        game.start();

        assertEquals(4, player1.getScore());
        assertEquals(5, player2.getScore());
    }

}
