package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze("examples/giant.maz.txt");
        maze.setEntryPoint();
    }

    @Test
    void testEntryPointIsSet() {
        int[] entry = maze.getEntryPoint();
        assertNotNull(entry);
        assertEquals(2, entry.length);
    }

    @Test
    void testExitPointIsSet() {
        int[] exit = maze.getExitPoint();
        assertNotNull(exit);
        assertEquals(2, exit.length);
    }

    @Test
    void testGetWidthAndHeight() {
        assertTrue(maze.getWidth() > 0);
        assertTrue(maze.getHeight() > 0);
    }

    @Test
    void testGetCellWithinBounds() {
        char cell = maze.getCell(maze.getEntryRow(), maze.getEntryColumn());
        assertTrue(cell == ' ');
    }

    @Test
    void testStartPointMatchesEntry() {
        assertArrayEquals(maze.getEntryPoint(), maze.getStartPoint());
    }
}

