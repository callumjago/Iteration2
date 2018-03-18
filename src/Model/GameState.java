package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameState {
    private ArrayList<ArrayList<Tile>> tileSet;
    private ArrayList<Entity> entities;
    private ArrayList<Interaction> interactions;
    private MovementHandler moveHandler;
    private InteractionHandler interactionHandler;
    private PickPocketInteraction pickPocketInteraction;

    public GameState() {
        interactions = new ArrayList<Interaction>();
        entities = new ArrayList<Entity>();
        moveHandler = new MovementHandler(this);
        interactionHandler = new InteractionHandler();
        pickPocketInteraction = null;
    }

    public Player getPlayer() {
        return (Player) entities.get(0);
    }
    public Point getPlayerPosition() {
        return entities.get(0).getPosition();
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
        entities.add(0,player);
    }

    public PickPocketInteraction getPickPocketInteraction() {
        return pickPocketInteraction;
    }
    public void pickPocket(int index, boolean success) {
        if(pickPocketInteraction == null) {
            return;
        }
        if(!success) {//Pickpocket failed
            pickPocketInteraction.getNpc().setAI(new HostileAI(pickPocketInteraction.getNpc(), this));
            ((Player)entities.get(0)).setPickpocketing(false);
            pickPocketInteraction = null;
            return;
        }

        pickPocketInteraction.applyEffect(index);
        pickPocketInteraction = null;

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

    public Entity getEntity(Point p) {
        for (Entity ent : entities) {
            if (ent.getPosition().equals(p)) return ent;
        }
        return null;
    }

    public boolean checkMove(Entity src, int x, int y, boolean realMove){ // Returns true if move is good
        Tile t =  getTileAt(x,y);
        if (t == null){
            if (src instanceof Projectile){
                removeEntity(src);
            }
            return false;
        }
        else if (!entityCollision(src,x,y,realMove)) {
            return false;
        }
        return t.isPassable();
    }

    public boolean checkEntity(Point point){
        for (Entity ent:entities){
            if (ent.getPosition() == point) return true;
        }
        return false;
    }

    private boolean entityCollision(Entity src, int x, int y, boolean realMove) {
        // X and Y are the prospective coordinates the src wants to move to.
        Iterator<Entity> it = entities.iterator();
        Entity entity = null;
        while (it.hasNext()) {
            entity = it.next();
            if (entity.getX() == x && entity.getY() == y && (!entity.equals(src))) {
                /*System.out.println("Entity: " + entity.getX() + ", " + entity.getY());
                System.out.println("Move Requester: " + src.getX() + ", " + src.getY());
                System.out.println("X and Y: " + x + ", " + y);

                /*if(entity.getPosition().y != src.getPosition().y || entity.getPosition().x != src.getPosition().x) {
                    return false;
                }*/
                if (entity instanceof Projectile && src instanceof SentientEntity && realMove) {
                    interactions.add(new ProjectileDamageIR((SentientEntity) src, ((Projectile) entity).getDamage(),this, (Projectile)entity));
                    System.out.println("Damage Interaction: " + x + ", " + y );
                }
                else if (src instanceof Projectile && entity instanceof SentientEntity && realMove) {
                    interactions.add(new ProjectileDamageIR((SentientEntity) entity, ((Projectile) src).getDamage(),this, (Projectile)src));
                    System.out.println("Damage Interaction");
                }
                return false;
            }
        }
        return true;
    }

    public Boolean AttackCollision(int x, int y, int damage) {
        Iterator<Entity> it = entities.iterator();
        Entity entity = null;
        while (it.hasNext()) {
            entity = it.next();
            if (entity.getPosition().x == x && entity.getPosition().y == y) {

                interactions.add(new DamageIR((SentientEntity) entity, damage));
                return true;
            }
        }
        return false;
    }

    public void handleInteractions() {
        interactionHandler.generateInteractions(this, interactions);
        for (int i = 0; i < interactions.size(); i++) {
            if(interactions.get(i) instanceof PickPocketInteraction) {
                pickPocketInteraction = (PickPocketInteraction) interactions.get(i);
                continue;
            }
            interactions.get(i).applyEffect();
        }
        interactions.clear();
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
                if(((NPC) ent).isDead()) {
                    entities.remove(i);
                }
                ((NPC)ent).tick();
            }
            if (ent.getAttemptMove()) {
                moveHandler.checkMove(ent, ent.getOrientation());
            }
            handleInteractions();
        }
    }

    public void playerTick() {
        if(getPlayer().getAttemptMove()) {
            moveHandler.checkMove(getPlayer(), getPlayer().getOrientation());
        }
        if(getPlayer().isAttemptAttack())
        {
            getPlayer().setAttemptAttack(false);
            System.out.println("Attempt Attack true");
            AttackAction a = new AttackAction(getPlayer(), this);
        }
        handleInteractions();
    }

    public void resetEntityAttempts() {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).resetAttemptMove();
        }
    }
}
