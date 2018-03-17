package Model;

public class Fireball extends Skill {
    private Player player;
    private GameState GS;

    public Fireball(Player player, GameState GS){
        super("Cast Fireball", "Incinerate your foes with a spicy fireball! (Costs 10 MP)", new Level(2));
        this.player = player;
        this.GS = GS;
    }

    @Override
    public void ApplySkill() {
        if (player.checkCast(10)){
            GS.addEntity(new Projectile(player.getProjectileStartPoint(),player.getDegree(),50,12));
            player.modifyMP(-10);
        }
    }

    @Override
    public void RemoveSkill() {
        return;
    }
}
