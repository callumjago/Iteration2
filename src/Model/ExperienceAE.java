package Model;

public class ExperienceAE extends AOE {
    private int exp;

    public ExperienceAE(int AmtPerTick) {
        super(7);
        exp = AmtPerTick;
    }

    public ExperienceAE() {
        super(7);
        exp = 0;
    }

    @Override
    public int getValue() {
        return exp;
    }

    public int getHealBoost() {
        return exp;
    }

    public void setHealBoost(int exp) {
        this.exp = exp;
    }
}
    