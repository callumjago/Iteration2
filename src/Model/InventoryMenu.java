package Model;

import View.Bound;

import java.util.ArrayList;

public class InventoryMenu extends SubMenu {
    private Inventory inventory;
    private InventoryMenuItem currentItem;
    public InventoryMenu(Inventory inventory) {
        super("Inventory");
        this.inventory = inventory;

        currentItem = generateInventoryMenuItem();
    }

    @Override
    ArrayList<String> generateMenuList() {
        return inventory.getInventoryAsList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        //Get menu item for selected item
        menuState.addMenuItem(currentItem);
        menuState.setScrollOffset(getScrollOffset());
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {
        if(currentItem.collisionCheckByName("Equip Item", mouseX, mouseY)) {
            System.out.println("Equiping");
        } else if(currentItem.collisionCheckByName("Use Item", mouseX, mouseY)) {
            System.out.println("Using");
        } else if(currentItem.collisionCheckByName("Drop Item", mouseX, mouseY)) {
            System.out.println("Dropping");
        }
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

    private InventoryMenuItem generateInventoryMenuItem() {
        InventoryMenuItem temp = new InventoryMenuItem(new ArrayList<>());
        if(inventory.getItem(getSubMenuSelectedIndex()) instanceof Equipment) {
            temp.addButton(new Bound(450, 750, 575, 650), "Equip Item");
        } else {
            temp.addButton(new Bound(450, 750, 575, 650), "Use Item");
        }
        temp.addButton(new Bound(450, 750, 675, 750), "Drop Item");
        return temp;
    }



}
