package Model;

public class KeyItem extends InteractiveItem {
    private int reqLvl;
    public KeyItem(int curLvl) {
        super();
    }
    public boolean passedRequirement(){
        if(curLvl >= reqLvl)
        {
            return true;
        }else
        {
            return false;
        }

    }

    public int getReqLvl() {
        return reqLvl;
    }

    public void setReqLvl(int reqLvl) {
        this.reqLvl = reqLvl;
    }
}