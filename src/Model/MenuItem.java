package Model;

import View.Bound;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class MenuItem {
    private ArrayList<DisplayItem> elements;
    private Bound itemBound;
    private Color backGroundColor;
    public MenuItem(Bound bound, Color color) {
        itemBound = bound;
        elements = new ArrayList<>();
        elements.add(new DisplayItem("", itemBound, Color.GRAY));
        backGroundColor = color;
    }

    public void addElement(DisplayItem element) {
        if(element == null) { return; }
        elements.add(element);
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public DisplayItem getElement(int index) {
        if(index >= elements.size()) {
            return null;
        }
        return elements.get(index);
    }

    public int getDisplayElementSize() {
        return elements.size();
    }

    public Bound getBound() {
        return itemBound;
    }

    public boolean collisionCheckByName(String text, int mouseX, int mouseY) {
        for(int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getText().equals(text)) {
                return elements.get(i).collisionCheck(mouseX, mouseY);
            }
        }
        return false;
    }

    public void addButton(Bound bound, String text) {
        DisplayItem item = new DisplayItem(text, bound,Color.BROWN);
        addElement(item);
    }
}
