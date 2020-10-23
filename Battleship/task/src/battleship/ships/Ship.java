package battleship.ships;

import battleship.Point;

public interface Ship {
    String getName();
    int getSize();
    void setBegin(Point rowBegin);
    void setEnd(Point rowEnd);
    Point getBegin();
    Point getEnd();
    boolean isPlaced();
    void setPlaced();
}
