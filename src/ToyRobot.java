import java.util.Arrays;
import java.util.Scanner;

public class ToyRobot {
    /*
    5x5 box
    PLACE X,Y,Facing
    MOVE by 1
    LEFT
    RIGHT
    REPORT X,Y, facing

    Doesn't move before being placed
    Doesn't move out of the boarders
     */

    public static class Robot {
        int x_Coordinate;
        int y_Coordinate;
        String facing;
        String report;
        boolean isOnTable;

        public String getReport() {
            setReport();
            return report;
        }

        public int getX_Coordinate() {
            return x_Coordinate;
        }

        public int getY_Coordinate() {
            return y_Coordinate;
        }

        public String getFacing() {
            return facing;
        }

        public boolean getIsOnTable() {return isOnTable;}

        public void setX_Coordinate(int x_Coordinate) {
            this.x_Coordinate = x_Coordinate;
        }

        public void setY_Coordinate(int y_Coordinate) {
            this.y_Coordinate = y_Coordinate;
        }

        public void setFacing(String facing) {
            this.facing = facing;
        }

        public void setReport() {

            this.report = getX_Coordinate() +","+ getY_Coordinate() +","+ getFacing();
        }

        public void setIsOnTable(boolean isOnTable) {
            this.isOnTable = isOnTable;
        }
    }

    public static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static class Table {
        Coordinate[][] coordinates;

        public Coordinate[][] getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinate[][] coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static Table generateTable (int[] size) {
        int row = size[0];
        int col = size[1];

        Coordinate[][] cords = new Coordinate[col][row];

        for (int y = 0; y < col; y++) {
            for (int x = 0; x < row; x++) {
                cords[y][x] = new Coordinate(x, y);
            }
        }

        Table table = new Table();
        table.setCoordinates(cords);
        return table;
    }

    public static void runRobot( Table table) {
        Robot robot = new Robot();
        robot.setIsOnTable(false);
        String[] directions = new String[] {"North, East, South, West"};

        while(true) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter command: ");
            String instructions = sc.nextLine();

            if (instructions.equals("quit")) {
                break;
            }

            String[] splitInstructions = instructions.split(" ");

            if (instructions.toLowerCase().contains("place")) {
                String[] coords = splitInstructions[1].split(",");

                int x_Coordinate = Integer.parseInt(coords[0]);
                int y_Coordinate = Integer.parseInt(coords[1]);

                robot.setX_Coordinate(x_Coordinate);
                robot.setY_Coordinate(y_Coordinate);
                robot.setFacing(splitInstructions[splitInstructions.length - 1]);
                robot.setIsOnTable(true);

            } else if (instructions.toLowerCase().contains("report") && robot.getIsOnTable()) {
                System.out.println(robot.getReport());
            } else if (instructions.toLowerCase().contains("move") && robot.getIsOnTable()) {
                if (robot.getFacing().equalsIgnoreCase("north")) {
                    robot.setY_Coordinate(robot.y_Coordinate +1);
                }
            }
        }
    }



    public static void main(String[] args) {
        Table table = generateTable(new int[]{5, 5});
        runRobot( table);

    }
}
