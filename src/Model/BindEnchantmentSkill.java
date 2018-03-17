package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BindEnchantmentSkill extends Skill {
    private GameState GS;
    private Player player;
    private Timer duration;

    public BindEnchantmentSkill(Player player, GameState GS){
        this.player = player;
        this.GS = GS;
        duration = new Timer();
    }

    @Override
    public void ApplySkill(){
        Entity ent = GS.getEntity(player.getForewardPosition());
        if (ent != null && ent instanceof SentientEntity){
            ent.toggeleMovement();
            duration.schedule(new TimerTask(){
                @Override
                public void run() {
                    ent.toggeleMovement();
                    RemoveSkill();
                }
            },30000);
        }
        else{
            System.out.println("No target found :( !");
        }
    }

    @Override
    public void RemoveSkill() {
        duration.cancel();

    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
