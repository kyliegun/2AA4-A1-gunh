/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

//Enum representing movement directions
public enum Direction {
    NORTH(0) {
        @Override
        public void moveForward(int[] position, Maze maze) {
            int row = position[0];
            int col = position[1];
            //Move up if the tile is open
            if (row - 1 >= 0 && maze.getCell(row - 1, col) == ' ') {
                position[0] = row - 1;
            }
        }
    },

    EAST(1) {
        @Override
        public void moveForward(int[] position, Maze maze) {
            int row = position[0];
            int col = position[1];
            //Move right if the tile is open
            if (col + 1 < maze.getWidth() && maze.getCell(row, col + 1) == ' ') {
                position[1] = col + 1;
            }
        }
    },

    SOUTH(2) {
        @Override
        public void moveForward(int[] position, Maze maze) {
            int row = position[0];
            int col = position[1];
            //Move down if the tile is open
            if (row + 1 < maze.getHeight() && maze.getCell(row + 1, col) == ' ') {
                position[0] = row + 1;
            }
        }
    },

    WEST(3) {
        @Override
        public void moveForward(int[] position, Maze maze) {
            int row = position[0];
            int col = position[1];
            //move left if the tile is open
            if (col - 1 >= 0 && maze.getCell(row, col - 1) == ' ') {
                position[1] = col - 1;
            }
        }
    };

    //stores an integer of the direction
    private int ordinalValue;

    Direction(int ordinalValue) {
        this.ordinalValue = ordinalValue;  //assigns an integer representation for the direction
    }

    public int getValue() {
        return this.ordinalValue;
    }

    public Direction[] getAllDirections() {
        return values();
    }

    public Direction initializeDirection(char startingDirection) {
        Direction[] allDirections = getAllDirections();
        //looping through all directions to find the matching one
        for (Direction direction : allDirections) {
            if (direction.name().charAt(0) == startingDirection) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid starting direction: " + startingDirection);
    }

    public Direction rotateCounterClockwise() {
        int newIndex = (this.ordinalValue + 3) % 4; //rotates left
        return getAllDirections()[newIndex];
    }  

    public Direction rotateClockwise() {
        int newIndex = (this.ordinalValue + 1) % 4; //rotates right
        return getAllDirections()[newIndex];
    }  

    public abstract void moveForward(int[] currentPosition, Maze maze);  //abstract method for movement, moves forward based on current direction
}  
  