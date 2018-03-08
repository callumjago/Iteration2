package Model;

import java.util.ArrayList;

public abstract class SubMenu {
    private int selectedInd;
    private int menuIndex; //Index of this sub menu
    private String name;
    public SubMenu(int index, String name) {
        menuIndex = index;
        selectedInd = 0;
        this.name = name;
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
        if(index >= subMenuSize()) {
            return;
        }
        selectedInd = index;
    }

    abstract ArrayList<String> generateMenuList();
    abstract MenuState generateSubMenuState(MenuState menuState);
    abstract void Enter(int mouseX, int mouseY);
    abstract int subMenuSize();
}
