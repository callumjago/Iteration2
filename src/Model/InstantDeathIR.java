package Model;

public class InstantDeathIR implements Interaction{
    SentientEntity entity;
    int health = 0;

    public InstantDeathIR(SentientEntity _entity) {
        entity = _entity;

    }

    public void applyEffect() {
        entity.setHP(health);
    }
}

