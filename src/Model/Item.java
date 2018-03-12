package Model;

import java.awt.Image;

public abstract class Item extends GameObject{

    private int ItemID;

    public Item(int ObjectID, Image Sprite, int itemID) {
        super(ObjectID,Sprite); // Object ID doesn't need to be taken in later once default ObjID is finalized.
        ItemID = itemID;
    }

    public Item(){
        super();
        ItemID = 000;
    }

    public String getName() {return "Item";}

    // Returns an item's ID
    public int getItemID(){
        return ItemID;
    }

    // Sets an item's ID
    void setItemID(int ID){ // If constrains are defined, fill in here later
        ItemID = ID;
    }
}
