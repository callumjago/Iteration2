package Model;

import java.util.ArrayList;

public class Fireball extends Skill {
    private Player player;
    private GameState GS;

    public Fireball(Player player, GameState GS){
        super("Cast Fireball", "Incinerate your foes with a spicy fireball! (Costs 10 MP)", new Level(2), new SkillLevel(5));
        this.player = player;
        this.GS = GS;
    }

    @Override
    public void ApplySkill() {
        int damage = 30;
        int range = 6;
        int mpCost = 10;
        if (getLvl() == 2){
            damage = 40;
            mpCost = 15;
            range = 7;
        }
        else if (getLvl() == 3){
            damage = 50;
            mpCost = 20;
            range = 8;
        }
        else if (getLvl() == 2){
            damage = 60;
            mpCost = 25;
            range = 10;
        }
        else if (getLvl() == 5){
            damage = 75;
            mpCost = 35;
            range = 12;
        }
        if (player.checkCast(-mpCost)){
            GS.addEntity(new Projectile(player.getForewardPosition(),player.getDegree(),damage,range,1));
            player.modifyMP(-mpCost);
        }
        else{
            System.out.println("Not enough MP!");
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
