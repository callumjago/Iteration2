package Model;

import View.Bound;
import View.MenuUI;
import javafx.scene.paint.Color;

public class InventoryMenuItem extends MenuItem {

    public InventoryMenuItem() {
        super(new Bound(425, 775, 25, 775), Color.GRAY);

        //Item display background
        addElement(new DisplayItem("", new Bound(450, 750, 50, 350), Color.BEIGE));
    }
}
