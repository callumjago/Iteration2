package Model;

import java.awt.Point;

public class Projectile {
	private int damage;
	private Point direction;
	
	Projectile(int _damage, Point _direction){
		damage = _damage;
		direction = _direction;
	}
	
	public Point getDirection() {
		return direction;
	}
	
	public int getDamage() {
		return damage;
	}
}
