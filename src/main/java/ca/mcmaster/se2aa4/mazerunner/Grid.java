package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Grid { // Helper for Maze
    private final char[][] grid;

    public Grid(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Grid cannot be initialized with an empty maze.");
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        this.grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public char getCell(int row, int column) {
        return grid[row][column];
    }

    public int[] findEntrance() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == ' ') {
                    return new int[]{row, col}; // Return the first open space found
                }
            }
        }
        throw new IllegalStateException("No valid entrance found in the maze.");
    }

    public int[] findFarthestOpenSpace(int[] startPoint) {
        int[] farthest = startPoint;
        int maxDistance = 0;

        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (grid[row][col] == ' ') {
                    int distance = Math.abs(row - startPoint[0]) + Math.abs(col - startPoint[1]);
                    if (distance > maxDistance) {
                        maxDistance = distance;
                        farthest = new int[]{row, col};
                    }
                }
            }
        }

        return farthest;
    }
}
