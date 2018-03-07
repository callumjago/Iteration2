package View;

import Model.MenuState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MenuView {
    private Canvas canvas;
    private GraphicsContext gc;
    private MenuUI ui;
    public MenuView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        ui = new MenuUI();
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

        Bound bound;
        for(int i = 0; i < state.getMenuSize(); i++) {
            gc.setFill(Color.GREEN);
            if(i == state.getSelectedInd()) {
                gc.setFill(Color.BROWN);
            }
            bound = ui.getMenuBound(i);
            gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWitdh(), bound.getHeight());

            //drawText
            gc.setFill(Color.BLACK);
            gc.fillText(state.getMenuListName(i), bound.getBoundLeft()+40, bound.getBoundTop()+(bound.getHeight()/2));
        }

    }

    private void renderSubMenu(MenuState state) {

        Bound bound;
        for(int i = 0; i < state.getSubMenuSize(); i++) {
            gc.setFill(Color.RED);
            if(i == state.getSubMenuSelectedInd()) {
                gc.setFill(Color.GRAY);
            }
            bound = ui.getSubMenuBound(i);
            gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWitdh(), bound.getHeight());

            gc.setFill(Color.BLACK);
            gc.fillText(state.getSubMenuListName(i), bound.getBoundLeft()+40, bound.getBoundTop()+(bound.getHeight()/2));
        }


    }
}
