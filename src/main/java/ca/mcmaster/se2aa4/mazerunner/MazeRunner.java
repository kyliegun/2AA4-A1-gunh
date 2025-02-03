package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;
    private FormatPath formatter;

    public MazeRunner(String mazeFile) {
        this.maze = new Maze(mazeFile);
        maze.setEntryPoint();
        this.directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntryPoint());
        this.formatter = new FormatPath();
    }

    public void verifyPath(String moveSequence) {
        logger.info("Verifying provided path...");
        formatter.setExpandedPath(moveSequence);
        MazeNavigator verifier = new PathValidator(maze, directionAnalyzer, formatter.getExpandedPath());
        verifier.navigateMaze();
        System.out.println(verifier.retrievePath());
    }

    public void computePath() {
        logger.info("Computing optimal path...");
        MazeNavigator solver = new MazeSolver(maze, directionAnalyzer, new RightHandSolver());
        solver.navigateMaze();
        formatter.setCompressedPath(solver.retrievePath());
        System.out.println(formatter.getCompressedPath());
    }
}