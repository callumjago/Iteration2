package Model;

import java.awt.Point;

public class Projectile extends Entity{
	private int damage;
	private int range;
	// Direction comes from Entity's Angle

	Projectile(Point origin, Angle direction, int _damage, int _range){
		super(origin, direction);
		damage = _damage;
		range = _range;
	}
	
	public int getDamage() {
		return damage;
	}

	public Boolean Tick(){
		if (range > 0) {
			System.out.println("Projectile Tick");
			moveForward();
			range--;
			return true;
		}
		else {
			return false;
		}
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
}
