package com.project.model;

import com.project.enums.EdgeType;

import java.util.Objects;

public class Edge {
    private final int row;
    private final int col;
    private final EdgeType type;
    private final boolean valid;

    private Edge(int row, int column, boolean valid, EdgeType edgeType) {
        this.row = row;
        this.col = column;
        this.valid = valid;
        this.type = edgeType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public EdgeType getType() {
        return type;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return row == edge.row && col == edge.col && type == edge.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, type);
    }

    @Override
    public String toString() {
        return "Edge{row=" + row + ", col=" + col + ", type=" + type + "}";
    }

    public static Edge parse(String input, int gridSize) {
        if (input == null || input.trim().length() != 2) {
            return invalid();
        }

        String trimmed = input.trim().toUpperCase();
        char colChar = trimmed.charAt(0);
        char rowChar = trimmed.charAt(1);

        char maxColChar = (char) ('A' + gridSize - 1);
        char maxRowChar = (char) ('0' + gridSize - 1);

        if (colChar < 'A' || colChar > maxColChar)
            return invalid();
        if (rowChar < '0' || rowChar > maxRowChar)
            return invalid();

        int col = colChar - 'A';
        int row = rowChar - '0';

        if (!isInputPositionValid(row, col) || isCenterOfTheBox(row, col)) {
            return invalid();
        }
        EdgeType type = (row % 2 == 0) ? EdgeType.HORIZONTAL : EdgeType.VERTICAL;

        return new Edge(row, col,true, type);
    }

    public static boolean isInputPositionValid(int row, int col) {
        //even row / odd col  → horizontal line
        boolean isHorizontal = (row % 2 == 0 && col % 2 == 1);

        //odd row  / even col → vertical line
        boolean isVertical   = (row % 2 == 1 && col % 2 == 0);
        return isHorizontal || isVertical;
    }

    public static boolean isCenterOfTheBox(int row, int col) {
        return row % 2 == 1 && col % 2 == 1;
    }

    private static Edge invalid() {
        return new Edge(-1, -1, false, null);
    }
}
