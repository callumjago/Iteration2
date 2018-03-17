package Model;

import java.util.ArrayList;

public class Villager extends NPC {

    Dialogue dialogue;
    DialogueTree villagerDialogueTree;

    private ArrayList<String> villagerString = new ArrayList<>();
    private ArrayList<String> villagerTypes = new ArrayList<>();

    public Villager(Dialogue dialogue) {
        this.dialogue = dialogue;

        villagerString.add("Welcome to the village!");
        villagerString.add("Feel free to explore");
        villagerString.add("Beware of Shrek's Swamp");
        villagerTypes.add("S");
        villagerTypes.add("S");
        villagerTypes.add("S");

        villagerDialogueTree = new DialogueTree(villagerString,villagerTypes);
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
