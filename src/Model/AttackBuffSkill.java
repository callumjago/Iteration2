package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AttackBuffSkill extends Skill{
    private Player player;
    private Timer duration;
    private boolean CoolDown;

    public AttackBuffSkill(Player player){
        super("Rage Boost", "Get mad to increase your attack! (Costs 25 MP)", new Level(3), new SkillLevel(3));
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
        int mpCost = 25;
        int attack = 15;
        if (getLvl() == 2){
            mpCost = 30;
            time = 15;
            attack = 15;
        }
        else if (getLvl() == 3){
            mpCost = 40;
            time = 30;
            attack = 20;
        }
        if (player.checkCast(-mpCost)) {
            player.modifyMP(-mpCost);
            castAttempt(attack, time);
            duration.schedule(new TimerTask() {
                @Override
                public void run() {
                    RemoveSkill();
                    CoolDown = false;
                }
            }, time * 1000);
        }
        else{
            System.out.println("Not enough MP!");
            CoolDown = false;
        }
    }

    private void castAttempt(int attack, int time) {
        double rand = Math.random();
        double req = 0;
        if (getLvl() == 0) req = .1;
        if (getLvl() == 1) req = .05;
        if (getLvl() == 2) req = 0.0;
        if (rand > req) {
            player.modifyAtk(attack);
        } else {
            player.modifyAtk(-attack);
        }
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
        player.modifyAtk(-15);
        CoolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
