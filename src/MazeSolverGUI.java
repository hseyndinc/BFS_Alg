import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MazeSolverGUI extends Application {
    private static final int TILE_SIZE = 40;  // Größe jedes Rechtecks
    private static final int[][] MAZE = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
    };

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

                // Hinzufügen eines Mausklick-Event-Handlers
                final int r = row;
                final int c = col;
                rect.setOnMouseClicked(event -> {
                    // Wechsel zwischen Wand und Weg
                    MAZE[r][c] = (MAZE[r][c] == 1) ? 0 : 1;
                    setColor(rect, MAZE[r][c]);
                });

                grid.add(rect, col, row);
            }
        }
    }

    // Methode zum Setzen der Farbe basierend auf dem Zellenwert
    private void setColor(Rectangle rect, int value) {
        if (value == 1) {
            rect.setFill(Color.GRAY); // Wand
        } else {
            rect.setFill(Color.WHITE); // Weg
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
