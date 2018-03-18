package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DefenseBuffSkill extends Skill{
    private Player player;
    private Timer duration;
    private boolean CoolDown;

    public DefenseBuffSkill(Player player){
        super("Iron Flesh", "Make your skin rock hard to shrug off arrows!(Costs 15 MP)", new Level(2), new SkillLevel(4));
        this.player = player;
        CoolDown = false;
    }

    @Override
    public void ApplySkill() {
        if (CoolDown) {return;}
        else if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        if (duration == null){
            duration = new Timer();
        }
        CoolDown = true;
        int time = 10;
        int mpCost = 20;
        int defense = 15;
        if (getLvl() == 2){
            mpCost = 30;
            time = 15;
            defense = 15;
        }
        else if (getLvl() == 3){
            mpCost = 40;
            time = 30;
            defense = 20;
        }
        if (player.checkCast(-mpCost)) {
            player.modifyMP(-mpCost);
            player.modifyDef(defense);
            duration.schedule(new TimerTask() {
                @Override
                public void run() {
                    RemoveSkill();
                }
            }, time * 1000);
        }
        else{
            System.out.println("Not enough MP!");
            CoolDown = false;
        }
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
