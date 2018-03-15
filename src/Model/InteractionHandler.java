package Model;

import java.util.ArrayList;

public class InteractionHandler {
	public InteractionHandler() {
		
	}
	
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
                	TeleportIR mapTransition = new TeleportIR((SentientEntity) ent, GS, tile.getObject());
                    ent.setInteraction(mapTransition);
                    mapTransition.applyEffect();
                    ent.clearInteracton();
                }
            }
        }
    }
}
