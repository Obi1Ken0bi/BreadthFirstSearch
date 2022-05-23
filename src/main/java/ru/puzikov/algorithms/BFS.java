package ru.puzikov.algorithms;

import lombok.Getter;

import java.util.LinkedList;
import java.util.function.Consumer;

public class BFS {
    private static final Point[] moves=new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
    private final CellType[][] matrix;
    @Getter
    private final boolean[][] visited;
    private final LinkedList<Point> queue = new LinkedList<>();;

    public BFS(int size) {
        this.matrix=new CellType[size][size];
        this.visited=new boolean[size][size];
        this.matrix[size-1][size-1]= CellType.TARGET;
    }


    public Point search(Point cords){
        init(cords);
        while (!queue.isEmpty()){
            cords= queue.poll();

            Point newX = getNextPoint(cords);
            if (newX != null) return newX;

        }


        return cords;
    }
    public Point search(Point cords,Consumer<boolean[][]> consumer){
        init(cords);
        consumer.accept(this.visited);
        while (!queue.isEmpty()){
            cords= queue.poll();

            Point newX = getNextPoint(cords);
            consumer.accept(this.visited);
            if (newX != null) return newX;

        }


        return cords;
    }

    private void init(Point cords) {
        visited[cords.getX()][cords.getY()]=true;
        queue.add(cords);
    }

    public boolean step(Consumer<boolean[][]> consumer){
        if(queue.isEmpty())
            return false;
        Point p = queue.poll();
        Point newX =getNextPoint(p);
        consumer.accept(this.visited);
        return newX != null;
    }
    public boolean step(){
        if(queue.isEmpty())
            return false;
        Point p = queue.poll();
        Point newX =getNextPoint(p);
        return newX != null;
    }

    private Point getNextPoint(Point cords) {
        for (Point move : moves) {
            int newX = cords.getX() + move.getX();
            int newY = cords.getY() + move.getY();
            Point point = new Point(newX, newY);
            if(isOutOfBorder(point))
                return null;
            if (matrix[newX][newY] != CellType.WALL) {
                if(matrix[newX][newY]== CellType.TARGET)
                    return point;
                if(!visited[newX][newY]) {
                    queue.add(point);
                    visited[newX][newY] = true;
                }
            }
        }
        return null;
    }

    private boolean isOutOfBorder(Point point){
        return point.getX() >= matrix.length || point.getY() >= matrix.length || point.getY() < 0 || point.getX() < 0;
    }
}
