package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BrainWashSkill extends Skill {
    private GameState GS;
    private Player player;
    private SentientEntity target;
    private Timer duration;
    private boolean coolDown;
    public BrainWashSkill(Player player, GameState GS) {
        super("Brainwash","Makes an enemy attack his allies", new Level(1), new SkillLevel(3));
        this.player = player;
        this.GS = GS;
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
            Entity ent = GS.getEntity(player.getForewardPosition());
            if (ent != null && ent instanceof NPC) {
                target = (NPC) ent;
                if (duration == null) {
                    duration = new Timer();
                }
                ((NPC) ent).setAI(new FriendlyAI((NPC)ent, GS));
                duration.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ((NPC) ent).setAI(new HostileAI((NPC)ent, GS));
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
