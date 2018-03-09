package Controller;

import Model.Menu;
import View.MenuView;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

public class ScrollController implements EventHandler<ScrollEvent> {
    private Menu menu;
    public ScrollController(Menu menu) {
        this.menu = menu;
    }

    public void handle(ScrollEvent event) {

        if(event.getDeltaY() > 0) {
            menu.scrollUp();
        }
        if(event.getDeltaY() < 0) {
            menu.scrollDown();
        }
    }
}
