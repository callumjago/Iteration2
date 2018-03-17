package Controller;

import View.DialogueUI;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class AnswerClickHandler implements EventHandler<MouseEvent> {

    DialogueUI dialogueUI;

    public AnswerClickHandler(DialogueUI dialogueUI) { this.dialogueUI = dialogueUI; }

    public void handle(MouseEvent event) {
        //System.out.println("User has clicked");
        dialogueUI.userClicked();
    }
}
