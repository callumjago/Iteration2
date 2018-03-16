package Model;

public class DamageIR implements Interaction{
    SentientEntity entity;
    int dmgAmt;

    public DamageIR(SentientEntity _entity, int _dmgAmt) {
        entity = _entity;

        if(_dmgAmt > 0) { //can't have positive damage
            _dmgAmt = _dmgAmt*(-1);
        }
        else if (_dmgAmt == 0) {
            _dmgAmt = -1;
        }
        dmgAmt = _dmgAmt;
    }

    public void applyEffect() {
        entity.modifyHP(dmgAmt);
    }
}