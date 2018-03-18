package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DetectTrapSkill extends Skill {
    private Player player;
    private Timer duration;
    private boolean coolDown;
    public DetectTrapSkill(Player player) {
        super("Detect Traps","Allows you to see traps", new Level(1), new SkillLevel(3));
        this.player = player;
        coolDown = false;
    }

    @Override
    public void ApplySkill() {
        if (coolDown) {return;}
        else if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        int time = 10;
        int mpCost = 25;
        if (getLvl() == 2){
            mpCost = 35;
            time = 20;
        }
        else if (getLvl() == 3) {
            mpCost = 50;
            time = 30;
        }
        if (player.checkCast(-mpCost)) {
            player.modifyMP(-mpCost);



            if (duration == null) {
                duration = new Timer();
            }
            player.setCanDetectTraps(true);
            duration.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.setCanDetectTraps(false);
                        RemoveSkill();
                    }
                }, time * 1000);


        }
        else{
            System.out.println("Not enough MP!");
            coolDown = false;
        }
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
        coolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
