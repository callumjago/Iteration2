package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RemoveTrapSkill extends Skill {

    private GameState GS;
    private Player player;
    private Trap target;
    private Tile targetTile;
    //private Timer duration;
    private boolean CoolDown;

    public RemoveTrapSkill(Player player, GameState GS){
        super("Disable Trap","Attempts to remove trap", new Level(1), new SkillLevel(3));
        this.player = player;
        this.GS = GS;
        CoolDown = false;
    }

    @Override
    public void ApplySkill(){
        if (CoolDown) {return;}
        else if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        //int time = 10;
        int mpCost = 25;
        if (getLvl() == 2){
            mpCost = 35;
            //time = 20;
        }
        else if (getLvl() == 3) {
            mpCost = 50;
            //time = 30;
        }
        if (player.checkCast(-mpCost)) {
                player.modifyMP(-mpCost);
                GameObject go = GS.getTile(player.getForewardPosition()).getObject();
                if (go != null && go instanceof Trap) {
                    target = (Trap) go;
                    targetTile = GS.getTile(player.getForewardPosition());
                    attemptToDisable(target,targetTile);
                } else {
                    System.out.println("No target found :( !");
                }

        } else{
            System.out.println("Not enough MP!");
            CoolDown = false;
        }
    }

    public void RemoveSkill() {
        //duration.cancel();
        //duration.purge();
        //duration = null;
        CoolDown = false;
    }

    private void attemptToDisable(Trap trap,Tile trapTile) {

        double randomNumber = Math.random();
        //System.out.println(randomNumber);
        //System.out.println(getLvl());
        double numberRequired = 0.0; //For successful disable
        if (getLvl() == 0)
            numberRequired = 0.75; // 25% chance
        if (getLvl() == 1)
            numberRequired = 0.50; // 50% chance
        if (getLvl() == 2)
            numberRequired = 0.0; // 100% chance
        //System.out.println(randomNumber);
        if (randomNumber > numberRequired)
            trapTile.removeObject();
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {

    }
}
