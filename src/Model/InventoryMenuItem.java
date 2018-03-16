package Model;

import View.Bound;
import View.MenuUI;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InventoryMenuItem extends MenuItem {

    public InventoryMenuItem(ArrayList<String> itemStats) {
        super(new Bound(425, 775, 25, 775), Color.GRAY);
        //System.out.println(itemStats.size());
        //Item display background
        addElement(new DisplayItem("", new Bound(550, 650, 50, 150), Color.BEIGE));

        for(int i = 0; i < itemStats.size(); i++) {
            addElement(new DisplayItem(itemStats.get(i), new Bound(450, 750, 175+(i*50), 225+(i*50)), Color.WHITE));
        }
    }

    public void setStats(ArrayList<String> stats) {
        for(int i = 0; i < stats.size(); i++) {
            updateElement(i+1, stats.get(i));
        }
    }
}
