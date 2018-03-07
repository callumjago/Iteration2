package Model;

public class Player extends Entity {

    public Player() {

    }

    public void addItem(Item item) {
        getInventory().addItem(item);
    }
}
