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
        super("Bind","Stops foes movement for 20 seconds", new Level(1));
        this.player = player;
        this.GS = GS;
        CoolDown = false;
    }

    @Override
    public void ApplySkill(){
        if (CoolDown) {return;}
        player.modifyMP(-25);
        Entity ent = GS.getEntity(player.getForewardPosition());
        if (ent != null && ent instanceof SentientEntity){
            System.out.println("Bingo!");
            target = (SentientEntity) ent;
            if (duration == null){
                duration = new Timer();
            }
            ent.toggleMovement();
            duration.schedule(new TimerTask(){
                @Override
                public void run() {
                    ent.toggleMovement();
                    RemoveSkill();
                }
            },20000);
        }
        else{
            System.out.println("No target found :( !");
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
