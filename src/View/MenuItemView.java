package View;

import Model.DisplayItem;
import Model.MenuItem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MenuItemView {
    private GraphicsContext gc;
    public MenuItemView(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
    }

    public void renderMenuItem(MenuItem item) {
        for(int i = 0; i < item.getDisplayElementSize(); i++) {
            renderDisplayItem(item.getElement(i));
        }

    }

    private void renderDisplayItem(DisplayItem item) {
        gc.setFill(item.getColor());
        gc.fillRect(item.getBound().getBoundLeft(), item.getBound().getBoundTop(), item.getBound().getWitdh(), item.getBound().getHeight());

        gc.setFill(Color.BLACK);
        gc.fillText(item.getText(), item.getBound().getBoundLeft()+20, item.getBound().getBoundTop() + (item.getBound().getHeight()/2));
    }


}
