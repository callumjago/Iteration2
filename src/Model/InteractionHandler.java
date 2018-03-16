package Model;

import java.util.ArrayList;

public class InteractionHandler {
    public InteractionHandler(){}

    public void generateInteractions(GameState GS, ArrayList<Interaction> interactions){
        ArrayList<Entity> entities = GS.getEntities();
        ArrayList<ArrayList<Tile>> tileset = GS.getTileSet();
        for (Entity ent : entities) {
            Tile tile = GS.getTile(ent.getPosition());
            if (ent instanceof SentientEntity) { // Means a generation needs to be implemented
                if (tile.getObject() != null) {
                    if (tile.getTileObjectID() == 2) { // Map Transition
                        interactions.add(new TeleportIR((SentientEntity) ent, GS, tile.getObject()));
                    }
                    else if (tile.getTileObjectID() == 3) { // Instant Death

                    }
                    else if (tile.getTileObjectID() == 4) { // Teleport

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
                }
            }
        }
    }
}
