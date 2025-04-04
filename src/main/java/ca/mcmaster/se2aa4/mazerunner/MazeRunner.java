/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Execution of the maze solving process
public class MazeRunner {
    private static final Logger logger = LogManager.getLogger(); //logger for debugging
    private Maze maze; //grid structure
    private DirectionAnalyzer directionAnalyzer; //handles movement and direction changes
    private FormatPath formatter; //formats the path sequence

    //Constructor that initializes the maze and sets entry point
    public MazeRunner(String mazeFile) {
        this.maze = new Maze(mazeFile); //retrieves maze from file
        maze.setEntryPoint(); //finds entry point
        this.directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntryPoint());
        this.formatter = new FormatPath();
    }

    public void verifyPath(String moveSequence) {
        logger.info("Verifying provided path...");
    
        // Remove all whitespace (including newlines) and trim
        moveSequence = moveSequence.trim().replaceAll("\\s+", "");
    
        formatter.setExpandedPath(moveSequence.trim().replaceAll("\\s+", ""));
        MazeNavigator verifier = new PathValidator(
            maze,
            new DirectionAnalyzer('E', maze, maze.getEntryPoint()),
            formatter.getExpandedPath()
        );

        verifier.navigateMaze();
        System.out.println(verifier.retrievePath());
    }
    
    private String formatPosition(int[] pos) {
        return "(" + pos[0] + ", " + pos[1] + ")";
    }

    //computes path using the right hand algorithm
    public void computePath() {
        logger.info("Computing optimal path...");
        MazeNavigator solver = new MazeSolver(maze, directionAnalyzer, new RightHandSolver());
        solver.navigateMaze(); //solving maze by navigating through it

        formatter.setCompressedPath(solver.retrievePath()); //converting the path to cannonical format
        System.out.println(formatter.getCompressedPath()); //printing the compressed format of the path

    }
}

