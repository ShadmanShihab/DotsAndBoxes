package com.project.impl;

import com.project.enums.EdgeType;
import com.project.interfaces.Board;
import com.project.model.Edge;
import com.project.model.Player;

public class BoardImpl implements Board {
    private int gridSize;
    private char[][] grid;


    public BoardImpl(int dotCount) {
        if (dotCount < 2) {
            throw new IllegalArgumentException("Board must have at least 2 dots per side");
        }
        this.gridSize = 2 * dotCount - 1;
        this.grid = new char[gridSize][gridSize];


        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                grid[row][col] = (row % 2 == 0 && col % 2 == 0) ? '*' : ' ';
            }
        }
    }

    @Override
    public int placeEdge(Edge edge, Player currentPlayer) {
        char move = edge.getType() == EdgeType.HORIZONTAL ? '-' : '|';
        grid[edge.getRow()][edge.getCol()] = move;
        return markCompletedBoxed(edge, currentPlayer);
    }

    @Override
    public boolean isOccupied(Edge edge) {
        return grid[edge.getRow()][edge.getCol()] != ' ';
    }

    @Override
    public boolean isGameOver() {
        for (int row = 1; row < gridSize; row += 2) {
            for (int col = 1; col < gridSize; col += 2) {
                if (grid[row][col] == ' ') return false;
            }
        }
        return true;
    }


    @Override
    public char[][] getBoard() {
        return grid;
    }

    @Override
    public int getGridSize() {
        return gridSize;
    }

    private int markCompletedBoxed(Edge edge, Player currentPlayer) {
        int count = 0;

        if (edge.getType() == EdgeType.HORIZONTAL) {
            count += claimBoxIfComplete(edge.getRow() - 1, edge.getCol(), currentPlayer);
            count += claimBoxIfComplete(edge.getRow() + 1, edge.getCol(), currentPlayer);
        } else {
            count += claimBoxIfComplete(edge.getRow(), edge.getCol() - 1, currentPlayer);
            count += claimBoxIfComplete(edge.getRow(), edge.getCol() + 1, currentPlayer);
        }

        return count;
    }

    private int claimBoxIfComplete(int row, int col, Player player) {
        if (!isInsideGrid(row, col)) return 0;
        if (!isBoxCentre(row, col))  return 0;
        if (grid[row][col] != ' ')   return 0;

        if (isBoxComplete(row, col)) {
            grid[row][col] = player.getName();
            return 1;
        }
        return 0;
    }

    private boolean isBoxComplete(int row, int col) {
        return grid[row - 1][col] == '-'  // top
                && grid[row + 1][col] == '-'  // bottom
                && grid[row][col - 1] == '|'  // left
                && grid[row][col + 1] == '|'; // right
    }

    private boolean isInsideGrid(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }

    private boolean isBoxCentre(int row, int col) {
        return row % 2 == 1 && col % 2 == 1;
    }
}
