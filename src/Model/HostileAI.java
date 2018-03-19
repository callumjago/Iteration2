package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;


public class HostileAI extends AI{
    public HostileAI(NPC entity, GameState gameState) {
        super(entity, gameState);
    }

    @Override
    void tick() {
        ArrayList<Point> path = getPath(getGameState().getPlayerPosition());
        if(path.size() == 0) { return; }
        if(path.size() >= getEntity().getDetectionRange()-getGameState().getPlayer().getSneakAmount()) {
            return;
        }

        if(path.size() >= 2) {//Move along path to player
            Angle moveAngle = getDirection(getEntity().getPosition(), path.get(path.size()-1));
            getEntity().setOrientation(moveAngle);
            if (super.getEntity().isMoveable()) {getEntity().setAttemptMove(true);}
        }
        if(path.size() == 1) {
            Angle moveAngle = getDirection(getEntity().getPosition(), path.get(path.size()-1));
            getEntity().setOrientation(moveAngle);
            if (super.getEntity().isMoveable()) {getEntity().setAttemptMove(true);}
        }


    }
}
