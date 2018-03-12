package Model;

import java.awt.Point;

public class Projectile extends Entity{
	private int damage;
	// Direction comes from Angle's Entity

	Projectile(int _damage){
		damage = _damage;
	}
	
	public int getDamage() {
		return damage;
	}
}
