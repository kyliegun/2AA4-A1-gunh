package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectionManager {
    private static final Logger logger = LogManager.getLogger();
    private Direction currentDir;
    
    //Enum for cardinal directions
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    //Constructor to initialize direction
    public DirectionManager() {
        this.currentDir = Direction.EAST; //Starting direction is East
    }

    // Method to turn right (clockwise)
    public Direction turnRight() {
        switch (currentDir) {
            case NORTH:
                currentDir = Direction.EAST;
                break;
            case EAST:
                currentDir = Direction.SOUTH;
                break;
            case SOUTH:
                currentDir = Direction.WEST;
                break;
            case WEST:
                currentDir = Direction.NORTH;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDir);
        }
        logger.debug("Turned right, new direction: " + currentDir);
        return currentDir;
    }

    // Method to turn left (counter-clockwise)
    public Direction turnLeft() {
        switch (currentDir) {
            case NORTH:
                currentDir = Direction.WEST;
                break;
            case EAST:
                currentDir = Direction.NORTH;
                break;
            case SOUTH:
                currentDir = Direction.EAST;
                break;
            case WEST:
                currentDir = Direction.SOUTH;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDir);
        }
        logger.debug("Turned left, new direction: " + currentDir);
        return currentDir;
    }

    //Method to get movement offsets for the current direction
    public int[] getForwardStepOffsets() {
        switch (currentDir) {
            case NORTH: 
                return new int[]{0, -1}; //Up
            case EAST:
                return new int[]{1, 0}; //Right
            case SOUTH: 
                return new int[]{0, 1}; //Down
            case WEST:
                return new int[]{-1, 0}; //Left
            default:
                throw new IllegalStateException("Unexpected value: " + currentDir);
        }
    }

    //Getter for the current direction
    public Direction getCurrentDirection() {
        return currentDir;
    }
}