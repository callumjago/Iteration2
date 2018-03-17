package Controller;

import Model.GameState;
import Model.PickPocketInteraction;
import javafx.scene.input.KeyCode;

public class PickPocketController extends SubKeyController {
    private PickPocketInteraction ppi;
    private int selectedIndex;
    private boolean confirmPickPocket;

    public PickPocketController() {
        selectedIndex = 0;
        confirmPickPocket = false;
    }

    @Override
    void keyInput(KeyCode code) {

        if(ppi == null) {
            return;
        }
        switch(code) {
            case ENTER:
                confirmPickPocket = true;
        }
    }

    public void setPickPocketInteraction(PickPocketInteraction ppi) {
        this.ppi = ppi;
        selectedIndex = 0;
    }

    public void handlePickPocket(GameState gs) {
        if(confirmPickPocket) {
            gs.pickPocket(selectedIndex);
        }
    }


}
