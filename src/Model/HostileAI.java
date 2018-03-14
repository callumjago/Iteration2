package Model;

import java.awt.*;
import java.util.ArrayList;

public class HostileAI extends AI{

    public HostileAI(Entity entity, GameState gameState) {
        super(entity, gameState);
    }

    @Override
    void tick() {
        ArrayList<Point> path = getPath(getGameState().getPlayerPosition());
//        for(int i = 0; i < path.size(); i++) {
//            System.out.println(path.get(i).toString());
//        }


        if(path.size() >= 2) {//Move along path to player
            Angle moveAngle = getDirection(getEntity().getPosition(), path.get(path.size()-1));
            getEntity().setOrientation(moveAngle);
            getEntity().setAttemptMove(true);
        }
        if(path.size() == 1) {
            getEntity().setAttemptAttack(true);
        }


    }
}
