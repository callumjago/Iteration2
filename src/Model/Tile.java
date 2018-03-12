package Model;

import java.awt.*;

public abstract class Tile {
    private Point position;
    private int terrainID;
    private boolean passable;

    public Tile(int terrainID) {

        this.terrainID = terrainID;
    }

    public int getTerrainID() {
        return terrainID;
    }

    abstract int getTileObjectID();
}
