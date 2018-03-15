package Model;

import java.util.ArrayList;

public class InteractionHandler {

    public InteractionHandler(){}

    public void generateInteractions(GameState GS){
        if (GS.getEntities() == null){
            return;
        }
        ArrayList<Entity> entities = GS.getEntities();
        ArrayList<ArrayList<Tile>> tileset = GS.getTileSet();
        for (int e = 0; e < entities.size(); ++e){
            Entity ent = entities.get(e);
            Tile tile = GS.getTile(ent.getPosition());
            if (tile.getObject() != null && ent instanceof SentientEntity){ // Means a generation needs to be implemented
                if (tile.getTileObjectID() == 2) { // Map Transition
                    //ent.setInteraction(new HealingIR(ent,tile.getObjectType())
                }
                else if (tile.getTileObjectID() == 3) { // Instant Death

                }
                else if (tile.getTileObjectID() == 4) { // Teleport

                }
                else if (tile.getTileObjectID() == 5) {  // Healing Effect

                }
                else if (tile.getTileObjectID() == 6) {  // Damage Effect

                }
                else if (tile.getTileObjectID() == 7) {  // Exp Effect

                }
                else if (tile.getTileObjectID() == 8) {  // Item

                }
                else if (tile.getTileObjectID() == 9) {  // OneShot
                    OneShotIR OneShot = new OneShotIR(tile, (SentientEntity) ent, tile.getValue(), GS);
                    ent.setInteraction(OneShot);
                    OneShot.applyEffect();
                    System.out.println("One Shot Interaction");
                }
            }
            else{
                ent.clearInteracton();
            }
        }
    }

}
