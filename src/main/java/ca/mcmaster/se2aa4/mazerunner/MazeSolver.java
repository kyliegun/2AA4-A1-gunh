/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

//Controls excecution of the strategy
public class MazeSolver implements MazeNavigator {
    private final Maze maze; //maze that will be solved
    private final DirectionAnalyzer directionAnalyzer; //movement and dir
    private final PathStrategy strategy; //pathfinding
    private String pathResult; //stores path

    //constructors a mazesolved with the maze, direction analyzer, and solving strategy
    public MazeSolver(Maze maze, DirectionAnalyzer directionAnalyzer, PathStrategy strategy) {
        this.maze = maze;
        this.directionAnalyzer = directionAnalyzer;
        this.strategy = strategy;
    }

    //executes path
    @Override
    public void navigateMaze() {
        strategy.computeRoute(maze, directionAnalyzer);
        this.pathResult = strategy.fetchRoute();

    }

    //returns the path after solving the maze
    @Override
    public String retrievePath() {
        return this.pathResult;
    }
}
