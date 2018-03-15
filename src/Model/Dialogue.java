package Model;

import javafx.scene.canvas.Canvas;

import View.DialogueUI;

import java.util.ArrayList;

public class Dialogue {

    DialogueUI dialogueUI;

    DialogueTree dialogueTree;
    String startingDialogue;
    String currentDialogue;
    ArrayList<String> testString;
    ArrayList<String> testTypes;

    public Dialogue(Canvas canvas) {
        dialogueUI = new DialogueUI(this,canvas);
        testString.add("Hello");
        testTypes.add("S");
        dialogueTree = new DialogueTree(testString, testTypes);
        startingDialogue = dialogueTree.getRootText();
    }

    public void displayDialogue(){
        dialogueUI.display();
    }

    public String getStartingDialogue() {
        return startingDialogue;
    }

    public String getCurrentDialogue() {
        return "Hello there!";//Default dialogue text goes here";
    }
}
