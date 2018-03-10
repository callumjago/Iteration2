package Model;

import java.awt.*;

public abstract class Entity {
    private Inventory inventory;
    private Point position;
    public Entity() {

        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
