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

    int dialogueX = 100;
    int dialogueY = 600;
    int dialogueWidth = 550;
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
        gc.setFont(gc.getFont());
        gc.setFill(Color.SILVER);
        gc.fillRoundRect(dialogueX,dialogueY,dialogueWidth,dialogueHeight,10,10);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentNPCTalking().getName(),375,610);
    }

    public void displayStatement() {
        gc.setFont(gc.getFont());
        answerShowing = false;
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,650);
        statementShowing = true;
        gc.fillPolygon(new double[]{dialogueX+dialogueWidth-15, dialogueX+dialogueWidth-15, dialogueX+dialogueWidth-5},
                new double[]{dialogueY+dialogueHeight-15, dialogueY+dialogueHeight-5, dialogueY+dialogueHeight-10}, 3);
    }

    public void displayQuestion() {
        gc.setFont(gc.getFont());
        statementShowing = false;
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,640);
    }

    public void displayAnswers(ArrayList<String> answers) {
        gc.setFont(gc.getFont());
        statementShowing = false;
        answerShowing = true;
        currentAmountOfAnswers = answers.size();
        for(int i = 1; i <= currentAmountOfAnswers; i++) {
            gc.setFill(Color.BLACK);
            gc.fillText(answers.get(i-1),(int)(dialogueX+375*((double)i/currentAmountOfAnswers)),dialogueY + 75);
        }
    }

    public void userClicked() { // called when user clicks
        //System.out.println(getSelectedAnswer());
        if (statementShowing && dialogue.statementContinueCollisionTest(mouseController.getMouseX(),mouseController.getMouseY()) == 1)
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
            //System.out.println("x: " + mouseX + " y: " + mouseY);
            if(bound.collisionTest(mouseX, mouseY)) {
                System.out.println(i);
                return i;
            }
        }
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

    public void exitDialogue() { gc.clearRect(dialogueX,dialogueY,dialogueWidth,dialogueHeight); }

    public int getCurrentAmountOfAnswer() { return currentAmountOfAnswers; }

    public void setControls() {
        canvas.setOnMouseClicked(ach);
        canvas.setOnMouseMoved(mouseController);
    }
}
