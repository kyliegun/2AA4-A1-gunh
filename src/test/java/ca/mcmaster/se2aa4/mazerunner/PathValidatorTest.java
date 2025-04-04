package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathValidatorTest {

    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;

    @BeforeEach
    void setUp() {
        maze = new Maze("examples/huge.maz.txt");
        maze.setEntryPoint();
        directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntryPoint());
    }

    @Test
    void testCorrectPathReachesExit() {
        String path = "FFRFF"; 
        PathValidator validator = new PathValidator(maze, directionAnalyzer, path);
        validator.navigateMaze();
        assertEquals("incorrect path", validator.retrievePath());
    }

    @Test
    void testIncorrectPathFailsToReachExit() {
        String path = "FFFFF"; 
        PathValidator validator = new PathValidator(maze, directionAnalyzer, path);
        validator.navigateMaze();
        assertEquals("incorrect path", validator.retrievePath());
    }
}

