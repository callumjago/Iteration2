package Model;

public class HealingAE extends AOE {
    private int healBoost;

    public HealingAE(int AmtPerTick){
        super(5);
        healBoost = AmtPerTick;
    }

    public HealingAE(){
        super(5);
        healBoost = 0;
    }

    @Override
    public int getValue() {
        return healBoost;
    }

    public int getHealBoost() {
        return healBoost;
    }

    public void setHealBoost(int healBoost) {
        this.healBoost = healBoost;
    }
}