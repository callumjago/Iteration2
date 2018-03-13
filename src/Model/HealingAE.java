package Model;

public class HealingAE implements AOE {
    private int healBoost;
    private int duration;
    public HealingAE(dur, Am){
        setDuration(dur);
        setHealBoost(Am);
    }

    public int getHealBoost() {
        return healBoost;
    }

    public void setHealBoost(int healBoost) {
        this.healBoost = healBoost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
