package battleship.ships;

import battleship.Point;

public interface Ship {
    String getName();

    int getSize();

    void setBegin(Point rowBegin);

    void setEnd(Point rowEnd);

    void checkIfDestroyed(char[][] field);

    boolean isDestroyed();
}
