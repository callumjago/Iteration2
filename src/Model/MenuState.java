package Model;

import java.util.ArrayList;

//Info needed for menu state includes:
//menuSelectedInd, subMenuSelectedInd, MenuList, subMenuList, Widgets
public class MenuState {
    private ArrayList<String> MenuList, SubMenuList;
    private ArrayList<MenuItem> MenuItems;
    private int SelectedInd, SubMenuSelectedInd;

    public MenuState(ArrayList<String> MenuList, ArrayList<String> SubMenuList, int SelectedInd, int SubMenuSelectedInd) {
        this.MenuList = MenuList;
        this.SubMenuList = SubMenuList;
        this.SelectedInd = SelectedInd;
        this.SubMenuSelectedInd = SubMenuSelectedInd;

        MenuItems = new ArrayList<>();
    }

    public void setSubMenuSelectedInd(int ind) {
        SubMenuSelectedInd = ind;
    }

    public void AddMenuItem(MenuItem item) {
        MenuItems.add(item);
    }

    public String getMenuListName(int index) {
        if(index >= MenuList.size()) { return ""; }
        return MenuList.get(index);
    }
    public String getSubMenuListName(int index) {
        if(index >= SubMenuList.size()) { return ""; }
        return SubMenuList.get(index);
    }

    public ArrayList<String> getSubMenuList() {
        return SubMenuList;
    }
    public int getSubMenuSize() {
        return SubMenuList.size();
    }

    public ArrayList<MenuItem> getMenuItems() {
        return MenuItems;
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public int getSubMenuSelectedInd() {
        return SubMenuSelectedInd;
    }
}
