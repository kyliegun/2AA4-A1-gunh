package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver implements MazeNavigator {
    private final Maze maze;
    private final DirectionAnalyzer directionAnalyzer;
    private final PathStrategy strategy;
    private String pathResult;

    public MazeSolver(Maze maze, DirectionAnalyzer directionAnalyzer, PathStrategy strategy) {
        this.maze = maze;
        this.directionAnalyzer = directionAnalyzer;
        this.strategy = strategy;
    }

    @Override
    public void navigateMaze() {
        strategy.computeRoute(maze, directionAnalyzer);
        this.pathResult = strategy.fetchRoute();
    }

    @Override
    public String retrievePath() {
        return this.pathResult;
    }
}
