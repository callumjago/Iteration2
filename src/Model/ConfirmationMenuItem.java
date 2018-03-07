package Model;

import View.Bound;
import javafx.scene.paint.Color;

public class ConfirmationMenuItem extends MenuItem {

    public ConfirmationMenuItem() {
        super(new Bound(300, 700, 200, 600));



    }

    public void addButton(Bound bound, String text) {
        DisplayItem item = new DisplayItem(text, bound,Color.BROWN);
        addElement(item);
    }


}
