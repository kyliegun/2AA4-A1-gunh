package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();

    private int currentColumn, currentRow;
    private int directionIndex;
    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private final String[] directionNames = {"UP", "RIGHT", "DOWN", "LEFT"};
    private Maze maze;
    private List<String> movementPath;

    public Explorer(Maze maze) {
        this.maze = maze;
        this.currentColumn = maze.getEntryColumn();
        this.currentRow = maze.getEntryRow();
        this.directionIndex = 1; 
        this.movementPath = new ArrayList<>();
    }

    public String computePath() {
        StringBuilder path = new StringBuilder();
        int[] position = {currentRow, currentColumn};

        if (position == null) {
            logger.error("No entry point found");
            return "";
        }

        logger.info("Starting traversal at position: {},{}", position[0], position[1]);

        position = move(position);
        path.append("F");
        logger.debug("FORWARD from entry to position: {},{}", position[0], position[1]);

        while (true) {
            logger.debug("Current position: {},{} - Facing: {}", position[0], position[1], directionNames[directionIndex]);

            if (isExit(position)) {
                logger.info("Exit found at position: {},{}", position[0], position[1]);
                break;
            }

            turnRight();
            int[] nextPos = move(position);
            if (isValidMove(nextPos)) {
                path.append("RF");
                logger.debug("Moved to: {},{}", nextPos[0], nextPos[1]);
                position = nextPos;
                continue;
            }

            turnLeft();
            nextPos = move(position);
            if (isValidMove(nextPos)) {
                path.append("F");
                logger.debug("Moved to: {},{}", nextPos[0], nextPos[1]);
                position = nextPos;
                continue;
            }

            turnLeft();
            nextPos = move(position);
            if (isValidMove(nextPos)) {
                path.append("LF");
                logger.debug("Moved to: {},{}", nextPos[0], nextPos[1]);
                position = nextPos;
                continue;
            }

            turnLeft();
        }

        return path.toString().trim();
    }

    private boolean isValidMove(int[] position) {
        int row = position[0], col = position[1];
        return row >= 0 && col >= 0 && maze.getTile(col, row) == ' ';
    }

    private boolean isExit(int[] position) {
        return position[0] == maze.getExitRow() && position[1] == maze.getExitColumn();
    }

    private void turnRight() {
        directionIndex = (directionIndex + 1) % 4;
    }

    private void turnLeft() {
        directionIndex = (directionIndex + 3) % 4; 
    }

    private int[] move(int[] position) {
        return new int[]{position[0] + directions[directionIndex][0], position[1] + directions[directionIndex][1]};
    }

    public List<String> getMovementPath() {
        return movementPath;
    }

    public void printCurrentStatus() {
        System.out.println("Position: (" + currentColumn + ", " + currentRow + ")");
        System.out.println("Direction: " + directionNames[directionIndex]);
    }
}
