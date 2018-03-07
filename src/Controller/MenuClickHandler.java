package Controller;

import Model.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MenuClickHandler implements EventHandler<MouseEvent> {
    private Menu menu;
    public MenuClickHandler(Menu menu) {
        this.menu = menu;
    }

    public void handle(MouseEvent event) {
        System.out.println("Clicked");
        menu.Enter();
    }
}
