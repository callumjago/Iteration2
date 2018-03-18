package Model;

import java.util.ArrayList;

public class ShopKeeper extends NPC {

    Dialogue dialogue;
    DialogueTree shopKeeperTree;
    private boolean isTrading;

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
        addToInventory(new Ring());

        shopKeeperTree = new DialogueTree(shopString,shopTypes);
        this.setName("Shopkeeper");
        isTrading = false;
    }

    public void talk() {
        dialogue.setDialogueTree(shopKeeperTree);
        dialogue.setCurrentNPCTalking(this);
        dialogue.setDialogueOpen(true);
    }

    public void checkForAnswerEvent() { // If user selects yes to open shop
        if (dialogue.getCurrentDialogue() == "Awesome!") {
            isTrading = true;
        }
    }

    public boolean isTrading() {
        return isTrading;
    }

    public void setIsTrading(boolean bool) {
        isTrading = bool;
    }

}
