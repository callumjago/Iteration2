package Controller;

import Model.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;

public class PlayerController implements EventHandler<KeyEvent> {
    private Player player;
    public PlayerController(Player player){
        this.player = player;
    }

    public void handle(KeyEvent event) {



    }
}
