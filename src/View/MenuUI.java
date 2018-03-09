package View;

//Handles the layout of menu elements
//Used in model for controller collision
//Used in view for rendering
public class MenuUI {
    private final int maxEntries = 8;
    private final int menuEntryWidth = 200;
    private final int menuEntryHeight = 100;
    public MenuUI() {

    }

    //Returns the bounds of an entry in the base menu
    public Bound getMenuBound(int index) {
        return new Bound(2, menuEntryWidth-2, index*menuEntryHeight+2, (index+1)*menuEntryHeight-2);
    }

    //Returns the bounds of an entry in a submenu
    public Bound getSubMenuBound(int index) {
        return new Bound(menuEntryWidth+2, menuEntryWidth*2 -2, index*menuEntryHeight+2, (index+1)*menuEntryHeight-2);
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
