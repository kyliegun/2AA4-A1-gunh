package ca.mcmaster.se2aa4.mazerunner;

public class PathValidator implements MazeNavigator {
    private final Maze maze;
    private final DirectionAnalyzer directionAnalyzer;
    private final String path;

    public PathValidator(Maze maze, DirectionAnalyzer directionAnalyzer, String path) {
        this.maze = maze;
        this.directionAnalyzer = directionAnalyzer;
        this.path = path;
    }

    @Override
    public void navigateMaze() {
        for (char move : path.toCharArray()) {
            directionAnalyzer.processMove(move);
        }
    }

    @Override
    public String retrievePath() {
        return directionAnalyzer.getPosition().equals(maze.getExitPoint()) ? "correct path" : "incorrect path";
    }
}
