package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyController implements EventHandler<KeyEvent> {
    private ArrayList<SubKeyController> controllers;
    public KeyController() {
        controllers = new ArrayList<>();
    }

    public void handle(KeyEvent event) {
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).keyInput(event.getCode());
        }
    }

    public void addController(SubKeyController controller) {
        if(controller == null) { return; }
        controllers.add(controller);
    }
}
