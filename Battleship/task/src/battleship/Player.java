package battleship;

public class Player {
    private final BattleField battleField;

    public Player() {
        battleField = new BattleField();
    }

    public void placeShips() {
        battleField.setShips();
    }

    public boolean allShipsDestroyed() {
        return battleField.isShipsDestroyed();
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void takeATurn(Player player) {
        battleField.printAllFields();
        battleField.takeAShot(player.getBattleField());
    }
}
