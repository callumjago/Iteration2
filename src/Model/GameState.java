package Model;
import java.util.Random;
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
    private Transaction transaction;
    private LevelUpMenu levelUpMenu;
    private MusicHandler musicHandler;

    public GameState() {
        interactions = new ArrayList<Interaction>();
        entities = new ArrayList<Entity>();
        moveHandler = new MovementHandler(this);
        interactionHandler = new InteractionHandler();
        pickPocketInteraction = null;
        transaction = null;
        levelUpMenu = null;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public LevelUpMenu getLevelUpMenu() {
        return levelUpMenu;
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

    public void performTransaction(int index) {
        if(transaction == null) {
            return;
        }

        transaction.applyEffect(index);
        transaction = null;
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
                if (entity instanceof Projectile && src instanceof SentientEntity && realMove) {

                    if((this.getPlayer().isSneaking() == true) && (entity.getDegree() - src.getDegree() == 90)){
                        ((Projectile) entity).setDamage(((Projectile) entity).getDamage()*2);
                    }
                    interactions.add(new ProjectileDamageIR((SentientEntity) src, ((Projectile) entity).getDamage(),this, (Projectile)entity));
                    if (src instanceof NPC) {
                        interactions.add(new NPC_DeathIR(getPlayer(), (NPC) src, this));
                    }
                }
                else if (src instanceof Projectile && entity instanceof SentientEntity && realMove) {
                    if((this.getPlayer().isSneaking() == true) && (src.getDegree() - entity.getDegree() == 90)){
                        ((Projectile) src).setDamage(((Projectile) src).getDamage()*2);
                    }
                    interactions.add(new ProjectileDamageIR((SentientEntity) entity, ((Projectile) src).getDamage(),this, (Projectile)src));
                    if (entity instanceof NPC) {
                        interactions.add(new NPC_DeathIR(getPlayer(), (NPC) entity, this));
                    }
                }
                return false;
            }
        }
        return true;
    }

    public Boolean AttackCollision(int x, int y, int damage, String tag, int accuracy) {
        Iterator<Entity> it = entities.iterator();
        Entity entity = null;
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        if (tag == "bow") {
            if (accuracy > n) {
                this.addEntity(new Projectile(getPlayer().getForewardPosition(), getPlayer().getDegree(), damage, 10, 0));
                getPlayer().modifyArrowCount(-1);
            } else {
                while (it.hasNext()) {
                    entity = it.next();
                    if (entity.getPosition().x == x && entity.getPosition().y == y) {
                        if ((this.getPlayer().isSneaking() == true) && (getPlayer().getDegree() == entity.getDegree())) {
                            damage = damage * 2;
                        }
                        interactions.add(new DamageIR((SentientEntity) entity, damage));
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void handleInteractions() {
        interactionHandler.generateInteractions(this, interactions);
        for (int i = 0; i < interactions.size(); i++) {
            if(interactions.get(i) instanceof PickPocketInteraction) {//Pick pocket attempt
                pickPocketInteraction = (PickPocketInteraction) interactions.get(i);
                continue;
            }
            if(interactions.get(i) instanceof Transaction) {//Transaction started
                transaction = (Transaction) interactions.get(i);
                continue;
            }
            interactions.get(i).applyEffect();
        }
        interactions.clear();

        if(getPlayer().hasLeveledUp()) {
            levelUpMenu = new LevelUpMenu(getPlayer().getPlayerClass());
            getPlayer().resetLevelUp();
        }
        if(levelUpMenu != null) {
            if(levelUpMenu.isLevelUpApplied()) { levelUpMenu = null; }
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
        entityCollision(e,e.getX(),e.getY(),true);
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

        }
        handleInteractions();

    }

    public void playerTick() {
        if(getPlayer().getAttemptMove()) {
            moveHandler.checkMove(getPlayer(), getPlayer().getOrientation());
        }
        if(getPlayer().isAttemptAttack())
        {
            getPlayer().setAttemptAttack(false);
            AttackAction a = new AttackAction(getPlayer(), this);
        }
        handleInteractions();


    }

    public void resetEntityAttempts() {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).resetAttemptMove();
        }
    }

    public MusicHandler getMusicHandler() { return this.musicHandler; }
    public void setMusicHandler(MusicHandler musicHandler) { this.musicHandler = musicHandler; }
}
