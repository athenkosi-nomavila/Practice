import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TestRunRobot {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private String run(String... commands) {
        String input = String.join("\n", commands) + "\nquit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ToyRobot.runRobot(ToyRobot.generateTable(new int[]{5, 5}));

        return output.toString().replace("Enter command: ", "").trim();
    }

    @Test
    void testCommandsBeforePlaceAreIgnored() {
        assertEquals("", run("move", "left", "right", "report"));
    }

    @Test
    void testPlaceAndReport() {
        assertEquals("1,2,north", run("place 1,2 north", "report"));
    }

    @Test
    void testPlaceOffTable() {
        assertEquals("Position is off the table", run("place 5,5 north", "report"));
    }

    @Test
    void testPlaceWithInvalidDirectionIsIgnored() {
        assertEquals("", run("place 1,1 up", "report"));
    }

    @Test
    void testPlaceAgainMovesRobot() {
        assertEquals("3,4,south", run("place 0,0 north", "place 3,4 south", "report"));
    }

    @Test
    void testMoveInEachDirection() {
        assertEquals("2,3,north", run("place 2,2 north", "move", "report"));
        assertEquals("3,2,east", run("place 2,2 east", "move", "report"));
        assertEquals("2,1,south", run("place 2,2 south", "move", "report"));
        assertEquals("1,2,west", run("place 2,2 west", "move", "report"));
    }

    @Test
    void testMoveOutOfBounds() {
        String output = run("place 0,0 south", "move", "report");
        assertTrue(output.contains("Robot would fall off the table"));
        assertTrue(output.contains("0,0,south"));
    }

    @Test
    void testTurnLeft() {
        assertEquals("0,0,west", run("place 0,0 north", "left", "report"));
        assertEquals("0,0,south", run("place 0,0 west", "left", "report"));
    }

    @Test
    void testTurnRight() {
        assertEquals("0,0,east", run("place 0,0 north", "right", "report"));
        assertEquals("0,0,north", run("place 0,0 west", "right", "report"));
    }

    @Test
    void testFullCircle() {
        assertEquals("0,0,north", run("place 0,0 north", "right", "right", "right", "right", "report"));
        assertEquals("0,0,north", run("place 0,0 north", "left", "left", "left", "left", "report"));
    }

    @Test
    void testCommands() {
        assertEquals("3,3,north", run("place 1,2 east", "move", "move", "left", "move", "report"));
    }
}
