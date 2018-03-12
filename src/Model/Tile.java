package Model;

import java.awt.Point;

public class Tile {
	private Point position;
	private int terrainID;
	private boolean passable;
	
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
			
		case 1:
			passable = false;
			
		case 2:
			passable = false;
		}
	}
}
