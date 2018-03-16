package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameState {
    private Player player;
    private ArrayList<ArrayList<Tile>> tileSet;
    private ArrayList<Entity> entities;
    private ArrayList<Interaction> interactions;
    private MovementHandler moveHandler;
    private InteractionHandler interactionHandler;

    public GameState() {
        interactions = new ArrayList<Interaction>();
        entities = new ArrayList<Entity>();
        moveHandler = new MovementHandler(this);
        interactionHandler = new InteractionHandler();
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
        entities.add(player);
        this.player = player;
        if (entities == null){
            entities = new ArrayList<Entity>();
        }
        entities.add(player);
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

    public int getWidth() {
        return tileSet.size();
    }

    public int getHeight() {
        if(getWidth() == 0) {
            return -1;
        }
        return tileSet.get(0).size();
    }

    public boolean checkMove(Entity src, int x, int y){ // Returns true if move is good
        Tile t =  getTileAt(x,y);
        if (t == null){
            if (src instanceof Projectile){
                removeEntity(src);
            }
            return false;
        }
        else if (!entityCollision(src,x,y)) {
            return false;
        }
        return t.isPassable();
    }

    public Boolean entityCollision(Entity src, int x, int y) {
        Iterator<Entity> it = entities.iterator();
        Entity entity = null;
        while (it.hasNext()) {
            entity = it.next();
            if (entity.getPosition().x == x && entity.getPosition().y == y) {
                if (entity instanceof Projectile && src instanceof SentientEntity) {
                    interactions.add(new ProjectileDamageIR((SentientEntity) src, ((Projectile) entity).getDamage(),this, (Projectile)entity));
                    System.out.println("Damage Interaction");
                }
                else if (src instanceof Projectile && entity instanceof SentientEntity) {
                    interactions.add(new ProjectileDamageIR((SentientEntity) entity, ((Projectile) src).getDamage(),this, (Projectile)src));
                    System.out.println("Damage Interaction");
                }
                return false;
            }
        }
        return true;
    }

    public void handleInteractions() {
        interactionHandler.generateInteractions(this, interactions);
        for (int i = 0; i < interactions.size(); i++) {
            interactions.get(i).applyEffect();
            interactions.clear();
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public  void removeEntity(Entity e){
        entities.remove(e);
    }


    public void tick() {

        for(int i = 1; i < entities.size(); i++) {
            Entity ent = entities.get(i);
            if (ent instanceof Projectile) {
               if (!((Projectile) ent).Tick()){
                    entities.remove(i);
                }
            }
            if (ent instanceof NPC) {
                ((NPC)ent).tick();
            }
            if (ent.getAttemptMove()) {
                moveHandler.checkMove(ent, ent.getOrientation());

            }
            handleInteractions();
        }
    }

    public void playerTick() {
        if(player.getAttemptMove()) {
            moveHandler.checkMove(player, player.getOrientation());

        }
        handleInteractions();
    }

    public void resetEntityAttempts() {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).resetAttemptMove();
        }
    }
}
