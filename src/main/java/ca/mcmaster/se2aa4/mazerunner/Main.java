/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class serves as the entry point for the application. Processes command line arguments, initializes the maze,
 * and verifies the maze.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(); //Logger for debugging error messages

    public static void main(String[] args) {
        //Handles command line arguments
        CommandLineHandler cliHandler = new CommandLineHandler(args);
        String mazeFile = cliHandler.getMazeFile(); //Retrieves the file path for the maze
        String moveSequence = cliHandler.getMoveSequence();

        if (mazeFile == null) {
            logger.error("No maze file specified."); //Logs error and exits if maze file is not provided
            return;
        }

        //Initialize the mazerunner
        MazeRunner runner = new MazeRunner(mazeFile);
        if (moveSequence != null) { //if a move sequence is not provided, compute a path
            runner.verifyPath(moveSequence);
        } else {
            runner.computePath(); //computes path using the algorithm provided
        }
    }
}

