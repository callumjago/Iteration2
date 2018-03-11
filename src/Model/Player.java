package Model;
import Controller.PlayerController;
public class Player extends Entity {
    private PlayerController pc;

    public Player() {

        pc = new PlayerController(this);
    }

    public void addItem(Item item) {
        getInventory().addItem(item);
    }


}
