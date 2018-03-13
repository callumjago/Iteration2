package Model;

public class ObjectTile extends Tile {
    private GameObject go;
    public ObjectTile(int terrainID) {
        super(terrainID);
    }

    @Override
    int getTileObjectID() {
        return go.getObjectID();
    }

    public void setObject(GameObject obj) {
        if(obj == null) {
            return;
        }
        go = obj;
    }
    
    public GameObject getObject() {
    	return go;
    }

}
