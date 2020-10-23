package battleship.ships;

import battleship.Point;

public class SubmarineShip implements Ship{
    private final static String nameShip = "Submarine";
    private final static int sizeShip = 3;
    private Point begin;
    private Point end;
    private boolean isPlaced;
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

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public boolean isPlaced() {
        return isPlaced;
    }

    @Override
    public void setPlaced() {
        isPlaced = true;
    }
}
