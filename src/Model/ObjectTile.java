package Model;

public class ObjectTile extends Tile {

    public ObjectTile(int terrainID) {
        super(terrainID);
    }

    @Override
    int getTileObjectID() {
        return 0;
    }


}
