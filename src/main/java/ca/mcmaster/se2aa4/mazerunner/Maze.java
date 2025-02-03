package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private Grid grid;
    private int[] entryPoint;
    private int[] exitPoint;

    public Maze(String mazeFile) {
        this.grid = new Grid(readMazeFromFile(mazeFile));
    }

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

    public void setEntryPoint() {
        this.entryPoint = grid.findEntrance();
        setExitPoint();
    }

    public void setExitPoint() {
        this.exitPoint = grid.findFarthestOpenSpace(entryPoint);
    }
    
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

    public char getCell(int row, int column) {
        return grid.getCell(row, column);
    }
}