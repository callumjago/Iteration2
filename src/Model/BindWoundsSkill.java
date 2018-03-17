package Model;

import java.util.ArrayList;

public class BindWoundsSkill extends Skill {
    private Player player;

    public BindWoundsSkill(Player player) {
        super("Bind Wounds","Heals your wounds for 10 HP (costs 5 MP)",new Level(1));
        this.player = player;
    }


    @Override
    public void ApplySkill() {
       if (player.checkCast(-5)){
            player.modifyMP(-5);
            player.modifyHP(10);
        }
    }

    @Override
    public void RemoveSkill() {
        return;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
