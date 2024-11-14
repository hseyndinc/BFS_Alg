import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private Rectangle[][] rectArray; // Array zur Speicherung der Rechtecke

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        rectArray = new Rectangle[MAZE.length][MAZE[0].length];
        drawMaze(grid);

        // Erstelle den Reset-Button
        Button resetButton = new Button("Reset Start & Ziel");
        resetButton.setOnAction(event -> resetStartAndEnd());

        // Layout einrichten
        VBox controls = new VBox(10, resetButton);
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(controls);

        Scene scene = new Scene(root, MAZE[0].length * TILE_SIZE, MAZE.length * TILE_SIZE + 50);
        primaryStage.setTitle("Maze Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMaze(GridPane grid) {
        for (int row = 0; row < MAZE.length; row++) {
            for (int col = 0; col < MAZE[0].length; col++) {
                Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
                setColor(rect, MAZE[row][col]);
                rectArray[row][col] = rect; // Rechteck im Array speichern

                final int r = row;
                final int c = col;
                rect.setOnMouseClicked(event -> {
                    // Startpunkt setzen, wenn keiner existiert
                    if (start == null) {
                        start = new Point(r, c);
                        rect.setFill(Color.GREEN);
                    }
                    // Zielpunkt setzen, wenn keiner existiert
                    else if (end == null) {
                        end = new Point(r, c);
                        rect.setFill(Color.RED);
                    }
                    // Zwischen Wand und Weg wechseln, wenn Start und Ziel bereits gesetzt sind
                    else {
                        if (MAZE[r][c] == 1) {
                            MAZE[r][c] = 0; // Zu Weg ändern
                            rect.setFill(Color.WHITE);
                        } else {
                            MAZE[r][c] = 1; // Zu Wand ändern
                            rect.setFill(Color.GRAY);
                        }
                    }
                });

                grid.add(rect, col, row);
            }
        }
    }

    // Methode zum Zurücksetzen von Start- und Zielpunkt
    private void resetStartAndEnd() {
        if (start != null) {
            rectArray[start.x][start.y].setFill(Color.WHITE); // Setze Startpunkt zurück
            start = null;
        }
        if (end != null) {
            rectArray[end.x][end.y].setFill(Color.WHITE); // Setze Zielpunkt zurück
            end = null;
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
