package battleship;

import java.util.Scanner;

public class BattleField {
    private final char[][] field;
    private final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final Scanner scanner;

    public BattleField() {
        scanner = new Scanner(System.in);
        field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
    }

    public void game() {
        printField();

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        buildShip(5);

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        buildShip(4);

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        buildShip(3);

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        buildShip(3);

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        buildShip(2);
    }

    private void buildShip(int length) {
        String firstCoordinate = scanner.next();
        String secondCoordinate = scanner.next();
        Point shipPartOne = parseCoordinate(firstCoordinate);
        Point shipPartTwo = parseCoordinate(secondCoordinate);
        checkOrder(shipPartOne, shipPartTwo);
        if (checkError(shipPartOne, shipPartTwo, length)) {
            setShip(shipPartOne, shipPartTwo);
            printField();
        } else {
            buildShip(length);
        }

    }

    private void checkOrder(Point firstPoint, Point secondPoint) {
        if (firstPoint.getX() > secondPoint.getX() || firstPoint.getY() > secondPoint.getY()) {
            Point tempPoint = new Point(firstPoint.getX(), firstPoint.getY());
            firstPoint.setX(secondPoint.getX());
            firstPoint.setY(secondPoint.getY());

            secondPoint.setX(tempPoint.getX());
            secondPoint.setY(tempPoint.getY());
        }
    }

    private boolean checkError(Point shipPartOne, Point shipPartTwo, int length) {
        if (isShipBiggerThanShould(shipPartOne, shipPartTwo, length) || isShipLesserThanShould(shipPartOne, shipPartTwo, length)) {
            System.out.println("Error! Wrong length of the %s! Try again: ");
            return false;
        } else if (shipPartOne.getX() != shipPartTwo.getX() && shipPartOne.getY() != shipPartTwo.getY()) {
            System.out.printf("Error! Wrong ship location! Try again:%n");
            return false;
        } else if (!checkBorders(shipPartOne, shipPartTwo)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }
        return true;
    }

    private boolean isShipBiggerThanShould(Point firstPoint, Point secondPoint, int ship) {
        return (Math.abs(secondPoint.getX() - firstPoint.getX()) > ship - 1)
                || (Math.abs(secondPoint.getY() - firstPoint.getY()) > ship - 1);
    }

    private boolean isShipLesserThanShould(Point firstPoint, Point secondPoint, int ship) {
        return (Math.abs(secondPoint.getX() - firstPoint.getX()) < ship - 1)
                && (Math.abs(secondPoint.getY() - firstPoint.getY()) < ship - 1);
    }

    private boolean checkBorders(Point firstPoint, Point secondPoint) {
        Point leftUpperCorner = getLeftUpperCornerOfCheckingArea(firstPoint);
        Point bottomRightCorner = getBottomRightCornerOfCheckingArea(secondPoint);
        for (int i = leftUpperCorner.getY(); i <= bottomRightCorner.getY(); i++) {
            for (int j = leftUpperCorner.getX(); j <= bottomRightCorner.getX(); j++) {
                if (!(field[i][j] == '~')) {
                    System.out.println(i + " " + j + " " + field[i][j]);
                    return false;
                }
            }
        }
        return true;
    }

    private Point getLeftUpperCornerOfCheckingArea(Point point) {
        int leftUpperCornerX = point.getX() == 0 ? 0 : point.getX() - 1;
        int leftUpperCornerY = point.getY() == 0 ? 0 : point.getY() - 1;
        return new Point(leftUpperCornerX, leftUpperCornerY);
    }

    private Point getBottomRightCornerOfCheckingArea(Point point) {
        int bottomRightCornerX = point.getX() == 10 - 1 ? point.getX() : point.getX() + 1;
        int bottomRightCornerY = point.getY() == 10 - 1 ? point.getY() : point.getY() + 1;
        return new Point(bottomRightCornerX, bottomRightCornerY);
    }

    private Point parseCoordinate(String coordinate) {
        String[] coordinates = coordinate.split("");
        Point firstCoordinate = new Point();
        firstCoordinate.setX(findPosition(coordinates[0].charAt(0)));
        if (coordinates.length == 3) {
            firstCoordinate.setY(Integer.parseInt(coordinates[1] + coordinates[2]) - 1);
        } else {
            firstCoordinate.setY(Integer.parseInt(coordinates[1]) - 1);
        }
        return firstCoordinate;
    }

    private int findPosition(char pos) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == pos) {
                return i;
            }
        }
        return -1;
    }

    private void setShip(Point firstPart, Point secondPart) {
        if (firstPart.getX() == secondPart.getX()) {
            for (int j = firstPart.getY(); j <= secondPart.getY(); j++) {
                field[firstPart.getX()][j] = 'O';
            }
        } else if (firstPart.getY() == secondPart.getY()) {
            for (int i = firstPart.getX(); i <= secondPart.getX(); i++) {
                field[i][firstPart.getY()] = 'O';
            }
        }
    }

    private void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
