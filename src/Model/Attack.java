package Model;

public class Attack {
    private int BaseAttackPoints;
    private int AddedAttackPoints;

    public Attack(int baseAttackPoints){
        if (baseAttackPoints <= 0) {
            BaseAttackPoints = 1; // Attack must be set above 0
        }
        else {
            BaseAttackPoints = baseAttackPoints;
        }
        AddedAttackPoints = 0;
    }

    public Attack(int baseAttackPoints, int addedAttackPoints) {
        if (baseAttackPoints <= 0) {
            BaseAttackPoints = 1; // Attack must be set above 0
        }
        else {
            BaseAttackPoints = baseAttackPoints;
        }
        AddedAttackPoints = addedAttackPoints;
    }

    public Attack(){
        BaseAttackPoints = 1;
        AddedAttackPoints = 0;
    }

    public int getBaseAttackPoints() {
        return BaseAttackPoints;
    }

    public void setBaseAttackPoints(int baseAttackPoints) {
        if (baseAttackPoints <= 0) {
            return;
        }
        else {
            BaseAttackPoints = baseAttackPoints;
        }
    }

    public int getAddedAttackPoints() {
        return AddedAttackPoints;
    }

    public void setAddedAttackPoints(int addedAttackPoints) {
        AddedAttackPoints = addedAttackPoints;
    }

    public int getAttackPoints(){
        if (AddedAttackPoints + BaseAttackPoints <= 0){
            return 1;
        }
        else {
            return BaseAttackPoints + AddedAttackPoints;
        }
    }

    public void clearAttackModifiers(){
        AddedAttackPoints = 0;
    }

    public void raiseBaseAttack(int boost){ // Base attack can only be raised upon level up!
        if (boost <= 0){
            return;
        }
        else{
            BaseAttackPoints += boost;
        }
    }

    public void modifyAttack(int delta) {
        AddedAttackPoints += delta;
    }
}
