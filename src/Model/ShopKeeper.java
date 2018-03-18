package Model;

import java.util.ArrayList;

public class ShopKeeper extends NPC {

    Dialogue dialogue;
    DialogueTree shopKeeperTree;

    private ArrayList<String> shopString = new ArrayList<>();
    private ArrayList<String> shopTypes = new ArrayList<>();

    public ShopKeeper(Dialogue dialogue) {
        this.dialogue = dialogue;

        shopString.add("Would you like to purchase some goods?");
        shopString.add("Yes");
        shopString.add("No");
        shopString.add("Awesome!");
        shopString.add("Wow, unbelievable.");
        shopTypes.add("Q");
        shopTypes.add("A");
        shopTypes.add("A");
        shopTypes.add("S");
        shopTypes.add("S");

        shopKeeperTree = new DialogueTree(shopString,shopTypes);
        this.setName("Shopkeeper");
    }

    public void talk() {
        dialogue.setDialogueTree(shopKeeperTree);
        dialogue.setCurrentNPCTalking(this);
        dialogue.setDialogueOpen(true);
    }

    public void checkForAnswerEvent() {
        if (dialogue.getCurrentDialogue() == "Awesome!") {
            System.out.println("Time to open the shop for you to browse!");
        }
    }

}
