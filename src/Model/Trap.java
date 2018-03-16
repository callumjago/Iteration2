package Model;

import java.awt.Point;

public class Trap extends OneShotItem {
	private int damage;
	private int requirement;
	
	public Trap(){
		super();
		damage = 10;
		requirement = 0;
	}

	@Override
	public int getValue() {
		return 0;
	}

	public Trap(int  _objectID, int _oneShotID, int _damage, int _requirement){
		super(_objectID, _oneShotID);
		damage = _damage;
		requirement = _requirement;
	}
	
	public Trap(int _damage, int _requirement){
		damage = _damage;
		requirement = _requirement;
	}
	
	public void setDamage(int  _damage) {
		damage = _damage;
	}
	
	public void setRequirement(int _requirement) {
		requirement = _requirement;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int requirement() {
		return requirement;
	}

}
