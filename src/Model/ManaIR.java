package Model;

public class ManaIR implements Interaction{
    SentientEntity entity;
    int restoreAmt;

    public ManaIR(SentientEntity _entity, int _restoreAmt) {
        entity = _entity;

        if(_restoreAmt < 0) { //can't have negative healing
            _restoreAmt = _restoreAmt*(-1);
        }

        restoreAmt = _restoreAmt;
    }

    public void applyEffect() {
        entity.modifyMP(restoreAmt);
    }
}