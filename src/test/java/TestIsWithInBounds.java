import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestIsWithInBounds {

    private final ToyRobot.Table table = ToyRobot.generateTable(new int[]{5, 3});

    @Test
    void testInsideTable() {
        assertTrue(ToyRobot.isWithinBounds(2, 1, table));
    }

    @Test
    void testCorners() {
        assertTrue(ToyRobot.isWithinBounds(0, 0, table));
        assertTrue(ToyRobot.isWithinBounds(4, 0, table));
        assertTrue(ToyRobot.isWithinBounds(0, 2, table));
        assertTrue(ToyRobot.isWithinBounds(4, 2, table));
    }

    @Test
    void testXOutOfBounds() {
        assertFalse(ToyRobot.isWithinBounds(5, 0, table));
        assertFalse(ToyRobot.isWithinBounds(-1, 0, table));
    }

    @Test
    void testYOutOfBounds() {
        assertFalse(ToyRobot.isWithinBounds(0, 3, table));
        assertFalse(ToyRobot.isWithinBounds(0, -1, table));
    }

    @Test
    void testBothOutOfBounds() {
        assertFalse(ToyRobot.isWithinBounds(5, 3, table));
        assertFalse(ToyRobot.isWithinBounds(-1, -1, table));
    }
}
