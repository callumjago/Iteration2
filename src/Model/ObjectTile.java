package Model;

import java.awt.Point;

public class ObjectTile extends Tile {
	private GameObject go;
	
	public ObjectTile(GameObject _go, Point _position, int _terrainID){
		super(_position, _terrainID);
		go = _go;
		this.setPassable();
	}
	
	public ObjectTile(int _terrain) {
		super(_terrain);
	}
	
	public ObjectTile(GameObject _go){
		super();
		go = _go;
		this.setPassable();
	}
	
	public GameObject getObjectType() {
		return go;
	}
	
	public void setPassable() {
		switch(getTerrainID()) {
		case 0:
			setPassable(true);
			break;
			
		case 1:
			setPassable(false);
			break;
			
		case 2:
			setPassable(false);
			break;
		}
		
		if(go instanceof Obstacle) {
			setPassable(false);
		}
	}
	
	public int getTileObjectID() {
		return go.getObjectID();
	}
	
	public boolean isPassable() {
		if(getPassable()) return true;
		
		else return false;
	}
	
	public void setObject(GameObject _go) {
		go = _go;
		this.setPassable();
	}
}
