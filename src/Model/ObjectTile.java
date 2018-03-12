package Model;

public class ObjectTile extends Tile {
	private GameObject go;
	
	ObjectTile(GameObject _go){
		go = _go;
	}
	
	public GameObject getGameObject() {
		return go;
	}
}
