package Model;

public class Level {
    private int Lvl;
    private int Experience;
    private int ExpToNextLevel;

    public Level(int lvl) {
        if (lvl < 1 || lvl > 100) {
            Lvl = 1;
        }
        else {
            Lvl = lvl;
        }
        ExpToNextLevel = Lvl * 100;
        Experience = 0;
    }

    public Level(){
        Lvl = 1;
        ExpToNextLevel = Lvl * 100;
        Experience = 0;
    }

    public int getLevel() {
        return Lvl;
    }

    public void setLevel(int lvl) {
        if (lvl < 1 || lvl > 100) {
            Lvl = 1;
        }
        else {
            Lvl = lvl;
        }
        ExpToNextLevel = Lvl * 100;
        Experience = 0;
    }

    private void raiseLvl(){
        Lvl++;
        ExpToNextLevel = Lvl * 100;
        Experience = 0;
    }

    public int gainExp(int expAmt){
        if (Experience + expAmt >= ExpToNextLevel) {
            expAmt -= ExpToNextLevel;
            raiseLvl();
            return expAmt;
        }
        else{
            Experience += expAmt;
            ExpToNextLevel -= Experience;
            return 0;
        }
    }

}
