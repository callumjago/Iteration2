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
        equipmentItems.add(new InventoryMenuItem(player.getEquipArmor().getStats()));
        equipmentItems.add(new InventoryMenuItem(player.getEquipWeapon().getStats()));
        equipmentItems.add(new InventoryMenuItem(player.getEquipRing().getStats()));

    }

    @Override
    ArrayList<String> generateMenuList() {
        return equipmentTypes;
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        equipmentItems.get(0).setStats(player.getEquipArmor().getStats());
        equipmentItems.get(1).setStats(player.getEquipWeapon().getStats());
        equipmentItems.get(2).setStats(player.getEquipRing().getStats());
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
