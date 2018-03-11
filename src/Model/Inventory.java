package Model;

public class Inventory {
	private Item[] bag;
	private int MAX_SIZE;
	private int index;
	
	Inventory(int size){
		MAX_SIZE = size;
		index = 0;
		bag = new Item[MAX_SIZE];
	}
	
	public void addItem(Item item) {
		if(index >= MAX_SIZE) {
			System.out.println("Bag is Full!");
			return;
		}
		
		bag[index] = item;
		index++;
	}
	
	public Item getItem(int _index) {
		return bag[_index];
	}
	
	public void tossItem(int _index) {
		if(index == 0) {
			System.out.println("Bag is Empty!");
			return;
		}
		
		bag[_index] = null;
		index--;
	}
	
	public Item[] getBag(){
		return bag;
	}
	
	public void setMAX_SIZE(int max) {
		if(index >= max) { //Checks if there are too many items in your bag to switch bags.
			System.out.println("This bag is too small to carry all your items, consider dropping some items before switching bags");
			return;
		}
		
		MAX_SIZE = max;
		
		Item[] temp = bag;
		bag = new Item[MAX_SIZE];
		
		for(int i = 0; i < MAX_SIZE; i++) {
			bag[i] = temp[i];
		}
	}
}
