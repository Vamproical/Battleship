package battleship.ships;

import battleship.Point;

public class CruiserShip implements Ship{
    private final static String nameShip = "Cruiser";
    private final static int sizeShip = 3;
    private boolean isDestroyed;
    private boolean isPlaced;
    private Point begin;
    private Point end;

    @Override
    public String getName() {
        return nameShip;
    }

    @Override
    public int getSize() {
        return sizeShip;
    }

    public void setBegin(Point begin) {
        this.begin = begin;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void checkIfDestroyed(char[][] field) {
        int result = 0;
        if (begin.getX() == end.getX()) {
            for (int j = begin.getY(); j <= end.getY(); j++) {
                if (field[begin.getX()][j] == 'X') {
                    ++result;
                }
            }
        } else if (begin.getY() == end.getY()) {
            for (int i = begin.getX(); i <= end.getX(); i++) {
                if (field[i][begin.getY()] == 'X') {
                    ++result;
                }
            }
        }
        isDestroyed = result == sizeShip;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
