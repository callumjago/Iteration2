package Model;

import java.util.ArrayList;

public class NPC_DeathIR implements Interaction {
    private Player player;
    private NPC other;
    private GameState GS;

    public NPC_DeathIR(Player _player, NPC _other, GameState _GS){
        player = _player;
        other = _other;
        GS = _GS;
    }

    @Override
    public void applyEffect() {
        if (other.isDead()){
            player.gainExp(other.getExpUponDeath());
            player.modifyMoney(other.getMoney());
            ArrayList<Item> loot = other.getBag();
            player.add(loot);
            GS.removeEntity(other);
            System.out.println("NPC money:\t" + other.getMoney());
        }
    }
}
