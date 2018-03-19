package Model;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> bag;
	private int MAX_SIZE;
	
	Inventory(int size){
		MAX_SIZE = size;
		bag = new ArrayList<Item>();
	}

	Inventory(){
		MAX_SIZE = 100;
		bag = new ArrayList<Item>();
	}

    public void addItem(Item item) {
        if(item == null) {
            return;
        }
        bag.add(item);
    }

    //Generates an array list containing the names of every inventory item
    public ArrayList<String> getInventoryAsList() {
        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0; i < bag.size(); i++) {
            stringList.add(bag.get(i).getName());
        }
        return stringList;
    }
    
    public int[] getInventoryIDs(){
    	int idList[] = new int[bag.size()];
    	for(int i = 0; i < bag.size(); i++) {
    		idList[i] = bag.get(i).getItemID();
    	}
    	return idList;
    }

    public int numOfItems() {
        return bag.size();
    }

	/*public void addItem(Item item) {
		if(bag.size() >= MAX_SIZE) {
			System.out.println("Bag is Full!");
			return;
		}
		bag.add(item);
	}*/
	
	public Item getItem(int _index) {
		return bag.get(_index);
	}
	
	public void tossItem(int _index) {
		if(_index < 0) {
			System.out.println("Bag is Empty!");
			return;
		}
		bag.remove(_index);
	}
	
	public ArrayList<Item> getBag(){
		return bag;
	}
	
	public void setMAX_SIZE(int max) {
		if(bag.size() >= max) { //Checks if there are too many items in your bag to switch bags.
			System.out.println("This bag is too small to carry all your items, consider dropping some items before switching bags");
			return;
		}
		
		MAX_SIZE = max;
	}

    public void remove(Item i) {
		bag.remove(i);
    }
}
