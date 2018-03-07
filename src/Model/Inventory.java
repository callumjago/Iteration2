package Model;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> itemList;
    public Inventory() {
        itemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        if(item == null) {
            return;
        }
        itemList.add(item);
    }

    //Generates an array list containing the names of every inventory item
    public ArrayList<String> getInventoryAsList() {
        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++) {
            stringList.add(itemList.get(i).getName());
        }
        return stringList;
    }
}
