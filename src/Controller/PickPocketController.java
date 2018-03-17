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
                break;
            case DOWN:
                if(selectedIndex < ppi.getNpc().getInventory().numOfItems()-1) {
                    selectedIndex++;
                }
                break;
            case UP:
                if(selectedIndex > 0) {
                    selectedIndex--;
                }
                break;
        }

    }

    @Override
    boolean isActive() {
        if(ppi != null) {
            return true;
        }
        return false;
    }

    public void setPickPocketInteraction(PickPocketInteraction ppi) {
        this.ppi = ppi;
    }

    public void handlePickPocket(GameState gs) {
        if(confirmPickPocket) {
            gs.pickPocket(selectedIndex);
            selectedIndex = 0;
            confirmPickPocket = false;
            ppi = null;
        }
    }

    public int getSelectedIndex() {

        return selectedIndex;
    }






}
