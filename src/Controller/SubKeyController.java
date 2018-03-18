package Controller;

import javafx.scene.input.KeyCode;

public abstract class SubKeyController {

    public SubKeyController() {

    }

    abstract void keyInput(KeyCode code);
    abstract boolean isActive();
}
