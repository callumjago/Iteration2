package Model;

import java.util.ArrayList;

public class ArrowHailSkill extends Skill {
    private Player player;
    private GameState GS;

    public ArrowHailSkill(Player player, GameState GS){
        super("Arrow Hail", "Launch a volley of arrows to damage foes around you!", new Level(5), new SkillLevel(3));
        this.player = player;
        this.GS = GS;
    }

    @Override
    public void ApplySkill() {
        if (!player.checkLvl(getReqLvl())){
            System.out.println("Level not high enough to use skill!");
            return;
        }
        int damage = 75;
        int range = 2;
        int mpCost = 20;
        if (getLvl() == 2){
            damage = 100;
            mpCost = 30;
            range = 3;
        }
        else if (getLvl() == 3){
            damage = 150;
            mpCost = 40;
            range = 4;
        }
        if (player.checkCast(-mpCost)){
            player.modifyMP(-mpCost);
            InfluenceRadius n = new InfluenceRadius(player.getPosition(), range, player.getOrientation());
            n.extendInfluence();
            for (int j = 0; j < range; j++){
                for (int i = 0; i < n.getInfluence().size(); i++) {
                    GS.addEntity(new Projectile(n.getInfluence().get(i), player.getOrientation().getDegree(), damage, range,0));
                }
                n.extendInfluence();
            }
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
