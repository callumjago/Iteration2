package View;

import Controller.AnswerClickHandler;
import Controller.MenuClickHandler;
import Controller.MenuMouseController;
import Model.TextNode;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.geometry.VPos;

import Model.Dialogue;

import java.awt.*;
import java.util.ArrayList;

public class DialogueUI {

    Dialogue dialogue;
    Canvas canvas;
    GraphicsContext gc;
    MenuMouseController mouseController;

    int dialogueX = 250;
    int dialogueY = 600;
    int dialogueWidth = 250;
    int dialogueHeight = 100;

    int currentAmountOfAnswers = 0;

    AnswerClickHandler ach;

    boolean statementShowing = false; // to see if the npc has said a statement so user can press ENTER to continue
    boolean answerShowing = false;

    public DialogueUI(Dialogue dialogue, Canvas canvas) {
        this.dialogue = dialogue;
        this.canvas = canvas;
        mouseController = new MenuMouseController();
        gc = canvas.getGraphicsContext2D();

        ach = new AnswerClickHandler(this);
        canvas.setOnMouseClicked(ach);
        canvas.setOnMouseMoved(mouseController);

    }

    public void display() {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(dialogueX,dialogueY,dialogueWidth,dialogueHeight,10,10);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        /*
        gc.fillText(
                "Text centered on your Canvas",
                Math.round(canvas.getWidth()  / 2),
                Math.round(canvas.getHeight() / 2)
        ); */

    }

    public void displayStatement() {
        answerShowing = false;
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,650);
        statementShowing = true;
    }

    public void displayQuestion() {
        statementShowing = false;
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,625);
        //System.out.println("displaying question");
    }

    public void displayAnswers(ArrayList<String> answers) {
        statementShowing = false;
        answerShowing = true;
        currentAmountOfAnswers = answers.size();
        for(int i = 1; i <= currentAmountOfAnswers; i++) {
            gc.setFill(Color.BLACK);
            gc.fillText(answers.get(i-1),(int)(dialogueX+175*((double)i/currentAmountOfAnswers)),dialogueY + 75);
            //System.out.println("displaying answer");
        }
    }

    public void userClicked() { // called when user clicks
        //System.out.println("User has clicked");
        System.out.println(getSelectedAnswer());
        if (statementShowing && statementContinueCollisionTest(mouseController.getMouseX(),mouseController.getMouseY()) == 1)
            dialogue.checkForContinue();
        if (answerShowing && getSelectedAnswer() != -1)
            dialogue.answerSelected(getSelectedAnswer());

    }

    public int getSelectedAnswer() { // TODO called when user clicks
        int answerChoice = answerCollisionTest(mouseController.getMouseX(),mouseController.getMouseY());
        return answerChoice;
    }

    public int answerCollisionTest(int mouseX,int mouseY) {
        Bound bound;
        for(int i = 1; i <= currentAmountOfAnswers; i++) {
            bound = getAnswerBound(i);
            System.out.println("x: " + mouseX + " y: " + mouseY);
            if(bound.collisionTest(mouseX, mouseY)) {
                return i;
            }
        }
        return -1;
    }

    public int statementContinueCollisionTest(int mouseX,int mouseY) {
        Bound bound = getStatementContinueBound();
        if(bound.collisionTest(mouseX, mouseY)) {
            return 1;
        } else
            return -1;
    }

    public Bound getAnswerBound(int index) {
        return new Bound(dialogueX + dialogueWidth * (index-1)/currentAmountOfAnswers, dialogueX + dialogueWidth * index/currentAmountOfAnswers, dialogueY+(dialogueHeight/2), dialogueY+dialogueHeight);
    }

    public Bound getStatementContinueBound() {
        return new Bound(dialogueX,dialogueX+dialogueWidth,dialogueY+(dialogueHeight/2),dialogueY+dialogueHeight);
    }

    public boolean getStatementShowing() {
        return statementShowing;
    }

    public void exitDialogue() {
        gc.clearRect(dialogueX,dialogueY,dialogueWidth,dialogueHeight);
    }
}
