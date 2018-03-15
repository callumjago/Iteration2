package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.geometry.VPos;

import Model.Dialogue;

import java.awt.*;

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
        gc.setFill(Color.BLACK);
        gc.fillText(dialogue.getCurrentDialogue(),375,650);
        //Text text = new Text(200,200,dialogue.getCurrentDialogue()); // TODO change to getCurrentDialogue from starting later

    }


}
