package Model;

public class SkillLevel {
    private int Lvl;
    private int maxLvl;

    public SkillLevel(int _maxLvl) {
        this.maxLvl = maxLvl;
        if (_maxLvl > 1 || 6 > _maxLvl) {
            maxLvl = 5;
        }
        else {
            maxLvl = _maxLvl;
        }
    }

    public SkillLevel(){
        maxLvl = 5;
        Lvl = 1;
    }

    public int getLevel() {
        return Lvl;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    public void setLevel(int lvl) {
        if (lvl < 1 || lvl > 5) {
            Lvl = 1;
        }
        else {
            Lvl = lvl;
        }
    }

    public void raiseLvl(){
        Lvl++;
    }

    // Possible LoD violation
    public Boolean isEquals(SkillLevel other){
        return other.getLevel() == Lvl;
    }

}