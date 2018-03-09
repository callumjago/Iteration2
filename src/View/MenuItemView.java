package View;

import Model.DisplayItem;
import Model.MenuItem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MenuItemView {
    private GraphicsContext gc;
    private MenuUI ui;
    public MenuItemView(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        ui = new MenuUI(canvas);
    }

    public void renderMenuItem(MenuItem item) {
        gc.setFill(item.getBackGroundColor());
        Bound bound = ui.resizeBound(item.getBound());
        gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWidth(), bound.getHeight());
        for(int i = 0; i < item.getDisplayElementSize(); i++) {
            renderDisplayItem(item.getElement(i));
        }

    }

    private void renderDisplayItem(DisplayItem item) {
        gc.setFill(item.getColor());
        Bound bound = ui.resizeBound(item.getBound());
        gc.fillRect(bound.getBoundLeft(), bound.getBoundTop(), bound.getWidth(), bound.getHeight());

        gc.setFont(item.getFont());
        gc.setFill(Color.BLACK);
        gc.fillText(item.getText(), bound.getBoundLeft()+20, bound.getBoundTop() + (bound.getHeight()/2));
    }


}
