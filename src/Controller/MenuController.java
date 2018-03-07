package Controller;

import Model.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class MenuController implements EventHandler<KeyEvent> {
    Menu menu;
    public MenuController(Menu menu) {
        this.menu = menu;
    }

    public void handle(KeyEvent event) {

        System.out.println("Handle");
        switch(event.getCode()) {
            case ESCAPE:
                menu.Escape();
                break;
        }
    }
}
