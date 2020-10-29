package battleship;

import battleship.ships.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleField {
    private final char[][] gameBoard;
    private final char[][] fightBoard;
    private final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final Scanner scanner;
    private List<Ship> ships;

    public BattleField() {
        scanner = new Scanner(System.in);
        gameBoard = new char[10][10];
        fightBoard = new char[10][10];
        fillBoard(gameBoard);
        fillBoard(fightBoard);
    }

    private void fillBoard(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
    }

    public void setShips() {
        printField();
        ships = new ArrayList<>();
        ships.add(new AirCraftShip());
        ships.add(new BattleShip());
        ships.add(new SubmarineShip());
        ships.add(new CruiserShip());
        ships.add(new DestroyerShip());

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        System.out.println();
        buildShip(ships.get(0));

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        System.out.println();
        buildShip(ships.get(1));

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        System.out.println();
        buildShip(ships.get(2));

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        System.out.println();
        buildShip(ships.get(3));

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        System.out.println();
        buildShip(ships.get(4));
    }

    private boolean checkCorrectInput(String coordinate) {
        String coordinateX = coordinate.substring(0, 1);
        String coordinateY = coordinate.substring(1);
        int x = findPosition(coordinateX.charAt(0));
        int y = Integer.parseInt(coordinateY);
        return x == -1 || (y < 1 || y > 10);
    }

    private void buildShip(Ship ship) {
        String firstCoordinate = scanner.next();
        String secondCoordinate = scanner.next();
        System.out.println();
        Point shipPartOne = parseCoordinate(firstCoordinate);
        Point shipPartTwo = parseCoordinate(secondCoordinate);
        checkOrder(shipPartOne, shipPartTwo);
        if (checkError(shipPartOne, shipPartTwo, ship)) {
            setShip(shipPartOne, shipPartTwo);
            ship.setBegin(shipPartOne);
            ship.setEnd(shipPartTwo);
            printField();
        } else {
            buildShip(ship);
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

    private boolean checkError(Point shipPartOne, Point shipPartTwo, Ship ship) {
        if (isShipBiggerThanShould(shipPartOne, shipPartTwo, ship) || isShipLesserThanShould(shipPartOne, shipPartTwo, ship)) {
            System.out.printf("Error! Wrong length of the %s! Try again: \n", ship.getName());
            return false;
        } else if (shipPartOne.getX() != shipPartTwo.getX() && shipPartOne.getY() != shipPartTwo.getY()) {
            System.out.println("Error! Wrong ship location! Try again: ");
            return false;
        } else if (!checkBorders(shipPartOne, shipPartTwo)) {
            System.out.println("Error! You placed it too close to another one. Try again: ");
            return false;
        }
        return true;
    }

    private boolean isShipBiggerThanShould(Point firstPoint, Point secondPoint, Ship ship) {
        return (Math.abs(secondPoint.getX() - firstPoint.getX()) > ship.getSize() - 1)
                || (Math.abs(secondPoint.getY() - firstPoint.getY()) > ship.getSize() - 1);
    }

    private boolean isShipLesserThanShould(Point firstPoint, Point secondPoint, Ship ship) {
        return (Math.abs(secondPoint.getX() - firstPoint.getX()) < ship.getSize() - 1)
                && (Math.abs(secondPoint.getY() - firstPoint.getY()) < ship.getSize() - 1);
    }

    private boolean checkBorders(Point firstPoint, Point secondPoint) {
        int minRow = Math.min(firstPoint.getX(), secondPoint.getX());
        int minCol = Math.min(firstPoint.getY(), secondPoint.getY());
        int maxRow = Math.max(firstPoint.getX(), secondPoint.getX());
        int maxCol = Math.max(firstPoint.getY(), secondPoint.getY());
        minRow = minRow == 0 ? 0 : minRow - 1;
        minCol = minCol == 0 ? 0 : minCol - 1;
        maxRow = maxRow == 9 ? 9 : maxRow + 1;
        maxCol = maxCol == 9 ? 9 : maxCol + 1;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (gameBoard[i][j] != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    private Point parseCoordinate(String coordinate) {
        String coordinateX = coordinate.substring(0, 1);
        String coordinateY = coordinate.substring(1);
        Point firstCoordinate = new Point();
        firstCoordinate.setX(findPosition(coordinateX.charAt(0)));
        firstCoordinate.setY(Integer.parseInt(coordinateY) - 1);
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
                gameBoard[firstPart.getX()][j] = 'O';
            }
        } else if (firstPart.getY() == secondPart.getY()) {
            for (int i = firstPart.getX(); i <= secondPart.getX(); i++) {
                gameBoard[i][firstPart.getY()] = 'O';
            }
        }
    }

    private void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printFieldForBattle() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(alphabet[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(fightBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isShipDestroyed(char[][] enemyBoard, List<Ship> ships) {
        for (Ship ship : ships) {
            ship.checkIfDestroyed(enemyBoard);
            if (ship.isDestroyed()) {
                ships.remove(ship);
                return true;
            }
        }
        return false;
    }

    public boolean isShipsDestroyed() {
        return ships.isEmpty();
    }

    public void printAllFields() {
        printFieldForBattle();
        System.out.println("---------------------");
        printField();
    }

    public void takeAShot(BattleField enemyBoard) {
        Scanner scanner = new Scanner(System.in);

        String coordinate = scanner.nextLine();
        if (checkCorrectInput(coordinate)) {
            return;
        }

        Point shot = parseCoordinate(coordinate);
        placeShotOnBoard(shot, enemyBoard);
    }

    private void placeShotOnBoard(Point shot, BattleField enemyBoard) {
        if (enemyBoard.gameBoard[shot.getX()][shot.getY()] == '~') {
            enemyBoard.gameBoard[shot.getX()][shot.getY()] = 'M';
            fightBoard[shot.getX()][shot.getY()] = 'M';
            System.out.println("You missed!");
        } else {
            enemyBoard.gameBoard[shot.getX()][shot.getY()] = 'X';
            fightBoard[shot.getX()][shot.getY()] = 'X';
            System.out.println(isShipDestroyed(enemyBoard.gameBoard, enemyBoard.ships) ? "You sank a ship!" : "You hit a ship!");
        }
    }

}
