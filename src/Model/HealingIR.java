package Model;

public class HealingIR implements Interaction{
    SentientEntity entity;
    int healAmt;

    public HealingIR(SentientEntity _entity, int _healAmt) {
        entity = _entity;

        if(_healAmt < 0) { //can't have negative healing
            _healAmt = _healAmt*(-1);
        }

        healAmt = _healAmt;
    }

    public void applyEffect() {
        entity.modifyHP(healAmt);
    }
}