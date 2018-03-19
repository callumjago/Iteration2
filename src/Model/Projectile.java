package Model;

import java.awt.Point;

public class Projectile extends Entity{
	private int damage;
	private int range;
	private int projectileID;
	// Direction comes from Entity's Angle

	public Projectile(Point origin, int direction, int _damage, int _range, int projectileID){
		super(origin, new Angle(direction));
		damage = _damage;
		range = _range;
		this.projectileID = projectileID;
	}
	
	public int getDamage() {
		return damage;
	}

	public Boolean Tick(){
		if (range > 0) {
			moveForward();
			range--;
			damage = (int)(damage*.75);
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

	public int getProjectileID() {
		return projectileID;
	}
}
