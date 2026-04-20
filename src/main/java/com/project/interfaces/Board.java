package com.project.interfaces;

import com.project.model.Edge;
import com.project.model.Player;

public interface Board {
    void placeEdgeInBoard(Edge edge, Player currentPlayer);

    boolean isOccupied(Edge edge);

    boolean isGameOver();

    char[][] getBoard();

    int getGridSize();

    int getGridSize(int numberOfDotsPerRow);

    int markCompletedBoxes(Edge edge, Player currentPlayer);
}
