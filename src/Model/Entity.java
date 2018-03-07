package Model;

public abstract class Entity {
    private Inventory inventory;
    public Entity() {

        inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
