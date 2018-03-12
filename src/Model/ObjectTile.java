package Model;

import java.awt.Point;

public class ObjectTile extends Tile {
	private GameObject go;
	
	ObjectTile(GameObject _go, Point _position, int _terrainID){
		go = _go;
		this.setPassable();
		terrainID = _terrainID;
		position = _position;
	}
	
	ObjectTile(GameObject _go){
		go = _go;
		position = new Point(0, 0);
		terrainID = 0;
		this.setPassable();
	}
	
	public GameObject getGameObject() {
		return go;
	}
	
	public void setPassable() {
		switch(getID()) {
		case 0:
			setPassable(true);
			
		case 1:
			setPassable(false);
			
		case 2:
			setPassable(false);
		}
		
		if(go instanceof Obstacle) {
			setPassable(false);
		}
	}
	
}
