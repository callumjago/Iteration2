package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BindEnchantmentSkill extends Skill {
    private GameState GS;
    private Player player;
    private SentientEntity target;
    private Timer duration;
    private boolean CoolDown;

    public BindEnchantmentSkill(Player player, GameState GS){
        super("Bind","Stops foes movement for 20 seconds", new Level(1), new SkillLevel(3));
        this.player = player;
        this.GS = GS;
        CoolDown = false;
    }

    @Override
    public void ApplySkill(){
        if (CoolDown) {return;}
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
            Entity ent = GS.getEntity(player.getForewardPosition());
            if (ent != null && ent instanceof SentientEntity) {
                target = (SentientEntity) ent;
                if (duration == null) {
                    duration = new Timer();
                }
                ent.toggleMovement();
                duration.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ent.toggleMovement();
                        RemoveSkill();
                    }
                }, time * 1000);
            }
            else{
                System.out.println("No target found :( !");
            }
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
        CoolDown = false;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
