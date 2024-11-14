public class Main {
    public static void main(String[] args) {
        // Beispiel-Labyrinth (1 = Wand, 0 = Weg)
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        // Start- und Endpunkte definieren
        MazeSolver.Point start = new MazeSolver.Point(1, 1); // Start bei (1,1)
        MazeSolver.Point end = new MazeSolver.Point(3, 5);   // Ziel bei (3,5)

        // MazeSolver-Instanz erstellen und bfs aufrufen
        MazeSolver solver = new MazeSolver();
        boolean pathExists = solver.bfs(maze, start, end);

        // Ergebnis ausgeben
        if (pathExists) {
            System.out.println("Ein Pfad wurde gefunden!");
        } else {
            System.out.println("Kein Pfad zum Ziel gefunden.");
        }
    }
}
