package ru.puzikov.algorithms;

import lombok.Data;

@Data
public class Cell {
    private CellType type = CellType.FREE;
    private boolean visited = false;
}
