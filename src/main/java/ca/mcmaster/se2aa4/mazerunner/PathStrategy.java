package ca.mcmaster.se2aa4.mazerunner;

public interface PathStrategy {
    void computeRoute(Maze maze, DirectionAnalyzer directionAnalyzer);
    String fetchRoute();
}
