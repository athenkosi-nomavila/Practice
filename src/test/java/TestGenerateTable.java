import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestGenerateTable {

    @Test
    void testGenerateTable() {
        ToyRobot.Table table = ToyRobot.generateTable(new int[]{5, 3});
        ToyRobot.Coordinate[][] cords = table.getCoordinates();

        assertEquals(5, cords.length);
        assertEquals(3, cords[0].length);

        ToyRobot.Coordinate coordinates_Set1 = cords[4][2];
        assertEquals(4, coordinates_Set1.x);
        assertEquals(2, coordinates_Set1.y);

        ToyRobot.Coordinate coordinates_Set2 = cords[2][1];
        assertEquals(2, coordinates_Set2.x);
        assertEquals(1, coordinates_Set2.y);

    }

}
