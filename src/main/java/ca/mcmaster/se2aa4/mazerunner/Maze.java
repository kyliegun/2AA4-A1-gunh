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
    
        if (lines.isEmpty()) { 
            throw new IllegalStateException("Maze file is empty: " + mazeFile);
        }
    
        return lines;
    }
    

    public void setEntryPoint() {
        this.entryPoint = grid.findEntrance(); // Automatically finds the first open space
        System.out.println("Entrance found at: " + entryPoint[0] + "," + entryPoint[1]);
        setExitPoint();
    }
    

    public int[] getEntryPoint() {
        return this.entryPoint;
    }

    public void setExitPoint() {
        int[] potentialExit = grid.findFarthestOpenSpace(entryPoint);
    
        if (entryPoint[0] == potentialExit[0] && entryPoint[1] == potentialExit[1]) {
            throw new IllegalStateException("Maze entrance and exit cannot be the same.");
        }
        
        this.exitPoint = potentialExit;
    }
    
    

    public int[] getExitPoint() {
        return this.exitPoint;
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