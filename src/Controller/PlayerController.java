package Controller;

import Model.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javax.swing.text.Position;
import java.awt.event.KeyListener;

public class PlayerController implements EventHandler<KeyEvent> {
    private Player player;
    private boolean inputRegistered;
    public PlayerController(Player player){
        this.player = player;
        inputRegistered = false;
    }

    public void handle(KeyEvent event) {
        System.out.println(event.getCode());
        inputRegistered = true;
        switch(event.getCode()) {
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

    public boolean isInputRegistered() {
        return inputRegistered;
    }

    public void resetInputRegistered() {
        inputRegistered = false;
    }
}
