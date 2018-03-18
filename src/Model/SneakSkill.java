package Model;

import java.util.ArrayList;

public class SneakSkill extends Skill {
    private Player player;

    public SneakSkill(Player p){
        super("Sneak","Allows you to zip around the map undetected by hostiles!",new Level(1));
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

    void getSpecificStats(ArrayList<String> stats) {

    }
}
