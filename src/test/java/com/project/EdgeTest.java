package com.project;

import com.project.enums.EdgeType;
import com.project.model.Edge;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest {
    private static final int DEFAULT_GRID_SIZE = 7;
    private static final int DEFAULT_DOT_SIZE = 4;

    @Test
    public void equalEdgesAreEqual() {
        Edge e1 = Edge.parse("B2", DEFAULT_GRID_SIZE);
        Edge e2 = Edge.parse("B2", DEFAULT_GRID_SIZE);
        assertEquals(e1, e2);
    }

    @Test
    public void inputPositionsAreValidTest() {
        boolean isValid = Edge.isInputPositionValid(3, 2);
        assertEquals(true, isValid);
    }

    @Test
    public void inputPositionsAreInvalidTest() {
        boolean isValid = Edge.isInputPositionValid(3, 1);
        assertEquals(false, isValid);
    }

    @Test
    public void isCenterOfTheBoxTest() {
        boolean isValid = Edge.isCenterOfTheBox(3, 1);
        assertEquals(true, isValid);
    }

    @Test
    public void horizontalEdgeStoresCorrectSymbolTest() {
        Edge e1 = Edge.parse("B0", DEFAULT_GRID_SIZE);
        assertEquals(EdgeType.HORIZONTAL, e1.getType());
    }

    @Test
    public void verticalEdgeStoresCorrectSymbolTest() {
        Edge e1 = Edge.parse("A1", DEFAULT_GRID_SIZE);
        assertEquals(EdgeType.VERTICAL, e1.getType());
    }
}

