package Model;

import java.awt.*;
import java.util.ArrayList;

public class GameState {
    private Player player;
    private ArrayList<ArrayList<Tile>> tileSet;
    private ArrayList<Entity> entities;
    private ArrayList<Projectile> projectiles;

    public GameState() {

    }

    public Player getPlayer() {
        return player;
    }
    public Point getPlayerPosition() {
        return player.getPosition();
    }

    public ArrayList<ArrayList<Tile>> getTileSet() {
        return tileSet;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public int getTerrainTypeAt(int x, int y) {
        return tileSet.get(x).get(y).getTerrainID();
    }
    public int getObjectID(int x, int y) {
        return tileSet.get(x).get(y).getTileObjectID();
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTileSet(ArrayList<ArrayList<Tile>> tileSet) {
        this.tileSet = tileSet;
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x > tileSet.size() - 1) {
            return null;
        } else if (y < 0 || y > tileSet.get(0).size() - 1) {
            return null;
        }
        return tileSet.get(x).get(y);
    }

    public Tile getTile(Point p){
        if (p.getX() < 0 || p.getX() > tileSet.size() - 1) {
            return null;
        } else if (p.getY() < 0 || p.getY() > tileSet.get(0).size() - 1) {
            return null;
        }
        return tileSet.get((int) p.getX()).get((int) p.getY());
    }
    
    public void setTileAt(Tile _tile, Point _point) {
    	tileSet.get((int) _point.getX()).get((int) _point.getY());
    	tileSet.get((int) _point.getX()).set((int) _point.getY(), _tile);
    }

    public int getWidth() {
        return tileSet.size();
    }
    public int getHeight() {
        if(getWidth() == 0) {
            return -1;
        }
        return tileSet.get(0).size();
    }
}
