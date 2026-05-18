package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    private InputParser parser;

    @BeforeEach
    void setUp() {
        parser = new InputParser();
    }


    @Test
    void parseSimpleCommand() {
        String[] tokens = parser.parseLine("PUSH 42");

        assertNotNull(tokens);
        assertEquals(2, tokens.length);
        assertEquals("PUSH", tokens[0]);
        assertEquals("42", tokens[1]);
    }

    @Test
    void parseCommandWithMultipleArgs() {
        String[] tokens = parser.parseLine("DEFINE x 10");
        assertNotNull(tokens);
        assertEquals(3, tokens.length);
        assertEquals("DEFINE", tokens[0]);
        assertEquals("x", tokens[1]);
        assertEquals("10", tokens[2]);
    }

    @Test
    void parseCommandWithSpaces() {
        String[] tokens = parser.parseLine("  PUSH   42  ");
        assertNotNull(tokens);
        assertEquals(2, tokens.length);
        assertEquals("PUSH", tokens[0]);
        assertEquals("42", tokens[1]);
    }

    @Test
    void parseCommandWithTabs() {
        String[] tokens = parser.parseLine("\tPUSH\t42\t");
        assertNotNull(tokens);
        assertEquals(2, tokens.length);
        assertEquals("PUSH", tokens[0]);
        assertEquals("42", tokens[1]);
    }

    @Test
    void parseEmptyLine() {
        String[] tokens = parser.parseLine("");
        assertNull(tokens);
    }

    @Test
    void parseNullLine() {
        String[] tokens = parser.parseLine(null);
        assertNull(tokens);
    }

    @Test
    void parseLineWithOnlySpaces() {
        String[] tokens = parser.parseLine("     ");
        assertNull(tokens);
    }

    @Test
    void parseCommentLine() {
        String[] tokens = parser.parseLine("#  комментарий");
        assertNull(tokens);
    }

    @Test
    void parseCommentWithSpaces() {
        String[] tokens = parser.parseLine("   #комментарий");
        assertNull(tokens);
    }

    @Test
    void parseCommandWithManySpaces() {
        String[] tokens = parser.parseLine("DEFINE    x    100");

        assertNotNull(tokens);
        assertEquals(3, tokens.length);
        assertEquals("DEFINE", tokens[0]);
        assertEquals("x", tokens[1]);
        assertEquals("100", tokens[2]);
    }
}