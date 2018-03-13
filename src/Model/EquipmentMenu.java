package Model;

import java.util.ArrayList;

public class EquipmentMenu extends SubMenu {
    private Player player;
    private ArrayList<String> equipmentTypes;
    private ArrayList<InventoryMenuItem> equipmentItems;

    public EquipmentMenu(Player player) {
        super("Equipment");
        this.player = player;

        equipmentTypes = new ArrayList<>();
        equipmentTypes.add("Armor");
        equipmentTypes.add("Weapon");
        equipmentTypes.add("Ring");

        equipmentItems = new ArrayList<>();
        equipmentItems.add(new InventoryMenuItem(new ArrayList<>()));
        equipmentItems.add(new InventoryMenuItem(player.getWeapon().getStats()));
        equipmentItems.add(new InventoryMenuItem(new ArrayList<>()));

    }

    @Override
    ArrayList<String> generateMenuList() {
        return equipmentTypes;
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        menuState.addMenuItem(equipmentItems.get(getSubMenuSelectedIndex()));
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {

    }

    @Override
    int subMenuSize() {
        return equipmentTypes.size();
    }

    private void updateArmor() {

    }
}
