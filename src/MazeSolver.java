import java.util.LinkedList;
import java.util.Queue;

public class MazeSolver {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean bfs(int[][] maze, Point start, Point end) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == end.x && point.y == end.y) {
                return true;  // Ziel gefunden
            }

            for (int[] dir : directions) {
                int newX = point.x + dir[0];
                int newY = point.y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && maze[newX][newY] == 0 && !visited[newX][newY]) {
                    queue.add(new Point(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
        return false; // Kein Pfad gefunden
    }
}
