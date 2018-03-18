package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DefenseBuffSkill extends Skill{
    private Player player;
    private Timer duration;
    private boolean CoolDown;

    public DefenseBuffSkill(Player player){
        super("Iron Flesh", "Make your skin rock hard to shrug off arrows!(Costs 15 MP)", new Level(2));
        this.player = player;
        CoolDown = false;
    }

    @Override
    public void ApplySkill() {
        if (CoolDown) {return;}
        if (duration == null){
            duration = new Timer();
        }
        CoolDown = true;
        player.modifyDef(15);
        duration.schedule(new TimerTask(){
            @Override
            public void run() {
                RemoveSkill();
                CoolDown = false;
            }
        },15000);
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
        player.modifyDef(-15);
        CoolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
