package Model;

import java.util.ArrayList;

public class CastLightningSkill extends Skill{
        private Player player;
        private GameState GS;

        public CastLightningSkill(Player player, GameState GS){
            super("Cast Lightning", "Zap your foes with a shocking bolt! (Costs 20 MP)", new Level(3), new SkillLevel(3));
            this.player = player;
            this.GS = GS;
        }

        @Override
        public void ApplySkill() {
            if (!player.checkLvl(getReqLvl())){
                System.out.println("Level not high enough to use skill!");
                return;
            }
            int damage = 30;
            int range = 3;
            int mpCost = 20;
            if (getLvl() == 2){
                damage = 40;
                mpCost = 25;
                range = 4;
            }
            else if (getLvl() == 3){
                damage = 60;
                mpCost = 30;
                range = 5;
            }
            if (player.checkCast(-mpCost)){
                player.modifyMP(-mpCost);
                attemptCast(range, damage);
            }
            else{
                System.out.println("Not enough MP!");
            }
        }

    private void attemptCast(int range, int damage) {
        double rand = Math.random();
        double req = 0;
        if (getLvl() == 0) req = .1;
        if (getLvl() == 1) req = .05;
        if (getLvl() == 2) req = 0.0;
        if (rand > req) {
            Entity p = null;
            for (int i = 0; i < range; ++i){
                if (p == null) {
                    p = new Projectile(player.getForewardPosition(), player.getDegree(), damage, 0, 1);
                }
                else{
                    p = new Projectile(p.getForewardPosition(), p.getDegree(),damage,0,1);
                }
                GS.addEntity(p);
            }
        } else {
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

