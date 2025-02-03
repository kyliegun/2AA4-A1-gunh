/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//This class represents the maze structure and manages the entry and exit points
public class Maze {
    private Grid grid; //grid representation of maze
    private int[] entryPoint; //coordinates of entrance
    private int[] exitPoint; //coordinates of exit

    //Constructs a Maze object by reading from the maze file
    public Maze(String mazeFile) {
        this.grid = new Grid(readMazeFromFile(mazeFile));
    }

    //Reads maze file and loads contents into a list
    private List<String> readMazeFromFile(String mazeFile) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read maze file: " + e.getMessage());
        }
        return lines;
    }

    //Identifies and sets the entrance point
    public void setEntryPoint() {
        this.entryPoint = grid.findEntrance();
        setExitPoint();
    }

    //Identifies and sets the exit point
    public void setExitPoint() {
        this.exitPoint = grid.findFarthestOpenSpace(entryPoint);
    }

    //retrieves coordinates for entrance/exit
    public int[] getEntryPoint() {
        return entryPoint;
    }

    public int[] getExitPoint() {
        return exitPoint;
    }

    public int getEntryRow() {
        return entryPoint[0];
    }

    public int getEntryColumn() {
        return entryPoint[1];
    }

    public int getExitRow() {
        return exitPoint[0];
    }

    public int getExitColumn() {
        return exitPoint[1];
    }

    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    //retrieves the char at a specific position in the maze 
    public char getCell(int row, int column) {
        return grid.getCell(row, column);
    }
}