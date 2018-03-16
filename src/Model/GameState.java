package Model;

import java.awt.*;
import java.util.ArrayList;

public class GameState {
    private Player player;
    private ArrayList<ArrayList<Tile>> tileSet;
    private ArrayList<Entity> entities;
    private ArrayList<Projectile> projectiles;
    private MovementHandler moveHandler;
    private InteractionHandler interactionHandler;

    public GameState() {
        entities = new ArrayList<>();
        moveHandler = new MovementHandler(this);
        interactionHandler = new InteractionHandler();
        tileSet = new ArrayList<>();
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
    public void addEntity(Entity entity) {
        if(entity == null) {
            return;
        }
        ((NPC)entity).setAI(new HostileAI((NPC)entity, this));
        entities.add(entity);

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
    public Entity getEntityAt(Point point) {
        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getPosition().x == point.x && entities.get(i).getPosition().y == point.y) {
                return entities.get(i);
            }
        }
        return null;
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

    public boolean checkMove(int x, int y){ // Returns true if move is good
        Tile t =  getTileAt(x,y);
        if (t == null){
            return false;
        }
        for(int i = 0; i < entities.size(); i++) {//If entity on tile
            if(entities.get(i).getPosition().x == x && entities.get(i).getPosition().y == y) {
                return false;
            }
        }
        return t.isPassable();

    }

    public void tick() {

        for(int i = 1; i < entities.size(); i++) {
            if(entities.get(i) instanceof NPC) {
                ((NPC) entities.get(i)).tick();
            }
            if(entities.get(i).getAttemptMove()) {
                moveHandler.checkMove(entities.get(i), entities.get(i).getOrientation());

            }
            if(entities.get(i).getAttemptAttack()) {

            }
        }
        handleInteractions();



    }

    public void handleInteractions() {
        ArrayList<Interaction> interactions = interactionHandler.generateInteractions(this);
        for(int i = 0; i < interactions.size(); i++) {
            interactions.get(i).applyEffect();
        }



        for(int i = 1; i < entities.size(); i++) {
            if(((SentientEntity) entities.get(i)).isDead()) {
                entities.remove(i);
            }
        }
    }



    public void playerTick() {
        if(player.getAttemptMove()) {
            moveHandler.checkMove(player, player.getOrientation());

        }
        handleInteractions();
    }

    public void resetEntities() {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).resetAttempt();
        }
    }
}
