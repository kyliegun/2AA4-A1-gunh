/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class DirectionAnalyzer implements Subject {
    private Direction currentDirection;
    private int[] position;
    private Maze maze;
    private List<MoveObserver> observers = new ArrayList<>();

    private static final Map<Character, Direction> directionLookup = Map.of(
        'N', Direction.NORTH,
        'E', Direction.EAST,
        'S', Direction.SOUTH,
        'W', Direction.WEST
    );

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
        notifyObservers(move);
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

    @Override
    public void addObserver(MoveObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MoveObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(char move) {
        for (MoveObserver obs : observers) {
            obs.onMove(move, position.clone(), getFacingDirection());
        }
    }
}
