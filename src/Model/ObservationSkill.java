package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ObservationSkill extends Skill {
    private Player player;
    private Timer duration;
    private boolean CoolDown;
    public ObservationSkill(Player player) {
        super("Observation","Allows you to see information about the enemy", new Level(1), new SkillLevel(3));
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
        int time = 60;
        int mpCost = 5;
        if (getLvl() == 2){
            time = 80;
        }
        else if (getLvl() == 3) {
            time = 120;
        }
        if (player.checkCast(-mpCost)) {
            player.modifyMP(-mpCost);


            if (duration == null) {
                duration = new Timer();
            }
            player.setObservationLevel(2);
            duration.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.setObservationLevel(0);
                        RemoveSkill();
                    }
                }, time * 1000);


        } else{
            System.out.println("Not enough MP!");
            CoolDown = false;
        }
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
        CoolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
