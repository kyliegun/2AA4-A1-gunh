package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormatPathTest {

    private FormatPath formatter;

    @BeforeEach
    void setUp() {
        formatter = new FormatPath();
    }

    @Test
    void testExpandSimplePath() {
        formatter.setExpandedPath("3F2R");
        assertEquals("FFFRR", formatter.getExpandedPath());
    }

    @Test
    void testExpandWithSingleMoves() {
        formatter.setExpandedPath("FRL");
        assertEquals("FRL", formatter.getExpandedPath());
    }

    @Test
    void testExpandWithMixed() {
        formatter.setExpandedPath("F2L3R");
        assertEquals("FLLRRR", formatter.getExpandedPath());
    }

    @Test
    void testExpandWithSpaces() {
        formatter.setExpandedPath("  2F 3R  L ");
        assertEquals("FFRRRL", formatter.getExpandedPath());
    }

    @Test
    void testCompressSimplePath() {
        formatter.setCompressedPath("FFFRR");
        assertEquals("3F2R", formatter.getCompressedPath());
    }

    @Test
    void testCompressWithSingleMoves() {
        formatter.setCompressedPath("FRL");
        assertEquals("FRL", formatter.getCompressedPath());
    }

    @Test
    void testCompressWithMixedMoves() {
        formatter.setCompressedPath("FLLRRR");
        assertEquals("F2L3R", formatter.getCompressedPath());
    }
}