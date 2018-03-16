package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

    public DialogueUI(Dialogue dialogue, Canvas canvas) {
        this.dialogue = dialogue;
        this.canvas = canvas;

        gc = canvas.getGraphicsContext2D();
    }

    public void display() {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(250,600,250,100,10,10);
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
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,650);
    }

    public void displayQuestion() {
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,625);
        //System.out.println("displaying question");
    }

    public void displayAnswers(ArrayList<String> answers) {
        int amountOfAnswers = answers.size();
        for(int i = 1; i <= amountOfAnswers; i++) {
            gc.setFill(Color.BLACK);
            gc.fillText(answers.get(i-1),350+50*(i/amountOfAnswers),650);
            //System.out.println("displaying answer");
        }
    }


}
