package Model;

public class DamageAE extends AOE {
    private int damage;

    public DamageAE(int AmtPerTick){
        super(6);
        damage = AmtPerTick;
    }

    public DamageAE(){
        super(6);
        damage = 0;
    }

    @Override
    public int getValue() {
        return damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

