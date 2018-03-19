package Model;

import javafx.scene.canvas.Canvas;
import java.util.ArrayList;

public abstract class SubMenu {
    private int selectedInd;
    private int menuIndex; //Index of this sub menu
    private String name;
    private int scrollOffset;
    public SubMenu(String name) {
        selectedInd = 0;
        this.name = name;
    }

    public void setMenuIndex(int index) {
        menuIndex = index;
    }
    public String getName() {
        return name;
    }
    public MenuState generateMenuState(ArrayList<String> menuList, int mouseX, int mouseY) {
        MenuState menuState = new MenuState(menuList, generateMenuList(), menuIndex, selectedInd, mouseX, mouseY);
        menuState = generateSubMenuState(menuState);
        return menuState;
    }

    public int getSubMenuSelectedIndex() {
        return selectedInd;
    }
    public void setSubMenuSelectedIndex(int index) {
        if(index+scrollOffset >= subMenuSize()) {
            return;
        }
        selectedInd = index+scrollOffset;

    }

    abstract ArrayList<String> generateMenuList();
    abstract MenuState generateSubMenuState(MenuState menuState);
    abstract void Enter(int mouseX, int mouseY);
    abstract int subMenuSize();

    public void scrollUp() {

    }
    public void scrollDown() {

    }
    public int getScrollOffset() {
        return scrollOffset;
    }
    public void setScrollOffset(int offset) {
        scrollOffset = offset;
    }
}
