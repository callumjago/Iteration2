package Model;

import View.Bound;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DisplayItem {
    private Bound bound;
    private String text;
    private boolean selected;
    private boolean hovered;
    private Color color;
    private Color hoverColor;
    private Color selectedColor;
    private Font font;

    public DisplayItem(String s, Bound b, Color c) {
        text = s;
        bound = b;
        color = c;
        font = Font.font("Verdana", 12);
    }

    public String getText() {
        return text;
    }
    public void setText(String newText) {
        text = newText;
    }
    public Bound getBound() {
        return bound;
    }

    public Color getColor() {
        if(selected) { return selectedColor; }
        else if(hovered) { return hoverColor; }
        else { return color; }
    }

    public boolean collisionCheck(int mouseX, int mouseY) {
        return bound.collisionTest(mouseX, mouseY);

    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
