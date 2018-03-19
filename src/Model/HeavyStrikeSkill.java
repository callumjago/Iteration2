package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HeavyStrikeSkill extends Skill {
    private Player player;
    private GameState GS;
    private double modifier;
    private Timer duration;
    private boolean CoolDown;

    public HeavyStrikeSkill(Player player, GameState GS){
        super("Heavy Strike", "Sacrifice HP to deal massive two-handed damage, but beware the stun after!", new Level(2), new SkillLevel(4));
        this.player = player;
        this.GS = GS;
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
        //else if (player.)
        modifier = 2.5;
        int hpCost = 10;
        if (getLvl() == 2){
            modifier = 3.5;
            hpCost = 20;
        }
        else if (getLvl() == 3){
            modifier = 5;
            hpCost = 30;
        }
        else if (getLvl() == 4){
            modifier = 6;
            hpCost = 40;
        }
        if (player.checkUse(-hpCost)){
            player.modifyHP(-hpCost);
            new AttackAction(player,GS,(int)modifier);
            player.toggleMovement();
            duration.schedule(new TimerTask() {
                @Override
                public void run() {
                    RemoveSkill();
                    player.toggleMovement();
                    CoolDown = false;
                }
            }, 3000);
        }
        else{
            System.out.println("Not enough HP!");
        }
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();
        duration.purge();
        duration = null;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
