package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyController implements EventHandler<KeyEvent> {
    private ArrayList<SubKeyController> controllers;
    private boolean keyPressed;
    public KeyController() {
        controllers = new ArrayList<>();
        keyPressed = false;
    }

    public void handle(KeyEvent event) {

        for(int i = 0; i < controllers.size(); i++) {//If player in pickpocket
            if(controllers.get(i) instanceof PickPocketController) {
                if(controllers.get(i).isActive()) {
                    controllers.get(i).keyInput(event.getCode());
                    return;
                }
            }
        }
        for(int i = 0; i < controllers.size(); i++) {//If player in transaction
            if(controllers.get(i) instanceof TransactionController) {
                if(controllers.get(i).isActive()) {
                    controllers.get(i).keyInput(event.getCode());
                    return;
                }
            }
        }
        for(int i = 0; i < controllers.size(); i++) {//If player in menus
            if(controllers.get(i) instanceof MenuController) {
                if(controllers.get(i).isActive()) {
                    controllers.get(i).keyInput(event.getCode());
                    return;
                }
            }
        }
        for(int i = 0; i < controllers.size(); i++) {
            controllers.get(i).keyInput(event.getCode());
        }
        keyPressed = true;
    }

    public void addController(SubKeyController controller) {
        if(controller == null) { return; }
        controllers.add(controller);
    }

    public boolean getKeyPressed() {
        return keyPressed;
    }
    public void resetKeyPressed() {
        keyPressed = false;
    }


}
