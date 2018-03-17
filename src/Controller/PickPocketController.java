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
                System.out.println("TEST");
                if(selectedIndex > 0) {
                    selectedIndex--;
                }
                break;
        }
        //System.out.println(selectedIndex);
    }

    public void setPickPocketInteraction(PickPocketInteraction ppi) {
        this.ppi = ppi;
        selectedIndex = 0;
    }

    public void handlePickPocket(GameState gs) {
        if(confirmPickPocket) {
            System.out.println(selectedIndex);
            gs.pickPocket(selectedIndex);
        }
    }

    public int getSelectedIndex() {

        return selectedIndex;
    }




}
