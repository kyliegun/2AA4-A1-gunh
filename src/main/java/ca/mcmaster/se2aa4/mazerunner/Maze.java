package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private char[][] grid; //2D array representing the maze
    private int entryColumn, entryRow; //Coordinates of the entry point
    private int exitColumn, exitRow; //Coordinates of the exit point

    public void loadMaze(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder mazeDataBuilder = new StringBuilder();
        String line;

        //Read the file line by line and store it in a StringBuilder
        while ((line = reader.readLine()) != null) {
            mazeDataBuilder.append(line).append("\n");
        }
        reader.close();

        String[] lines = mazeDataBuilder.toString().split("\n");
        grid = new char[lines.length][lines[0].length()];

        //Store each line in a 2D char array 
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }

        findEntryExit(); //Locate the entry and exit points
    }

    //Locate the entry (west border) and exit (east border) points in the maze
    private void findEntryExit() {
        boolean entryFound = false;
        boolean exitFound = false;

        //Scan the first and last columns to find the entry and exit points
        for (int row = 0; row < grid.length; row++) {
            if (!entryFound && grid[row][0] == ' ') {
                entryColumn = 0;
                entryRow = row;
                entryFound = true; //Only set the entry once
            }
            if (!exitFound && grid[row][grid[0].length - 1] == ' ') {
                exitColumn = grid[0].length - 1;
                exitRow = row;
                exitFound = true; //Only set the exit once
            }
        }

        //If entry or exit is not found, throw an exception
        if (!entryFound) {
            throw new IllegalArgumentException("No entry point found in the maze.");
        }

        if (!exitFound) {
            throw new IllegalArgumentException("No exit point found in the maze.");
        }
    }

    public char getTile(int column, int row) {
        return grid[row][column]; //Returns the tile at a given position in the grid
    }

    public int getEntryColumn() {
        return entryColumn;
    }

    public int getEntryRow() {
        return entryRow;
    }

    public int getExitColumn() {
        return exitColumn;
    }

    public int getExitRow() {
        return exitRow;
    }

    //Print the maze to the console (for debugging or visual representation)
    public void printMaze() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    //Verify if the given path is valid by checking if all the tiles are spaces
    public boolean verifyPath(String path) {
        String[] coordinates = path.split(" ");
        for (String coordinate : coordinates) {
            String[] coords = coordinate.split(",");
            int col = Integer.parseInt(coords[0]);
            int row = Integer.parseInt(coords[1]);

            // Check if the tile is a space
            if (getTile(col, row) != ' ') {
                return false; //Path is invalid if a tile is not a space
            }
        }
        return true; //If all coordinates are valid, return true
    }
}