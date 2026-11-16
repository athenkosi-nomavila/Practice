import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestGenerateTable {

    @Test
    void testGenerateTable() {
        ToyRobot.Table table = ToyRobot.generateTable(new int[]{5, 3});

        assertEquals(5, table.getWidth());
        assertEquals(3, table.getHeight());
    }

}
