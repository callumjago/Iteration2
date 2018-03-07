package View;

import Model.MenuState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MenuView {
    private Canvas canvas;
    private GraphicsContext gc;
    public MenuView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }

    public void render(MenuState state) {
        //System.out.println("rending");
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);
        //System.out.println(state.getMenuListName(0));

        renderMenu(state);
        renderSubMenu(state);
    }

    private void renderMenu(MenuState state) {
        gc.setFill(Color.BLUE);
        gc.fillText(state.getMenuListName(0), 100, 100);
    }

    private void renderSubMenu(MenuState state) {
        gc.setFill(Color.BLUE);
        for(int i = 0; i < state.getSubMenuSize(); i++) {
            gc.fillText(state.getSubMenuListName(i), 200, 100 + 100*i);
        }
    }
}
