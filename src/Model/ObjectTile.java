package Model;

import java.awt.Point;

public class ObjectTile extends Tile {
	private GameObject go;
	
	public ObjectTile(GameObject _go, Point _position, int _terrainID){
		super(_position, _terrainID);
		go = _go;
		this.setPassable();
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
		switch(getID()) {
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
	
}
