package ru.puzikov.algorithms;

import lombok.Getter;

import java.util.LinkedList;
import java.util.function.Consumer;

public class BFS {
    private static final Point[] moves = new Point[]{new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
    @Getter
    private final Cell[][] matrix;

    private final LinkedList<Point> queue = new LinkedList<>();

    public BFS(int size) {
        this.matrix = new Cell[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = new Cell();
            }
        }
        this.matrix[size - 1][size - 1].setType(CellType.TARGET);
    }


    public Point search(Point cords) {
        init(cords);
        while (!queue.isEmpty()) {
            cords = queue.poll();

            Point newX = getNextPoint(cords);
            if (newX != null) return newX;

        }


        return cords;
    }

    public Point search(Point cords, Consumer<Point> consumer) {
        int i = 0;
        init(cords);
        while (!queue.isEmpty()) {
            cords = queue.poll();
            consumer.accept(cords);

            Point newX = getNextPoint(cords);

            if (newX != null) return newX;

        }


        return cords;
    }


    private void init(Point cords) {
        matrix[cords.getX()][cords.getY()].setVisited(true);
        queue.add(cords);
    }

    public boolean step(Consumer<Cell[][]> consumer) {
        if (queue.isEmpty())
            return false;
        Point p = queue.poll();
        Point newX = getNextPoint(p);
        consumer.accept(this.matrix);
        return newX != null;
    }

    public boolean step() {
        if (queue.isEmpty())
            return false;
        Point p = queue.poll();
        Point newX = getNextPoint(p);
        return newX != null;
    }

    public void setWall(Point p) {
        this.matrix[p.getX()][p.getY()].setType(CellType.WALL);
    }

    private Point getNextPoint(Point cords) {
        for (Point move : moves) {
            int newX = cords.getX() + move.getX();
            int newY = cords.getY() + move.getY();
            Point point = new Point(newX, newY);
            if (isOutOfBorder(point))
                continue;
            if (matrix[newX][newY].getType() != CellType.WALL) {
                if (matrix[newX][newY].getType() == CellType.TARGET)
                    return point;
                if (!matrix[newX][newY].isVisited()) {
                    queue.add(point);
                    matrix[newX][newY].setVisited(true);
                }
            }
        }
        return null;
    }


    private boolean isOutOfBorder(Point point) {
        return point.getX() >= matrix.length || point.getY() >= matrix.length || point.getY() < 0 || point.getX() < 0;
    }
}
