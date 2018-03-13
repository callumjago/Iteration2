package Model;

public class DamageAE extends AOE {
    private int DamageAm;
    private int duration;

    public DamageAE(int dur, int Am) {
        setDamageAm(Am);
        setDuration(dur);
    }

    public int getDamageAm() {
        return DamageAm;
    }

    public void setDamageAm(int damageAm) {
        DamageAm = damageAm;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

