import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MazeSolverGUI extends Application {
    private static final int TILE_SIZE = 40;
    private static final int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

    private Point start = null;  // Startpunkt
    private Point end = null;    // Zielpunkt

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        drawMaze(grid);

        Scene scene = new Scene(grid, MAZE[0].length * TILE_SIZE, MAZE.length * TILE_SIZE);
        primaryStage.setTitle("Maze Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMaze(GridPane grid) {
        for (int row = 0; row < MAZE.length; row++) {
            for (int col = 0; col < MAZE[0].length; col++) {
                Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
                setColor(rect, MAZE[row][col]);

                final int r = row;
                final int c = col;
                rect.setOnMouseClicked(event -> {
                    // Beim ersten Klick setzen wir den Startpunkt
                    if (start == null) {
                        start = new Point(r, c);
                        rect.setFill(Color.GREEN);
                    }
                    // Beim zweiten Klick setzen wir den Zielpunkt
                    else if (end == null) {
                        end = new Point(r, c);
                        rect.setFill(Color.RED);
                    }
                    // Falls Start und Ziel bereits gesetzt sind, wechseln wir zwischen Wand und Weg
                    else {
                        if (MAZE[r][c] == 1) {
                            MAZE[r][c] = 0; // Wird zu einem begehbaren Weg
                            rect.setFill(Color.WHITE);
                        } else {
                            MAZE[r][c] = 1; // Wird zu einer Wand
                            rect.setFill(Color.GRAY);
                        }
                    }
                });

                grid.add(rect, col, row);
            }
        }
    }

    private void setColor(Rectangle rect, int value) {
        if (value == 1) {
            rect.setFill(Color.GRAY);  // Wand
        } else {
            rect.setFill(Color.WHITE); // Weg
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Hilfsklasse zur Darstellung eines Punkts im Labyrinth
    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
