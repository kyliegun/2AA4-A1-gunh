/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

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
        int maxMoves = maze.getWidth() * maze.getHeight() * 2;

        while (!isExplorerAtExit(directionAnalyzer.getPosition())) {
            executeMove(directionAnalyzer, maze);
            moveCount++;

            if (moveCount > maxMoves) {
                logger.error("Exceeded max move limit of {}. Exiting to prevent infinite loop.", maxMoves);
                break;
            }
        }

        this.computedRoute = movementLog.toString();
    }

    private void executeMove(DirectionAnalyzer directionAnalyzer, Maze maze) {
        // Try to turn right and move
        directionAnalyzer.processMove('R');
        if (isForwardFree(directionAnalyzer, maze)) {
            directionAnalyzer.processMove('F');
            movementLog.append("RF");
            return;
        }

        // Undo right turn
        directionAnalyzer.processMove('L');

        // Try moving forward
        if (isForwardFree(directionAnalyzer, maze)) {
            directionAnalyzer.processMove('F');
            movementLog.append("F");
            return;
        }

        // Turn left and try forward
        directionAnalyzer.processMove('L');
        if (isForwardFree(directionAnalyzer, maze)) {
            directionAnalyzer.processMove('F');
            movementLog.append("LF");
            return;
        }

        // Turn around (another left), then move forward
        directionAnalyzer.processMove('L');
        directionAnalyzer.processMove('F');
        movementLog.append("LLF");
    }

    private boolean isExplorerAtExit(int[] currentPosition) {
        return currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1];
    }

    private boolean isForwardFree(DirectionAnalyzer directionAnalyzer, Maze maze) {
        int[] pos = directionAnalyzer.getPosition();
        int dir = directionAnalyzer.getFacingDirectionValue();
        int newRow = pos[0] + directionOffsets[dir][0];
        int newCol = pos[1] + directionOffsets[dir][1];

        return isValidMove(newRow, newCol, maze);
    }

    private boolean isValidMove(int row, int col, Maze maze) {
        if (row < 0 || row >= maze.getHeight() || col < 0 || col >= maze.getWidth()) {
            return false;
        }
        return maze.getCell(row, col) == ' ';
    }

    @Override
    public String fetchRoute() {
        return this.computedRoute;
    }
}