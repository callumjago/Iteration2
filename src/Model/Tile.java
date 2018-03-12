package Model;

import java.awt.Point;

public class Tile {
	protected Point position;
	protected int terrainID;
	protected boolean passable;
	
	Tile(Point _pos, int _terrainID){
		position = _pos;
		terrainID = _terrainID;
		setPassable();
	}
	
	Tile(){
		terrainID = 0;
		position = new Point(0, 0);
		setPassable();
	}
	
	public Point getPosition() {
		return position;
	}
	
	public boolean getPassable() {
		return passable;
	}
	
	public int getID() {
		return terrainID;
	}
	
	public void setPassable() {
		switch(terrainID) {
		case 0:
			passable = true;
			break;
			
		case 1:
			passable = false;
			break;
			
		case 2:
			passable = false;
			break;
		}
	}
	
	public void setPassable(boolean _passable) {
		passable = _passable;
	}
}
