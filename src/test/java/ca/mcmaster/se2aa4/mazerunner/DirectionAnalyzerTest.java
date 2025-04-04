package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionAnalyzerTest {

    private Maze mockMaze;
    private int[] startPosition;
    private DirectionAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        mockMaze = new Maze("examples/medium.maz.txt");
        mockMaze.setEntryPoint();
        startPosition = mockMaze.getEntryPoint();
        analyzer = new DirectionAnalyzer('E', mockMaze, startPosition.clone());
    }

    @Test
    void testInitialFacingDirection() {
        assertEquals('E', analyzer.getFacingDirection());
        assertEquals(Direction.EAST.getValue(), analyzer.getFacingDirectionValue());
    }

    @Test
    void testTurnLeft() {
        analyzer.processMove('L');
        assertEquals('N', analyzer.getFacingDirection());
    }

    @Test
    void testTurnRight() {
        analyzer.processMove('R');
        assertEquals('S', analyzer.getFacingDirection());
    }

    @Test
    void testMoveForward() {
        int[] before = analyzer.getPosition().clone();
        analyzer.processMove('F');
        int[] after = analyzer.getPosition();
        assertEquals(before[0], after[0]);
        assertEquals(before[1] + 1, after[1]);
    }

    @Test
    void testInvalidDirectionInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DirectionAnalyzer('Z', mockMaze, startPosition);
        });
        assertTrue(exception.getMessage().contains("Invalid starting direction"));
    }

    @Test
    void testInvalidMoveInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            analyzer.processMove('X');
        });
        assertTrue(exception.getMessage().contains("Invalid move"));
    }
}
