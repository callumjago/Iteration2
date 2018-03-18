package Model;

import java.util.Random;

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
        Random rand = new Random();

        Item item = npc.getInventory().getItem(index);
        npc.getInventory().tossItem(index);
        player.addItem(item);



        player.setPickpocketing(false);
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNpc() {
        return npc;
    }

    public boolean getSuccess() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
