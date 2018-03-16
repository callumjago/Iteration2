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
                	MapTransitionIR mapTransition = new MapTransitionIR((SentientEntity) ent, tile.getObject(), GS);
                    ent.setInteraction(mapTransition);
                    mapTransition.applyEffect();
                    ent.clearInteraction();
                }
                else if (tile.getTileObjectID() == 3) { // Instant Death

                }
                else if (tile.getTileObjectID() == 4) { // Teleport

                }
                else if (tile.getTileObjectID() == 5) {  // Healing Effect
                    HealingIR HealIR = new HealingIR((SentientEntity) ent, tile.getValue());
                    ent.setInteraction(HealIR);
                    HealIR.applyEffect();
                    ent.clearInteraction();
                    System.out.println("Healing Area Effect Interaction");
                    System.out.println("Player HP: \t" + ((SentientEntity) ent).getHP());
                }
                else if (tile.getTileObjectID() == 6) {  // Damage Effect
                    DamageIR DmgIR = new DamageIR((SentientEntity) ent, tile.getValue());
                    ent.setInteraction(DmgIR);
                    DmgIR.applyEffect();
                    ent.clearInteraction();
                    System.out.println("Damage Area Effect Interaction");
                    System.out.println("Player HP: \t" + ((SentientEntity) ent).getHP());
                }
                else if (tile.getTileObjectID() == 7) {  // Exp Effect
                    ExperienceIR ExpIR = new ExperienceIR((SentientEntity) ent, tile.getValue());
                    ent.setInteraction(ExpIR);
                    ExpIR.applyEffect();
                    ent.clearInteraction();
                    System.out.println("Experience Area Effect Interaction");
                    System.out.println("Player Lvl : \t" + ((SentientEntity) ent).getLvl() + "\tPlayer EXP: \t" + ((SentientEntity) ent).getExp());
                }
                else if (tile.getTileObjectID() == 8) {  // Item
                	ItemInteraction item = new ItemInteraction(tile, (SentientEntity) ent, (Item) tile.getObject(), GS);
                	ent.setInteraction(item);
                	item.applyEffect();
                    ent.clearInteraction();
                    System.out.println("Item Interaction");
                }
                else if (tile.getTileObjectID() == 9) {  // OneShot
                    OneShotIR OneShot = new OneShotIR(tile, (SentientEntity) ent, tile.getValue());
                    ent.setInteraction(OneShot);
                    OneShot.applyEffect();
                    ent.clearInteraction();
                    System.out.println("One Shot Interaction");
                    System.out.println("Player HP: \t" + ((SentientEntity) ent).getHP());
                }
            }
            else{
                ent.clearInteraction();
            }
        }
    }
}
