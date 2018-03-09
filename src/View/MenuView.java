package View;

import Model.MenuState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuView {
    private Canvas canvas;
    private MenuItemView itemView;

    private GraphicsContext gc;
    private MenuUI ui;

    private final Font menuFont = Font.font ("Verdana", FontWeight.BOLD, 18);

    private Color backgroundColor;
    public MenuView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        ui = new MenuUI(canvas);
        itemView = new MenuItemView(canvas);


        backgroundColor = new Color(.85f, .644f, .125f, .5);

    }

    public void render(MenuState state) {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //System.out.println("rending");

        //System.out.println(state.getMenuListName(0));

        renderMenu(state);
        renderSubMenu(state);

        for(int i = 0; i < state.getMenuItemSize(); i++) {
            itemView.renderMenuItem(state.getMenuItem(i));
        }

        renderMenuDivisionLine();
        if(state.getSubMenuSize() > 0) {
            renderSubMenuDivisionLine();
        }
    }

    private void renderMenu(MenuState state) {

        Bound bound;
        for(int i = 0; i < state.getMenuSize(); i++) {
            gc.setFill(Color.GRAY);
            bound = ui.getMenuBound(i);
            if(bound.collisionTest(state.getMouseX(), state.getMouseY())) {
                gc.setFill(Color.BLUE);
            }
            if(i == state.getSelectedInd()) {
                gc.setFill(Color.RED);
            }

            gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWidth(), bound.getHeight());

            //drawText
            gc.setFont(menuFont);
            gc.setFill(Color.BLACK);
            gc.fillText(state.getMenuListName(i), bound.getBoundLeft()+40, bound.getBoundTop()+(bound.getHeight()/2)+5);

            entryDivisionLine(bound.getBoundLeft(), bound.getBoundBottom(), bound.getWidth(), 4);
        }

        //Render top division line
        bound = ui.getMenuBound(0);
        entryDivisionLine(bound.getBoundLeft(), -2, bound.getWidth(), 6);

    }

    private void renderSubMenu(MenuState state) {

        Bound bound;
        for(int i = 0; i < Math.min(state.getSubMenuSize(), ui.getMaxEntries()); i++) {

            gc.setFill(Color.GRAY);
            bound = ui.getSubMenuBound(i);
            if(bound.collisionTest(state.getMouseX(), state.getMouseY())) {
                gc.setFill(Color.BLUE);
            }
            if(i == state.getSubMenuSelectedInd()) {
                gc.setFill(Color.RED);
            }

            gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWidth(), bound.getHeight());

            gc.setFont(menuFont);
            gc.setFill(Color.BLACK);
            gc.fillText(state.getSubMenuListName(i), bound.getBoundLeft()+40, bound.getBoundTop()+(bound.getHeight()/2)+5);

            entryDivisionLine(bound.getBoundLeft(), bound.getBoundBottom(), bound.getWidth(), 4);
        }

        if(state.getSubMenuSize() > 0) {//Render top division line
            bound = ui.getSubMenuBound(0);
            entryDivisionLine(bound.getBoundLeft(), -2, bound.getWidth(), 6);
        }
    }

    private void renderMenuDivisionLine() {
        gc.setFill(Color.BLACK);
        gc.fillRect(ui.getMenuEntryWidth()-2, 0, 4, canvas.getHeight());
    }
    private void renderSubMenuDivisionLine() {
        gc.setFill(Color.BLACK);
        gc.fillRect((ui.getMenuEntryWidth()*2)-2, 0, 4, canvas.getHeight());
    }

    private void entryDivisionLine(int left, int top, int width, int height) {
        gc.setFill(Color.BLACK);
        gc.fillRect(left, top, width, height);
    }


}
