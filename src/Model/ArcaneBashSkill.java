package Model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ArcaneBashSkill extends Skill {
    private Player player;
    private GameState GS;
    private double modifier;


    public ArcaneBashSkill(Player player, GameState GS){
        super("Arcane Bash", "Sacrifice HP to deal massive staff modifier!", new Level(1), new SkillLevel(4));
        this.player = player;
        this.GS = GS;
    }

    @Override
    public void ApplySkill() {
        if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        //else if (player.)
        modifier = 2;
        int hpCost = 10;
        if (getLvl() == 2){
            modifier = 2.5;
            hpCost = 20;
        }
        else if (getLvl() == 3){
            modifier = 3.5;
            hpCost = 30;
        }
        else if (getLvl() == 4){
            modifier = 5;
            hpCost = 40;
        }
        if (player.checkUse(-hpCost)){
            player.modifyHP(-hpCost);
            new AttackAction(player,GS,(int)modifier);
        }
        else{
            System.out.println("Not enough HP!");
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
