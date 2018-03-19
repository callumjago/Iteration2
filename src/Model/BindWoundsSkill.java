package Model;

import java.util.ArrayList;

public class BindWoundsSkill extends Skill {
    private Player player;

    public BindWoundsSkill(Player player) {
        super("Bind Wounds","Heals your wounds for 10 HP (costs 5 MP)",new Level(1), new SkillLevel(5));
        this.player = player;
    }


    @Override
    public void ApplySkill() {
        if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        int mpCost = 5;
        int HP = 10;
        if (getLvl() == 2){
            HP = 25;
            mpCost = 10;
        }
        else if (getLvl() == 3){
            HP = 50;
            mpCost = 15;
        }
        else if (getLvl() == 4){
            HP = 75;
            mpCost = 20;
        }
        else if (getLvl() == 5){
            HP = 100;
            mpCost = 25;
        }
        if (player.checkCast(-mpCost)){
            player.modifyMP(-mpCost);
            attemptCast(HP);
        }
        else{
            System.out.println("Not enough MP!");
        }
    }

    private void attemptCast(int HP) {
        double rand = Math.random();
        double req = 0;
        if (getLvl() == 0) req = .75;
        if (getLvl() == 1) req = .5;
        if (getLvl() == 2) req = 0.0;
        if (rand > req) {
            player.modifyHP(HP);
        } else {
            player.modifyHP((int)(-HP*.25));
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
