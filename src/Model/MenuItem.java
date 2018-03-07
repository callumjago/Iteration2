package Model;

import View.Bound;

import java.util.ArrayList;

public abstract class MenuItem {
    ArrayList<DisplayItem> elements;
    Bound itemBound;
    public MenuItem(Bound bound) {
        itemBound = bound;
        elements = new ArrayList<>();
    }

    public void addElement(DisplayItem element) {
        if(element == null) { return; }
        elements.add(element);
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
}
