package Model;

import java.util.ArrayList;

public abstract class SubMenu {
    private int selectedInd;
    private int menuIndex; //Index of this menu item
    private String name;
    public SubMenu(int index, String name) {
        menuIndex = index;
        selectedInd = 0;
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public MenuState generateMenuState(ArrayList<String> menuList) {
        MenuState menuState = new MenuState(menuList, generateMenuList(), menuIndex, selectedInd);
        menuState = generateSubMenuState(menuState);
        return menuState;
    }

    abstract ArrayList<String> generateMenuList();
    abstract MenuState generateSubMenuState(MenuState menuState);
}
