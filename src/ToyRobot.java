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
        String[] directions = new String[] {"north, east, south, west"};

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.print("Enter command: ");
            String instructions = sc.nextLine();

            if (instructions.equals("quit")) {
                break;
            }

            String[] splitInstructions = instructions.split(" ");

            if (instructions.toLowerCase().contains("place")) {
                boolean isValidDirection = Arrays.toString(directions).contains(splitInstructions[splitInstructions.length - 1].toLowerCase());

                if (isValidDirection) {
                    String[] coords = splitInstructions[1].split(",");

                    int x_Coordinate = Integer.parseInt(coords[0]);
                    int y_Coordinate = Integer.parseInt(coords[1]);

                    if (!isWithinBounds(x_Coordinate, y_Coordinate, table)) {
                        System.out.println("Position is off the table");
                        continue;
                    }

                    robot.setX_Coordinate(x_Coordinate);
                    robot.setY_Coordinate(y_Coordinate);
                    robot.setFacing(splitInstructions[splitInstructions.length - 1]);
                    robot.setIsOnTable(true);
                }

            } else if (instructions.toLowerCase().contains("report") && robot.getIsOnTable()) {
                System.out.println(robot.getReport());
            } else if (instructions.toLowerCase().contains("move") && robot.getIsOnTable()) {
                int newX_Coordinate = robot.getX_Coordinate();
                int newY_Coordinate = robot.getY_Coordinate();

                if (robot.getFacing().equalsIgnoreCase("north")) {
                    newY_Coordinate = newY_Coordinate + 1;
                } else if (robot.getFacing().equalsIgnoreCase("east")) {
                    newX_Coordinate = newX_Coordinate + 1;
                } else if (robot.getFacing().equalsIgnoreCase("south")) {
                    newY_Coordinate = newY_Coordinate - 1;
                } else if (robot.getFacing().equalsIgnoreCase("west")) {
                    newX_Coordinate = newX_Coordinate - 1;
                }

                if (isWithinBounds(newX_Coordinate, newY_Coordinate, table)) {
                    robot.setX_Coordinate(newX_Coordinate);
                    robot.setY_Coordinate(newY_Coordinate);
                } else {
                    System.out.println("Robot would fall off the table");
                }
            }
        }
    }


    public static boolean isWithinBounds(int newX_Coordinate, int newY_Coordinate, Table table) {
        Coordinate[][] tableCoordinates = table.getCoordinates();

        int y = tableCoordinates.length;
        int x = tableCoordinates[0].length;

        boolean isXWithinBounds = newX_Coordinate >= 0 && newX_Coordinate < x;
        boolean isYWithinBounds = newY_Coordinate >= 0 && newY_Coordinate < y;

        return isXWithinBounds && isYWithinBounds;
    }

    public static void main(String[] args) {
        Table table = generateTable(new int[]{5, 5});
        runRobot( table);

    }
}
