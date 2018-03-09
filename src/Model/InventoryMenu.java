package Model;

import java.util.ArrayList;

public class InventoryMenu extends SubMenu {
    private Inventory inventory;
    public InventoryMenu(Inventory inventory) {
        super("Inventory");
        this.inventory = inventory;
    }

    @Override
    ArrayList<String> generateMenuList() {
        return inventory.getInventoryAsList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        menuState.setScrollOffset(getScrollOffset());
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {

    }

    @Override
    int subMenuSize() {
        return inventory.numOfItems();
    }

    public void scrollUp() {
        if(getScrollOffset() > 0) {
            setScrollOffset(getScrollOffset()-1);
        }
    }
    public void scrollDown() {
        if(getScrollOffset() < inventory.numOfItems()-8) {
            setScrollOffset(getScrollOffset()+1);
        }

    }


}
