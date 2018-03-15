package Model;

import java.awt.*;
import java.util.ArrayList;

public class InteractionHandler {
    public InteractionHandler() {

    }

    public ArrayList<Interaction> generateInteractions(GameState gs) {
        ArrayList<Interaction> interactions = new ArrayList<>();

        //Check for attack interactions
        for(int i = 0; i < gs.getEntities().size(); i++) {
            if(gs.getEntities().get(i) instanceof SentientEntity) {
                if (gs.getEntities().get(i).getAttemptAttack()) {//Entity is attacking
                    //System.out.println(getAttackPoint((SentientEntity) gs.getEntities().get(i)).toString());
                    if (gs.getEntityAt(getAttackPoint((SentientEntity) gs.getEntities().get(i))) != null) {
                        interactions.add(new DamageInteraction((SentientEntity) gs.getEntityAt(getAttackPoint((SentientEntity) gs.getEntities().get(i))), ((SentientEntity) gs.getEntities().get(i)).getEquipWeapon().getAttackPoints()));
                    }
                }
            }

        }

        return interactions;
    }

    private Point getAttackPoint(SentientEntity entity) {
        int range = entity.getEquipWeapon().getRange();

        Point pos = entity.getPosition();
        if(entity.getOrientation().getDegree() == 0) {
            return new Point(pos.x+range, pos.y);
        } else if(entity.getOrientation().getDegree() == 90) {
            return new Point(pos.x, pos.y + range);
        } else if(entity.getOrientation().getDegree() == 180) {
            return new Point(pos.x-range, pos.y);
        } else {
            return new Point(pos.x, pos.y-range);
        }
    }
}
