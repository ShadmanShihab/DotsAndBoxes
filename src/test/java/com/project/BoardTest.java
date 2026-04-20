package com.project;

import com.project.impl.BoardImpl;
import com.project.impl.BoardRendererImpl;
import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Edge;
import com.project.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    private static final int DEFAULT_GRID_SIZE = 7;
    private static final int DEFAULT_DOT_SIZE = 4;
    private Board board;


    @Before
    public void setUp() throws Exception {
        board = new BoardImpl(4);
    }

    @Test
    public void validGridSizeFromDotCountTest() {
        assertEquals(DEFAULT_GRID_SIZE, board.getGridSize(DEFAULT_DOT_SIZE));
    }


    @Test
    public void boardHasDotsAtCornersTest() {
        char[][] grid = board.getBoard();
        assertEquals('*',  grid[0][0]);
        assertEquals('*',  grid[0][6]);
        assertEquals('*',  grid[6][0]);
        assertEquals('*',  grid[6][6]);
    }

    @Test
    public void boardHasEmptySpaceAtLinePositionTest() {
        char[][] grid = board.getBoard();
        assertEquals(' ', grid[0][ 1]);
        assertEquals(' ', grid[1][0]);
    }

    @Test
    public void boardHasSpacesAtBoxCentresForPlayerNameTest() {
        char[][] grid = board.getBoard();
        assertEquals(' ', grid[1][1]);
        assertEquals(' ', grid[3][3]);
        assertEquals(' ', grid[5][5]);
    }

    @Test
    public void boardIsNotGameOverAtStartTest() {
        assertFalse(board.isGameOver());
    }

    @Test
    public void putHorizontalEdgeInBoardTest() {
        String input = "B0";
        Edge edge = Edge.parse(input, DEFAULT_GRID_SIZE);
        Player player1 = new Player('1', 1, 0);
        board.placeEdgeInBoard(edge, player1);
        char[][] grid = board.getBoard();

        assertEquals('-', grid[0][1]);
    }

    @Test
    public void putVerticalEdgeInBoardTest() {
        String input = "A1";
        Edge edge = Edge.parse(input, DEFAULT_GRID_SIZE);
        Player player1 = new Player('1', 1, 0);
        board.placeEdgeInBoard(edge, player1);
        char[][] grid = board.getBoard();

        assertEquals('|', grid[1][0]);
    }

    @Test
    public void edgeIsAlreadyOccupiedTest() {
        String input = "A1";
        Edge edge = Edge.parse(input, DEFAULT_GRID_SIZE);
        Player player1 = new Player('1', 1, 0);

       board.placeEdgeInBoard(edge, player1);

        assertTrue(board.isOccupied(edge));
    }

    @Test
    public void completingOneBoxReturnsOneTest() {
        Player player1 = new Player('1', 1, 0);
        Player player2 = new Player('2', 2, 0);

        Edge a1 = Edge.parse("A1", DEFAULT_GRID_SIZE);
        Edge b0 = Edge.parse("B0", DEFAULT_GRID_SIZE);
        Edge c1 = Edge.parse("C1", DEFAULT_GRID_SIZE);
        Edge b2 = Edge.parse("B2", DEFAULT_GRID_SIZE);

        board.placeEdgeInBoard(a1, player1);
        board.placeEdgeInBoard(b0, player2);
        board.placeEdgeInBoard(c1, player1);
        board.placeEdgeInBoard(b2, player2);

        int count = board.markCompletedBoxes(b2, player2);;
        assertEquals(1, count);
    }

    @Test
    public void completedBoxCentreMarkedWithPlayerSymbolTest() {
        Player player1 = new Player('1', 1, 0);
        Player player2 = new Player('2', 2, 0);

        Edge a1 = Edge.parse("A1", DEFAULT_GRID_SIZE);
        Edge b0 = Edge.parse("B0", DEFAULT_GRID_SIZE);
        Edge c1 = Edge.parse("C1", DEFAULT_GRID_SIZE);
        Edge b2 = Edge.parse("B2", DEFAULT_GRID_SIZE);

        board.placeEdgeInBoard(a1, player1);
        board.markCompletedBoxes(a1, player1);

        board.placeEdgeInBoard(b0, player2);
        board.markCompletedBoxes(b0, player2);

        board.placeEdgeInBoard(c1, player1);
        board.markCompletedBoxes(c1, player1);

        board.placeEdgeInBoard(b2, player2);
        board.markCompletedBoxes(b2, player2);

        assertEquals('2', board.getBoard()[1][1]);
    }

    @Test
    public void gameOverWhenAllBoxesAreFilled() {
        fillAllEdgesInTheBoard(board, DEFAULT_GRID_SIZE);
        assertTrue(board.isGameOver());
    }

    private void fillAllEdgesInTheBoard(Board board, int gridSize) {
        char[][] grid = board.getBoard();

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (row % 2 == 0 && col % 2 == 1) {
                    grid[row][col] = '-';
                } else if (row % 2 == 1 && col % 2 == 0) {
                    grid[row][col] = '|';
                }
                else if (row % 2 == 1 && col % 2 == 1) {
                    grid[row][col] = '1'; //demo player signs
                }
            }
        }


        BoardRenderer renderer = new BoardRendererImpl();
        Player player1 = new Player('1', 1, 0);
        Player player2 = new Player('2', 2, 0);
        renderer.render(board, player1, player2);
    }
}
