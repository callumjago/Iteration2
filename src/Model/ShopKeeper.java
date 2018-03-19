package Model;

import java.awt.*;
import java.util.ArrayList;

public class ShopKeeper extends NPC {

    Dialogue dialogue;
    DialogueTree shopKeeperTree;
    private boolean isTrading;
    private boolean isSelling;
    private float priceModifyer;

    private ArrayList<String> shopString = new ArrayList<>();
    private ArrayList<String> shopTypes = new ArrayList<>();

    public ShopKeeper(Dialogue dialogue, String name, String _description, Point pos, Angle theta, Armor initArm, Weapon initWeapon, Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney, int _exp, String tag, int _maxHP){
        super();

        this.setPosition(pos);

        this.dialogue = dialogue;

        shopString.add("Would you like to purchase some goods?");
        shopString.add("Yes(Buy)");
        shopString.add("Yes(Sell)");
        shopString.add("No");
        shopTypes.add("Q");
        shopTypes.add("A");
        shopTypes.add("A");
        shopTypes.add("A");
        addToInventory(new Ring());

        shopKeeperTree = new DialogueTree(shopString,shopTypes);
        this.setName("Shopkeeper");
        isTrading = false;
        priceModifyer = 1f;
    }

    public void talk() {
        dialogue.setDialogueTree(shopKeeperTree);
        dialogue.setCurrentNPCTalking(this);
        dialogue.setDialogueOpen(true);
    }

    public void checkForAnswerEvent() { // If user selects yes to open shop
        if (dialogue.getCurrentDialogue() == "Yes(Buy)") {
            isTrading = true;
        }
        else if(dialogue.getCurrentDialogue() == "Yes(Sell)") {
            isSelling = true;
        }
    }

    public boolean isTrading() {
        return isTrading;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setIsTrading(boolean bool) {
        isTrading = bool;
    }

    public void setIsSelling(boolean bool) {
        isSelling = bool;
    }

    public void modifyPriceModifyer(float delta) {
        if(priceModifyer + delta < 0) {
            priceModifyer = 0;
        }
        if(priceModifyer + delta > 1) {
            priceModifyer = 1;
        }
        priceModifyer += delta;
    }

    public float getPriceModifyer() {
        return priceModifyer;
    }

}
