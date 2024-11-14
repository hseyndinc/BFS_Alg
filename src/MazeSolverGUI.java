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
                if (MAZE[row][col] == 1) {
                    rect.setFill(Color.GRAY);  // Wände in Grau
                } else {
                    rect.setFill(Color.WHITE);  // Wege in Weiß
                }
                grid.add(rect, col, row);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
