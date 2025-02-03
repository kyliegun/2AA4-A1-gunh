/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

//validates a given sequence
public class PathValidator implements MazeNavigator {
    private final Maze maze; //maze being validated
    private final DirectionAnalyzer directionAnalyzer; //tracks movement and direction
    private final String path; //move is validated

    public PathValidator(Maze maze, DirectionAnalyzer directionAnalyzer, String path) {
        this.maze = maze;
        this.directionAnalyzer = directionAnalyzer;
        this.path = path;
    }

    //tests to see if path reaches exit
    @Override
    public void navigateMaze() {
        for (char move : path.toCharArray()) {
            directionAnalyzer.processMove(move);
        }
    }

    //returns whether the sequence reaches the maze exit or not
    @Override
    public String retrievePath() {
        return directionAnalyzer.getPosition().equals(maze.getExitPoint()) ? "correct path" : "incorrect path";
    }
}
