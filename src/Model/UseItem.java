package Model;

public abstract class UseItem extends Item {
	private int value;
	private String name;
	private String description;
	
	public UseItem() {
		super();
		name = "Healing Potion";
		description = "A potion that restores lost HP";
		value = getValue();
	}
	
	public UseItem(int _itemID, int _value, String _name, String _description) {
		super(_itemID);
		name = _name;
		description = _description;
		value = _value;
	}
	
	public UseItem(String _name, String _description) {
		super();
		name = _name;
		description = _description;
	}
	
	public void setValue(int _value) {
		value = _value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public abstract void use(Player player);
}

