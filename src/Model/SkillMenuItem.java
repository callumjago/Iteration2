package Model;

import View.Bound;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SkillMenuItem extends MenuItem {

    public SkillMenuItem() {
        super(new Bound(425, 775, 25, 775), Color.GRAY);

    }

    public void setStats(ArrayList<String> stats) {
        clearElements();
        addElement(new DisplayItem("", new Bound(425, 775, 25, 775), Color.GRAY));

        for(int i = 0; i < stats.size(); i++) {
            addElement(new DisplayItem(stats.get(i), new Bound(450, 750, 175+(i*50), 225+(i*50)), Color.WHITE));
        }
    }
}
