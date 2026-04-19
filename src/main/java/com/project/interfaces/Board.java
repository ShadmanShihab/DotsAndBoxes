package com.project.interfaces;

import com.project.model.Edge;
import com.project.model.Player;

public interface Board {
    int placeEdge(Edge edge, Player currentPlayer);

    boolean isOccupied(Edge edge);

    boolean isGameOver();

    char[][] getBoard();

    int getGridSize();

    int getGridSizeFromDotCount(int dotCount);
}
