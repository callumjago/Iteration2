// Attribute Class
package Model;

public class ClassStat implements Stat{

    private int StatPoints;
    private int modifier;

    public ClassStat(int statPoints){
        if (statPoints < 0) {
            StatPoints = 0;
        }
        else if (statPoints > 99) {
            StatPoints = 99;
        }
        else {
            StatPoints = statPoints;
        }
    }

    public ClassStat(int statPoints, int modifier) {
        this.modifier = modifier;
        if (statPoints < 0) {
            StatPoints = 0;
        }
        else if (statPoints > 99) {
            StatPoints = 99;
        }
        else {
            StatPoints = statPoints;
        }
    }

    public ClassStat(){
        modifier = 0;
        StatPoints = 0;
    }

    public int getClassStat() {
        if (StatPoints + modifier > 99){
            return 99;
        }
        else if (StatPoints + modifier < 0){
            return 0;
        }
        else {
            return StatPoints + modifier;
        }
    }

    public void setClassStat(int statPoints) {
        if (statPoints < 0 || statPoints > 99){
            StatPoints = 0;
        }
        StatPoints = statPoints;
    }

    public void raiseBaseStat(int AddedPoints){
        if (AddedPoints < 0){
            return;
        }
        else if (StatPoints+AddedPoints > 99){
            StatPoints = 99;
        }
        else {
            StatPoints += AddedPoints;
        }
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int mod) {
        modifier = mod;
    }

    public void clearModifier(){
        modifier = 0;
    }

    public void modify(int delta) {
        modifier += delta;
    }
}
