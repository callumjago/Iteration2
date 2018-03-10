package View;

import javafx.scene.canvas.Canvas;

//Handles the layout of menu elements
//Used in model for controller collision
//Used in view for rendering
public class MenuUI {
    private Canvas canvas;
    private final int maxEntries;
    private int menuEntryWidth;
    private int menuEntryHeight;
    private int screenWidth, screenHeight;

    private final int defaultScreenWidth = 800;
    private final int defaultScreenHeight = 800;

    public MenuUI(Canvas canvas) {
        this.canvas = canvas;
        screenWidth = (int) canvas.getWidth();
        screenHeight = (int) canvas.getHeight();
        menuEntryWidth = screenWidth/4;
        menuEntryHeight = screenHeight/8;
        maxEntries = screenWidth/menuEntryHeight;
    }

    //Returns the bounds of an entry in the base menu
    public Bound getMenuBound(int index) {
        return new Bound(2, menuEntryWidth-2, index*menuEntryHeight+2, (index+1)*menuEntryHeight-2);
    }

    //Returns the bounds of an entry in a submenu
    public Bound getSubMenuBound(int index) {
        return new Bound(menuEntryWidth+2, menuEntryWidth*2 -2, index*menuEntryHeight+2, (index+1)*menuEntryHeight-2);
    }

    public Bound resizeBound(Bound bound) {
        return new Bound(bound.getBoundLeft()*screenWidth/defaultScreenWidth, bound.getBoundRight()*screenWidth/defaultScreenWidth, bound.getBoundTop()*screenHeight/defaultScreenHeight, bound.getBoundBottom()*screenHeight/defaultScreenHeight);
    }


    public int resizeXCoord(int x) {
        return x*defaultScreenWidth/screenWidth;
    }
    public int resizeYCoord(int y) {
        return y*defaultScreenHeight/screenHeight;
    }
    //Checks for collision in the base menu, returns index of entry hit, -1 if no hit
    public int menuEntryCollisionTest(int mouseX, int mouseY) {
        Bound bound;
        for(int i = 0; i < maxEntries; i++) {
            bound = getMenuBound(i);
            if(bound.collisionTest(mouseX, mouseY)) {
                return i;
            }
        }
        return -1;
    }

    public int subMenuEntryCollisionTest(int mouseX, int mouseY) {
        Bound bound;
        for(int i = 0; i < maxEntries; i++) {
            bound = getSubMenuBound(i);
            if(bound.collisionTest(mouseX, mouseY)) {
                return i;
            }
        }
        return -1;
    }

    public int getMenuEntryWidth() {
        return menuEntryWidth;
    }

    public int getMaxEntries() {
        return maxEntries;
    }
}
