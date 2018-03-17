package Model;

public class DefenseBuffSkill extends Skill{
    private Player player;

    public DefenseBuffSkill(Player player){
        super("Iron Flesh", "Make your skin rock hard to shrug off arrows!(Costs 15 MP)", new Level(2));
        this.player = player;
    }

    @Override
    public void ApplySkill() {
        player.modifyDef(15);
    }

    @Override
    public void RemoveSkill() {
        return;
    }
}
