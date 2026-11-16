import java.util.Arrays;
import java.util.Scanner;

public class ToyRobot {
    public static class Robot {
        int x_Coordinate;
        int y_Coordinate;
        String facing;

        public String getReport() {
            return getX_Coordinate() +","+ getY_Coordinate() +","+ getFacing();
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

        public void setX_Coordinate(int x_Coordinate) {
            this.x_Coordinate = x_Coordinate;
        }

        public void setY_Coordinate(int y_Coordinate) {
            this.y_Coordinate = y_Coordinate;
        }

        public void setFacing(String facing) {
            this.facing = facing;
        }

    }

    public static class Table {
        final int width;
        final int height;
        boolean isRobotOnTable;

        public Table(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public boolean getIsRobotOnTable() {return isRobotOnTable;}

        public void setIsRobotOnTable(boolean isOnTable) {
            this.isRobotOnTable = isOnTable;
        }
    }

    public static Table generateTable(int[] size) {
        return new Table(size[0], size[1]);
    }

    public static void runRobot( Table table) {
        Robot robot = new Robot();
        table.setIsRobotOnTable(false);
        String[] directions = new String[] {"north", "east", "south", "west"};

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.print("Enter command: ");
            String instructions = sc.nextLine();

            if (instructions.equals("quit")) {
                break;
            }

            String[] originalInstructions = instructions.trim().split(" ");
            String command = instructions.trim().toLowerCase();
            String[] splitInstructions = command.split(" ");

            if (splitInstructions[0].equals("place")) {
                String direction = splitInstructions[splitInstructions.length - 1].toLowerCase();
                boolean isValidDirection = Arrays.asList(directions).contains(direction);

                if (isValidDirection) {
                    if (splitInstructions.length != 3) {
                        continue;
                    }

                    String[] coords = splitInstructions[1].split(",");

                    if (coords.length != 2) {
                        continue;
                    }

                    int x_Coordinate;
                    int y_Coordinate;
                    try {
                        x_Coordinate = Integer.parseInt(coords[0]);
                        y_Coordinate = Integer.parseInt(coords[1]);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    if (!isWithinBounds(x_Coordinate, y_Coordinate, table)) {
                        System.out.println("Position is off the table");
                        continue;
                    }

                    robot.setX_Coordinate(x_Coordinate);
                    robot.setY_Coordinate(y_Coordinate);
                    robot.setFacing(originalInstructions[2]);
                    table.setIsRobotOnTable(true);
                }

            } else if (command.equals("report") && table.getIsRobotOnTable()) {
                System.out.println(robot.getReport());
            } else if (command.equals("move") && table.getIsRobotOnTable()) {
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
            } else if (table.getIsRobotOnTable() && (command.equals("left") || command.equals("right"))) {
                int dile;
                if (robot.getFacing().equalsIgnoreCase("east")) {
                    dile = 1;
                } else if (robot.getFacing().equalsIgnoreCase("south")) {
                    dile = 2;
                } else if (robot.getFacing().equalsIgnoreCase("west")) {
                    dile = 3;
                } else {
                    dile = 0;
                }

                if (command.equals("left")) {
                    dile--;
                    if (dile < 0) {
                        dile = 3;
                        robot.setFacing(directions[dile]);
                    }
                } else {
                    dile++;
                    if (dile > 3) {
                        dile = 0;
                        robot.setFacing(directions[dile]);
                    }
                }
                robot.setFacing(directions[dile].toUpperCase());
            }
        }
    }

    public static boolean isWithinBounds(int newX_Coordinate, int newY_Coordinate, Table table) {
        int width = table.getWidth();
        int height = table.getHeight();

        boolean isXWithinBounds = newX_Coordinate >= 0 && newX_Coordinate < width;
        boolean isYWithinBounds = newY_Coordinate >= 0 && newY_Coordinate < height;

        return isXWithinBounds && isYWithinBounds;
    }

    public static void main(String[] args) {
        Table table = generateTable(new int[]{5, 5});
        runRobot( table);

    }
}
