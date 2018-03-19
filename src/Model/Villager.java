package Model;

import java.awt.*;
import java.util.ArrayList;

public class Villager extends NPC {

    Dialogue dialogue;
    DialogueTree villagerDialogueTree;

    private ArrayList<String> villagerString = new ArrayList<>();
    private ArrayList<String> villagerTypes = new ArrayList<>();

    public Villager(Dialogue dialogue, String name, String _description, Point pos, Angle theta, Armor initArm, Weapon initWeapon, Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney, int _exp, String tag, int _maxHP){
        super();
        this.dialogue = dialogue;
        this.setPosition(pos);

        villagerString.add("Welcome to the village!");
        villagerString.add("Feel free to explore");
        villagerString.add("Beware of Shrek's Swamp");
        villagerTypes.add("S");
        villagerTypes.add("S");
        villagerTypes.add("S");

        villagerDialogueTree = new DialogueTree(villagerString,villagerTypes);
        this.setName("Villager");
    }

    public void talk() {
        dialogue.setDialogueTree(villagerDialogueTree);
        dialogue.setCurrentNPCTalking(this);
        dialogue.setDialogueOpen(true);
    }

    public void checkForAnswerEvent() {
        //do nothing for this NPC
    }

}
