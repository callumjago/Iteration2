package Model;

public class Defense {
    private int BaseDefensePoints;
    private int AddedDefensePoints;

    public Defense(int baseDefensePoints, int addedDefensePoints) {
        if (baseDefensePoints <= 0) {
            BaseDefensePoints = 1; // Defense must be set above 0
        }
        else {
            BaseDefensePoints = baseDefensePoints;
        }
        if (addedDefensePoints <= 0) {
            AddedDefensePoints = 0; // Defense must be set above 0
        }
        else {
            AddedDefensePoints = addedDefensePoints;
        }
    }

    public int getBaseDefensePoints() {
        return BaseDefensePoints;
    }

    public void setBaseDefensePoints(int baseDefensePoints) {
        if (baseDefensePoints <= 0) {
            return;
        }
        else {
            BaseDefensePoints = baseDefensePoints;
        }
    }

    public int getAddedDefensePoints() {
        return AddedDefensePoints;
    }

    public void setAddedDefensePoints(int addedDefensePoints) {
        if (addedDefensePoints <= 0) {
            return;
        }
        else {
            AddedDefensePoints = addedDefensePoints;
        }
    }

    public int getDefensePoints(){
        return BaseDefensePoints + AddedDefensePoints;
    }

    public void clearDefenseModifiers(){
        AddedDefensePoints = 0;
    }

    public void raiseBaseDefense(int boost){ // Base defense can only be raised upon level up!
        if (boost <= 0){
            return;
        }
        else{
            BaseDefensePoints += boost;
        }
    }
}
