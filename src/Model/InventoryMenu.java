package Model;

import java.util.ArrayList;

public class InventoryMenu extends SubMenu {
    private Inventory inventory;
    public InventoryMenu(int index, Player player) {
        super(index, "Inventory");
    }

    @Override
    ArrayList<String> generateMenuList() {
        return inventory.getInventoryAsList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {

        return menuState;
    }
}
