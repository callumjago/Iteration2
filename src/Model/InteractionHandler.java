package Model;

import java.util.ArrayList;

public class InteractionHandler {
    public InteractionHandler(){}

    public void generateInteractions(GameState GS, ArrayList<Interaction> interactions){
        ArrayList<Entity> entities = GS.getEntities();
        ArrayList<ArrayList<Tile>> tileset = GS.getTileSet();
        for (Entity ent : entities) {
            Tile tile = GS.getTile(ent.getPosition());
            if (ent instanceof Player) { // Means a generation needs to be implemented
                if (tile.getObject() != null) {
                    if (tile.getTileObjectID() == 2) { // Map Transition
                        interactions.add(new TeleportIR((SentientEntity) ent, GS, tile.getObject(), ((SentientEntity) ent).getInventory()));
                    }
                    else if (tile.getTileObjectID() == 3) { // Instant Death
                    	interactions.add(new InstantDeathIR((SentientEntity) ent));
                    }
                    else if (tile.getTileObjectID() == 4) { // Teleport
                    	interactions.add(new TeleportIR((SentientEntity) ent, GS, tile.getObject(), ((SentientEntity) ent).getInventory()));
                    }
                    else if (tile.getTileObjectID() == 5) {  // Healing Effect
                        interactions.add(new HealingIR((SentientEntity) ent, tile.getValue()));
                    }
                    else if (tile.getTileObjectID() == 6) {  // Damage Effect
                        interactions.add(new DamageIR((SentientEntity) ent, tile.getValue()));
                    }
                    else if (tile.getTileObjectID() == 7) {  // Exp Effect
                        interactions.add(new ExperienceIR((SentientEntity) ent, tile.getValue()));
                    }
                    else if (tile.getTileObjectID() == 8) {  // Item
                        interactions.add(new ItemInteraction(tile, (SentientEntity) ent, (Item) tile.getObject(), GS));
                    }
                    else if (tile.getTileObjectID() == 9) {  // OneShot
                        interactions.add(new OneShotIR(tile, (SentientEntity) ent, tile.getValue()));
                    }
                    else if(tile.getTileObjectID() == 10) { //MP Effect
                    	interactions.add(new ManaIR((SentientEntity) ent, tile.getValue()));
                    }
                }
            }

            if(ent instanceof ShopKeeper) {
                if(((ShopKeeper)ent).isTrading()) {
                    GS.getTransactionController().setCloseRequest(false);
                    interactions.add(new Transaction((ShopKeeper)ent, GS.getPlayer()));
                    System.out.println("Shopping");
                    ((ShopKeeper)ent).setIsTrading(false);
                }
                else if(((ShopKeeper)ent).isSelling()) {
                    GS.getTransactionController().setCloseRequest(false);
                    interactions.add(new Transaction((ShopKeeper)ent, GS.getPlayer()));
                    System.out.println("Selling");
                    //((ShopKeeper)ent).setIsSelling(false);
                }
            }
        }

        if(GS.getPlayer().isPickpocketing()) {
            Entity entity = GS.getEntity(GS.getPlayer().getForewardPosition());
            if(entity != null) {
                if(entity instanceof NPC) {
                    interactions.add(new PickPocketInteraction((NPC)entity, GS.getPlayer()));
                }
            }
        }
    }
}
