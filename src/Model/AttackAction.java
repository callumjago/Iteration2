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
    private String tag;

    public AttackAction(SentientEntity _entity,  GameState gs) {
        entity = _entity;
        orientation = entity.getOrientation();
        AttackOr weapon = entity.getAtOr();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk();
        tag = entity.getWeaponTag();
        pos = entity.getNearbyLoc(weapon, entity.getWeaRange());
        if(pos != null) {
            System.out.println("something");
            for (Point pt : pos) {
                System.out.println("something");
                gs.AttackCollision(pt.x, pt.y, actAmt, tag);
            }
        }
    }

    public AttackAction(SentientEntity _entity,  GameState gs, int modifier) {
        entity = _entity;
        orientation = entity.getOrientation();
        AttackOr weapon = entity.getAtOr();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk()*modifier;
        pos = entity.getNearbyLoc(weapon, entity.getWeaRange());
        if(pos != null) {
            for (Point pt : pos) {
                gs.AttackCollision(pt.x, pt.y, actAmt, "");
            }
        }
    }

    @Override
    public void applyEffect() {}
}
