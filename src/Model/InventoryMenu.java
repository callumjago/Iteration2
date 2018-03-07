package Model;

import java.util.ArrayList;

public class InventoryMenu extends SubMenu {
    private Inventory inventory;
    public InventoryMenu(int index, Inventory inventory) {
        super(index, "Inventory");
        this.inventory = inventory;
    }

    @Override
    ArrayList<String> generateMenuList() {
        return inventory.getInventoryAsList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {

        return menuState;
    }

    @Override
    int subMenuSize() {
        return inventory.numOfItems();
    }


}
