package Model;

import javafx.scene.canvas.Canvas;

import View.DialogueUI;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Dialogue {

    private DialogueUI dialogueUI;
    private DialogueTree dialogueTree;
    private String startingDialogue;
    private TextNode currentTextNode;
    private String currentDialogue;
    private ArrayList<TextNode> currentAnswerNodes;
    private ArrayList<String> testString = new ArrayList<>();
    private ArrayList<String> testTypes = new ArrayList<>();
    private NPC currentNPCTalking;

    private boolean dialogueOpen = false;

    public Dialogue(Canvas canvas) {
        dialogueUI = new DialogueUI(this,canvas);
        testString.add("How are you?");
        testString.add("Good");
        testString.add("Bad");
        testString.add("Im glad you are good!");
        testString.add("Dang dude, that sucks. Hope you feel better.");
        testTypes.add("Q");
        testTypes.add("A");
        testTypes.add("A");
        testTypes.add("S");
        testTypes.add("S");
        dialogueTree = new DialogueTree(testString, testTypes);
        currentTextNode = dialogueTree.getRoot();
        currentDialogue = dialogueTree.getRootText();

        /*
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Enter has been pressed");
                checkForContinue();
            }
        });*/
    }

    public void startDialogue() {
        if (dialogueOpen == true) {
            dialogueUI.display();
            displayNextDialogue();
        }
    }

    public void displayNextDialogue(){

        dialogueUI.display();
        if(currentTextNode.getTextType() == "S") { // displays statement
            dialogueUI.displayStatement();
        }
        else if(currentTextNode.getTextType() == "Q") { // displays question and its answers
            dialogueUI.displayQuestion();
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<TextNode> answerNodes = currentTextNode.getChildren();
            currentAnswerNodes = answerNodes;
            for(TextNode textNode: answerNodes) {
                answers.add(textNode.getText());
            }
            dialogueUI.displayAnswers(answers);
        }
    }

    public void checkForContinue() {
        if (dialogueUI.getStatementShowing()) { // if a statement is currently showing so user can press ENTER
            if (currentTextNode.getChildren().size() == 0) {
                if (dialogueOpen)
                    dialogueUI.exitDialogue();
                dialogueOpen = false;
            }
            else {
                currentTextNode = currentTextNode.getChild(0);
                displayNextDialogue();
            }
        }
    }

    public void answerSelected(int nodeNumber) {
        //System.out.println(currentAnswerNodes.size());
        currentTextNode = currentAnswerNodes.get(nodeNumber-1).getChild(0);
        displayNextDialogue();
        currentNPCTalking.checkForAnswerEvent();
    }

    public String getStartingDialogue() {
        return startingDialogue;
    }

    public String getCurrentDialogue() {
        return currentTextNode.getText();//Default dialogue text goes here";
    }

    public void setCurrentTextNode(TextNode textNode) {
        currentTextNode = textNode;
    }

    public void setDialogueOpen(boolean bool) { dialogueOpen = bool; }

    public void setDialogueTree(DialogueTree newDialogueTree) {
        this.dialogueTree = newDialogueTree;
        this.currentTextNode = newDialogueTree.getRoot();
    }

    public void setCurrentNPCTalking(NPC npc) { currentNPCTalking = npc; }

    public NPC getCurrentNPCTalking() { return currentNPCTalking; }
}
