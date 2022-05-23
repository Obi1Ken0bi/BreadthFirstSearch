package ru.puzikov.algorithms;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.SneakyThrows;


public class BfsApplication extends Application {
    private final int MATRIX_SIZE = 10;
    private final int CANVAS_SIZE = 1000;
    private final int BLOCK_SIZE = CANVAS_SIZE / MATRIX_SIZE;
    private BFS bfs;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("BFS");
        bfs = new BFS(MATRIX_SIZE);
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();
        drawBase();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, CANVAS_SIZE, CANVAS_SIZE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && !mouseEvent.isSecondaryButtonDown())
                    beginSearch(new Point((int) (mouseEvent.getX() / BLOCK_SIZE), (int) (mouseEvent.getY() / BLOCK_SIZE)));
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isSecondaryButtonDown())
                    setWall(new Point((int) (mouseEvent.getX() / BLOCK_SIZE), (int) (mouseEvent.getY() / BLOCK_SIZE)));
            }
        });
        stage.show();
    }

    public void drawBase() {
        drawField(bfs.getMatrix());
    }

    public void drawField(Cell[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                gc.setStroke(Color.WHITE);
                Cell cell = matrix[i][j];
                if (cell.isVisited())
                    gc.setFill(Color.YELLOW);
                else gc.setFill(Color.BLACK);
                if (cell.getType() == CellType.TARGET)
                    gc.setFill(Color.GREEN);
                if (cell.getType() == CellType.WALL)
                    gc.setFill(Color.NAVY);
                gc.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE, i * BLOCK_SIZE + BLOCK_SIZE, j * BLOCK_SIZE + BLOCK_SIZE);
                gc.strokeRect(i * BLOCK_SIZE, j * BLOCK_SIZE, i * BLOCK_SIZE + BLOCK_SIZE, j * BLOCK_SIZE + BLOCK_SIZE);
            }
        }
    }

    @SneakyThrows
    public void drawCell(Point point) {

        gc.setStroke(Color.WHITE);
        Cell cell = bfs.getMatrix()[point.getX()][point.getY()];
        if (cell.isVisited())
            gc.setFill(Color.YELLOW);
        else gc.setFill(Color.BLACK);
        if (cell.getType() == CellType.TARGET)
            gc.setFill(Color.GREEN);
        if (cell.getType() == CellType.WALL)
            gc.setFill(Color.NAVY);
        gc.fillRect(point.getX() * BLOCK_SIZE, point.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        gc.strokeRect(point.getX() * BLOCK_SIZE, point.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);


    }

    private void beginSearch(Point p) {

        Point[] points = bfs.search(p, this::drawCell);
        drawWinPath(points);


    }

    private void drawWinPath(Point[] points) {
        for (Point point : points) {
            gc.setStroke(Color.WHITE);
            gc.setFill(Color.RED);
            gc.fillRect(point.getX() * BLOCK_SIZE, point.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            gc.strokeRect(point.getX() * BLOCK_SIZE, point.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }


    }

    private void setWall(Point point) {
        bfs.setWall(point);
        drawCell(point);
    }
}