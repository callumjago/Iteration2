package Model;

public class ProjectileDamageIR extends DamageInteraction{

    private Projectile P;
    private GameState GS;

    public ProjectileDamageIR(SentientEntity entity, int damageAmount, GameState gameState, Projectile projectile) {
        super(entity, damageAmount);
        P = projectile;
        GS = gameState;
    }

    @Override
    public void applyEffect(){
        super.applyEffect();
        GS.removeEntity(P);
    }

}
