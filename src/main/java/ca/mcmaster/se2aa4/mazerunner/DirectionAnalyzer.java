/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

//This class manages movement and orientation within the maze
public class DirectionAnalyzer {
    private Direction currentDirection; //direction that the explorer is facing
    private int[] position; //current position in the maze
    private Maze maze;

    //Lookup table to map direction chars
    private static final Map<Character, Direction> directionLookup = new HashMap<>();

    static {
        directionLookup.put('N', Direction.NORTH);
        directionLookup.put('E', Direction.EAST);
        directionLookup.put('S', Direction.SOUTH);
        directionLookup.put('W', Direction.WEST);
    }

    /**
     * Constructs a DirectionAnalyzer with an initial direction, maze reference, and starting position
     * 
     * @param startingDirection The initial direction the explorer faces
     * @param maze The maze in which the explorer navigates
     * @param entrance The starting position of the explorer in the maze
     */
    public DirectionAnalyzer(char startingDirection, Maze maze, int[] entrance) {
        this.currentDirection = initializeDirection(startingDirection);
        this.position = entrance;
        this.maze = maze;
    }

    /**
     * Maps a character direction to a corresponding direction enum
     * 
     * @param direction The character representing the initial direction
     * @return The corresponding Direction enum
     * @throws IllegalArgumentException if an invalid direction is provided
     */
    private Direction initializeDirection(char direction) {
        if (directionLookup.containsKey(direction)) {
            return directionLookup.get(direction);
        }
        throw new IllegalArgumentException("Invalid starting direction: " + direction);
    }

    public void processMove(char move) {
        switch (move) {
            case 'L' -> currentDirection = currentDirection.rotateCounterClockwise(); //Turns left
            case 'R' -> currentDirection = currentDirection.rotateClockwise(); //Turns right
            case 'F' -> moveForward(); //Moves forward
            default -> throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    private void moveForward() { //Moving forward in whatever current direction
        currentDirection.moveForward(position, maze);
    }

    public char getFacingDirection() { //Retrieves current facing direction
        return currentDirection.name().charAt(0);
    }

    public int getFacingDirectionValue() { //Retrieves numerical value
        return currentDirection.getValue();
    }

    public int[] getPosition() { //Retrieves current position
        return this.position;
    }
}
