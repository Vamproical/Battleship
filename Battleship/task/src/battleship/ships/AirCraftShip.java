package battleship.ships;

import battleship.Point;

public class AirCraftShip implements Ship{
    private final static String nameShip = "Aircraft Carrier";
    private final static int sizeShip = 5;
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
