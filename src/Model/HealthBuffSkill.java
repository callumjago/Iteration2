package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HealthBuffSkill extends Skill{
    private Player player;
    private Timer duration;
    private boolean CoolDown;
    private int oldMaxHP;

    public HealthBuffSkill(Player player){
        super("Fortify Health", "Buff your constitution! (Costs 30 MP)", new Level(4), new SkillLevel(3));
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
        oldMaxHP = player.getMaxHP();
        int time = 10;
        int mpCost = 30;
        int health = 25;
        if (getLvl() == 2){
            mpCost = 35;
            time = 15;
            health = 50;
        }
        else if (getLvl() == 3){
            mpCost = 40;
            time = 30;
            health = 75;
        }
        if (player.checkCast(-mpCost)) {
            player.modifyMP(-mpCost);
            attemptCast(health);
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

    private void attemptCast(int health) {
        double rand = Math.random();
        double req = 0;
        if (getLvl() == 0) req = .75;
        if (getLvl() == 1) req = .5;
        if (getLvl() == 2) req = 0.0;
        if (rand > req) {
            player.setMaxHP(health+oldMaxHP);
            player.modifyHP(health);
        } else {
            player.modifyHP((int)(-health*.25));
        }

    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
        player.setMaxHP(oldMaxHP);
        CoolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
