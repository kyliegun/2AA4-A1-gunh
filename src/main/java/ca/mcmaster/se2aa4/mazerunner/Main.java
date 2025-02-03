package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        CommandLineHandler cliHandler = new CommandLineHandler(args);
        String mazeFile = cliHandler.getMazeFile();
        String moveSequence = cliHandler.getMoveSequence();

        if (mazeFile == null) {
            logger.error("No maze file specified.");
            return;
        }

        MazeRunner runner = new MazeRunner(mazeFile);
        if (moveSequence != null) {
            runner.verifyPath(moveSequence);
        } else {
            runner.computePath();
        }
    }
}

