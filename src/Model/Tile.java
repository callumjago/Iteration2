package Model;

import java.awt.*;

public class Tile {
	private Point position;
	private int terrainID;
	private boolean passable;
	
	public Tile(Point _pos, int _terrainID){
		position = _pos;
		terrainID = _terrainID;
		setPassable();
	}
	
	public Tile(){
		terrainID = 0;
		position = new Point(0, 0);
		setPassable();
	}
	
	public Tile(int _terrain) {
		terrainID = _terrain;
		position = new Point(0, 0);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public boolean getPassable() {
		return passable;
	}
	
	public int getTerrainID() {
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
	
	public int getTileObjectID() {
		return 0;
	}
	
	public boolean isPassable() {
		if(passable) {
			return true;
		}
		
		else return false;
	}
}
