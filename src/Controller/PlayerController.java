package Controller;

import Model.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.text.Position;
import java.awt.event.KeyListener;

public class PlayerController extends SubKeyController {
    private Player player;
    private boolean inputRegistered;
    public PlayerController(Player player){
        super();
        this.player = player;
        inputRegistered = false;
    }





    public boolean isInputRegistered() {
        return inputRegistered;
    }

    public void resetInputRegistered() {
        inputRegistered = false;
    }

    @Override
    void keyInput(KeyCode code) {
        inputRegistered = true;
        switch(code) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
        }
    }
}
