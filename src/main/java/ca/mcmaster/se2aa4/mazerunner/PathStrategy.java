/**
 * Kylie Gun
 * Assignment 1 MazeRunner 2AA4 - Winter 2025
 * 400524717
 */

package ca.mcmaster.se2aa4.mazerunner;

public interface PathStrategy {
    void computeRoute(Maze maze, DirectionAnalyzer directionAnalyzer); //Computes path using right hand algorithm
    String fetchRoute(); //Retrieves the path as a string
}
