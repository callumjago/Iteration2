package Model;

public class OneShotItem extends GameObject {
    private int OneShotID;
    private int Modifier;
    private int reqLvl;

    public OneShotItem(int _oneShotID, int modifier, int _reqLvl) {
        super(9);
        OneShotID = _oneShotID;
        Modifier = modifier;
        reqLvl = _reqLvl;
    }

    public OneShotItem() {
        super(9);
        Modifier = 0;
        OneShotID = 0;
        reqLvl = 0;
    }

    @Override
    public int getValue() {
        return Modifier;
    }

    public int getOneShotID() {
        return OneShotID;
    }

    public void setOneShotID(int _oneShotID) {
        OneShotID = _oneShotID;
    }

    public int getModifier() {
        return Modifier;
    }

    public void setModifier(int modifier) {
        Modifier = modifier;
    }
}