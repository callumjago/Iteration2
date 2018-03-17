package Model;

import java.awt.*;
import java.util.Queue;
import java.util.Iterator;

public class AttackAction implements Interaction {
    SentientEntity entity;
    int actAmt;
    private Angle orientation;
    private GameState gs;
    private Weapon weapon;
    private Queue<Point> pos;



    public AttackAction(SentientEntity _entity) {
        entity = _entity;
        orientation = entity.getOrientation();
        AttackOr weapon = entity.getAtOr();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk();
        pos = entity.getNearbyLoc(weapon, entity.getWeaRange());
        for (Point pt: pos){
            gs.AttackCollision(pt.x, pt.y, actAmt);
        }
    }
//    public void applyEffect() {
//        entity.modifyHP(dmgAmt);
//    }
    @Override
    public void applyEffect() {}
}
