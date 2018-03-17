package Model;

public class AttackAction implements Interaction {
    SentientEntity entity;
    int actAmt;
    private Angle orientation;
    private GameState gs;



    public AttackAction(SentientEntity _entity) {
        entity = _entity;
        orientation = entity.getOrientation();
        int dir = orientation.getDegree();
        actAmt = entity.getAtk();
        gs.AttackCollision(entity.getNearbyLoc().x, entity.getNearbyLoc().y, actAmt);
    }
//    public void applyEffect() {
//        entity.modifyHP(dmgAmt);
//    }
    @Override
    public void applyEffect() {}
}
