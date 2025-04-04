package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze("examples/direct.maz.txt");
    }

    @Test
    void testMoveSouth() {
        int[] pos = {1, 1}; // Assume open space below
        Direction.SOUTH.moveForward(pos, maze);
        assertEquals(2, pos[0]);
        assertEquals(1, pos[1]);
    }

    @Test
    void testMoveWest() {
        int[] pos = {1, 1}; // Assume open space to the left
        Direction.WEST.moveForward(pos, maze);
        assertEquals(1, pos[0]);
        assertEquals(0, pos[1]);
    }

    @Test
    void testTurnClockwise() {
        assertEquals(Direction.EAST, Direction.NORTH.turnClockwise());
        assertEquals(Direction.SOUTH, Direction.EAST.turnClockwise());
        assertEquals(Direction.WEST, Direction.SOUTH.turnClockwise());
        assertEquals(Direction.NORTH, Direction.WEST.turnClockwise());
    }

    @Test
    void testTurnCounterClockwise() {
        assertEquals(Direction.WEST, Direction.NORTH.turnCounterClockwise());
        assertEquals(Direction.NORTH, Direction.EAST.turnCounterClockwise());
        assertEquals(Direction.EAST, Direction.SOUTH.turnCounterClockwise());
        assertEquals(Direction.SOUTH, Direction.WEST.turnCounterClockwise());
    }

    @Test
    void testFromChar() {
        assertEquals(Direction.NORTH, Direction.fromChar('N'));
        assertEquals(Direction.EAST, Direction.fromChar('E'));
        assertEquals(Direction.SOUTH, Direction.fromChar('S'));
        assertEquals(Direction.WEST, Direction.fromChar('W'));
    }

    @Test
    void testFromCharInvalid() {
        Maze mockMaze = new Maze("examples/direct.maz.txt");
        mockMaze.setEntryPoint();
        int[] pos = mockMaze.getEntryPoint();
    
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DirectionAnalyzer('X', mockMaze, pos);
        });
    
        assertTrue(exception.getMessage().contains("Invalid starting direction"));
    }    
}