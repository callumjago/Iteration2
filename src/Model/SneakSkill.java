package Model;

public class SneakSkill extends Skill {
    private Player player;

    public SneakSkill(Player p){
        super("Sneak","Allows you to zip around the map undetected by hostiles!",new Level(1));
        player = p;
    }

    @Override
    public void ApplySkill() {
        player.toggleSneak();
    }

    @Override
    public void RemoveSkill() {
        player.stopSneaking();
    }
}
