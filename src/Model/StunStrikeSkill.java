package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class StunStrikeSkill extends Skill {
    private Player player;
    private GameState GS;
    private double modifier;
    private Timer duration;
    private boolean CoolDown;
    private SentientEntity target;

    public StunStrikeSkill(Player player, GameState GS){
        super("Stun Strike", "Deal a heavy blow that stuns target", new Level(3), new SkillLevel(3));
        this.player = player;
        this.GS = GS;
        CoolDown = false;
    }

    @Override
    public void ApplySkill() {
        if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        //else if (player.)
        modifier = 2;
        int time = 5;
        int hpCost = 10;
        if (getLvl() == 2){
            modifier = 2.5;
            time = 10;
            hpCost = 15;
        }
        else if (getLvl() == 3){
            modifier = 3;
            time = 15;
            hpCost = 20;
        }

        if (player.checkUse(-hpCost)){
            player.modifyHP(-hpCost);
            new AttackAction(player,GS,(int)modifier);
        }

        Entity ent = GS.getEntity(player.getForewardPosition());
        if (ent != null && ent instanceof SentientEntity) {
            target = (SentientEntity) ent;
            if (duration == null) {
                duration = new Timer();
            }
            ent.toggleMovement();
            //((SentientEntity) ent).modifyHP((int)(-20*modifier));
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

    @Override
    public void RemoveSkill() {
        return;
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
