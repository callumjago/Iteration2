package Model;

import java.awt.Image;

public class UseItem extends Item {
	private int effect;
	private int value;
	private String name;
	private String description;
	
	public UseItem() {
		super();
		effect = 0;
		name = "Healing Potion";
		description = "A potion that restores lost HP";
		setValue();
	}
	
	public UseItem(int _itemID, Image _sprite, int _objectID, int _effect, String _name, String _description) {
		super(_objectID, _sprite, _itemID);
		effect = _effect;
		name = _name;
		description = _description;
		setValue();
	}
	
	public UseItem(int _effect, String _name, String _description) {
		super();
		effect = _effect;
		name = _name;
		description = _description;
	}
	
	public void setValue() {
		switch(effect) {
		case 0: //Healing
			value = 5;
			break;
			
		case 1: //Damage
			value = -5;
			break;
		}
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
	
	
}
