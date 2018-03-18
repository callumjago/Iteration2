package Model;

public class Transaction implements Interaction {
    private NPC npc;
    private Player player;
    public Transaction(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
    }

    @Override
    public void applyEffect() {

    }
}
