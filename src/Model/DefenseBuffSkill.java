package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DefenseBuffSkill extends Skill{
    private Player player;
    private Timer duration;

    public DefenseBuffSkill(Player player){
        super("Iron Flesh", "Make your skin rock hard to shrug off arrows!(Costs 15 MP)", new Level(2));
        this.player = player;
        duration = new Timer();
    }

    @Override
    public void ApplySkill() {
        player.modifyDef(15);
        duration.schedule(new TimerTask(){
            @Override
            public void run() {
                RemoveSkill();
            }
        },30000);
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        player.modifyDef(-15);
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
