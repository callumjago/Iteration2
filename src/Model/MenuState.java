package Model;

import java.util.ArrayList;

//Info needed for menu state includes:
//menuSelectedInd, subMenuSelectedInd, MenuList, subMenuList, Widgets
public class MenuState {
    private ArrayList<String> MenuList, SubMenuList;
    private ArrayList<MenuItem> MenuItems;
    private int SelectedInd, SubMenuSelectedInd;
    private int mouseX, mouseY;
    private int scrollOffset;

    public MenuState(ArrayList<String> MenuList, ArrayList<String> SubMenuList, int SelectedInd, int SubMenuSelectedInd, int mouseX, int mouseY) {
        this.MenuList = MenuList;
        this.SubMenuList = SubMenuList;
        this.SelectedInd = SelectedInd;
        this.SubMenuSelectedInd = SubMenuSelectedInd;
        this.mouseX = mouseX; this.mouseY = mouseY;
        scrollOffset = 0;
        MenuItems = new ArrayList<>();
    }

    public void setSubMenuSelectedInd(int ind) {
        SubMenuSelectedInd = ind;
    }

    public void setScrollOffset(int offset) {
        scrollOffset = offset;
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void addMenuItem(MenuItem item) {
        MenuItems.add(item);
    }

    public String getMenuListName(int index) {
        if(index >= MenuList.size()) { return ""; }
        return MenuList.get(index);
    }
    public String getSubMenuListName(int index) {
        if(index+scrollOffset >= SubMenuList.size()) { return ""; }
        return SubMenuList.get(index + scrollOffset);
    }

    public ArrayList<String> getSubMenuList() {
        return SubMenuList;
    }
    public int getSubMenuSize() {
        return SubMenuList.size();
    }
    public int getMenuSize() {
        return MenuList.size();
    }

    public MenuItem getMenuItem(int index) {
        if(index >= MenuItems.size()) {
            return null;
        }
        return MenuItems.get(index);
    }

    public int getMenuItemSize() {
        return MenuItems.size();
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public int getSubMenuSelectedInd() {
        return SubMenuSelectedInd-scrollOffset;
    }
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
