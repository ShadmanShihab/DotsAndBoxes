package com.project.impl;

import com.project.interfaces.Board;
import com.project.interfaces.BoardRenderer;
import com.project.model.Player;

public class BoardRendererImpl implements BoardRenderer {

    @Override
    public void render(Board board, Player player1, Player player2) {
        printBoard(board);
        printScore(player1, player2);
    }

    private void printBoard(Board board) {
        StringBuilder sb = new StringBuilder("  ");
        for (int col = 0; col < board.getGridSize(); col++) {
            sb.append((char) ('A' + col));
        }
        String columnHeaderAlp = sb.toString();
        System.out.println(columnHeaderAlp);


        char[][] grid = board.getBoard();

        for (int row = 0; row < grid.length; row++) {
            System.out.println(buildRow(row, grid[row]));
        }
    }

    private String buildRow(int rowNumber, char[] cells) {
        StringBuilder sb = new StringBuilder();
        sb.append(rowNumber).append(' ');
        for (char cell : cells) {
            sb.append(cell);
        }
        // Strip trailing spaces so empty rows look clean
        return sb.toString().stripTrailing();
    }

    private void printScore(Player player1, Player player2) {
        System.out.println("SCORE Player 1: " + player1.getScore()
                + " Player 2: " + player2.getScore() + "\n");

    }
}
