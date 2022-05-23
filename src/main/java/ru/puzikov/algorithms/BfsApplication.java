package ru.puzikov.algorithms;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class BfsApplication extends Application {
    private  BFS bfs;
    private final int MATRIX_SIZE =10;
    private final int CANVAS_SIZE = 1000;

    private final int BLOCK_SIZE =CANVAS_SIZE/ MATRIX_SIZE;
    private GraphicsContext gc;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("BFS");
        bfs=new BFS(MATRIX_SIZE);
        Group root=new Group();
        Canvas canvas=new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();
        drawBase();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root,CANVAS_SIZE,CANVAS_SIZE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                beginSearch(new Point((int) (mouseEvent.getX()/BLOCK_SIZE), (int) (mouseEvent.getY()/BLOCK_SIZE)));
            }
        });
        stage.show();
    }
    public void drawBase(){
        drawField(bfs.getVisited());
    }
    public void drawField(boolean[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                gc.setStroke(Color.WHITE);
                if (matrix[i][j])
                    gc.setFill(Color.YELLOW);
                else gc.setFill(Color.BLACK);
                gc.fillRect(i*BLOCK_SIZE,j*BLOCK_SIZE,i*BLOCK_SIZE+BLOCK_SIZE,j*BLOCK_SIZE+BLOCK_SIZE);
                gc.strokeRect(i*BLOCK_SIZE,j*BLOCK_SIZE,i*BLOCK_SIZE+BLOCK_SIZE,j*BLOCK_SIZE+BLOCK_SIZE);
            }
        }
    }

    private void beginSearch(Point p){
        bfs.search(p, this::drawField);
    }

    public static void main(String[] args) {
        launch();

    }
}