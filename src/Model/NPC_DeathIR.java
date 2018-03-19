package Model;

public class NPC_DeathIR implements Interaction {
    private Player player;
    private NPC other;

    public NPC_DeathIR(Player _player, NPC _other){
        player = _player;
        other = _other;
    }

    @Override
    public void applyEffect() {
        if (other.isDead()){
            player.gainExp(other.getExpUponDeath());
            player.modifyMoney(other.getMoney());
            System.out.println("NPC money:\t" + other.getMoney());
        }
    }
}
