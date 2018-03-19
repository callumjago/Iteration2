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
    private int accuracy;

    public AttackAction(SentientEntity _entity,  GameState gs) {
        entity = _entity;
        orientation = entity.getOrientation();
        AttackOr weapon = entity.getAtOr();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk();
        tag = entity.getWeaponTag();
        accuracy = entity.getEquipWeapon().getAccuracy();
        System.out.println(entity.getEquipWeapon().getTag());
        pos = entity.getNearbyLoc(weapon, entity.getWeaRange());
        System.out.println(pos.toString());
        if(pos != null) {
            for (Point pt : pos) {
                gs.AttackCollision(pt.x, pt.y, actAmt, tag, accuracy);
            }
        }
    }

    public AttackAction(SentientEntity _entity,  GameState gs, int modifier) {
        entity = _entity;
        orientation = entity.getOrientation();
        AttackOr weapon = entity.getAtOr();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk()*modifier;
        tag = entity.getWeaponTag();
        pos = entity.getNearbyLoc(weapon, entity.getWeaRange());
        if(pos != null) {
            for (Point pt : pos) {
                gs.AttackCollision(pt.x, pt.y, actAmt, tag, accuracy);
            }
        }
    }

    @Override
    public void applyEffect() {}
}
