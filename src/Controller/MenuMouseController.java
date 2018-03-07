package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class MenuMouseController implements EventHandler<MouseEvent> {
    private int mouseX, mouseY;
    public MenuMouseController() {
        mouseX = 0;
        mouseY = 0;

    }

    public void handle(MouseEvent event) {
        mouseX = (int)event.getX();
        mouseY = (int)event.getY();


    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
