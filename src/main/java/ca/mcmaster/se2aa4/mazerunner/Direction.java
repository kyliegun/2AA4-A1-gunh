/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

 package ca.mcmaster.se2aa4.mazerunner;

 import java.util.Map;
 import java.util.HashMap;
 
 public enum Direction {
     NORTH(0, -1, 0),
     EAST(1, 0, 1),
     SOUTH(2, 1, 0),
     WEST(3, 0, -1);
 
     private final int value;
     private final int rowOffset;
     private final int colOffset;
 
     private static final Direction[] VALUES = values();
     private static final Map<Character, Direction> CHAR_LOOKUP = new HashMap<>();
 
     static {
         for (Direction dir : VALUES) {
             CHAR_LOOKUP.put(dir.name().charAt(0), dir); // 'N', 'E', etc.
         }
     }
 
     Direction(int value, int rowOffset, int colOffset) {
         this.value = value;
         this.rowOffset = rowOffset;
         this.colOffset = colOffset;
     }
 
     public int getValue() {
         return this.value;
     }
 
     public static Direction fromChar(char c) {
         Direction dir = CHAR_LOOKUP.get(Character.toUpperCase(c));
         if (dir == null) {
             throw new IllegalArgumentException("Invalid direction: " + c);
         }
         return dir;
     }
 
     public Direction turnClockwise() {
         return VALUES[(this.value + 1) % 4];
     }
 
     public Direction turnCounterClockwise() {
         return VALUES[(this.value + 3) % 4];
     }
 
     public void moveForward(int[] position, Maze maze) {
         int newRow = position[0] + rowOffset;
         int newCol = position[1] + colOffset;
 
         if (newRow >= 0 && newRow < maze.getHeight() &&
             newCol >= 0 && newCol < maze.getWidth() &&
             maze.getCell(newRow, newCol) == ' ') {
 
             position[0] = newRow;
             position[1] = newCol;
         }
     }
 } 