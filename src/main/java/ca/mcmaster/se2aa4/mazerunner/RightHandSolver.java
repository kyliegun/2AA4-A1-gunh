package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements PathStrategy {
    private static final Logger logger = LogManager.getLogger();
    private StringBuilder movementLog = new StringBuilder(); 
    private String computedRoute;
    private int[][] directionOffsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[] exitPosition;

    @Override
    public void computeRoute(Maze maze, DirectionAnalyzer directionAnalyzer) {
        exitPosition = maze.getExitPoint();
        int moveCount = 0; 
        int maxMoves = maze.getWidth() * maze.getHeight() * 2; // Safety limit

        do {
            executeMove(directionAnalyzer, maze);
            moveCount++;

            if (moveCount > maxMoves) {
                logger.error("Exceeded max move limit of {}. Exiting to prevent infinite loop.", maxMoves);
                break;
            }

        } while (!isExplorerAtExit(directionAnalyzer.getPosition()));

        this.computedRoute = movementLog.toString();
    }

    private void executeMove(DirectionAnalyzer directionAnalyzer, Maze maze) {
        if (isForwardFree(directionAnalyzer, maze)) {
            directionAnalyzer.processMove('F');
            movementLog.append('F');
        } else if (isRightFree(directionAnalyzer, maze)) {
            directionAnalyzer.processMove('R');
            movementLog.append('R');
        } else {
            directionAnalyzer.processMove('L');
            movementLog.append('L');
        }
    }

    private boolean isExplorerAtExit(int[] currentPosition) {
        return currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1];
    }

    private boolean isForwardFree(DirectionAnalyzer directionAnalyzer, Maze maze) {
        int currentRow = directionAnalyzer.getPosition()[0];
        int currentColumn = directionAnalyzer.getPosition()[1];
        int facingDirection = directionAnalyzer.getFacingDirectionValue();

        int newRow = currentRow + directionOffsets[facingDirection][0];
        int newColumn = currentColumn + directionOffsets[facingDirection][1];

        return isValidMove(newRow, newColumn, maze);
    }

    private boolean isRightFree(DirectionAnalyzer directionAnalyzer, Maze maze) {
        int facingDirection = directionAnalyzer.getFacingDirectionValue();
        int rightDirection = (facingDirection + 1) % 4;

        int currentRow = directionAnalyzer.getPosition()[0];
        int currentColumn = directionAnalyzer.getPosition()[1];

        int newRow = currentRow + directionOffsets[rightDirection][0];
        int newColumn = currentColumn + directionOffsets[rightDirection][1];

        return isValidMove(newRow, newColumn, maze);
    }

    private boolean isValidMove(int row, int column, Maze maze) {
        if (row < 0 || row >= maze.getHeight() || column < 0 || column >= maze.getWidth()) {
            return false;
        }
        return maze.getCell(row, column) == ' ';
    }

    @Override
    public String fetchRoute() {
        return this.computedRoute;
    }
}