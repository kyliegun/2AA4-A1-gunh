package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

public class DirectionAnalyzer {
    private Direction currentDirection;
    private int[] position;
    private Maze maze;

    private static final Map<Character, Direction> directionLookup = new HashMap<>();

    static {
        directionLookup.put('N', Direction.NORTH);
        directionLookup.put('E', Direction.EAST);
        directionLookup.put('S', Direction.SOUTH);
        directionLookup.put('W', Direction.WEST);
    }

    public DirectionAnalyzer(char startingDirection, Maze maze, int[] entrance) {
        this.currentDirection = initializeDirection(startingDirection);
        this.position = entrance;
        this.maze = maze;
    }

    private Direction initializeDirection(char direction) {
        if (directionLookup.containsKey(direction)) {
            return directionLookup.get(direction);
        }
        throw new IllegalArgumentException("Invalid starting direction: " + direction);
    }

    public void processMove(char move) {
        switch (move) {
            case 'L' -> currentDirection = currentDirection.rotateCounterClockwise();
            case 'R' -> currentDirection = currentDirection.rotateClockwise();
            case 'F' -> moveForward();
            default -> throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    private void moveForward() {
        currentDirection.moveForward(position, maze);
    }

    public char getFacingDirection() {
        return currentDirection.name().charAt(0);
    }

    public int getFacingDirectionValue() {
        return currentDirection.getValue();
    }

    public int[] getPosition() {
        return this.position;
    }
}
