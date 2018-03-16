package Model;

import java.awt.Point;

public class Projectile extends Entity{
	private int damage;
	// Direction comes from Entity's Angle

	Projectile(Point origin, Angle direction, int _damage){
		super(origin, direction);
		damage = _damage;
	}
	
	public int getDamage() {
		return damage;
	}

	public void Tick(){
		moveForward();
	}
}
