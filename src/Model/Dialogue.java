package Model;

import javafx.scene.canvas.Canvas;

import View.DialogueUI;

import java.util.ArrayList;

public class Dialogue {

    private DialogueUI dialogueUI;
    private DialogueTree dialogueTree;
    private String startingDialogue;
    private TextNode currentTextNode;
    private String currentDialogue;
    private ArrayList<String> testString = new ArrayList<>();
    private ArrayList<String> testTypes = new ArrayList<>();

    private boolean dialogueOpen = false;

    public Dialogue(Canvas canvas) {
        dialogueUI = new DialogueUI(this,canvas);
        testString.add("How are you?");
        testString.add("Good");
        testString.add("Bad");
        testTypes.add("Q");
        testTypes.add("A");
        testTypes.add("A");
        dialogueTree = new DialogueTree(testString, testTypes);
        currentTextNode = dialogueTree.getRoot();
        currentDialogue = dialogueTree.getRootText();
    }

    public void startDialogue() {
        dialogueOpen = true;
        dialogueUI.display();
        displayNextDialogue();
    }

    public void displayNextDialogue(){

        dialogueUI.display();
        //System.out.println(currentTextNode.getTextType());
        if(currentTextNode.getTextType() == "S") { // displays statement
            dialogueUI.displayStatement();
        }
        else if(currentTextNode.getTextType() == "Q") { // displays question and its answers
            //System.out.println("made it here");
            dialogueUI.displayQuestion();
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<TextNode> answerNodes = currentTextNode.getChildren();
            for(TextNode textNode: answerNodes) {
                answers.add(textNode.getText());
            }
            dialogueUI.displayAnswers(answers);
        }
        /*
        else if(currentTextNode.getTextType() == "A") {
            ArrayList<String> answers = new ArrayList<>();
            TextNode curr = currentTextNode;

            //dialogueUI.displayAnswers();
        }*/
    }

    public String getStartingDialogue() {
        return startingDialogue;
    }

    public String getCurrentDialogue() {
        return currentDialogue;//Default dialogue text goes here";
    }
}
