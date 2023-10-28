package edu.project2;

public enum CellType {

    EMPTY(0),
    WALL(1);

    private final int value;

    CellType(int value) {
        this.value = value;
    }
}
