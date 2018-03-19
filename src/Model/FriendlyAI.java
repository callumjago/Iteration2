package Model;

import java.awt.*;
import java.util.ArrayList;

public class FriendlyAI extends AI{

    public FriendlyAI(NPC entity, GameState gameState) {
        super(entity, gameState);
    }

    @Override
    void tick() {

        ArrayList<Entity> entities = getGameState().getEntities();
        int closestEntityDistance = Integer.MAX_VALUE;
        int closestEntityIndex = -1;
        for(int i = 1; i < entities.size(); i++) {
            if(entities.get(i) instanceof NPC && entities.get(i) != getEntity()) {
                if(manhattenDistance(getEntity().getPosition(), entities.get(i).getPosition()) < closestEntityDistance) {
                    closestEntityDistance = manhattenDistance(getEntity().getPosition(), entities.get(i).getPosition());
                    closestEntityIndex = i;
                }
            }
        }
        if(closestEntityIndex == -1) {
            return;
        }
        ArrayList<Point> path = getPath(entities.get(closestEntityIndex).getPosition());
        if(path.size() >= 2) {//Move along path to player
            Angle moveAngle = getDirection(getEntity().getPosition(), path.get(path.size()-1));
            getEntity().setOrientation(moveAngle);
            if (super.getEntity().isMoveable()) {getEntity().setAttemptMove(true);}
        }
    }
}
