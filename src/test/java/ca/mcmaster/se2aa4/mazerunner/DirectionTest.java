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
    void testRotateClockwise() {
        assertEquals(Direction.EAST, Direction.NORTH.rotateClockwise());
        assertEquals(Direction.SOUTH, Direction.EAST.rotateClockwise());
        assertEquals(Direction.WEST, Direction.SOUTH.rotateClockwise());
        assertEquals(Direction.NORTH, Direction.WEST.rotateClockwise());
    }

    @Test
    void testRotateCounterClockwise() {
        assertEquals(Direction.WEST, Direction.NORTH.rotateCounterClockwise());
        assertEquals(Direction.NORTH, Direction.EAST.rotateCounterClockwise());
        assertEquals(Direction.EAST, Direction.SOUTH.rotateCounterClockwise());
        assertEquals(Direction.SOUTH, Direction.WEST.rotateCounterClockwise());
    }

    @Test
    void testInitializeDirection() {
        assertEquals(Direction.NORTH, Direction.NORTH.initializeDirection('N'));
        assertEquals(Direction.EAST, Direction.NORTH.initializeDirection('E'));
        assertEquals(Direction.SOUTH, Direction.NORTH.initializeDirection('S'));
        assertEquals(Direction.WEST, Direction.NORTH.initializeDirection('W'));
    }

    @Test
    void testInitializeDirectionInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Direction.NORTH.initializeDirection('X');
        });
        assertTrue(exception.getMessage().contains("Invalid starting direction"));
    }
}

