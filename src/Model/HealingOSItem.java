package Model;

import java.awt.Image;
import java.awt.Point;

public class HealingOSItem extends OneShotItem{
	private int healing;
	
	public HealingOSItem() {
		super();
		healing = 10;
	}
	
	public HealingOSItem(int  _objectID, int _oneShotID, Image _sprite, int _healing, Point _position){
		super(_objectID, _oneShotID, _sprite);
		healing = _healing;
	}
	
	public HealingOSItem(int _healing, Point _position){
		healing = _healing;
	}
	
	public void setHealing(int _healing) {
		healing = _healing;
	}
	
	public int getHealing() {
		return healing;
	}

}
