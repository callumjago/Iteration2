package Model;

public class EmptyTile extends Tile {

    public EmptyTile(int terrainID) {
        super(terrainID);
    }

    @Override
    int getTileObjectID() {
        return 0;
    }
}
