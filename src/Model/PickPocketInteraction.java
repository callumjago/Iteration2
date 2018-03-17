package Model;

public class PickPocketInteraction implements Interaction {
    private NPC npc;
    private Player player;

    public PickPocketInteraction(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
    }

    @Override
    public void applyEffect() {

    }

    public void applyEffect(int index) {
        if(index >= npc.getInventory().numOfItems()) {
            return;
        }
        Item item = npc.getInventory().getItem(index);
        npc.getInventory().tossItem(index);
        player.addItem(item);
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNpc() {
        return npc;
    }
}
