package Model;

import java.awt.*;

public class Tile {
	private GameObject go;
	private Point position;
	private int terrainID;
	private boolean passable;
	
	public Tile(Point _pos, int _terrainID){
		position = _pos;
		terrainID = _terrainID;
		setPassable();
	}
	
	public Tile(Point _pos, int _terrainID, GameObject _go) {
		position = _pos;
		terrainID = _terrainID;
		go = _go;
		setPassable();
	}
	
	public Tile(){
		terrainID = 0;
		position = new Point(0, 0);
		setPassable();
	}
	
	public Tile(GameObject _go) {
		go = _go;
		setPassable();
	}
	
	public Tile(int _terrain) {
		terrainID = _terrain;
		position = new Point(0, 0);
		setPassable();
	}
	
	public Point getPosition() {
		return position;
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
		
		if(go instanceof Obstacle) {
			passable = false;
		}
	}
	
	public void setPassable(boolean _passable) {
		passable = _passable;
	}
	
	public int getTileObjectID() {
		if(go == null) return 0;
		
		else return go.getObjectID();
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public void setObject(GameObject _go) {
		go = _go;
		setPassable();
	}
	
	public GameObject getObject() {
		return go;
	}

	public int getValue(){
		return go.getValue();
	}
}