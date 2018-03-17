package Model;

public class SneakSkill extends Skill {
    private Player player;

    public SneakSkill(Player p){
        player = p;
    }

    @Override
    public void ApplySkill() {
        player.Sneak();
    }

    @Override
    public void RemoveSkill() {
        player.stopSneaking();
    }
}
