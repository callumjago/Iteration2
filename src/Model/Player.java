package Model;
import Controller.PlayerController;

import javax.swing.text.Position;
import java.awt.*;

public class Player extends Entity {
    private PlayerController pc;

    public Player() {

        pc = new PlayerController(this);
    }

    public void addItem(Item item) {
        getInventory().addItem(item);
    }

    public void moveUp() {
        setPosition(new Point(getPosition().x, --getPosition().y));
    }
    public void moveDown() {
        setPosition(new Point(getPosition().x, ++getPosition().y));
    }
    public void moveLeft() {
        setPosition(new Point(--getPosition().x, getPosition().y));
    }
    public void moveRight() {
        setPosition(new Point(++getPosition().x, getPosition().y));
    }


    public PlayerController getPc() {
        return pc;
    }

    public boolean isInputRegistered() {
        return pc.isInputRegistered();
    }

    public void reset() {
        pc.resetInputRegistered();
    }
}
